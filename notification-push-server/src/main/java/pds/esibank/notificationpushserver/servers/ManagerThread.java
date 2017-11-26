package pds.esibank.notificationpushserver.servers;

import org.apache.log4j.Logger;
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
 * Date     18/11/2017
 * Time     23:49
 */
public class ManagerThread extends Thread {

    private final Logger logger = Logger.getLogger(ManagerThread.class);

    private int _ADMIN_PORT;
    private int _PORT;
    private int _PUSH_PORT;

    private Boolean serverPause;

    private ServerThread serverThread;
    private QueueListenerThread listenNotificationThread;

    public void set_ADMIN_PORT(int _ADMIN_PORT) {
        this._ADMIN_PORT = _ADMIN_PORT;
    }

    public void set_PORT(int _PORT) {
        this._PORT = _PORT;
    }

    public void set_PUSH_PORT(int _PUSH_PORT) {
        this._PUSH_PORT = _PUSH_PORT;
    }

    @Override
    public void run() {

        logger.info("");
        logger.info("");
        logger.info("##########################");
        logger.info("#                        #");
        logger.info("#   NEW SERVER STARTED   #");
        logger.info("#                        #");
        logger.info("#   Mobile : 9090        #");
        logger.info("#   Admin  : 9999        #");
        logger.info("#                        #");
        logger.info("##########################");
        logger.info("");
        logger.info("");

        serverPause = false;

        listenNotificationThread = new QueueListenerThread();
        listenNotificationThread.start();

        serverThread = new ServerThread();
        serverThread.set_PORT(_PORT);
        serverThread.start();

        adminServerProcess();

    }

    private void adminServerProcess() {
        ServerSocket adminServer = null;
        try {
            adminServer = new ServerSocket(_ADMIN_PORT);
            logger.info("Admin socket listening on port " + _ADMIN_PORT);
        } catch (IOException e) {
            logger.error("Admin socket don't start on port " + _ADMIN_PORT);
            logger.error("Error information: " + e.getMessage());
            logger.error("Please restart server");
        }

        while (!adminServer.isClosed()) {
            try {
                Socket socket = adminServer.accept();

                PrintWriter writer = null;
                BufferedInputStream reader = null;

                writer = new PrintWriter(socket.getOutputStream(), true);
                reader = new BufferedInputStream(socket.getInputStream());

                String msgGet = readString(reader);
                logger.info("Receive " + msgGet + " on admin port");

                if(msgGet.equals("START_LISTEN")) {
                    if(!listenNotificationThread.listennerIsAlive()) {

                        listenNotificationThread = new QueueListenerThread();
                        listenNotificationThread.start();

                        sendString(writer, "START_OK");
                        logger.info("Start listening queue");
                    } else {
                        sendString(writer, "ALREADY_START");
                        logger.info("Already start listening queue");
                    }
                }

                if(msgGet.equals("STOP_LISTEN")) {
                    if(listenNotificationThread.listennerIsAlive()) {

                        listenNotificationThread.stopListenner();
                        listenNotificationThread.stop();

                        sendString(writer, "STOP_OK");
                        logger.info("Stop listening queue");
                    } else {
                        sendString(writer, "ALREADY_STOP");
                        logger.info("Already stop listening queue");
                    }
                }

                if(!msgGet.equals("STOP_LISTEN") && !msgGet.equals("START_LISTEN")) {
                    sendString(writer, "MESSAGE_ERROR");
                }

            } catch (IOException e) {
                logger.error("Socket accept throw exception with message: " + e.getMessage());
                logger.error("Socket accept throw exception 3 time. Server of socket shut down");
                logger.error("Please restart server");
                try {
                    adminServer.close();
                } catch (IOException e1) {
                    logger.error("Can't close admin socker: " + e1.getMessage());
                }
            }
        }
    }
}