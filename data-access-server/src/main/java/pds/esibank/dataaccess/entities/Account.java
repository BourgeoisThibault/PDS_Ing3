package pds.esibank.dataaccess.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity(name = "account")
public class Account implements  java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "account_id" )
    private Long account_id;


    @Column( name = "amount" )
    private long amount;


    //@OneToMany(mappedBy = "account")
    //private Set<Card> card ;


    @Column( name = "card_id_fk" )
    private long card_id_fk;

}
