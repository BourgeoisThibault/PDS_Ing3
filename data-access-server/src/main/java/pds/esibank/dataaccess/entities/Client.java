package pds.esibank.dataaccess.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Abbdoulaye on 06/02/2018.
 */
@Data
@ToString
@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "client_id" )
    private Long id_client;
    @Column(name = "lastName")
    private String lastName;
    @Column( name = "firstName" )
    private String firstName;
    @Column( name = "profession" )
    private String profession;

    //List<Account> accounts;



}
