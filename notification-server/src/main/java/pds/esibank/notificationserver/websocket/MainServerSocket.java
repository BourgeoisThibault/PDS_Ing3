package pds.esibank.notificationserver.websocket;

import jdk.nashorn.internal.runtime.logging.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     16:35
 */
public class MainServerSocket extends Thread {

    private final int _PORT = 2702;
    private Boolean isStopped;
    private ServerSocket serverSocket;

    public MainServerSocket() {
        super();
    }

    public void setIsStopped(Boolean value) {
        this.isStopped = value;
    }

    public void run() {

        this.isStopped = false;

        // Initialize list of mobile connected to empty
        SocketTraitement.initializeList();

        // Initialize Socket server to null
        serverSocket = null;

        try {
            serverSocket = new ServerSocket(_PORT);
        } catch (IOException e) {
            System.out.println("ERREUR: SockeServer down -> " + e.getMessage());
            this.setIsStopped(true);
        }

        int compteur = 0;
        while (!isStopped && !serverSocket.isClosed()) {

            try {
                Socket socket = serverSocket.accept();
                new MobileConnection(socket, compteur).start();
            } catch (IOException e) {
                System.out.println("ERREUR: socker accept down -> " + e.getMessage());
            }

            compteur++;
        }

    }

}
