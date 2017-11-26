package pds.esibank.it.notification;

import com.google.gson.Gson;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pds.esibank.models.notification.MobileToken;

import java.io.IOException;

/**
 * @author BOURGEOIS Thibault
 * Date     24/11/2017
 * Time     16:29
 */
@Scope("cucumber-glue")
@Component("pds.esibank.it.notification.NotificationToken")
public class NotificationToken {

    private String _URI;
    private ResponseEntity<String> _RESPONSE;
    private static MobileToken _MobileToken;
    private MobileToken _TempMobileToken;

    @Given("The REST service at \"(.+?)\"")
    public void setUriOfNotification(final String uri) {
        _URI = uri;
    }

    @When("Try to post MobileToken object at \"(.+?)\" with new set \"(.+?)\"")
    public void getResponseFromUriShouldReturnCode200(String moreUri, Boolean isNew) throws IOException {
        final RestTemplate restTemplate = new RestTemplate();

        if (isNew)
            _MobileToken = new MobileToken();

        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        _RESPONSE = restTemplate.postForEntity(_URI + moreUri, _MobileToken, String.class);
    }

    @Then("Status code is OK")
    public void statusCodeShouldBe200() {
        Assert.assertTrue(_RESPONSE.getStatusCode() == HttpStatus.OK);
    }

    @And("Convert response to MobileToken object")
    public void responseShouldBeConvertedToMobileTokenObject() {
        _MobileToken = new Gson().fromJson(_RESPONSE.getBody(), MobileToken.class);
    }

    @And("Receive random token with good pattern")
    public void tokenShouldHaveGoodPattern() {
        Assert.assertTrue(_MobileToken.getToken().matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));
    }

    @And("Receive uid anonymous")
    public void uidShouldBeNotNullAndEqual0() {
        Assert.assertFalse(_MobileToken.getUid().equals(0));
    }

    @And("Save old MobileToken for check")
    public void saveOldMobileToken() {
        _TempMobileToken = _MobileToken;
    }

    @And("Receive same token than previous scenario")
    public void newTokenShouldBeSameThanOlder() {
        Assert.assertTrue(_MobileToken.getToken().equals(_TempMobileToken.getToken()));
    }

    @And("Receive uid is always anonymous")
    public void newUidShouldBeSameThanOlder() {
        Assert.assertTrue(_MobileToken.getUid().equals(_TempMobileToken.getUid()));
    }

}
