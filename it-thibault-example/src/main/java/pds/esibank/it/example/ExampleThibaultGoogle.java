package pds.esibank.it.example;



import org.junit.Assert;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author BOURGEOIS Thibault
 * Date     23/11/2017
 * Time     17:08
 */
@Scope("cucumber-glue")
@Component("pds.esibank.testing.TestingGet")
public class ExampleThibaultGoogle {

    private ResponseEntity<String> _RESPONSE;

    @When("try to get page at \"(.+?)\"")
    public void getResponseFromUri(final String uri) {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(uri, String.class);

        _RESPONSE = responseEntity;

        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Then("body value is not null")
    public void bodyShouldBeNotNull() {
        Assert.assertFalse(_RESPONSE.getBody().equals(null));
    }

}
