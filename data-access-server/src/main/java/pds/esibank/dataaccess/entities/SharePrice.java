package pds.esibank.dataaccess.entities;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="share_prices")
public class SharePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "share_price_id" )
    private int sharePriceId;

    @Column( name = "price" )
    private BigDecimal price;

    @Column( name = "change_pourcent" )
    private BigDecimal change;

    @Column( name = "change_time" )
    private Date time;

    @ManyToOne
    private Share share;

    public SharePrice()
    {
        super();
    }
}
