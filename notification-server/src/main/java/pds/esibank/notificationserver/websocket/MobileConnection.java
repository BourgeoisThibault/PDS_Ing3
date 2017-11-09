package pds.esibank.notificationserver.websocket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     17:12
 */
public class MobileConnection extends Thread {

    private Socket socket;
    private int id;

    public MobileConnection(Socket socket ,int id) {
        super();
        this.socket = socket;
        this.id = id;
    }

    public void run() {
        System.out.println("CLIENT ID: " + id);

        try {
            OutputStream outputStream = socket.getOutputStream();

            String msg = "Tu est qui?";
            byte[] msgByte = msg.getBytes();
            outputStream.write(msgByte, 0, msgByte.length);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
