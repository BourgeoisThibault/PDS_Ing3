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
import pds.esibank.models.notification.NotificationModel;
import pds.esibank.models.notification.PushNotificationModel;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
    private NotificationModel _Notification;

    private String _Host;
    private int _Port;
    private Socket _Socket;

    private PrintWriter writer = null;
    private BufferedInputStream reader = null;

    @Given("The REST service at \"(.+?)\"")
    public void setUriOfNotification(final String uri) {
        _URI = uri;
    }

    @When("Try to post MobileToken object at \"(.+?)\" with new set \"(.+?)\"")
    public void postMethodForMobileToken(String moreUri, Boolean isNew) throws IOException {
        final RestTemplate restTemplate = new RestTemplate();

        if (isNew) {
            _MobileToken = new MobileToken();
            _MobileToken.setUid("999999");
        }

        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        _RESPONSE = restTemplate.postForEntity(_URI + moreUri, _MobileToken, String.class);
    }

    @When("Try to post Notification object at \"(.+?)\" for uid \"(.+?)\"")
    public void postMethodForNotification(String moreUri, String uid) throws IOException {
        final RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        _RESPONSE = restTemplate.postForEntity(_URI + moreUri + "/" + uid, _Notification, String.class);
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

    @And("Receive uid same")
    public void uidShouldBeNotNullAndEqual0() {
        Assert.assertFalse(_MobileToken.getUid().equals(999999));
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

    @And("Set new notification with title \"(.+?)\" and message \"(.+?)\"")
    public void setNewPushNotificationModel(String title, String message) {
        _Notification = new NotificationModel();
        _Notification.setTitle(title);
        _Notification.setMessage(message);
    }

    @Given("The push host at \"(.+?)\" on port \"(.+?)\"")
    public void setHostAndPort(String host, int port) {
        _Host = host;
        _Port = port;
    }

    @When("Try to create socket to push server")
    public void connectSocketToPushServer() throws IOException {
        _Socket = new Socket(_Host, _Port);

        writer = new PrintWriter(_Socket.getOutputStream(), true);
        reader = new BufferedInputStream(_Socket.getInputStream());

        writer.write(_MobileToken.getToken());
        writer.flush();
    }

    @Then("Receive notification")
    public void socketShouldBeReceiveNotification() throws IOException {
        Boolean isReceive = false;
        int tryCount = 0;

        _Socket.setSoTimeout(5000);

        while (!isReceive && tryCount<25) {

            System.out.println("Reception " + tryCount);

            int stream;
            byte[] b = new byte[4096];
            stream = reader.read(b);
            String response = new String(b, 0, stream);

            if(response.equals("PING")){
                writer.write("PONG");
                writer.flush();
            } else {
                NotificationModel notificationModel = new Gson().fromJson(response, NotificationModel.class);
                _Notification = notificationModel;
                isReceive =true;
            }

            tryCount++;
        }

        Assert.assertTrue(isReceive);

    }

    @And("Check if title equal \"(.+?)\"")
    public void notificationShouldHaveThisTitle(String title) {
        Assert.assertTrue(_Notification.getTitle().equals(title));
    }

    @And("Check if message equal \"(.+?)\"")
    public void notificationShouldHaveThisMessage(String message) {
        Assert.assertTrue(_Notification.getMessage().equals(message));
    }

    @And("Delete all token for uid \"(.+?)\"")
    public void deleteAllTokenAdmin(String uid) {
        final RestTemplate restTemplate = new RestTemplate();
        _RESPONSE = restTemplate.postForEntity(_URI + "admin", null, String.class);
        Assert.assertTrue(_RESPONSE.getStatusCode().equals(HttpStatus.OK));
    }

    @And("Stop listener of push server at \"(.+?)\" on port \"(.+?)\"")
    public void stopListenerPushMom(String host, int port) throws IOException {
        _Socket = new Socket(host, port);

        writer = new PrintWriter(_Socket.getOutputStream(), true);
        reader = new BufferedInputStream(_Socket.getInputStream());

        writer.write("STOP_LISTEN");
        writer.flush();

        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        String response = new String(b, 0, stream);

        Assert.assertTrue(response.equals("STOP_OK") || response.equals("ALREADY_STOP"));

    }

    @And("Start listener of push server at \"(.+?)\" on port \"(.+?)\"")
    public void startListenerPushMom(String host, int port) throws IOException {
        _Socket = new Socket(host, port);

        writer = new PrintWriter(_Socket.getOutputStream(), true);
        reader = new BufferedInputStream(_Socket.getInputStream());

        writer.write("START_LISTEN");
        writer.flush();

        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        String response = new String(b, 0, stream);

        Assert.assertTrue(response.equals("START_OK") || response.equals("ALREADY_START"));

    }


}
