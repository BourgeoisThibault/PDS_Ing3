package pds.esibank.models.notification;

import lombok.Data;
import lombok.ToString;

/**
 * @author BOURGEOIS Thibault
 * Date     21/11/2017
 * Time     17:07
 */
@Data
@ToString
public class PushNotificationModel {
    private String title;
    private String message;
    private String target;
    private String token;

    public PushNotificationModel() {
        super();
    }
}
