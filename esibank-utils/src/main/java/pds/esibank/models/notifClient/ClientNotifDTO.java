package pds.esibank.models.notifClient;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
@Data
@ToString
public class ClientNotifDTO {
    private int idClientNotif;
    private int idClient;
    private String alerteMessage;


    public ClientNotifDTO(int idClientNotif, int idClient, String alerteMessage) {
        this.idClientNotif = idClientNotif;
        this.idClient = idClient;
        this.alerteMessage = alerteMessage;
    }

    public ClientNotifDTO() {
    }
}
