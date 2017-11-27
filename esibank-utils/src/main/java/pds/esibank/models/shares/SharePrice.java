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

    public SharePrice(String name, String symbol, String currency, BigDecimal price, BigDecimal change, LocalDateTime dateTTime) {
        this.share = new Share(name, symbol, currency);
        this.price = price;
        this.change = change;
        this.dateTTime = dateTTime;
    }
}
