package pds.esibank.notificationserver.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pds.esibank.models.notification.MobileClient;
import pds.esibank.notificationserver.utils.JsonUtils;
import pds.esibank.notificationserver.utils.ListConnection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static javax.imageio.ImageIO.read;
import static pds.esibank.notificationserver.utils.JsonUtils.*;
import static pds.esibank.notificationserver.utils.StreamUtils.readString;
import static pds.esibank.notificationserver.utils.StreamUtils.sendString;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     22:02
 */
@Service
public class SocketCommunicationService {

    private static final Logger logger = LoggerFactory.getLogger(SocketCommunicationService.class);

    private PrintWriter writer = null;
    private BufferedInputStream reader = null;

    public SocketCommunicationService() {
    }

    @Async
    public void newCommunication(Socket socket) {
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedInputStream(socket.getInputStream());

            MobileClient mobileClient = objectFromJson(readString(reader),MobileClient.class);
            mobileClient.generateToken();

            ListConnection.addSocketAndMobileToMap(socket,mobileClient);
            sendString(writer, objectToJson(mobileClient));
        } catch (IOException e) {
            logger.error("Communication " + socket.getInetAddress() + " is stop");
        }
    }
}
