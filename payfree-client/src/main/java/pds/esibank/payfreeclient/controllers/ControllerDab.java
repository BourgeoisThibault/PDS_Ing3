package pds.esibank.payfreeclient.controllers;

import com.google.gson.Gson;
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
import pds.esibank.models.elastik.CltInfo;
import pds.esibank.models.elastik.LogRequest;
import pds.esibank.models.payfree.PfClientDto;
import pds.esibank.payfreeclient.config.ElastikLog;

import java.util.Date;

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

    @Value("${elastik.link.client}")
    private String elastikLink;

    @GetMapping("/check")
    public ResponseEntity CheckValidityCard(
            @RequestParam("card") String card,
            @RequestParam("pin") String pin
    ){

        ElastikLog.sendLog(elastikLink+"simplelog", new CltInfo(new Date(),"CHECK"));

        String finalUrl = URL_SECURE + "checkcard";

        String encryptedPass = MySHA.passToSHA(pin, cryptPassType);
        String signature = MySHA.generateSign(encryptedPass, "", cryptSignType);

        HttpHeaders headers = new HttpHeaders();
        headers.set("card-id", card);
        headers.set("request-sign", signature);

        HttpEntity entity = new HttpEntity("parameters",headers);

        try {
            restTemplate.exchange(finalUrl, GET, entity, String.class);
            ElastikLog.sendLog(elastikLink+"statuscode", new LogRequest(HttpStatus.OK.toString()));
            return new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            ElastikLog.sendLog(elastikLink+"statuscode", new LogRequest(ex.getStatusCode().toString()));
            return new ResponseEntity(ex.getStatusCode());
        }
    }

    @GetMapping("/validate")
    public ResponseEntity CheckValidityTransaction(
            @RequestParam("card") String card,
            @RequestParam("pin") String pin,
            @RequestParam("amount") String amount
    ){

        ElastikLog.sendLog(elastikLink+"simplelog", new CltInfo(new Date(),"VALIDATE"));

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
            ElastikLog.sendLog(elastikLink+"statuscode", new LogRequest(HttpStatus.OK.toString()));
            return new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            ElastikLog.sendLog(elastikLink+"statuscode", new LogRequest(ex.getStatusCode().toString()));
            return new ResponseEntity(ex.getStatusCode());
        }
    }

    @GetMapping("/confirme")
    public ResponseEntity ValidatingTransaction(
            @RequestParam("card") String card,
            @RequestParam("pin") String pin,
            @RequestParam("amount") String amount){

        ElastikLog.sendLog(elastikLink+"simplelog", new CltInfo(new Date(),"CONFIRME"));

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
            ElastikLog.sendLog(elastikLink+"statuscode", new LogRequest(HttpStatus.OK.toString()));
            return new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            ElastikLog.sendLog(elastikLink+"statuscode", new LogRequest(ex.getStatusCode().toString()));
            return new ResponseEntity(ex.getStatusCode());
        }
    }

}
