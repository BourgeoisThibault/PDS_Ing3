package pds.esibank.models.notification;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.DatatypeConverter;
import java.net.Socket;
import java.util.Base64;
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
        String str = new String(DatatypeConverter.parseBase64Binary(imei + id_user));
        token = DatatypeConverter.printBase64Binary(str.getBytes());
    }
}
