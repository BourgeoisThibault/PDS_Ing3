package pds.esibank.dataaccess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pds.esibank.dataaccess.entities.InvestCustomer;
import pds.esibank.dataaccess.services.InvestCustomerService;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by SarahAllouche on 07/02/2018.
 */

@RestController
@RequestMapping(value="/investcustomer")
public class InvestCustomerController {

    @Autowired
    InvestCustomerService InvestCustomerService;

    @RequestMapping(value="/allInvestCustomer", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<InvestCustomer> getInvestCustomer() throws JsonProcessingException {
        List<InvestCustomer> list = InvestCustomerService.getInvestCustomer();
        return list;
    }
}


