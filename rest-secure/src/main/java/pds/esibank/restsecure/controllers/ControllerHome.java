package pds.esibank.restsecure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pds.esibank.models.Clients.ClientDTO;
import pds.esibank.models.notification.MobileToken;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static sun.security.pkcs11.wrapper.Functions.toHexString;

/**
 * Pass crypte puis hash par client puis envoye
 * Pas d'envoi du mot de pass
 */
@RestController
public class ControllerHome {

    @GetMapping("/")
    public String home(){
        return "<H1>REST SECURE ESIBANK HOME NEW</H1>";
    }

    @GetMapping(value = "/example",consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity testing(@RequestHeader(value = "user-id") String userId,
                                  @RequestHeader(value = "request-sign") String requestSign) throws InvalidKeyException, NoSuchAlgorithmException {

        if(!userId.equals("thibault"))
            return new ResponseEntity("",HttpStatus.UNAUTHORIZED);

        String pass = "toto";

        SecretKeySpec signingKey = new SecretKeySpec(pass.getBytes(), "HmacSHA512");
        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(signingKey);

        if(requestSign.equals(toHexString(mac.doFinal())))
            return new ResponseEntity("",HttpStatus.OK);
        else
            return new ResponseEntity("",HttpStatus.UNAUTHORIZED);
    }

}

