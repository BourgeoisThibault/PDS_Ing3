package pds.esibank.notificationserver.websocket;

import pds.esibank.models.notification.MobileClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     16:38
 */
public class SocketTraitement {

    private static List<MobileClient> mobileClientList;

    public static void initializeList() {
        mobileClientList = new ArrayList<MobileClient>();
    }

    public static Boolean addMobileToList(MobileClient mobileClient) {

        return true;
    }

}
