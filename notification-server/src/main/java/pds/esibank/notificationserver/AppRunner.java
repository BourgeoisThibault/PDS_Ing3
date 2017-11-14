package pds.esibank.notificationserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pds.esibank.notificationserver.threads.NotificationService;
import pds.esibank.notificationserver.threads.SocketCommunicationService;
import pds.esibank.notificationserver.utils.ListConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     21:39
 */
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final int _PORT = 2702;
    private Boolean isStopped;
    private ServerSocket serverSocket;

    private final SocketCommunicationService socketCommunicationService;
    private final NotificationService notificationService;

    public AppRunner(SocketCommunicationService socketCommunicationService, NotificationService notificationService) {
        this.socketCommunicationService = socketCommunicationService;
        this.notificationService = notificationService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.isStopped = false;

        // Initialize list of mobile connected to empty
        ListConnection.initializeList();

        notificationService.checkAllConnection();

        // Initialize Socket server to null
        serverSocket = null;

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
                socketCommunicationService.newCommunication(socket);
                logger.info("Socket accept from " + socket.getRemoteSocketAddress().toString());
            } catch (IOException e) {
                logger.error("Socket accept throw exception with message: " + e.getMessage());
                counterError++;
                if(counterError == 3) {
                    logger.error("Socket accept throw exception 3 time. Server of socket shut down");
                    logger.error("Please restart server");
                    serverSocket.close();
                    this.isStopped = true;
                }
            }
        }
    }

}