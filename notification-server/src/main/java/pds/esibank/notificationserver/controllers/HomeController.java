package pds.esibank.notificationserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     22:14
 */
@RestController
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/test")
    public String getTest(){
        logger.info("this is test logging.");
        return "<h1>NOTIFICATION TEST</h1>";
    }

}
