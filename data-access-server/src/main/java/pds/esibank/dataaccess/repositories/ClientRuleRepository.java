package pds.esibank.dataaccess.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pds.esibank.dataaccess.entities.ClientRule;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
public interface ClientRuleRepository extends PagingAndSortingRepository<ClientRule,Long> {

}
