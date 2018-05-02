package pds.esibank.dataaccess.repositories.payfree;

import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.payfree.LinkCardToAccount;

public interface LinkCardRepo extends CrudRepository<LinkCardToAccount, Long> {
    public LinkCardToAccount getByCard_CardNum(String cardNum);
}
