package pds.esibank.notificationpushserver.communications;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static pds.esibank.notificationpushserver.utils.StreamUtils.readString;
import static pds.esibank.notificationpushserver.utils.StreamUtils.sendString;

/**
 * @author BOURGEOIS Thibault
 * Date     18/11/2017
 * Time     23:00
 */
public class CommunicationThread extends Thread {

    private Logger logger = Logger.getLogger(CommunicationThread.class);

    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedInputStream(socket.getInputStream());

            String msgToken = readString(reader);

            if(msgToken.length() > 50) {
                sendString(writer, "Not TOKEN");
                logger.info("Wrong token not save");
            } else {
                logger.info("New connection with TOKEN: " + msgToken);
                ListConnection.addSocketAndMobileToMap(socket,msgToken);
            }

        } catch (IOException e) {
            logger.error("Communication " + socket.getInetAddress() + " is stop");
        }
    }
}
