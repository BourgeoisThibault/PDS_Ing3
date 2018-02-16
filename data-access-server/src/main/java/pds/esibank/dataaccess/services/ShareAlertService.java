package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.Share;
import pds.esibank.dataaccess.repositories.ShareAlertRepository;


import java.util.List;

/**
 * Created by Abbdoulaye on 05/12/2017.
 */
@Service
public class ShareAlertService {
    @Autowired
    private final ShareAlertRepository shareAlertRepository;

    @Autowired
    public ShareAlertService(ShareAlertRepository shareAlertRepository) {
        this.shareAlertRepository = shareAlertRepository;
    }
    public List<Share> getAllShareAlert(){
        List<Share> listShare=shareAlertRepository.findAll();

        return listShare;
    }

}
