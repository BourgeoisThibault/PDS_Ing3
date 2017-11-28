package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.Share;
import pds.esibank.dataaccess.repositories.ShareRepository;

@Service
public class ShareService {

    @Autowired
    private ShareRepository shareRepository;

    public void update(Share sp) {
        shareRepository.save(sp);
    }

}
