package Beans;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by SarahAllouche on 19/11/2017.
 */

@Entity
@Table(name="Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id_transaction" )
    private int idTransaction;

    @Basic(optional = true)
    @Column( name = "lastname_crediter" )
    private String lastNameCrediter;

    @Basic(optional = true)
    @Column( name = "firstname_crediter" )
    private String firstNameCrediter;

    @Basic(optional = true)
    @Column( name = "credit_account" )
    private String creditAccount;

    @Basic(optional = true)
    @Column( name = "impacted_bank" )
    private String impactedBank;

    @Basic(optional = true)
    @Column( name = "amount_transaction" )
    private float amountTransaction;

    @Basic(optional = true)
    @Column( name = "lastname_customer" )
    private String lastNameCustomer;

    @Basic(optional = true)
    @Column( name = "firstname_customer" )
    private String firstNameCustomer;

    @Basic(optional = true)
    @Column( name = "debit_account" )
    private String debitAccount;

    @Basic(optional = true)
    @Column( name = "date_transaction" )
    @Temporal(TemporalType.DATE)
    private Date dateTransaction;

    /** Construteur **/
    public Transaction()
    {
        super();
    }

    /** Getters **/
    public int getIdTransaction() {
        return idTransaction;
    }
    public String getLastNameCrediter() {
        return lastNameCrediter;
    }
    public String getFirstNameCrediter() {
        return firstNameCrediter;
    }
    public String getCreditAccount() {
        return creditAccount;
    }
    public String getImpactedbank() {
        return impactedBank;
    }
    public float getAmountTransaction() {
        return amountTransaction;
    }
    public String getLastNameCustomer() {
        return lastNameCustomer;
    }
    public String getFirstNameCustomer() {
        return firstNameCustomer;
    }
    public String getDebitAccount() {
        return debitAccount;
    }
    public Date getDateTransaction()
    {
        return dateTransaction;
    }

    /** Setters **/
    public void setIdTransaction(final int idTransaction) {
        this.idTransaction = idTransaction;
    }
    public void setLastNameCrediter(final String lastNameCrediter) {
        this.lastNameCrediter = lastNameCrediter;
    }
    public void setFirstNameCrediter(final String firstNameCrediter) {
        this.firstNameCrediter = firstNameCrediter;
    }
    public void setCreditAccount(final String creditAccount) {
        this.creditAccount = creditAccount;
    }
    public void setImpactedbank(final String impactedBank) {
        this.impactedBank = impactedBank;
    }
    public void setAmountTransaction(final float amountTransaction) {
        this.amountTransaction = amountTransaction;
    }
    public void setLastNameCustomer(final String lastNameCustomer) {
        this.lastNameCustomer = lastNameCustomer;
    }
    public void setFirstNameCustomer(final String firstNameCustomer) {
        this.firstNameCustomer = firstNameCustomer;
    }
    public void setDebitAccount(final String debitAccount) {
        this.debitAccount = debitAccount;
    }
    public void setDateTransaction(final Date dateTransaction)
    {
        this.dateTransaction = dateTransaction;
    }


}
