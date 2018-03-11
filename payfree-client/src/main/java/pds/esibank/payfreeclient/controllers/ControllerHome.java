package pds.esibank.payfreeclient.controllers;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pds.esibank.payfreeclient.config.SslConfiguration;

import javax.net.ssl.SSLContext;
import java.io.IOException;

@RestController
public class ControllerHome {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.secure}")
    private String URL_SECURE;

    @GetMapping("/")
    public String home(){
        return "<H1>PAYFREE ESIBANK HOME NEW</H1>";
    }

    @GetMapping("/homesecure")
    public String test() throws Exception {
        return restTemplate.getForObject(URL_SECURE,String.class);
    }

}

