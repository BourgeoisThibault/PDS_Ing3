package pds.esibank.dataaccess.repositories;

import org.springframework.data.repository.Repository;
import pds.esibank.dataaccess.entities.Share;

import java.util.List;

public interface ShareRepository extends Repository<Share, Long> {

    List<Share> findAll();

    Share findByName(String name);

    void save(Share share);

    void update(Share share);

}
