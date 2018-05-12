package pds.esibank.models.dab;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CardDto {
        private Long card_id;
        private String cardNum;

        public Long getCard_id() {
                return card_id;
        }

        public void setCard_id(Long card_id) {
                this.card_id = card_id;
        }

        public String getCardNum() {
                return cardNum;
        }

        public void setCardNum(String cardNum) {
                this.cardNum = cardNum;
        }

        public String getCardPass() {
                return cardPass;
        }

        public void setCardPass(String cardPass) {
                this.cardPass = cardPass;
        }

        private String cardPass;

        public CardDto() {
                super();
        }

}
