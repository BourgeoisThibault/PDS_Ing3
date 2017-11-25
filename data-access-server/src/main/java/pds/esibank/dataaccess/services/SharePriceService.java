package pds.esibank.dataaccess.services;

import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.Share;
import pds.esibank.dataaccess.entities.SharePrice;
import pds.esibank.dataaccess.repositories.SharePriceRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SharePriceService implements SharePriceRepository {

    @Resource
    private SharePriceRepository sharePriceRepository;

    @Override
    public List<SharePrice> findAll() {
        return sharePriceRepository.findAll();
    }

    @Override
    public List<SharePrice> findByShare(Share share) {
        return sharePriceRepository.findByShare(share);
    }

    @Override
    public void save(SharePrice share) {
        sharePriceRepository.save(share);
    }

    @Override
    public void update(SharePrice share) {
        sharePriceRepository.update(share);
    }

}
