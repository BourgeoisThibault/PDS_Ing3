package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.LeavingParameters;
import pds.esibank.dataaccess.repositories.LPRepository;

import java.util.List;

@Service
public class LPService {

    @Autowired
    private LPRepository lpRepository;

    public List<LeavingParameters> findAll() {
        return lpRepository.findAll();
    }

}
