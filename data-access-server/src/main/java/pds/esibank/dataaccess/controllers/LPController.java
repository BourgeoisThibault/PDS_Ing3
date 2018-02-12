package pds.esibank.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pds.esibank.dataaccess.entities.LeavingParameters;
import pds.esibank.dataaccess.services.LPService;

import java.util.List;

@RestController
@RequestMapping(path = "/lp")
public class LPController {

    @Autowired
    LPService lpService;

    @RequestMapping(value="/findAll", method= RequestMethod.GET)
    public @ResponseBody List<LeavingParameters> getInvestCustomer() {
        List<LeavingParameters> list = lpService.findAll();
        return list;
    }
}
