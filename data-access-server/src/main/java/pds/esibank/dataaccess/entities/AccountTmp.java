package pds.esibank.dataaccess.entities;

/**
 * Created by SarahAllouche on 19/03/2018.
 */

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="AccountTmp")
public class AccountTmp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_account" )
    private Long id_account;

    @OneToOne
    @JoinColumn(name="id_customer")
    private CustomerTmp customer;

    @OneToOne
    @JoinColumn(name="id_cardtype")
    private TypeCard typeCard;

    @Basic(optional = false)
    @Column( name = "sold" )
    private float sold;


}
