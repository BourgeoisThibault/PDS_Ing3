package pds.esibank.dataaccess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pds.esibank.dataaccess.entities.Transaction;
import pds.esibank.dataaccess.services.TransactionService;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by SarahAllouche on 23/11/2017.
 */

@RestController
@RequestMapping(value="/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value="/allByDate", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List <Transaction> getTransactionByDate() throws JsonProcessingException{
        List<Transaction> list = transactionService.getTransactionByDate();
        return list;
   }

    @RequestMapping(value = "/InputTransaction", method = RequestMethod.POST)
    public ResponseEntity<List<Transaction>> InputTransaction(@RequestBody List<Transaction> tabTransaction) {
        transactionService.InputTransaction(tabTransaction);
        return new ResponseEntity<List<Transaction>>(tabTransaction, HttpStatus.OK);
    }


}
