package pds.esibank.it.withdrawal;

import com.google.gson.Gson;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Ca;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pds.esibank.crypto.MySHA;
//import pds.esibank.dataaccess.entities.Account;
import pds.esibank.it.withdrawal.config.SslConfiguration;
import pds.esibank.models.dab.AccountDto;
import pds.esibank.models.dab.CardDto;

import javax.smartcardio.Card;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * @author ABID BUTT USMAN
 * Date     10/05/2018
 * Time     16:28
 */

@Scope("cucumber-glue")
@Component("pds.esibank.it.withdrawal.DabAppPayfree")
public class DabAppPayfree {

    @Autowired
    private RestTemplate restTemplate;

    private String _URI;
    private String _card;
    private String _pin;
    private String _amount;

    private ResponseEntity<String> _RESPONSE;
    private ResponseEntity<String> _RESPONSE2;


    private String _URL_DATABASE;

//    private static Account _account;

    private String _Host;
    private int _Port;
    private Socket _Socket;

    private PrintWriter writer = null;
    private BufferedInputStream reader = null;


    private String _ID_ACCOUNT;
    private String _ID_CARD;

    private String _DATABASE_URL = "http://ws.esibank.inside.esiag.info/";
//private String _DATABASE_URL = "http://localhost:8082/";

    @Given("The REST web service at \"(.+?)\"")
    public void setCardCheckingVariables(final String uri) {
        _URI = uri;
    }



    @When("Try to get a card checking \"(.+?)\"")
    public void postMethodForCardChecking(String moreUri) throws Exception {
        final SslConfiguration s = new SslConfiguration();

        restTemplate = s.restTemplate();

        String finalUrl = _URI + moreUri;
        String encryptedPass = MySHA.passToSHA(_pin, "SHA-512");
        String signature = MySHA.generateSign(encryptedPass, "", "HmacSHA512");

        HttpHeaders headers = new HttpHeaders();
        headers.set("card-id", _card);
        headers.set("request-sign", signature);

        HttpEntity entity = new HttpEntity("parameters",headers);
        try {
            restTemplate.exchange(finalUrl, GET, entity, String.class);

            _RESPONSE = new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            _RESPONSE =  new ResponseEntity(ex.getStatusCode());
        }
        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);

    }


    @Then("Status code is OK")
    public void statusCodeShouldBe200() {
        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);
    }

    @Then("Status code is KO")
    public void statusCodeShouldBe404() {
        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Then("Status code is unauthorized")
    public void statusCodeShouldBe401() {
        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.UNAUTHORIZED);
    }


    @Given("The REST web service2 at \"(.+?)\" with amount \"(.+?)\"")
    public void setCardValidityVariables(final String uri, final String amount) {
        _URI = uri;
        _amount = amount;
    }

    @When("Try to get a card and amount confirmation transaction \"(.+?)\"")
    public void postMethodForTransactionConfirmation(String moreUri) throws Exception {
        final SslConfiguration s = new SslConfiguration();
        restTemplate = s.restTemplate();

        String finalUrl = _URI + moreUri;
        String encryptedPass = MySHA.passToSHA(_pin, "SHA-512");
        String signature = MySHA.generateSign(encryptedPass, _card + _amount, "HmacSHA512");

        HttpHeaders headers = new HttpHeaders();
        headers.set("card-id", _card);
        headers.set("amount", _amount);
        headers.set("request-sign", signature);

        HttpEntity entity = new HttpEntity("parameters",headers);
        try {
            restTemplate.exchange(finalUrl, GET, entity, String.class);

            _RESPONSE = new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            _RESPONSE =  new ResponseEntity(ex.getStatusCode());
        }

//        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);

    }

    @When("Try to get a card and amount validation transaction \"(.+?)\"")
    public void postMethodForTransactionValidation(String moreUri) throws Exception {
        final SslConfiguration s = new SslConfiguration();
        restTemplate = s.restTemplate();

        String finalUrl = _URI + moreUri;
        String encryptedPass = MySHA.passToSHA(_pin, "SHA-512");
        String signature = MySHA.generateSign(encryptedPass, _amount, "HmacSHA512");

        HttpHeaders headers = new HttpHeaders();
        headers.set("card-id", _card);
        headers.set("amount", _amount);
        headers.set("request-sign", signature);

        HttpEntity entity = new HttpEntity("parameters",headers);
        try {
            restTemplate.exchange(finalUrl, GET, entity, String.class);

            _RESPONSE = new ResponseEntity(HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            _RESPONSE =  new ResponseEntity(ex.getStatusCode());
        }

//        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);

    }

    @And("create account with sold \"(.+?)\"")
    public void shouldCreateValidAccount(String sold) {
        final RestTemplate restTemplate = new RestTemplate();
        AccountDto a =
                AccountDto.builder()
                .amount(sold)
                .build();
        String finalUrl = _DATABASE_URL+"createAccount";
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        AccountDto ad=null;
        try {
            _RESPONSE = restTemplate.postForEntity(finalUrl, a, String.class);
            ad = new Gson().fromJson(_RESPONSE.getBody(), AccountDto.class);
        } catch (HttpClientErrorException ex) {
            _RESPONSE = restTemplate.postForEntity(finalUrl, a, String.class);
        }
        _ID_ACCOUNT = ad.getAccount_id();


        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);
        Assert.assertEquals(Float.valueOf(ad.getAmount()), Float.valueOf(sold));
    }

    @And("set cardnum at \"(.+?)\"")
    public void shouldsetCardnum(final String card) {
        _card = card;
    }

        @And("create card with cardnum \"(.+?)\" and pin \"(.+?)\"")
    public void shouldCreateValidCard(final String card, final String pin) {
        _card = card;
        _pin = pin;

        final RestTemplate restTemplate = new RestTemplate();
        CardDto cardDto = new CardDto();
//                CardDto.builder()
//                        .cardNum(_card)
//                        .cardPass(_pin)
//                        .build();
        cardDto.setCardNum(_card);
        cardDto.setCardPass(_pin);
        String finalUrl = _DATABASE_URL+"createCard";
//        HttpEntity entity = new HttpEntity(cardDto);
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        CardDto cd=null;
        try {
            _RESPONSE = restTemplate.postForEntity(finalUrl, cardDto, String.class);
            cd = new Gson().fromJson(_RESPONSE.getBody(), CardDto.class);
        } catch (HttpClientErrorException ex) {
            _RESPONSE = restTemplate.postForEntity(finalUrl, cardDto, String.class);
        }
        _ID_CARD = String.valueOf(cd.getCard_id());


        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);
        Assert.assertEquals(cd.getCardNum(), _card);
    }


    @And("remove link Account to card")
    public void shouldDeleteLink() {
        final RestTemplate restTemplate = new RestTemplate();
        String finalUrl = _DATABASE_URL+"deleteLink/"+_card;
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        String cd=null;
        try {
            _RESPONSE = restTemplate.getForEntity(finalUrl, String.class);
            cd = new Gson().fromJson(_RESPONSE.getBody(), String.class);
        } catch (HttpClientErrorException ex) {
            _RESPONSE = restTemplate.getForEntity(finalUrl, String.class);
        }
        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);
    }


    @And("remove account")
    public void shouldDeleteAccount() {
        final RestTemplate restTemplate = new RestTemplate();
        String finalUrl = _DATABASE_URL+"deleteAccount/"+_ID_ACCOUNT;
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        String cd=null;
        try {
            _RESPONSE = restTemplate.getForEntity(finalUrl, String.class);
            cd = new Gson().fromJson(_RESPONSE.getBody(), String.class);
        } catch (HttpClientErrorException ex) {
            _RESPONSE = restTemplate.getForEntity(finalUrl, String.class);
        }
        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);
    }



    @And("remove card")
    public void shouldDeleteCard() {
        final RestTemplate restTemplate = new RestTemplate();
        String finalUrl = _DATABASE_URL+"deleteCard/"+ _ID_CARD;
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        String cd=null;
        try {
            _RESPONSE = restTemplate.getForEntity(finalUrl, String.class);
            cd = new Gson().fromJson(_RESPONSE.getBody(), String.class);
        } catch (HttpClientErrorException ex) {
            _RESPONSE = restTemplate.getForEntity(finalUrl, String.class);
        }
        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);
        _RESPONSE = null;
    }


}
