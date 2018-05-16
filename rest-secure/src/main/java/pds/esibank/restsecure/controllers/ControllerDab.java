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
import pds.esibank.models.elastik.SrvInfoSign;
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.restsecure.ElastikLog;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

            if (MySHA.checkSign(cardDto.getCardPass(),"",cryptSignType,requestSign)){
                ElastikLog.sendLog(elastic_enpoint+"payfree-server/validesign", new SrvInfoSign(true, cardId));
                return new ResponseEntity(HttpStatus.OK);
            }else{
                ElastikLog.sendLog(elastic_enpoint+"payfree-server/validesign", new SrvInfoSign(false, cardId));
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
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

                ElastikLog.sendLog(elastic_enpoint+"payfree-server/validesign", new SrvInfoSign(true,cardId));

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
            }else{
                ElastikLog.sendLog(elastic_enpoint+"payfree-server/validesign", new SrvInfoSign(false,cardId));
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
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

                ElastikLog.sendLog(elastic_enpoint+"payfree-server/validesign", new SrvInfoSign(true,cardId));

                String uidCustommer = restTemplate.getForObject(URL_DATABASE + "validatingtransaction?card=" + cardId + "&amount=" + amount, String.class);

                NotificationModel notificationModel = new NotificationModel();
                notificationModel.setTitle("Retrait sur DAB");
                notificationModel.setMessage("Retrait de " + amount + " € effectué.");
                notificationModel.setTarget("tbd");

                String myUri = "http://notification.esibank.inside.esiag.info/send/" + uidCustommer;

                restTemplate.postForEntity(myUri,notificationModel,String.class);
                notificationModel.setTarget(uidCustommer);
                notificationModel.setAmount(Integer.valueOf(amount));

                String formatDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                notificationModel.setDate(new Date());
                ElastikLog.sendLog(elastic_enpoint+"dab-confirme/doc", notificationModel);

                return new ResponseEntity(HttpStatus.OK);
            }else {
                ElastikLog.sendLog(elastic_enpoint+"payfree-server/validesign", new SrvInfoSign(false,cardId));
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity(ex.getStatusCode());
        }
    }

}
