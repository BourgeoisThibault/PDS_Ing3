package pds.esibank.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerHome {

    @GetMapping("/")
    public String home(){
        return "<H1>SI EXTERNE HOME NEW</H1>";
    }

}
