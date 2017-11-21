package pds.esibank.notificationpushserver.QueueRabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.apache.log4j.Logger;
import pds.esibank.models.notification.NotificationModel;
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

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,
                               AMQP.BasicProperties properties, byte[] body) throws IOException {

        try {
            String message = new String(body, "UTF-8");
            NotificationModel notificationModel = JsonUtils.objectFromJson(message,NotificationModel.class);

            if (notificationModel.getTitle().equals("NotifTest")) {

                channel.basicReject(envelope.getDeliveryTag(),true);

                logger.info("Traitement done for " + notificationModel.getTitle());

            } else {
                logger.info("Traitement done for " + notificationModel.getTitle());
                channel.basicAck(envelope.getDeliveryTag(),false);

            }

        } catch (UnsupportedEncodingException e) {
            logger.error("Error for pars body byte to String with message: " + e.getMessage());
        } catch (IOException e) {
            logger.error("Error for pars message json to NotificationModel with message: " + e.getMessage());
        }

    }

}
