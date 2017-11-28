package pds.esibank.models.shares;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class SharePrice {
    private Share share;
    private BigDecimal price;
    private BigDecimal change;
    private Date time;

    public SharePrice() {
        super();
    }

    public SharePrice(String name, String symbol, String currency, BigDecimal price, BigDecimal change, Date time) {
        this.share = new Share(name, symbol, currency);
        this.price = price;
        this.change = change;
        this.time = time;
    }
}
