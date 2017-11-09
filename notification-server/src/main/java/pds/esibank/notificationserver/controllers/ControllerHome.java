package pds.esibank.notificationserver.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerHome {

    @GetMapping("/")
    public String home(){
        return "<H1>REST ESIBANK NOTIFICATION HOMEPAGE</H1>";
    }

}
