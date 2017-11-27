package pds.esibank.dataaccess.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pds.esibank.dataaccess.entities.SharePrice;
import pds.esibank.dataaccess.services.SharePriceService;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/shareprice")
public class SharesController {

    @Autowired
    SharePriceService sharePriceService;

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<SharePrice>> update(@RequestBody ArrayList<SharePrice> sharePriceList) {
        sharePriceList.forEach(sp -> sharePriceService.update(sp));
        return new ResponseEntity<>(sharePriceList, HttpStatus.OK);
    }
}
