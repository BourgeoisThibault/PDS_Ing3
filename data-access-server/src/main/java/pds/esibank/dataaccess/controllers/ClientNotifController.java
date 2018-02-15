package pds.esibank.dataaccess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pds.esibank.dataaccess.entities.ClientNotif;
import pds.esibank.dataaccess.repositories.ClientNotifRepository;
import pds.esibank.dataaccess.services.ClientNotifService;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
@Controller
@RequestMapping("/notif")
public class ClientNotifController {
    @Autowired
    ClientNotifService clientNotifService;

    @RequestMapping(value="/all", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<ClientNotif>> getAllShare() throws JsonProcessingException {
        List<ClientNotif> list= clientNotifService.getAllNotifClient();
        return new ResponseEntity<List<ClientNotif>>(list, HttpStatus.OK);

    }

}
