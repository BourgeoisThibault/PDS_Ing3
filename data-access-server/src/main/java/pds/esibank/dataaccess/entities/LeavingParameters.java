package pds.esibank.dataaccess.entities;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="leaving_parameters")
public class LeavingParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "lp_id" )
    private int lpId;

    @Column( name = "lp_y" )
    private BigDecimal lpY;

    @Column( name = "balance" )
    private BigDecimal balance;

    @Column( name = "made_transactions" )
    private int transactions;

    @Column( name = "made_connections" )
    private int connections;

    @Column( name = "made_transfers" )
    private int transfers;

    @Column( name = "charge" )
    private BigDecimal charge;

    @Column( name = "customer_appreciation" )
    private int c_appreciation;

    public LeavingParameters()
    {
        super();
    }
}
