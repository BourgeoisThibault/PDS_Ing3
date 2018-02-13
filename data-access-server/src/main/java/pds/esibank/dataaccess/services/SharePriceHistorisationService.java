package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.repositories.SharePriceRepository;
import pds.esibank.models.shares.SharePrice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maria on 14/01/2018.
 */

@Service
public class SharePriceHistorisationService implements SharePriceHistorisationInterface{

    @Autowired
    private SharePriceRepository sharePriceRepository;

    @Autowired
    public SharePriceHistorisationService(SharePriceRepository sharePriceRepository) {
        this.sharePriceRepository = sharePriceRepository;
    }


    public List<SharePrice> getAllSharePrice() {

        return sharePriceRepository.findAll()
                .stream()
                .map(
                        u -> SharePrice.builder()
                                .price(u.getPrice())
                                .change(u.getChange())
                                .time(u.getTime())
                                .build()
                )
                .collect(Collectors.toList());

    }





    //    @Autowired
//    private SharePriceHistorisationRepo historisationRepo;
//
//    @Autowired
//    public SharePriceHistorisationService(SharePriceHistorisationRepo historisationRepo) {
//        this.historisationRepo = historisationRepo;
//    }



//    public List<SharePrice> getSharePrice(){
//        return historisationRepo.getSharePrice();
//    }

}
