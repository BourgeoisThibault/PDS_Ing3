package pds.esibank.dataaccess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pds.esibank.dataaccess.entities.LeavingParameters;
import pds.esibank.dataaccess.services.LPService;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping(path = "/lp")
public class LPController {

    @Autowired
    LPService lpService;

    @RequestMapping(value="/findAll", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<LeavingParameters> finAll() throws JsonProcessingException {
        List<LeavingParameters> list = lpService.findAll();
        return list;
    }
}
