package pds.esibank.dataaccess.entities;

import javax.persistence.*;
import lombok.Data;
/**
 * Created by SarahAllouche on 08/02/2018.
 */

@Data
@Entity
@Table(name="InvestCustomer")
public class InvestCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "id_Invest_Customer")
    private long id_Invest_Customer;

    @Basic(optional = false)
    @Column(name = "firstname_customer")
    private String firstname_customer;

    @Basic(optional = false)
    @Column(name = "lastname_customer")
    private String lastname_customer;

    @Basic(optional = false)
    @Column(name = "salary_customer")
    private float salary_customer;

    @Basic(optional = false)
    @Column( name = "sold_customer")
    private float sold_customer;

    @Basic(optional = false)
    @Column(name = "sold1_customer")
    private float sold1_customer;

    @Basic(optional = false)
    @Column(name = "sold2_customer")
    private float sold2_customer;

    @Basic(optional = false)
    @Column(name = "status_customer")
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


}
