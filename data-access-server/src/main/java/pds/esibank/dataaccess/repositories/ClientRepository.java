package pds.esibank.dataaccess.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pds.esibank.dataaccess.entities.Client;

import java.util.List;

/**
 * Created by Abbdoulaye on 06/02/2018.
 */
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

}
