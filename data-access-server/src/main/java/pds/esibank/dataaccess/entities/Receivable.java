package pds.esibank.dataaccess.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by jeremy on 27/03/2018.
 */
@Data
@Entity(name = "receivable")
public class Receivable implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "receivable_id" )
    private Long receivable_id;
    @Column(name = "receivable_amount")
    private int receivable_amount;
    @Column( name = "receivable_rate" )
    private String receivable_rate;
    @Column( name = "receivable_assurance" )
    private String receivable_assurance;
    @Column( name = "receivable_duration" )
    private int receivable_duration;

    @OneToMany
    @JoinColumn(name="id_Customer")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="id_loan")
    private Loan loan;

}
