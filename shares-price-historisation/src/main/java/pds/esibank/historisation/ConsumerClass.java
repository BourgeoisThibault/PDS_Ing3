package pds.esibank.historisation;



import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import pds.esibank.models.shares.SharePrice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by maria on 03/02/2018.
 */

public class ConsumerClass extends DefaultConsumer{

    private static RabbitTemplate rabbitTemplate;
    private ConnectionDataAccess dataAccess1 = new ConnectionDataAccess();
    private List<SharePrice> sharePricetab = new ArrayList<SharePrice>();

  private Channel channel;
    private Logger logger = Logger.getLogger(ConsumerClass.class);

    public ConsumerClass(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,
                               AMQP.BasicProperties properties, byte[] body) throws IOException {

        try {

            String message = new String(body, "UTF-8");

            //SharePriceHistory sharePriceHistory = utilsJson.objectFromJson(message, SharePriceHistory.class);
            System.out.println(" [x] Received '" + message + "'");

          /*  for (int i=0 ; i<sharePricetab.size() ; i++){

            }*/

                channel.basicAck(envelope.getDeliveryTag(),false);

        } catch (UnsupportedEncodingException e) {
            logger.error("Error for parse body byte to String with message: " + e.getMessage());
        }
    }
}
