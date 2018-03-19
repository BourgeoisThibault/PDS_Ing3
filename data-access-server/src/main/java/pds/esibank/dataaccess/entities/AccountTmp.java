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
    @Column( name = "account_id" )
    private Long account_id;

    @Column( name = "id_customer" )
    private long id_customer;

    @Column( name = "sold" )
    private long sold;
}
