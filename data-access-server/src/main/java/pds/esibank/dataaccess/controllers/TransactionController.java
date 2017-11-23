package pds.esibank.dataaccess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import pds.esibank.dataaccess.entities.Transaction;
import pds.esibank.dataaccess.services.TransactionService;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


/**
 * Created by SarahAllouche on 23/11/2017.
 */

@RestController
@RequestMapping(value="/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value="/allByDate", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)

    public @ResponseBody ArrayList <Transaction> getTransactionByDate() throws JsonProcessingException{
        ArrayList<Transaction> list = transactionService.getTransactionByDate();
        return list;
   }
}
