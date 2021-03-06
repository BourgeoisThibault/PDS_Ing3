package pds.esibank.notificationserver.controllers;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.models.notification.PushNotificationModel;
import pds.esibank.notificationserver.utils.ListOfTokenGenerate;


import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     22:14
 */
@RestController
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public HomeController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getTest(){
        return "<h1>NOTIFICATION SERVER</h1>";
    }

    @RequestMapping(path = "/send/{uid}", method = RequestMethod.POST)
    public ResponseEntity<String> postNotification(@PathVariable Long uid, @RequestBody NotificationModel notificationModel) {

        if(uid == 0) {
            PushNotificationModel pushNotificationModel = new PushNotificationModel();

            pushNotificationModel.setTitle(notificationModel.getTitle());
            pushNotificationModel.setMessage(notificationModel.getMessage());
            pushNotificationModel.setToken("AllToken");
            pushNotificationModel.setTarget(notificationModel.getTarget());
            rabbitTemplate.convertAndSend(pushNotificationModel);

            return new ResponseEntity<String>("SUCCESS - Send to all token connected",
                    HttpStatus.OK);
        }

        List<String> listToken = ListOfTokenGenerate.getTokenFromUid(uid);
        if(listToken.size() == 0) {
            return new ResponseEntity<String>("No token for this uid",
                    HttpStatus.OK);
        } else {
            for (int i=0 ; i<listToken.size() ; i++) {
                PushNotificationModel pushNotificationModel = new PushNotificationModel();

                pushNotificationModel.setTitle(notificationModel.getTitle());
                pushNotificationModel.setMessage(notificationModel.getMessage());
                pushNotificationModel.setTarget(notificationModel.getTarget());
                pushNotificationModel.setToken(listToken.get(i));

                rabbitTemplate.convertAndSend(pushNotificationModel);
            }
        }

        return new ResponseEntity<String>("SUCCESS - Send to " + listToken.size() + " token.",
                HttpStatus.OK);
    }


    /**
     *
     * Method for cucumber test
     * @return
     */
    @RequestMapping(path = "/admin", method = RequestMethod.POST)
    public ResponseEntity deleteTokenForUidTest() {
        ListOfTokenGenerate.deleteAdminToken();
        return new ResponseEntity(HttpStatus.OK);
    }

}
