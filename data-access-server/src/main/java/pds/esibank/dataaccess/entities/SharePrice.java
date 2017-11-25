package pds.esibank.dataaccess.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name="share_prices")
public class SharePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "share_price_id" )
    private int sharePriceId;

    @Column( name = "price" )
    private BigDecimal price;

    @Column( name = "change" )
    private BigDecimal change;

    @Column( name = "time" )
    private LocalTime time;

    @ManyToOne
    private Share share;

    public SharePrice()
    {
        super();
    }

}
