package pds.esibank.dataaccess.repositories;

import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.Share;

import java.util.List;

/**
 * Created by Abbdoulaye on 04/12/2017.
 */
public interface ShareAlertRepository extends CrudRepository<Share, Integer> {
    @Override
    public List<Share> findAll();
}