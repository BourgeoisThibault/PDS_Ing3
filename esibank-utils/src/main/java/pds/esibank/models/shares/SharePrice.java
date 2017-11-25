package pds.esibank.models.shares;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@ToString
public class SharePrice {
    private Share share;
    private BigDecimal price;
    private BigDecimal change;
    private LocalTime time;

    public SharePrice() {
        super();
    }

}
