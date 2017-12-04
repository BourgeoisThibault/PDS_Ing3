package pds.esibank.dataaccess.repositories;

import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.Share;

public interface ShareRepository extends CrudRepository<Share, Long> {

}
