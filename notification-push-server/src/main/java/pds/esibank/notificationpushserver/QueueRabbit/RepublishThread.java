package pds.esibank.notificationpushserver.QueueRabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import org.apache.log4j.Logger;
import pds.esibank.notificationpushserver.servers.QueueListenerThread;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author BOURGEOIS Thibault
 * Date     21/11/2017
 * Time     16:52
 */
public class RepublishThread extends Thread {

    private Logger logger = Logger.getLogger(RepublishThread.class);

    private Channel channel;
    private Envelope envelope;

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setEnvelope(Envelope envelope) {
        this.envelope = envelope;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(60000);
            channel.basicReject(envelope.getDeliveryTag(),true);
        } catch (IOException e) {
            logger.error("Error for republish message to the queue.");
        } catch (InterruptedException e) {
            logger.error("Error for sleep 60s");
        }
    }

}
