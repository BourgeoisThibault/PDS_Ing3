package pds.esibank.dataaccess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pds.esibank.dataaccess.services.SharePriceHistorisationService;
import pds.esibank.models.shares.SharePrice;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by maria on 14/01/2018.
 */

@RestController
@RequestMapping(value="/sharePriceHistorisation")
public class SharePriceHistorisationController {


    @Autowired
    SharePriceHistorisationService sharePriceHistorisationService;

        @RequestMapping(value="/Historisation", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
        public @ResponseBody List<SharePrice> getSharePrice() throws JsonProcessingException {
            List<SharePrice> list = sharePriceHistorisationService.getAllSharePrice();

            return list;
        }
}

//            List<SharePrice> list = sharePriceHistorisationService.getSharePrice();
