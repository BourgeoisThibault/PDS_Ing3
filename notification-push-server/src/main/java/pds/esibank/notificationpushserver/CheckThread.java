package pds.esibank.notificationpushserver;

import org.apache.log4j.Logger;
import pds.esibank.models.notification.MobileClient;
import pds.esibank.notificationpushserver.utils.ListConnection;
import static pds.esibank.notificationpushserver.utils.StreamUtils.readString;
import static pds.esibank.notificationpushserver.utils.StreamUtils.sendString;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 * @author BOURGEOIS Thibault
 * Date     18/11/2017
 * Time     22:57
 */
public class CheckThread extends Thread {

    private Logger logger = Logger.getLogger(CheckThread.class);

    private static Boolean isStopped = false;

    public void setIsStopped(Boolean value) {
        isStopped=value;
    }

    @Override
    public void run() {
        while(!isStopped) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("Can't sleep");
            }
            for (Map.Entry<Socket,String> list : ListConnection.getSocketMobileClientMap().entrySet()) {

                try {
                    PrintWriter writer = new PrintWriter(list.getKey().getOutputStream(), true);
                    sendString(writer, "PING");
                    try {
                        BufferedInputStream reader = new BufferedInputStream(list.getKey().getInputStream());
                        String response = readString(reader);
                    } catch (IOException e2) {
                        ListConnection.getSocketMobileClientMap().remove(list.getKey());
                        logger.info("TOKEN disconnect: " + list.getValue());
                    }
                } catch (IOException e1) {
                    ListConnection.getSocketMobileClientMap().remove(list.getKey());
                    logger.info("TOKEN disconnect: " + list.getValue());
                }
            }
        }
    }
}
