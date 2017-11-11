package pds.esibank.models.notification;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.net.Socket;
import java.util.Random;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     16:40
 */
@Data
@ToString
public class MobileClient {
    private String token;
    private String id_user;
    private String imei;

    public MobileClient() {
        super();
    }

    public void generateToken() {
        setToken("mypersonneltoken" + Math.random()*1000000);
    }
}
