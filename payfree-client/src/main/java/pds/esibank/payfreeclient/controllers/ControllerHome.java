package pds.esibank.payfreeclient.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pds.esibank.crypto.MySHA;
import pds.esibank.models.payfree.PfClientDto;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import static org.springframework.http.HttpMethod.GET;

@RestController
public class ControllerHome {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

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

    @GetMapping("/")
    public String home(){
        return "Home";
    }

    @GetMapping("/homesecure")
    public String homeSecure() throws Exception {
        return restTemplate.getForObject(URL_SECURE,String.class);
    }

    @GetMapping(value = "/testsign",produces = MediaType.APPLICATION_JSON)
    public PfClientDto testsign() throws Exception {

        String encryptedPass = MySHA.passToSHA("mypass", cryptPassType);
        String signature = MySHA.generateSign(encryptedPass,"",cryptSignType);

        HttpHeaders headers = new HttpHeaders();
        headers.set("user-id","ncna");
        headers.set("request-sign",signature);

        HttpEntity entity = new HttpEntity("parameters",headers);

        ResponseEntity<PfClientDto> response = restTemplate.exchange(
                URL_SECURE + "example", GET, entity, PfClientDto.class);

        PfClientDto pfClientDto = response.getBody();

        return pfClientDto;
    }

}

