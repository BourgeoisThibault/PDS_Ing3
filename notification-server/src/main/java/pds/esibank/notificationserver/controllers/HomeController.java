package pds.esibank.notificationserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pds.esibank.notificationserver.threads.QueuService;

/**
 * @author BOURGEOIS Thibault
 * Date     10/11/2017
 * Time     22:14
 */
@RestController
public class HomeController {

    private QueuService queuService;

    @Autowired
    public HomeController(QueuService queuService) {
        this.queuService = queuService;
    }

    @GetMapping("/")
    public String getHome(){
        return queuService.sendNotif();
    }

    @GetMapping("/test")
    public String getTest(){
        return "Test OK";
    }

}
