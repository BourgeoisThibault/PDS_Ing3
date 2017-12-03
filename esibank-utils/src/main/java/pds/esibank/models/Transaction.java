package pds.esibank.models;

import lombok.*;

import java.util.Date;

/**
 * Created by SarahAllouche on 23/11/2017.
 */
@Data
@Builder
@AllArgsConstructor

@ToString
public class Transaction {

    private long idTransaction;
    private String lastNameCrediter;
    private String firstNameCrediter;
    private String creditAccount;
    private String impactedBank;
    private float amountTransaction;
    private String lastNameCustomer;
    private String firstNameCustomer;
    private String debitAccount;
    private Date dateTransaction;

    /** Construteur **/
    public Transaction()
    {
        super();
    }

    /** Getters **/
    public long getIdTransaction() {
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
    public void setIdTransaction(final long idTransaction) {
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
