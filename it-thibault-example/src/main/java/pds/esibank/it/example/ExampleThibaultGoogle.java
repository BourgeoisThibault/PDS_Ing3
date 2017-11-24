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

import org.apache.log4j.Logger;

/**
 * @author BOURGEOIS Thibault
 * Date     23/11/2017
 * Time     17:08
 */
@Scope("cucumber-glue")
@Component("pds.esibank.testing.TestingGet")
public class ExampleThibaultGoogle {

    private Logger logger = Logger.getLogger(ExampleThibaultGoogle.class);

    private ResponseEntity<String> _RESPONSE;

    @When("try to get page at \"(.+?)\"")
    public void getResponseFromUriShouldReturnCode200(final String uri) {
        logger.info("Declare RestTemplate object");
        final RestTemplate restTemplate = new RestTemplate();

        logger.info("Execute request to " + uri);
        _RESPONSE = restTemplate.getForEntity(uri, String.class);

        logger.info("Execute assert for check HttpStatus - " + _RESPONSE.getStatusCode());
        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);
    }

    @Then("body value is not null")
    public void bodyShouldBeNotNull() {
        logger.info("Execute assert for check if body is not null");
        Assert.assertFalse(_RESPONSE.getBody().equals(null));
    }

}
