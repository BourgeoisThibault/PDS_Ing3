package pds.esibank.dataaccess.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by SarahAllouche on 19/03/2018.
 */

@Data
@Entity
@Table(name="Transaction")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id_transaction" )
    private long idTransaction;

    @OneToOne
    @JoinColumn(name="id_customer")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="id_account")
    private Account account;

    @OneToOne
    @JoinColumn( name = "fk_id_region" )
    private Region region;

    @Basic(optional = false)
    @Column( name = "amount" )
    private float amount;

    @Basic(optional = false)
    @Column( name = "date_operation" )
    @Temporal(TemporalType.DATE)
    private Date date_operation;

    @Basic(optional = false)
    @Column( name = "type_payment" )
    private String type_payment;
}
