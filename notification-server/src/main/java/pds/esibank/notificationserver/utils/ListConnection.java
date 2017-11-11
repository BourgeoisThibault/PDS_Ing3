package pds.esibank.notificationserver.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pds.esibank.models.notification.MobileClient;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     16:38
 */
public class ListConnection {

    private static Map<Socket,MobileClient> socketMobileClientMap;

    public static void initializeList() {
        socketMobileClientMap = new HashMap<Socket, MobileClient>();
    }

    public static void addSocketAndMobileToMap(Socket socket, MobileClient mobileClient) {
        socketMobileClientMap.put(socket,mobileClient);
    }

    public static Map<Socket,MobileClient> getSocketMobileClientMap() {
        return socketMobileClientMap;
    }

}
