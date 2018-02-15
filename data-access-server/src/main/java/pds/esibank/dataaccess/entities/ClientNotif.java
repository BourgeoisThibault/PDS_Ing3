package pds.esibank.dataaccess.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
@Data
@Entity
@Table(name="ClientNotif")
public class ClientNotif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "idClientNotif" )
    private int idClientNotif;
    @Column( name = "idClient" )
    private int idClient;

    @Column( name = "alerteMessage" )
    private String alerteMessage;

}
