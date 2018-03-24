package pds.esibank.restsecure.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pds.esibank.crypto.MySHA;
import pds.esibank.models.Clients.ClientDTO;
import pds.esibank.models.notification.MobileToken;
import pds.esibank.models.payfree.PfClientDto;

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

    @Value("${crypt.sign.type}")
    private String cryptSignType;

    @GetMapping("/")
    public String home(){
        return "<H1>REST SECURE ESIBANK HOME NEW</H1>";
    }

    @Value("${url.database}")
    private String URL_DATABASE;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/example",produces = MediaType.APPLICATION_JSON)
    public ResponseEntity testing(@RequestHeader(value = "user-id") String userId,
                                  @RequestHeader(value = "request-sign") String requestSign) throws InvalidKeyException, NoSuchAlgorithmException {

        PfClientDto pfClientDto = restTemplate.getForObject(URL_DATABASE + userId,PfClientDto.class);

        if(pfClientDto == null)
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        if (MySHA.checkSign(pfClientDto.getEncryptedPass(),"",cryptSignType,requestSign))
            return new ResponseEntity(pfClientDto,HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);


    }

}

