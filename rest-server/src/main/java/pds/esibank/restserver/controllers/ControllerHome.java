package pds.esibank.restserver.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class ControllerHome {

    @GetMapping("/")
    public String home(){
        return "<H1>REST ESIBANK HOME NEW</H1>";
    }

    private static final String URI_DATA_ACCESS = "http://10.10.2.1:8080/";
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/test")
    public String test() throws IOException {

        String toto = restTemplate.getForObject(URI_DATA_ACCESS,String.class);
        return toto;
    }

}

