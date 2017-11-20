package pds.esibank.notificationserver.controllers;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.notificationserver.utils.ListOfTokenGenerate;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     22:14
 */
@RestController
public class HomeController {

    private final Logger logger = Logger.getLogger(HomeController.class);

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
    public String sendMessage() {

        AtomicInteger counter = new AtomicInteger();
        for (int i = 0; i < 10; i++){
            System.out.println("Sending notification number " + i);
            NotificationModel notificationModel = new NotificationModel();
            notificationModel.setTitle("Titre number " + i);
            notificationModel.setMessage("Message number " + i);
            rabbitTemplate.convertAndSend(notificationModel);
        }

        return "Success";
    }

}
