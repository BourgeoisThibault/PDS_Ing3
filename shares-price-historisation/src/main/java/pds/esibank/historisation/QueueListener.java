package pds.esibank.historisation;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by maria on 08/02/2018.
 */
public class QueueListener extends Thread {

    private Logger logger = Logger.getLogger(QueueListener.class);

    private static  final String QUEUE_NAME ="MARIAM_QUEUE";

    private Channel channel;

    @Override
    public void run() {
        try {
            Consum();
        } catch (IOException e) {
            logger.error("IOException with message: " + e.getMessage());
        } catch (TimeoutException e) {
            logger.error("TimeOutException with message: " + e.getMessage());
        }
    }

    public Boolean listennerIsAlive() {
        return channel.isOpen();
    }

    public void stopListenner() {
        try {
            channel.close();
        } catch (IOException e) {
            logger.error("IOException with message: " + e.getMessage());
        } catch (TimeoutException e) {
            logger.error("TimeOutException with message: " + e.getMessage());
        }
    }

    private void Consum() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("mariam");
        factory.setPassword("esibank");
        factory.setVirtualHost("mariam-mom");
        factory.setHost("esibank.inside.esiag.info");
        factory.setPort(5672);



        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        Map<String, Object> argu = new HashMap<String, Object>();
        argu.put("x-message-ttl", 86400000);
        channel.queueDeclare(QUEUE_NAME, true, false, false, argu);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        ConsumerClass consumer = new ConsumerClass(channel);

        channel.basicConsume(QUEUE_NAME, true, consumer);

    }

}