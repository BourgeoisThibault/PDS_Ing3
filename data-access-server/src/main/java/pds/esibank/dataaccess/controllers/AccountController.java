package pds.esibank.dataaccess.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pds.esibank.dataaccess.entities.Account;
import pds.esibank.dataaccess.services.AccountService;
import pds.esibank.models.dab.AccountDto;

import java.util.List;

@RestController
@RequestMapping(value="/Account")
public class AccountController {
    @Autowired
    AccountService accountService;


  /*  @GetMapping("/accountsByCardId/{card_id}")
    public ResponseEntity<?> getAccountsByCardId(
            @PathVariable long card_id){
        List<AccountDto> list= accountService.getListAccountByCardId(card_id);
        return new ResponseEntity<List<AccountDto>>(list, HttpStatus.OK);
    }
    */
}
