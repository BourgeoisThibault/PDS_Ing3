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
    private String loan_type;
    @Column( name = "loan_rate" )
    private String loan_rate;
    @Column( name = "loan_assurance" )
    private String loan_assurance;
    @Column( name = "loan_duration" )
    private int loan_duration;

}
