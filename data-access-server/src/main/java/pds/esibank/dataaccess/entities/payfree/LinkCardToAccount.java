package pds.esibank.dataaccess.entities.payfree;

import lombok.Data;
import pds.esibank.dataaccess.entities.Account;
import pds.esibank.dataaccess.entities.Card;
import pds.esibank.dataaccess.entities.Customer;

import javax.persistence.*;

/**
 * @author BOURGEOIS Thibault
 * Date     04/04/2018
 * Time     16:41
 */

@Data
@Entity(name = "link_card_account")
public class LinkCardToAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_link" )
    private Long idLink;

    @OneToOne
    @JoinColumn(name="card_id")
    private Card card;

    @OneToOne
    @JoinColumn(name="id_account")
    private Account account;
}
