package pds.esibank.models.notification;

import lombok.Data;
import lombok.ToString;

/**
 * @author BOURGEOIS Thibault
 * Date     11/11/2017
 * Time     00:42
 */
@Data
@ToString
public class NotificationModel {
    private String title;
    private String message;
    private String target;
    private String date;

    public NotificationModel() {
        super();
    }
}
