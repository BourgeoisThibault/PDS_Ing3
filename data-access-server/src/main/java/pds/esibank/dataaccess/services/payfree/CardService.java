package pds.esibank.dataaccess.services.payfree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.Card;
import pds.esibank.dataaccess.repositories.payfree.CardRepo;

/**
 * @author BOURGEOIS Thibault
 * Date     28/03/2018
 * Time     14:26
 */

@Service
public class CardService {

    @Autowired
    CardRepo cardRepo;

    public Card getOneCard(String cardNum) {
        return cardRepo.getCardByCardNum(cardNum);
    }

}
