package pds.esibank.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pds.esibank.dataaccess.entities.Share;
import pds.esibank.dataaccess.entities.SharePrice;
import pds.esibank.dataaccess.repositories.SharePriceRepository;
import pds.esibank.dataaccess.services.SharePriceService;
import pds.esibank.dataaccess.services.ShareService;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/shareprice")
public class SharesController {

    @Autowired
    SharePriceService sharePriceService;
    @Autowired
    ShareService shareService;

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<SharePrice>> update(@RequestBody ArrayList<SharePrice> sharePriceList) {
        System.out.println("toto : "+sharePriceList.get(0).getShare().getName());
        sharePriceList.forEach(sp -> {
            shareService.update(sp.getShare());
            sharePriceService.update(sp);
        });
        return new ResponseEntity<>(sharePriceList, HttpStatus.OK);
    }

    @RequestMapping(path = "/toto", method = RequestMethod.GET)
    public String home(){
        return "<H1>REST ESIBANK DATA-ACCESS</H1>";
    }
}
