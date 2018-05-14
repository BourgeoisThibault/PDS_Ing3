package pds.esibank.restsecure.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pds.esibank.crypto.MySHA;
import pds.esibank.models.dab.CardDto;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.models.payfree.PfClientDto;

import javax.ws.rs.core.MediaType;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.HttpMethod.GET;

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
    @Value("${elastic.endpoint}")
    private String elastic_enpoint;
    @Value("${url.database}")
    private String URL_DATABASE;

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/checkcard")
    public ResponseEntity CheckCardValidity(
            @RequestHeader(value = "card-id") String cardId,
            @RequestHeader(value = "request-sign") String requestSign) {

        try {
            CardDto cardDto = restTemplate.getForObject(URL_DATABASE + "card/" + cardId,CardDto.class);

            if (MySHA.checkSign(cardDto.getCardPass(),"",cryptSignType,requestSign))
                return new ResponseEntity(HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        } catch (HttpClientErrorException ex) {
            return new ResponseEntity(ex.getStatusCode());
        }

    }

    @GetMapping(value = "/checkvaliditytransaction")
    public ResponseEntity CheckTransactionValidity(
            @RequestHeader(value = "card-id") String cardId,
            @RequestHeader(value = "amount") String amount,
            @RequestHeader(value = "request-sign") String requestSign) {

        try {
            CardDto cardDto = restTemplate.getForObject(URL_DATABASE + "card/" + cardId,CardDto.class);

            if (MySHA.checkSign(cardDto.getCardPass(),amount,cryptSignType,requestSign)) {

                String finalUrl = URL_DATABASE + "checktransaction?card=" + cardId + "&amount=" + amount;

                HttpHeaders headers = new HttpHeaders();
                headers.set("card", cardId);
                headers.set("amount", amount);
                HttpEntity entity = new HttpEntity("parameters",headers);
                try {
                    restTemplate.exchange(finalUrl, GET, entity, String.class);
                    return new ResponseEntity(HttpStatus.OK);
                } catch (HttpClientErrorException ex) {
                    return new ResponseEntity(ex.getStatusCode());
                }
            }else
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity(ex.getStatusCode());
        } catch (Exception es) {
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping(value = "/validatingtransaction")
    public ResponseEntity confirmTransaction(
            @RequestHeader(value = "card-id") String cardId,
            @RequestHeader(value = "amount") String amount,
            @RequestHeader(value = "request-sign") String requestSign) {

        try {
            CardDto cardDto = restTemplate.getForObject(URL_DATABASE + "card/" + cardId,CardDto.class);

            if (MySHA.checkSign(cardDto.getCardPass(),cardId + amount,cryptSignType,requestSign)) {
                String uidCustommer = restTemplate.getForObject(URL_DATABASE + "validatingtransaction?card=" + cardId + "&amount=" + amount, String.class);

                NotificationModel notificationModel = new NotificationModel();
                notificationModel.setTitle("Retrait sur DAB");
                notificationModel.setMessage("Retrait de " + amount + " effectué.");
                notificationModel.setTarget("tbd");

                sendLogToElasticEngine(notificationModel);
                String myUri = "http://notification.esibank.inside.esiag.info/send/" + uidCustommer;

                restTemplate.postForEntity(myUri,notificationModel,String.class);

                return new ResponseEntity(HttpStatus.OK);
            }else
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity(ex.getStatusCode());
        }
    }

    public void sendLogToElasticEngine(NotificationModel notificationModel){
        String formatDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        notificationModel.setDate(formatDateTime);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(elastic_enpoint,notificationModel,String.class);
    }

}
