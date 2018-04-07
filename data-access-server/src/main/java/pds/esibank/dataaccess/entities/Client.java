package pds.esibank.dataaccess.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jeremy on 07/04/2018.
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
    @Column( name = "birth" )
    private Date birth;
    @Column( name = "Gender" )
    private String Gender;

}
