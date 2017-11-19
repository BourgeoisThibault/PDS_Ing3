package pds.esibank.notificationpushserver.communications;

import pds.esibank.models.notification.MobileClient;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.notificationpushserver.utils.JsonUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static pds.esibank.notificationpushserver.utils.StreamUtils.sendString;

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

    public static Boolean searchToken(String token) {

        for (Map.Entry<Socket,String> list : socketMobileClientMap.entrySet()) {
            if(list.getValue().equals(token))
                return true;
        }

        return false;
    }

    public static void sendNotification(String token, NotificationModel notificationModel) throws IOException {

        PrintWriter writer = null;

        for (Map.Entry<Socket,String> list : socketMobileClientMap.entrySet()) {
            if(list.getValue().equals(token))
                writer = new PrintWriter(list.getKey().getOutputStream(), true);
                sendString(writer, JsonUtils.objectToJson(notificationModel));
        }

    }
}
