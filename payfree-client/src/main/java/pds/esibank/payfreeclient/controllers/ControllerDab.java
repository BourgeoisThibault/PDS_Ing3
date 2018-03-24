package pds.esibank.payfreeclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pds.esibank.crypto.MySHA;
import pds.esibank.models.payfree.PfClientDto;

import static org.springframework.http.HttpMethod.GET;

/**
 * @author  BOURGEOIS Thibault
 * Date     23/03/2018
 * Time     11:39
 */

@RestController("/dab")
public class ControllerDab {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.secure}")
    private String URL_SECURE;
    @Value("${crypt.pass.type}")
    private String cryptPassType;
    @Value("${crypt.sign.type}")
    private String cryptSignType;

    @Value("${perso.user}")
    private String myUser;
    @Value("${perso.pass}")
    private String myPass;

    @GetMapping("/check")
    public ResponseEntity CheckValidityCard(
            @RequestParam("card") String card,
            @RequestParam("pin") String pin
    ){

        String finalUrl = URL_SECURE + "example";

        String encryptedPass = MySHA.passToSHA(pin, cryptPassType);
        String signature = MySHA.generateSign(encryptedPass, finalUrl, cryptSignType);

        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", card);
        headers.set("request-sign", signature);

        HttpEntity entity = new HttpEntity("parameters",headers);

        ResponseEntity response = restTemplate.exchange(
                finalUrl, GET, entity, String.class);

        return new ResponseEntity(response.getStatusCode());
    }

    @GetMapping("/validate")
    public ResponseEntity CheckValidityTransaction(
            @RequestParam("card") String card,
            @RequestParam("pin") String pin
    ){

        String finalUrl = URL_SECURE + "example";

        String encryptedPass = MySHA.passToSHA(pin, cryptPassType);
        String signature = MySHA.generateSign(encryptedPass, finalUrl, cryptSignType);

        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id", card);
        headers.set("request-sign", signature);

        HttpEntity entity = new HttpEntity("parameters",headers);

        ResponseEntity response = restTemplate.exchange(
                finalUrl, GET, entity, String.class);

        return new ResponseEntity(response.getStatusCode());
    }

    @GetMapping("/confirme")
    public ResponseEntity ConfirmeTransaction(){
        return new ResponseEntity(HttpStatus.OK);
    }

}
