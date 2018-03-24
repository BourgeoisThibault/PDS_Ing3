package pds.esibank.dataaccess.services.payfree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.payfree.PfClient;
import pds.esibank.dataaccess.repositories.payfree.PfClientRepo;

/**
 * @author BOURGEOIS Thibault
 * Date     11/03/2018
 * Time     17:49
 */
@Service
public class PfClientService {
    @Autowired
    PfClientRepo pfClientRepo;

    public PfClient getOneClient(String pseudo) {
        return pfClientRepo.findOne(pseudo);
    }
}
