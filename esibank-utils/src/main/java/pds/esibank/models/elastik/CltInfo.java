package pds.esibank.models.elastik;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author BOURGEOIS Thibault
 * Date     16/05/2018
 * Time     12:32
 */
@Data
@ToString
public class CltInfo {
    private Date date;

    public CltInfo(Date date, String logStep) {
        this.date = date;
        this.logStep = logStep;
    }

    private String logStep;
}
