package pds.esibank.notificationpushserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author BOURGEOIS Thibault
 * Date     18/11/2017
 * Time     21:53
 */
public class App {

    private static int _ADMIN_PORT = 9999;
    private static int _PORT = 9090;

    public static void main(String[] args) {

        ManagerThread managerThread = new ManagerThread();
        managerThread.set_ADMIN_PORT(_ADMIN_PORT);
        managerThread.set_PORT(_PORT);
        managerThread.start();

    }
}
