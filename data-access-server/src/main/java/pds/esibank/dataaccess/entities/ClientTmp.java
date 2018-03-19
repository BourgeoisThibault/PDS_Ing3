package pds.esibank.dataaccess.entities;

/**
 * Created by SarahAllouche on 19/03/2018.
 */

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="ClientTmp")
public class ClientTmp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_Customer" )
    private Long id_Customer;

    @Column( name = "adress" )
    private String adress;

    @Basic(optional = true)
    @Column( name = "phone" )
    private String phone;

    @Column( name = "id_Status" )
    private long id_Status;

    @Column( name = "id_CardType" )
    private long id_CardType;


}
