package pds.esibank.restsecure.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pds.esibank.crypto.MySHA;
import pds.esibank.models.dab.CardDto;
import pds.esibank.models.payfree.PfClientDto;

import javax.ws.rs.core.MediaType;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author BOURGEOIS Thibault
 * Date     28/03/2018
 * Time     13:50
 */
@RestController
public class ControllerDab {

    @Value("${crypt.pass.type}")
    private String cryptPassType;
    @Value("${crypt.sign.type}")
    private String cryptSignType;

    @Value("${url.database}")
    private String URL_DATABASE;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/verify")
    public ResponseEntity verifyCard(@RequestHeader(value = "card-id") String cardId,
                                  @RequestHeader(value = "request-sign") String requestSign) throws InvalidKeyException, NoSuchAlgorithmException {

        try {
            CardDto cardDto = restTemplate.getForObject(URL_DATABASE + "card/" + cardId,CardDto.class);

            if (MySHA.checkSign(cardDto.getCardPass(),"",cryptSignType,requestSign))
                return new ResponseEntity(cardDto,HttpStatus.OK);
            else
                return new ResponseEntity("",HttpStatus.UNAUTHORIZED);

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity("", ex.getStatusCode());
        }

    }

    @GetMapping(value = "/checking")
    public ResponseEntity chackingAmount(@RequestHeader(value = "card-id") String cardId,
                                         @RequestHeader(value = "amount") String amount,
                                     @RequestHeader(value = "request-sign") String requestSign) throws InvalidKeyException, NoSuchAlgorithmException {

        try {
            CardDto cardDto = restTemplate.getForObject(URL_DATABASE + "card/" + cardId,CardDto.class);

            String valide = "Faire validation";

            if (MySHA.checkSign(cardDto.getCardPass(),amount,cryptSignType,requestSign))
                return new ResponseEntity(cardDto,HttpStatus.OK);
            else
                return new ResponseEntity("",HttpStatus.UNAUTHORIZED);

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity("", ex.getStatusCode());
        }

    }

    @GetMapping(value = "/confirm")
    public ResponseEntity confirmTransaction(@RequestHeader(value = "card-id") String cardId,
                                         @RequestHeader(value = "amount") String amount,
                                         @RequestHeader(value = "request-sign") String requestSign) throws InvalidKeyException, NoSuchAlgorithmException {

        try {
            CardDto cardDto = restTemplate.getForObject(URL_DATABASE + "card/" + cardId,CardDto.class);

            String valide = "Confirme transaction";

            if (MySHA.checkSign(cardDto.getCardPass(),cardId + amount,cryptSignType,requestSign))
                return new ResponseEntity(cardDto,HttpStatus.OK);
            else
                return new ResponseEntity("",HttpStatus.UNAUTHORIZED);

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity("", ex.getStatusCode());
        }

    }


}
