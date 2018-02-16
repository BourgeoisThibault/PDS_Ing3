package pds.esibank.dataaccess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pds.esibank.dataaccess.entities.ClientNotif;
import pds.esibank.dataaccess.entities.ClientRule;
import pds.esibank.dataaccess.services.ClientNotifService;
import pds.esibank.dataaccess.services.ClientRuleService;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
@Controller
@RequestMapping("/rules")

public class ClientRuleController {
    @Autowired
    ClientRuleService clientRuleService;

    @RequestMapping(value="/add", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public ClientRule addClientRule(@RequestBody ClientRule clientRule) throws JsonProcessingException {
       // ClientRule clientRule= new ClientRule(idClient,shareId,seuilMin,seuilMax,communicationType,frequenceAlerte);
        return clientRuleService.addClientRule(clientRule);

    }
}
