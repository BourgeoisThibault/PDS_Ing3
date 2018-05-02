package pds.esibank.payfreeclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pds.esibank.crypto.MySHA;
import pds.esibank.models.payfree.PfClientDto;

import static org.springframework.http.HttpMethod.GET;

/**
 * @author  BOURGEOIS Thibault
 * Date     23/03/2018
 * Time     11:39
 */

@RestController
public class ControllerDab {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.secure}")
    private String URL_SECURE;
    @Value("${crypt.pass.type}")
    private String cryptPassType;
    @Value("${crypt.sign.type}")
    private String cryptSignType;

    @GetMapping("/check")
    public ResponseEntity CheckValidityCard(
            @RequestParam("card") String card,
            @RequestParam("pin") String pin
    ){

        String finalUrl = URL_SECURE + "checkcard";

        String encryptedPass = MySHA.passToSHA(pin, cryptPassType);
        String signature = MySHA.generateSign(encryptedPass, "", cryptSignType);

        HttpHeaders headers = new HttpHeaders();
        headers.set("card-id", card);
        headers.set("request-sign", signature);

        HttpEntity entity = new HttpEntity("parameters",headers);

        try {
            restTemplate.exchange(finalUrl, GET, entity, String.class);
            return new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity(ex.getStatusCode());
        }
    }

    @GetMapping("/validate")
    public ResponseEntity CheckValidityTransaction(
            @RequestParam("card") String card,
            @RequestParam("pin") String pin,
            @RequestParam("amount") String amount
    ){

        String finalUrl = URL_SECURE + "checkvaliditytransaction";

        String encryptedPass = MySHA.passToSHA(pin, cryptPassType);
        String signature = MySHA.generateSign(encryptedPass, amount, cryptSignType);

        HttpHeaders headers = new HttpHeaders();
        headers.set("card-id", card);
        headers.set("amount", amount);
        headers.set("request-sign", signature);

        HttpEntity entity = new HttpEntity("parameters",headers);

        try {
            restTemplate.exchange(finalUrl, GET, entity, String.class);
            return new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity(ex.getStatusCode());
        }
    }

    @GetMapping("/confirme")
    public ResponseEntity ValidatingTransaction(
            @RequestParam("card") String card,
            @RequestParam("pin") String pin,
            @RequestParam("amount") String amount){

        String finalUrl = URL_SECURE + "validatingtransaction";

        String encryptedPass = MySHA.passToSHA(pin, cryptPassType);
        String signature = MySHA.generateSign(encryptedPass, card + amount, cryptSignType);

        HttpHeaders headers = new HttpHeaders();
        headers.set("card-id", card);
        headers.set("amount", amount);
        headers.set("request-sign", signature);

        HttpEntity entity = new HttpEntity("parameters",headers);

        try {
            restTemplate.exchange(finalUrl, GET, entity, String.class);
            return new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity(ex.getStatusCode());
        }
    }

}
