package pds.esibank.notificationserver.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pds.esibank.models.notification.MobileClient;
import pds.esibank.models.notification.NotificationModel;
import static pds.esibank.notificationserver.utils.JsonUtils.*;
import pds.esibank.notificationserver.utils.ListConnection;
import pds.esibank.notificationserver.utils.StreamUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     23:29
 */
@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(SocketCommunicationService.class);

    private static Boolean isStopped = false;

    public NotificationService() {
    }

    public static void setIsStopped(Boolean value) {
        isStopped=value;
    }

    @Async
    public void checkAllConnection() {
        while(!isStopped) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error("Can't sleep");
            }
            for (Map.Entry<Socket,MobileClient> e : ListConnection.getSocketMobileClientMap().entrySet()) {

                try {
                    PrintWriter writer = new PrintWriter(e.getKey().getOutputStream(), true);
                    writer.write("PING");
                    writer.flush();
                    try {
                        BufferedInputStream reader = new BufferedInputStream(e.getKey().getInputStream());
                        String response = new String(new byte[4096], 0, reader.read(new byte[4096]));
                    } catch (IOException e2) {
                        ListConnection.getSocketMobileClientMap().remove(e.getKey());
                        logger.info("IMEI disconnect: " + e.getValue().getImei());
                    }
                } catch (IOException e1) {
                    ListConnection.getSocketMobileClientMap().remove(e.getKey());
                    logger.info("IMEI disconnect: " + e.getValue().getImei());
                }
            }
        }
    }

    public Boolean sendNotification(NotificationModel notificationModel, String imei) {
        Boolean isFind = false;
        for (Map.Entry<Socket,MobileClient> e : ListConnection.getSocketMobileClientMap().entrySet()) {
            if(e.getValue().getImei().equals(imei)) {
                try {
                    PrintWriter writer = new PrintWriter(e.getKey().getOutputStream(), true);
                    writer.write(objectToJson(notificationModel));
                    writer.flush();
                    isFind=true;
                } catch (IOException e1) {
                    logger.info("Error send notification to " + e.getValue().getImei());
                }
            }
        }
        return isFind;
    }
}
