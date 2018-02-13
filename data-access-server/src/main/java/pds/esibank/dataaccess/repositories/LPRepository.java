package pds.esibank.dataaccess.repositories;

import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.LeavingParameters;

import java.util.List;

public interface LPRepository extends CrudRepository<LeavingParameters, Long> {

    List<LeavingParameters> findAll();

}