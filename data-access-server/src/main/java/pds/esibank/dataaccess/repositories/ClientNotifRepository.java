package pds.esibank.dataaccess.repositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import pds.esibank.dataaccess.entities.ClientNotif;

import java.util.List;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
public interface ClientNotifRepository extends PagingAndSortingRepository<ClientNotif, Long> {
    public List<ClientNotif> findAll();
    public ClientNotif save(ClientNotif clientNotif);
}
