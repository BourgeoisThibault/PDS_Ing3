package pds.esibank.dataaccess.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pds.esibank.dataaccess.entities.Share;
import pds.esibank.dataaccess.services.ShareAlertService;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Abbdoulaye on 05/12/2017.
 */
@RestController
@RequestMapping(value="/shareAlert")
public class ShareAlertController {
    @Autowired
    ShareAlertService shareAlertService;

    @RequestMapping(value="/allShareAlert", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<Share>>getAllShare() throws JsonProcessingException {
        List<Share> list= shareAlertService.getAllShareAlert();
        return new ResponseEntity<List<Share>>(list, HttpStatus.OK);

    }
/*
    @RequestMapping(value="/allShareInAlert", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<Share> getShareInAlert() throws JsonProcessingException {
        List<Share> list = shareAlertService.getShareInAlert();

        return list;
    }*/



}
