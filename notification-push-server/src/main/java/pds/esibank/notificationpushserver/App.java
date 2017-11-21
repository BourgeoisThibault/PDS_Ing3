package pds.esibank.notificationpushserver;

import pds.esibank.notificationpushserver.servers.ManagerThread;

/**
 * @author BOURGEOIS Thibault
 * Date     18/11/2017
 * Time     21:53
 */
public class App {

    private static int _ADMIN_PORT = 9999;
    private static int _PORT = 9090;
    private static int _PUSH_PORT = 8888;

    public static void main(String[] args) {

        ManagerThread managerThread = new ManagerThread();
        managerThread.set_ADMIN_PORT(_ADMIN_PORT);
        managerThread.set_PORT(_PORT);
        managerThread.set_PUSH_PORT(_PUSH_PORT);
        managerThread.start();

    }
}
