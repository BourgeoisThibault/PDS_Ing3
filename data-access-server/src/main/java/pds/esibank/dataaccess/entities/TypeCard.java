package pds.esibank.dataaccess.entities;

/**
 * Created by SarahAllouche on 19/03/2018.
 */

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="TypeCard")
public class TypeCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_typecard" )
    private Long id_TypeCard;

    @Column( name = "label" )
    private String label;
}
