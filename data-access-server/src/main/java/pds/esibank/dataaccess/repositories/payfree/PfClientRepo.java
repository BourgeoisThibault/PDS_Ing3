package pds.esibank.dataaccess.repositories.payfree;

import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.payfree.PfClient;

/**
 * @author BOURGEOIS Thibault
 * Date     11/03/2018
 * Time     17:48
 */
public interface PfClientRepo extends CrudRepository<PfClient, String> {
}
