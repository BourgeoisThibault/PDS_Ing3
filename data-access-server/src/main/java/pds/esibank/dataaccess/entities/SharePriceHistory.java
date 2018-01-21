package pds.esibank.dataaccess.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by maria on 19/12/2017.
 */

@Data
@Entity
@Table(name="share_price_history")
public class SharePriceHistory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column( name = "share_price_history_id" )
        private int sharePriceHistoryId;

        @Column( name = "price" )
        private BigDecimal price;

        @Column( name = "change_pourcent" )
        private BigDecimal change;

        @Column( name = "change_time" )
        private Date time;

        @ManyToOne
        private Share share;

        public SharePriceHistory()
        {
            super();
        }
    }
