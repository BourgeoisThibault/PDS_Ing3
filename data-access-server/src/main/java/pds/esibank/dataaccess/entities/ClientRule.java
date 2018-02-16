package pds.esibank.dataaccess.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
@Data
@ToString
@Entity
@Table(name="clientRule")
public class ClientRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "idClientRule" )
    private Long idClientRule;
    @Column(name = "idClient")
    private int idClient;
    @Column( name = "shareId" )
    private  int shareId;
    @Column( name = "seuilMin" )
    private int seuilMin;
    @Column( name = "seuilMax" )
    private int seuilMax;
    @Column( name = "communicationType" )
    private int communicationType;
    @Column( name = "frequenceAlerte" )
    private int frequenceAlerte;

    public ClientRule(int idClient, int shareId, int seuilMin, int seuilMax, int communicationType, int frequenceAlerte) {
        this.idClient = idClient;
        this.shareId = shareId;
        this.seuilMin = seuilMin;
        this.seuilMax = seuilMax;
        this.communicationType = communicationType;
        this.frequenceAlerte = frequenceAlerte;
    }
}
