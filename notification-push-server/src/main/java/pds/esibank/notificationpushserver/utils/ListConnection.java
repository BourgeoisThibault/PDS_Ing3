package pds.esibank.notificationpushserver.utils;

import pds.esibank.models.notification.MobileClient;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     16:38
 */
public class ListConnection {

    private static Map<Socket,String> socketMobileClientMap;

    public static void initializeList() {
        socketMobileClientMap = new HashMap<Socket, String>();
    }

    public static void addSocketAndMobileToMap(Socket socket, String token) {
        socketMobileClientMap.put(socket,token);
    }

    public static Map<Socket,String> getSocketMobileClientMap() {
        return socketMobileClientMap;
    }

    public static void closeAllConnection() {
        for (Map.Entry<Socket,String> list : socketMobileClientMap.entrySet()) {
            socketMobileClientMap.remove(list.getKey());
        }
    }
}
