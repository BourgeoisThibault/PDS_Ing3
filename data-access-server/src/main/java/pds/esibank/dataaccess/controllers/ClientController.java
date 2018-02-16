package pds.esibank.dataaccess.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pds.esibank.dataaccess.entities.Client;
import pds.esibank.dataaccess.services.ClientService;

import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * Created by Abbdoulaye on 06/02/2018.
 */
@Controller
@RequestMapping("/Clients")
public class ClientController {
    @Autowired
    ClientService clientService;

    @RequestMapping(value="/allClients", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Client>> getAllShare() throws JsonProcessingException {
        List<Client> list= clientService.getAllClient();
        return new ResponseEntity<List<Client>>(list, HttpStatus.OK);

    }
}
