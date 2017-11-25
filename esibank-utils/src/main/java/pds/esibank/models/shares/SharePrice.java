package pds.esibank.models.shares;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class SharePrice {
    private Share share;
    private BigDecimal price;
    private BigDecimal change;
    private LocalDateTime dateTTime;

    public SharePrice() {
        super();
    }

}
