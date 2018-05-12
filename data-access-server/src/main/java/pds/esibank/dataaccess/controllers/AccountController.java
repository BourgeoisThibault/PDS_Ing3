package pds.esibank.dataaccess.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pds.esibank.dataaccess.entities.Account;
import pds.esibank.dataaccess.entities.Card;
import pds.esibank.dataaccess.services.AccountService;
import pds.esibank.models.dab.AccountDto;
import pds.esibank.models.dab.CardDto;

import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping(value="/Account")
public class AccountController {
    @Autowired
    AccountService accountService;



    @GetMapping("/accountsByCardId/{card_id}")
    public ResponseEntity<?> getAccountsByCardId(
            @PathVariable long card_id){
        List<AccountDto> list= accountService.getListAccountByCardId(card_id);
        return new ResponseEntity<List<AccountDto>>(list, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
                return "ok";
    }


    @RequestMapping(value="/createAccount", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> createAccount(
            @RequestBody AccountDto accountDto
            ){
        System.out.println("id account : "+accountDto.getAccount_id());
        AccountDto a = accountService.createAccount(accountDto);
        return new ResponseEntity<AccountDto>(a, HttpStatus.OK);
    }

    @RequestMapping(value="/createCard", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> createCard(
            @RequestBody CardDto cardDto
            ){
        System.out.println("id account : "+cardDto.getCardNum());
        CardDto c = accountService.createCard(cardDto);
        return new ResponseEntity<CardDto>(c, HttpStatus.OK);
    }

    @GetMapping("/deleteAccount/{account_id}")
    public ResponseEntity<?> deleteAccount(
            @PathVariable String account_id){
        try {
            String s = accountService.removeAccount(Long.valueOf(account_id));
            System.out.println(s);
            return new ResponseEntity<String>(s, HttpStatus.OK);
        }catch (Exception e){

        }
        return new ResponseEntity<String>("ko",HttpStatus.CONFLICT);
    }

    @GetMapping("/deleteLink/{card_num}")
    public ResponseEntity<?> deleteLink(
            @PathVariable String card_num){
        try {
            String s = accountService.removeLinkAccountToCard(card_num);
            System.out.println(s);
            return new ResponseEntity<String>(s, HttpStatus.OK);
        }catch (Exception e){

        }
        return new ResponseEntity<String>("ko",HttpStatus.CONFLICT);
    }

    @GetMapping("/deleteCard/{card_id}")
    public ResponseEntity<?> deleteCard(
            @PathVariable String card_id){
        try {
            String s = accountService.removeCard(Long.valueOf(card_id));
            System.out.println(s);
            return new ResponseEntity<String>(s, HttpStatus.OK);
        }catch (Exception e){

        }
        return new ResponseEntity<String>("ko",HttpStatus.CONFLICT);
    }

}
