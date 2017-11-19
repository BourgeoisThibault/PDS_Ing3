package pds.esibank.notificationpushserver.servers;

import org.apache.log4j.Logger;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.notificationpushserver.utils.JsonUtils;
import pds.esibank.notificationpushserver.communications.ListConnection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static pds.esibank.notificationpushserver.utils.StreamUtils.readString;
import static pds.esibank.notificationpushserver.utils.StreamUtils.sendString;

/**
 * @author BOURGEOIS Thibault
 * Date     19/11/2017
 * Time     14:17
 */
public class ListenNotificationThread extends Thread {

    private Logger logger = Logger.getLogger(ListenNotificationThread.class);

    private int _PUSH_PORT;

    public void set_PORT(int _PUSH_PORT) {
        this._PUSH_PORT = _PUSH_PORT;
    }

    @Override
    public void run() {

        // Initialize Socket server to null
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(_PUSH_PORT);
            logger.info("Listen of notif listening on port " + _PUSH_PORT);
        } catch (IOException e) {
            logger.error("Listen of notif don't start on port " + _PUSH_PORT);
            logger.error("Error information: " + e.getMessage());
            logger.error("Please restart server");
        }

        while (!serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedInputStream reader = new BufferedInputStream(socket.getInputStream());

                String getToken = readString(reader);

                if(ListConnection.searchToken(getToken)) {
                    sendString(writer, "true");
                    logger.info("Good token for notification");

                    NotificationModel notificationModel = JsonUtils.objectFromJson(readString(reader), NotificationModel.class);

                    ListConnection.sendNotification(getToken, notificationModel);

                } else {
                    sendString(writer, "false");
                    logger.info("Wrong token for notification");
                }


            } catch (IOException e) {
                logger.error("Socket accept throw exception with message: " + e.getMessage());
            }
        }

    }
}
