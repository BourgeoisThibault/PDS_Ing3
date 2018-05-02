package pds.esibank.dataaccess.entities;
import lombok.Data;
import javax.persistence.*;

/**
 * Created by jeremy on 27/03/2018.
 */
@Data
@Entity(name = "loan")
public class Loan implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "loan_id" )
    private Long loan_id;
    @Column(name = "loan_type")
    private int loan_type;
    @Column( name = "loan_rate" )
    private float loan_rate;
    @Column( name = "loan_amount" )
    private float loan_amount;
    @Column( name = "loan_duration" )
    private int loan_duration;
    @Column( name = "loan_assurance" )
    private float loan_assurance;

}
