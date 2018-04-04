package pds.esibank.dataaccess.services.payfree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.Card;
import pds.esibank.dataaccess.entities.payfree.LinkCardToAccount;
import pds.esibank.dataaccess.repositories.payfree.CardRepo;
import pds.esibank.dataaccess.repositories.payfree.LinkCardRepo;

/**
 * @author BOURGEOIS Thibault
 * Date     04/04/2018
 * Time     16:47
 */
@Service
public class LinkCardService {

    @Autowired
    LinkCardRepo linkCardRepo;

    public LinkCardToAccount getInfoByCard(String cardNum) {
        return linkCardRepo.getByCard_CardNum(cardNum);
    }


}
