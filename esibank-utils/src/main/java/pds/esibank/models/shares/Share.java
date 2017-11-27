package pds.esibank.models.shares;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Share {
    private String name;
    private String symbol;
    private String currency;

    public Share() {
        super();
    }

    public Share(String name, String symbol, String currency) {
        this.name = name;
        this.symbol = symbol;
        this.currency = currency;
    }
}
