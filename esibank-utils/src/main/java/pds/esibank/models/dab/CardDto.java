package pds.esibank.models.dab;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CardDto {
        private Long card_id;
        private String cardNum;
        private String cardPass;
}
