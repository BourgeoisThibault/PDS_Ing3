package pds.esibank.models.shares;

import lombok.Data;
import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@Builder
public class SharePrice {
    private Share share;
    private BigDecimal price;
    private BigDecimal change;
    private Date time;

    public SharePrice() {
        super();
    }

    public SharePrice(Share share, BigDecimal price, BigDecimal change, Date time) {
        this.share = share;
        this.price = price;
        this.change = change;
        this.time = time;
    }
}
