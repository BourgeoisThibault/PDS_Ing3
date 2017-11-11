package pds.esibank.notificationserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pds.esibank.models.notification.MobileClient;
import pds.esibank.models.notification.NotificationModel;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     17:51
 */
public class MainClientProto {

    /**
     * todo: delete after create service android. THIS IS ONLY FOR TEST
     */

    private static String _IMEI = "351557010202731";

    private static PrintWriter writer = null;
    private static BufferedInputStream reader = null;

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 2702);

            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedInputStream(socket.getInputStream());

            MobileClient mobileClient = new MobileClient();
            mobileClient.setImei(_IMEI);
            
            send(toJson(mobileClient));
            
            mobileClient = fromJson(read(),MobileClient.class);

            System.out.println(mobileClient.toString());

            while (true) {
                String accept = read();
                if(accept.equals("PING")) {
                    send("PONG");
                } else {
                    System.out.println(fromJson(accept,NotificationModel.class).toString());
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Méthode pour lire les réponses du serveur
    private static String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
    
    private static void send(String msg) throws IOException {
        writer.write(msg);
        //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
        writer.flush();
    }

    private static <T> String toJson(T object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    private static <T> T fromJson(String json, Class<T> toClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, toClass);
    }

}
