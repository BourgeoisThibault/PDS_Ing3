package pds.esibank.notificationserver.controllers;

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

    @GetMapping("/test")
    public String getTest(){
        return "<h1>NOTIFICATION TEST</h1>";
    }

}
