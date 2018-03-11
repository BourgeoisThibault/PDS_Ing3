package pds.esibank.dataaccess.entities.payfree;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author BOURGEOIS Thibault
 * Date     11/03/2018
 * Time     17:33
 */
@Data
@ToString
@Entity
public class PfClient {
    @Id
    @Column
    private String pseudo;
    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private String encryptedPass;
}
