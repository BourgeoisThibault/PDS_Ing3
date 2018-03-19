package pds.esibank.dataaccess.entities;

/**
 * Created by SarahAllouche on 19/03/2018.
 */

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="Status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "status_id" )
    private Long status_id;

    @Column( name = "label" )
    private String label;

}
