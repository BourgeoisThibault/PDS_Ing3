package pds.esibank.models.shareAlert;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShareDTO {


    private int shareId;

    private String name;

    private String symbol;

    private String currency;

    private List<SharePriceDTO> sharePrices ;


    public ShareDTO(int shareId, String name, String symbol, String currency, List<SharePriceDTO> sharePrices) {
        this.shareId = shareId;
        this.name = name;
        this.symbol = symbol;
        this.currency = currency;
        this.sharePrices = sharePrices;
    }

    public ShareDTO() {
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<SharePriceDTO> getSharePrices() {
        return sharePrices;
    }

    public void setSharePrices(List<SharePriceDTO> sharePrices) {
        this.sharePrices = sharePrices;
    }
}
