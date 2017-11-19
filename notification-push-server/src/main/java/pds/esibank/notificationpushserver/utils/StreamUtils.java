package pds.esibank.notificationpushserver.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author BOURGEOIS Thibault
 * Date     11/11/2017
 * Time     01:26
 */
public class StreamUtils {

    //Méthode pour lire les réponses du serveur
    public static String readString(BufferedInputStream reader) throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }

    public static void sendString(PrintWriter writer, String msg) throws IOException {
        writer.write(msg);
        //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
        writer.flush();
    }
}
