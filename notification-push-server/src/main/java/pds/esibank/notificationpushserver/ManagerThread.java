package pds.esibank.notificationpushserver;

import org.apache.log4j.Logger;
import pds.esibank.notificationpushserver.servers.ListenNotificationThread;
import pds.esibank.notificationpushserver.servers.ServerThread;
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
    private ListenNotificationThread listenNotificationThread;

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
        logger.info("#   Push   : 8888        #");
        logger.info("#   Admin  : 9999        #");
        logger.info("#                        #");
        logger.info("##########################");
        logger.info("");
        logger.info("");

        serverPause = false;

        listenNotificationThread = new ListenNotificationThread();
        listenNotificationThread.set_PORT(_PUSH_PORT);
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

                if(msgGet.equals("run")) {
                    if(serverPause.equals(true)) {
                        serverThread = new ServerThread();
                        serverThread.set_PORT(_PORT);
                        serverThread.start();

                        serverPause=false;

                        sendString(writer, "New server running");
                    } else {
                        sendString(writer, "Server already running");
                    }
                }

                if(msgGet.equals("stop")) {
                    if(serverPause.equals(false)) {
                        serverThread.setStopped(true);
                        serverThread.stop();

                        ListConnection.closeAllConnection();
                        logger.info("All connection closed");

                        serverPause=true;

                        sendString(writer, "Server is stopped");
                    } else {
                        sendString(writer, "Server already stopped");
                    }
                }

                if(!msgGet.equals("run") && !msgGet.equals("stop")) {
                    sendString(writer, "Wrong message");
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