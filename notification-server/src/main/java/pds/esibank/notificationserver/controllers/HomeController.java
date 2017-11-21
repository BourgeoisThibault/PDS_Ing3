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
        return "<h1>NOTIFICATION TEST</h1>";
    }

    @RequestMapping(path = "/token", method = RequestMethod.GET)
    public String genToken(){
        return ListOfTokenGenerate.generateAnonymousToken();
    }

    @RequestMapping(path = "/token/{uid}", method = RequestMethod.GET)
    public String genToken(@PathVariable Long uid){
        return ListOfTokenGenerate.generateUidToken(uid);
    }

    @RequestMapping(path = "/mq", method = RequestMethod.GET)
    public ResponseEntity sendMessage() {

        PushNotificationModel pushNotificationModel = new PushNotificationModel();

        pushNotificationModel.setTitle("NotifThibault");
        pushNotificationModel.setMessage("Message number ");
        pushNotificationModel.setToken("TokenThibault");
        rabbitTemplate.convertAndSend(pushNotificationModel);

        pushNotificationModel.setTitle("NotifTest");
        pushNotificationModel.setMessage("Message number ");
        pushNotificationModel.setToken("TokenThibault");
        rabbitTemplate.convertAndSend(pushNotificationModel);

        pushNotificationModel.setTitle("NotifUsman");
        pushNotificationModel.setMessage("Message number ");
        pushNotificationModel.setToken("TokenThibault");
        rabbitTemplate.convertAndSend(pushNotificationModel);

        pushNotificationModel.setTitle("NotifRuben");
        pushNotificationModel.setMessage("Message number ");
        pushNotificationModel.setToken("TokenThibault");
        rabbitTemplate.convertAndSend(pushNotificationModel);

        pushNotificationModel.setTitle("NotifLinda");
        pushNotificationModel.setMessage("Message number ");
        pushNotificationModel.setToken("TokenThibault");
        rabbitTemplate.convertAndSend(pushNotificationModel);

        pushNotificationModel.setTitle("NotifTony");
        pushNotificationModel.setMessage("Message number ");
        pushNotificationModel.setToken("TokenThibault");
        rabbitTemplate.convertAndSend(pushNotificationModel);

        return new ResponseEntity("SUCCESS", HttpStatus.OK);
    }

}
