package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.CommunicationType;
import pds.esibank.dataaccess.repositories.CommunicationTypeRepository;

import java.util.List;

/**
 * Created by Abbdoulaye on 09/02/2018.
 */
@Service
public class CommunicationTypeService {
    @Autowired
    private final CommunicationTypeRepository communicationTypeRepository;

    @Autowired
    public CommunicationTypeService(CommunicationTypeRepository communicationTypeRepository) {
        this.communicationTypeRepository = communicationTypeRepository;
    }
    public List<CommunicationType> getAllCommunicationType(){
      List<CommunicationType> listCommunicationType= (List<CommunicationType>) communicationTypeRepository.findAll();

        return listCommunicationType;
    }
}
