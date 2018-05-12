package pds.esibank.models.dab;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class CardDto {
        private Long card_id;
        private String cardNum;
        private String cardPass;
}
