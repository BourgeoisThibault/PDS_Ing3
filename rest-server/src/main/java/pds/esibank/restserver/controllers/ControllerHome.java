package pds.esibank.restserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pds.esibank.restserver.services.DabManagementService;

import java.io.IOException;

@RestController
public class ControllerHome {

    private final DabManagementService dabManagementService;

    public ControllerHome(DabManagementService dabManagementService) {
        this.dabManagementService = dabManagementService;
    }

    @GetMapping("/")
    public String home(){
        return "<H1>REST ESIBANK HOME NEW</H1>";
    }

    private static final String URI_DATA_ACCESS = "http://esibanklab_dataaccess:8080/";
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/test")
    public String test() throws IOException {

        String toto = restTemplate.getForObject(URI_DATA_ACCESS,String.class);
        return toto;
    }

    @GetMapping("/checkAccountValid/{card_id}")
    public String getAccountsByCardId(
            @PathVariable long card_id){

        if(dabManagementService.checkAccountValid(card_id)){
            return "ok";
        }
        else
            return "ko";

    }




}

