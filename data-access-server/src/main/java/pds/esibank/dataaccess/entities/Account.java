package pds.esibank.dataaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Created by SarahAllouche on 21/11/2017.
 */

@Entity
@Table(name="Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id_account" )
    private int idAccount;

    @Column( name = "type_account" )
    private String typeAccount;

    @Column( name = "amount" )
    private Float amount;

    @Column( name = "id_client" )
    private String idClient;


    public Account()
    {
        super();
    }

    public int getIdAccount() {
        return idAccount;
    }
    public String getTypeAccount() {
        return typeAccount;
    }
    public Float getAmount() {
        return amount;
    }
    public String getIdClient() {
        return idClient;
    }

    public void setIdAccount(final int idAccount) {
        this.idAccount = idAccount;
    }
    public void setTypeAccount(final String typeAccount) {
        this.typeAccount = typeAccount;
    }
    public void setAmount(final Float amount) {
        this.amount = amount;
    }
    public void setIdClient(final String idClient) {
        this.idClient = idClient;
    }

}

