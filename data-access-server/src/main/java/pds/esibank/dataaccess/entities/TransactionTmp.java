package pds.esibank.dataaccess.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by SarahAllouche on 19/03/2018.
 */

@Data
@Entity
@Table(name="TransactionTmp")
public class TransactionTmp {

    /*
    id_Transaction INTEGER AUTO_INCREMENT PRIMARY KEY,
id_Customer integer,
id_Compte integer,
date_Operation datetime,
amount float null,
type_payment varchar(20),
CONSTRAINT fk_Customer_Transaction FOREIGN KEY (id_Customer) REFERENCES client(id_Customer),
CONSTRAINT fk_Compte_Transaction FOREIGN KEY (id_Compte) REFERENCES Compte(id_Compte)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id_transaction" )
    private long idTransaction;

    @Column( name = "id_Customer" )
    private Long id_Customer;

    @Column( name = "id_Compte" )
    private Long id_Compte;

    @Column( name = "amount" )
    private float amount;

    @Basic(optional = true)
    @Column( name = "date_transaction" )
    @Temporal(TemporalType.DATE)
    private Date dateTransaction;

    @Column( name = "type_payment" )
    private String type_payment;
}
