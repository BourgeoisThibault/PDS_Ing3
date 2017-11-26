package pds.esibank.notificationpushserver.mocktodelete;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.notificationpushserver.utils.JsonUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author BOURGEOIS Thibault
 * Date     26/11/2017
 * Time     14:25
 */
public class Test_MockAdmin {


    /**
     * todo: delete after create service android. THIS IS ONLY FOR TEST
     */

    private static String T_TOKEN = "thibault";

    private static PrintWriter writer = null;
    private static BufferedInputStream reader = null;

    public static void main(String[] args) {

        try {
            //Socket socket = new Socket("push.esibank.inside.esiag.info", 9999);
            Socket socket = new Socket("127.0.0.1", 9999);

            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedInputStream(socket.getInputStream());

            send("START_LISTEN");

            String msg = read();
            System.out.println(msg);

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
