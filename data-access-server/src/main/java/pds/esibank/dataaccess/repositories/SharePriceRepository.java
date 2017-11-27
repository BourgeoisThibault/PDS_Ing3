package pds.esibank.dataaccess.repositories;

import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.SharePrice;

import java.util.List;

public interface SharePriceRepository extends CrudRepository<SharePrice, Long> {

}