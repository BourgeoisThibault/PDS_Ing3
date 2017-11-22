package pds.esibank.notificationserver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pds.esibank.models.notification.MobileToken;
import pds.esibank.notificationserver.utils.ListOfTokenGenerate;

/**
 * @author BOURGEOIS Thibault
 * Date     22/11/2017
 * Time     18:09
 */
@RestController
@RequestMapping(path = "/token")
public class TokenController {

    private final Logger logger = LoggerFactory.getLogger(TokenController.class);

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<MobileToken> genToken(@RequestBody MobileToken mobileToken){

        if(mobileToken == null)
            return new ResponseEntity<MobileToken>(new MobileToken(),HttpStatus.NO_CONTENT);

        return new ResponseEntity<MobileToken>(ListOfTokenGenerate.putMobileToken(mobileToken),
                HttpStatus.OK);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<MobileToken> genToken() {

        MobileToken mobileToken = new MobileToken();

        return new ResponseEntity<MobileToken>(mobileToken,HttpStatus.OK);
    }

}
