package pds.esibank.dataaccess.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by SarahAllouche on 25/03/2018.
 */
@Data
@Entity(name = "region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_region" )
    private Integer id_region;

    @Basic(optional = false)
    @Column( name = "name_region" )
    private String name_region;
}
