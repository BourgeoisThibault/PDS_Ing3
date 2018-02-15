package pds.esibank.dataaccess.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Abbdoulaye on 09/02/2018.
 */
@Data
@ToString
@Entity
@Table(name="communicationType")
public class CommunicationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_CommunicationType" )
    private Long idCommunicationType;
    private String libelle;

    public CommunicationType() {
    }

    public CommunicationType(String libelle) {
        this.libelle = libelle;
    }

}
