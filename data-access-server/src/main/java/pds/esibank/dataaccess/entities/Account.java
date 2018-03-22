package pds.esibank.dataaccess.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "account")
public class Account implements  java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_account" )
    private Long id_account;

    @OneToOne
    @JoinColumn(name="id_customer")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="id_cardtype")
    private TypeCard typeCard;

    @Basic(optional = false)
    @Column( name = "sold" )
    private float sold;

    @Basic(optional = false)
    @Column( name = "card_id_fk" )
    private float card_id_fk;

}
