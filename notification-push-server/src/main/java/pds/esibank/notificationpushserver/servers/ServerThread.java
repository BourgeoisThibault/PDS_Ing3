package pds.esibank.notificationpushserver.servers;

import org.apache.log4j.Logger;
import pds.esibank.notificationpushserver.communications.CheckThread;
import pds.esibank.notificationpushserver.communications.CommunicationThread;
import pds.esibank.notificationpushserver.communications.ListConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author BOURGEOIS Thibault
 * Date     18/11/2017
 * Time     22:37
 */
public class ServerThread extends Thread {

    private Logger logger = Logger.getLogger(ServerThread.class);

    private int _PORT;
    private Boolean isStopped;

    public void set_PORT(int _PORT) {
        this._PORT = _PORT;
    }

    public void setStopped(Boolean stopped) {
        isStopped = stopped;
    }

    @Override
    public void run() {

        this.isStopped = false;

        // Initialize list of mobile connected to empty
        ListConnection.initializeList();

        //CheckThread checkThread = new CheckThread();
        //checkThread.start();

        // Initialize Socket server to null
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(_PORT);
            logger.info("Server of socket listening on port " + _PORT);
        } catch (IOException e) {
            logger.error("Server of socket don't start on port " + _PORT);
            logger.error("Error information: " + e.getMessage());
            logger.error("Please restart server");
            this.isStopped=true;
        }

        int counterError = 0;

        while (!isStopped && !serverSocket.isClosed()) {
            try {
                counterError=0;
                Socket socket = serverSocket.accept();

                socket.setSoTimeout(5000);

                CommunicationThread communicationThread = new CommunicationThread();
                communicationThread.setSocket(socket);
                communicationThread.start();

            } catch (IOException e) {
                logger.error("Socket accept throw exception with message: " + e.getMessage());
                counterError++;
                if(counterError == 3) {
                    logger.error("Socket accept throw exception 3 time. Server of socket shut down");
                    logger.error("Please restart server");
                    try {
                        //checkThread.stop();
                        serverSocket.close();
                    } catch (IOException e1) {
                        logger.error("Can't stop server");
                    }
                    this.isStopped = true;
                }
            }
        }

        if(isStopped) {
            //checkThread.setIsStopped(true);
        }

    }

}
