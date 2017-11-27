package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.SharePrice;
import pds.esibank.dataaccess.repositories.SharePriceRepository;

@Service
public class SharePriceService {

    @Autowired
    private SharePriceRepository sharePriceRepository;

    public void update(SharePrice sp) {
        sharePriceRepository.save(sp);
    }

}
