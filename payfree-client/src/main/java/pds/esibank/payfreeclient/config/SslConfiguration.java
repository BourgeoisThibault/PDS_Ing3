package pds.esibank.payfreeclient.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

/**
 * @author BOURGEOIS Thibault
 * Date     11/03/2018
 * Time     15:19
 */
@Configuration
public class SslConfiguration {

    @Value("${ssl.trust-store-password}")
    private String keyStorePassword;

    @Value("${ssl.trust-store-path}")
    private String trustStorePath;

    @Bean
    RestTemplate restTemplate() throws Exception {

        ClassPathResource classPathResource = new ClassPathResource(trustStorePath);

        SSLContext sslContext = SSLContexts
                .custom()
                .loadTrustMaterial(classPathResource.getFile(),keyStorePassword.toCharArray())
                .build();

        final CloseableHttpClient client = HttpClients
                .custom()
                .setSSLContext(sslContext)
                .build();

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
    }
}