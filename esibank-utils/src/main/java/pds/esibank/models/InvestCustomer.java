package pds.esibank.models;

import lombok.*;
/**
 * Created by SarahAllouche on 08/02/2018.
 */
@Data
@Builder
@AllArgsConstructor

@ToString
public class InvestCustomer {

    private long id_Invest_Customer;
    private String firstname_customer;
    private String lastname_customer;
    private float salary_customer;
    private float sold_customer;
    private float sold1_customer;
    private float sold2_customer;
    private int status_customer;

    public InvestCustomer()
    {
        super();
    }

    public long GetId()
    {
        return this.id_Invest_Customer;
    }
    public String GetFirstName()
    {
        return this.firstname_customer;
    }
    public String GetLastName()
    {
        return this.lastname_customer;
    }
    public float GetSalary()
    {
        return this.salary_customer;
    }
    public float GetSold()
    {
        return this.sold_customer;
    }
    public float GetSold1()
    {
        return this.sold1_customer;
    }
    public float GetSold2()
    {
        return this.sold2_customer;
    }
    public int GetStatus()
    {
        return this.status_customer;
    }


    public void SetId(final long id)
    {
        this.id_Invest_Customer = id;
    }
    public void SetFirstName(final String firstName)
    {
        this.firstname_customer = firstName;
    }
    public void SetLastName(final String lastName)
    {
        this.lastname_customer = lastName;
    }
    public void SetSalary(final float salary)
    {
        this.salary_customer = salary;
    }
    public void SetSold(final float sold)
    {
        this.sold_customer = sold;
    }
    public void SetSold1(final float sold1)
    {
        this.sold1_customer = sold1;
    }
    public void SetSold2(final float sold2)
    {
        this.sold2_customer = sold2;
    }
    public void SetStatus(final int status)
    {
        this.status_customer = status;
    }


    public String [] ToString()
    {
        String[] ObjString = {String.valueOf(this.id_Invest_Customer), this.firstname_customer,  this.lastname_customer,
                String.valueOf(this.salary_customer), String.valueOf(this.sold_customer), String.valueOf(this.sold1_customer),
                String.valueOf(this.sold2_customer), Integer.toString(this.status_customer)};
        return ObjString;
    }
}
