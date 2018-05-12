package pds.esibank.restserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pds.esibank.models.dab.AccountDto;
import pds.esibank.models.dab.CardDto;
import pds.esibank.restserver.rest.RestDab;
import pds.esibank.restserver.services.DabManagementService;

import javax.ws.rs.core.MediaType;
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



    @RequestMapping(value="/createAccount", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> createAccount(
            @RequestBody AccountDto accountDto
    ){
        System.out.println("id account : "+accountDto.getAccount_id());
        AccountDto a = RestDab.createAccount(accountDto);
        return new ResponseEntity<AccountDto>(a, HttpStatus.OK);
    }

    @RequestMapping(value="/createCard", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> createCard(
            @RequestBody CardDto cardDto
    ){
        System.out.println("id account : "+cardDto.getCardNum());
        CardDto c = RestDab.createCard(cardDto);
        return new ResponseEntity<CardDto>(c, HttpStatus.OK);
    }

    @GetMapping("/deleteAccount/{account_id}")
    public ResponseEntity<?> deleteAccount(
            @PathVariable String account_id){
        try {
            String s = RestDab.removeAccount(Long.valueOf(account_id));
            System.out.println(s);
            return new ResponseEntity<String>(s, HttpStatus.OK);
        }catch (Exception e){

        }
        return new ResponseEntity<String>("ko",HttpStatus.CONFLICT);
    }

    @GetMapping("/deleteLink/{card_num}")
    public ResponseEntity<?> deleteLink(
            @PathVariable String card_num){
        String s = "";
        try {
            s = RestDab.removeLinkAccountToCard(card_num);
            System.out.println("Réponse :"+s);
            return new ResponseEntity<String>(s, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Réponse :"+s);

        }
        return new ResponseEntity<String>("ko",HttpStatus.CONFLICT);
    }

    @GetMapping("/deleteCard/{card_id}")
    public ResponseEntity<?> deleteCard(
            @PathVariable String card_id){
        try {
            String s = RestDab.removeCard(Long.valueOf(card_id));
            System.out.println(s);
            return new ResponseEntity<String>(s, HttpStatus.OK);
        }catch (Exception e){

        }
        return new ResponseEntity<String>("ko",HttpStatus.CONFLICT);
    }




}

