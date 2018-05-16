package pds.esibank.models.elastik;

import lombok.Data;
import lombok.ToString;

/**
 * @author BOURGEOIS Thibault
 * Date     16/05/2018
 * Time     12:33
 */
@Data
@ToString
public class LogRequest {
    public LogRequest(String statusReturn) {
        this.statusReturn = statusReturn;
    }

    private String statusReturn;
}
