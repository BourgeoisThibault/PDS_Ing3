package pds.esibank.payfreeclient.controllers;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;

@RestController
public class ControllerHome {

    @GetMapping("/")
    public String home(){
        return "<H1>PAYFREE ESIBANK HOME NEW</H1>";
    }

    /**
     ** Add custom SSL TrustStore because Let's Encrypt root is not in all JDK stores yet.
     ** This is the cacerts from JDK7.0_79 and manually added root isrgrootx1.der
     */
    @Bean
    public static RestTemplate restTemplate() throws Exception {

        String mypass = "esibank";

        //ClassPathResource classPathResource = new ClassPathResource("ssl/client-truststore.jks");
        ClassPathResource classPathResource = new ClassPathResource("ssl/trustEsibank");
        SSLContext sslContext = SSLContexts
                .custom()
                .loadTrustMaterial(classPathResource.getFile(),mypass.toCharArray())
                .build();
        final CloseableHttpClient client = HttpClients
                .custom()
                .setSSLContext(sslContext)
                .build();
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
    }

    private static final String URI_DATA_ACCESS = "https://ws.esibank.inside.esiag.info/";
    //private static final String URI_DATA_ACCESS = "https://localhost:1234/";
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/test")
    public String test() throws Exception {
        String toto = restTemplate().getForObject(URI_DATA_ACCESS,String.class);
        return toto;
    }

}

