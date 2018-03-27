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
    private String receivable_type;
    @Column( name = "receivable_rate" )
    private String receivable_rate;
    @Column( name = "receivable_assurance" )
    private String receivable_assurance;
    @Column( name = "receivable_duration" )
    private String receivable_duration;

    @OneToMany
    private Loan loan;
    @OneToMany
    private Client client;



}
