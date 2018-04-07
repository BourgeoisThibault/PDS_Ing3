package pds.esibank.dataaccess.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by jeremy on 07/04/2018.
 */

@Data
@Entity
@Table(name="typeLoan")
public class TypeLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loantype")
    private Long id_loantype;

    @Column(name = "type")
    private String type;

    @Column(name = "amountMin")
    private int amountMin;

    @Column(name = "amountMax")
    private int amountMax;

    @Column(name = "durationMax")
    private int durationMax;

    @Column(name = "durationMin")
    private int durationMin;

    @Column(name = "rateMin")
    private float rateMin;

    @Column(name = "rateMax")
    private float rateMax;

    @Column(name = "assurance")
    private float assurance;
}