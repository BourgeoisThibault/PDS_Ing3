package pds.esibank.notificationserver.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.models.notification.NotificationModel;

/**
 * @author BOURGEOIS Thibault
 * Date     11/11/2017
 * Time     00:58
 */
@Service
public class QueuService {

    private static final Logger logger = LoggerFactory.getLogger(SocketCommunicationService.class);

    private NotificationService notificationService;

    @Autowired
    public QueuService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public String sendNotif() {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setTitle("Notification titre");
        notificationModel.setMessage("Message de la notification : " + Math.random()*10);

        notificationService.sendNotification(notificationModel,"351557010202731");

        return "Notification send";
    }
}
