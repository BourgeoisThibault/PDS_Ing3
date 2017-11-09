package pds.esibank.models.notification;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.net.Socket;

/**
 * @author BOURGEOIS Thibault
 * Date     09/11/2017
 * Time     16:40
 */
@Data
@Builder
@ToString
public class MobileClient {
    private Socket socket;
    private String token;
    private String name;
}
