package pds.esibank.notificationserver.utils;

import pds.esibank.models.notification.MobileToken;

import java.net.Socket;
import java.util.*;

/**
 * @author BOURGEOIS Thibault
 * Date     19/11/2017
 * Time     22:29
 */
public class ListOfTokenGenerate {

    private static Map<String, Long> tokenUidList;
    private static Map<String, Date> tokenExpireList;

    public static void initializeMap() {
        tokenUidList = new HashMap<String, Long>();
        tokenExpireList = new HashMap<String, Date>();
    }

    public static MobileToken putMobileToken(MobileToken mobileToken) {

        if(mobileTokenNull(mobileToken) ||
                tokenNoPresent(mobileToken.getToken()) ||
                tokenNoIdentique(mobileToken))
            return initializeNewMobileToken(mobileToken.getUid());

        updateExpireDateForToken(mobileToken.getToken());

        return mobileToken;

    }

    public static List<String> getTokenFromUid(Long uid) {
        List<String> listToken = new ArrayList<String>();

        for (Map.Entry<String,Long> list : tokenUidList.entrySet()) {
            if(list.getValue().equals(uid))
                listToken.add(list.getKey());
        }

        return listToken;
    }

    public static void deleteExpireToken() {
        Date date = new Date();
        date.setDate(date.getDate()+1);

        for (Map.Entry<String,Date> list : tokenExpireList.entrySet()) {
            if(list.getValue().getDate() < date.getDate()) {
                tokenExpireList.remove(list.getKey());
                tokenUidList.remove(list.getKey());
            } else {
                if(list.getValue().getDate() == date.getDate()) {
                    if(list.getValue().getTime() < date.getTime()) {
                        tokenExpireList.remove(list.getKey());
                        tokenUidList.remove(list.getKey());
                    }
                }
            }
        }
    }

    private static String generateNewToken() {
        String newToken = null;
        Boolean find = true;

        while (find) {
            newToken = UUID.randomUUID().toString();
            if(tokenUidList.get(newToken) == null) {
                find = false;
            }
        }

        return newToken;
    }

    private static MobileToken initializeNewMobileToken(String uid) {
        MobileToken mobileToken = new MobileToken();
        mobileToken.setToken(generateNewToken());

        if(uid == null)
            mobileToken.setUid("0");
        else
            mobileToken.setUid(uid);

        Date date = new Date();
        date.setDate(date.getDate()+7);

        tokenUidList.put(mobileToken.getToken(),Long.parseLong(mobileToken.getUid()));
        tokenExpireList.put(mobileToken.getToken(),date);

        return mobileToken;
    }

    private static Boolean tokenNoPresent(String token) {
        if(tokenUidList.get(token) == null)
            return true;
        else
            return false;
    }

    private static Boolean tokenNoIdentique(MobileToken mobileToken) {
        if(tokenUidList.get(mobileToken.getToken()) == null)
            return true;

        if(tokenUidList.get(mobileToken.getToken()) != Long.parseLong(mobileToken.getUid()))
            return true;

        return false;
    }

    private static Boolean mobileTokenNull(MobileToken mobileToken) {
        if (mobileToken.getToken() == null &&
                mobileToken.getUid() == null)
            return true;
        else
            return false;
    }

    private static void updateExpireDateForToken(String token) {

        Date date = new Date();
        date.setDate(date.getDate() + 7);

        for (Map.Entry<String,Date> list : tokenExpireList.entrySet()) {
            if(list.getKey().equals(token))
                list.setValue(date);
        }
    }

}
