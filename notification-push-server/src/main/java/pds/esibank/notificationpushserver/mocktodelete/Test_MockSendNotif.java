package pds.esibank.notificationpushserver.mocktodelete;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pds.esibank.models.notification.MobileClient;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.notificationpushserver.utils.JsonUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.interfaces.RSAKey;
import java.security.spec.RSAKeyGenParameterSpec;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     17:51
 */
public class Test_MockSendNotif {

    /**
     * todo: delete after create service notif. THIS IS ONLY FOR TEST
     */

    private static String T_TOKEN = "tokentest";

    private static PrintWriter writer = null;
    private static BufferedInputStream reader = null;

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("esibank.inside.esiag.info", 8888);

            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedInputStream(socket.getInputStream());

            send(T_TOKEN);

            String msg = read();

            if(msg.equals("true")) {
                NotificationModel notificationModel = new NotificationModel();
                notificationModel.setTitle("Un super titre");
                notificationModel.setMessage("Ici ce sera le message de la notification.");
                send(JsonUtils.objectToJson(notificationModel));
            }else {
                System.out.println("Notif not send");
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
