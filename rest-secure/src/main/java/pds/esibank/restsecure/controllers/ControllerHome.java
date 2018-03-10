package pds.esibank.restsecure.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class ControllerHome {

    @GetMapping("/")
    public String home(){
        return "<H1>REST SECURE ESIBANK HOME NEW</H1>";
    }

}

