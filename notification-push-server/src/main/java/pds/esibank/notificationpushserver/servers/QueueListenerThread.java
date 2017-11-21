package pds.esibank.notificationpushserver.servers;

import com.rabbitmq.client.*;
import org.apache.log4j.Logger;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.notificationpushserver.QueueRabbit.NotifConsumer;
import pds.esibank.notificationpushserver.utils.JsonUtils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author BOURGEOIS Thibault
 * Date     19/11/2017
 * Time     14:17
 */
public class QueueListenerThread extends Thread {

    private Logger logger = Logger.getLogger(QueueListenerThread.class);

    private static final String QUEUE_NAME = "test.queue.testttlday";

    @Override
    public void run() {
        try {
            testingConsum();
        } catch (IOException e) {
           logger.error("IOException with message: " + e.getMessage());
        } catch (TimeoutException e) {
            logger.error("TimeOutException with message: " + e.getMessage());
        }
    }

    private void testingConsum() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("esibank");
        factory.setPassword("esibankpds");
        factory.setVirtualHost("esibank-mom");
        //factory.setHost("esibank.inside.esiag.info");
        factory.setHost("192.154.88.166");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl", 86400000);
        String queu = channel.queueDeclare(QUEUE_NAME, true, false, false, args).getQueue();

        NotifConsumer notifConsumer = new NotifConsumer(channel);

        channel.basicConsume(queu, false, notifConsumer);

    }

}
