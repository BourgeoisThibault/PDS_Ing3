package pds.esibank.notificationserver.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author BOURGEOIS Thibault
 * Date     19/11/2017
 * Time     22:29
 */
public class ListOfTokenGenerate {

    private static Map<String, Long> tokenList;

    public static void initializeMap() {
        tokenList = new HashMap<String, Long>();
    }

    public static Map<String, Long> getTokenList() {
        return tokenList;
    }

    public static void addToList(String token, Long uid) {
        tokenList.put(token,uid);
    }

    public static String getTokenByUid(Long uid) {
        for (Map.Entry<String, Long> list : tokenList.entrySet()) {
            if(list.getValue().equals(uid))
                return list.getKey();
        }
        return null;
    }

    public static String generateAnonymousToken() {
        String token = genToken();

        addToList(token,Long.parseLong("0"));

        return token;
    }

    public static String generateUidToken(Long uid) {
        String token = genToken();

        addToList(token,uid);

        return token;
    }

    private static String genToken() {
        String token = UUID.randomUUID().toString();
        Boolean find = false;

        if(tokenList.get(token) == null) {
            find = true;
            while (find=true) {
                token = UUID.randomUUID().toString();
                if(tokenList.get(token) != null) {
                    find = false;
                }
            }
        }

        return token;
    }
}
