package pds.esibank.dataaccess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pds.esibank.dataaccess.entities.CommunicationType;
import pds.esibank.dataaccess.services.CommunicationTypeService;


import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Abbdoulaye on 09/02/2018.
 */
@Controller
@RequestMapping("/communication")
public class CommunicationTypeController {
    @Autowired
    CommunicationTypeService communicationTypeService;

    @RequestMapping(value="/all", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<CommunicationType>> getAllCommunicationType() throws JsonProcessingException {
        List<CommunicationType> list= communicationTypeService.getAllCommunicationType();
        return new ResponseEntity<List<CommunicationType>>(list, HttpStatus.OK);

    }
}
