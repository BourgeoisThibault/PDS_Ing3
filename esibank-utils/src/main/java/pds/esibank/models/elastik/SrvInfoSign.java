package pds.esibank.models.elastik;

import lombok.Data;
import lombok.ToString;

/**
 * @author BOURGEOIS Thibault
 * Date     16/05/2018
 * Time     17:33
 */
@Data
@ToString
public class SrvInfoSign {

    public SrvInfoSign(Boolean correctSign, String idCard) {
        this.idCard = idCard;
        this.correctSign = correctSign;
    }

    private String idCard;
    private Boolean correctSign;
}
