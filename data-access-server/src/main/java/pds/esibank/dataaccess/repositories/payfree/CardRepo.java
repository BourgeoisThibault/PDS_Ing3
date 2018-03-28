package pds.esibank.dataaccess.repositories.payfree;

import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.Card;
import pds.esibank.dataaccess.entities.payfree.PfClient;

/**
 * @author BOURGEOIS Thibault
 * Date     28/03/2018
 * Time     14:25
 */

public interface CardRepo extends CrudRepository<Card, Long> {

    public Card getCardByCardNum(String cardNum);

}
