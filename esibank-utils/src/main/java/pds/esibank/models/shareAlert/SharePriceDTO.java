package pds.esibank.models.shareAlert;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class SharePriceDTO {


    private int sharePriceId;

    private BigDecimal price;

    private BigDecimal change;

     private Date time;

    public int getSharePriceId() {
        return sharePriceId;
    }

    public void setSharePriceId(int sharePriceId) {
        this.sharePriceId = sharePriceId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
