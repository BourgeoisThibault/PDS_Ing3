package pds.esibank.dataaccess.repositories;

import org.springframework.data.repository.Repository;
import pds.esibank.dataaccess.entities.Share;
import pds.esibank.dataaccess.entities.SharePrice;

import java.util.List;

public interface SharePriceRepository extends Repository<SharePrice, Long> {

    List<SharePrice> findAll();

    List<SharePrice> findByShare(Share share);

    void save(SharePrice sharePrice);

    void update(SharePrice sharePrice);

}