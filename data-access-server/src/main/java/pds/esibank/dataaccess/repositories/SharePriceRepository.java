package pds.esibank.dataaccess.repositories;

import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.SharePrice;

public interface SharePriceRepository extends CrudRepository<SharePrice, Long> {

}