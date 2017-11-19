package pds.esibank.notificationserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pds.esibank.notificationserver.utils.ListOfTokenGenerate;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     22:14
 */
@RestController
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

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

}
