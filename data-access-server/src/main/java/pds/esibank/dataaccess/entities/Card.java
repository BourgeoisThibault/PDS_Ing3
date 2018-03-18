package pds.esibank.dataaccess.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.*;


@Data
@Entity(name = "card")
public class Card implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "card_id" )
    private Long card_id;


  //  @ManyToOne
//    private Account account;

}
