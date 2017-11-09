package pds.esibank.notificationserver;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     17:51
 */
public class mainTest {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 2702);

            BufferedInputStream reader = null;
            reader = new BufferedInputStream(socket.getInputStream());
            //On attend la réponse
            String response = read(reader);
            System.out.println("Réponse reçue " + response);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Méthode pour lire les réponses du serveur
    private static String read(BufferedInputStream reader) throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }

}
