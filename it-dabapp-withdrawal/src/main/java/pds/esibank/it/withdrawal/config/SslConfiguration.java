package pds.esibank.it.withdrawal.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

/**
 * @author BOURGEOIS Thibault
 * Date     11/03/2018
 * Time     15:19
 */
@Configuration
public class SslConfiguration {

//    @Value("${ssl.trust-store-password}")
    private String keyStorePassword;

//    @Value("${ssl.trust-store-path}")
    private Resource trustStorePath;


    @Bean
    public RestTemplate restTemplate() throws Exception {
        keyStorePassword = "esibank";
//        trustStorePath = new UrlResource("classpath:ssl/trustEsibank");

        SSLContext sslContext = SSLContexts
                .custom()
                .loadTrustMaterial(ResourceUtils.getFile("classpath:ssl/trustEsibank"),keyStorePassword.toCharArray())
                .build();

        final CloseableHttpClient client = HttpClients
                .custom()
                .setSSLContext(sslContext)
                .build();

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
    }
}