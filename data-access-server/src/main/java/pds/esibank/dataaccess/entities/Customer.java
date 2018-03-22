package pds.esibank.dataaccess.entities;

/**
 * Created by SarahAllouche on 19/03/2018.
 */

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_Customer" )
    private Long id_Customer;

    @OneToOne
    @JoinColumn(name="id_status")
    private Status status;

    @Basic(optional = true)
    @Column( name = "address" )
    private String address;

    @Basic(optional = true)
    @Column( name = "phone" )
    private String phone;

}
