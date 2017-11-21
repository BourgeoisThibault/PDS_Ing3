package pds.esibank.models.notification;

import lombok.Data;
import lombok.ToString;

/**
 * @author BOURGEOIS Thibault
 * Date     21/11/2017
 * Time     23:02
 */
@Data
@ToString
public class MobileToken {
    private String token;
    private String uid;

    public MobileToken() {
        super();
    }
}
