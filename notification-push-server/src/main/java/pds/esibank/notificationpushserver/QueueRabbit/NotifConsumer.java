package pds.esibank.notificationpushserver.QueueRabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.apache.log4j.Logger;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.models.notification.PushNotificationModel;
import pds.esibank.notificationpushserver.communications.ListConnection;
import pds.esibank.notificationpushserver.servers.QueueListenerThread;
import pds.esibank.notificationpushserver.utils.JsonUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author BOURGEOIS Thibault
 * Date     21/11/2017
 * Time     15:45
 */
public class NotifConsumer extends DefaultConsumer {

    private Logger logger = Logger.getLogger(NotifConsumer.class);

    Channel channel;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public NotifConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    /**
     *
     * TODO Uncomment process to verify and send notif to token
     * @param consumerTag
     * @param envelope
     * @param properties
     * @param body
     * @throws IOException
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,
                               AMQP.BasicProperties properties, byte[] body) throws IOException {

        try {
            String message = new String(body, "UTF-8");
            PushNotificationModel pushNotificationModel = JsonUtils.objectFromJson(message,PushNotificationModel.class);

            NotificationModel notificationModel = new NotificationModel();
            notificationModel.setTitle(pushNotificationModel.getTitle());
            notificationModel.setMessage(pushNotificationModel.getMessage());

            if (ListConnection.searchToken(pushNotificationModel.getToken())) {
                ListConnection.sendNotification(pushNotificationModel.getToken(), notificationModel);

                logger.info("Sending push to " + pushNotificationModel.getToken());
                channel.basicAck(envelope.getDeliveryTag(),false);
            } else {
                if(pushNotificationModel.getToken().equals("allToken")) {
                    ListConnection.sendNotificationToAllDevice(notificationModel);

                    logger.info("Sending push to all device connected ");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                } else {
                    RepublishThread republishThread = new RepublishThread();
                    republishThread.setChannel(channel);
                    republishThread.setEnvelope(envelope);
                    republishThread.start();
                }
            }

        } catch (UnsupportedEncodingException e) {
            logger.error("Error for parse body byte to String with message: " + e.getMessage());
        } catch (IOException e) {
            logger.error("Error for parse message json to NotificationModel with message: " + e.getMessage());
        }

    }

}
