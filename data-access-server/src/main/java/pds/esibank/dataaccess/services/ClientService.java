package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.Client;
import pds.esibank.dataaccess.repositories.ClientRepository;

import java.util.List;

/**
 * Created by Abbdoulaye on 06/02/2018.
 */
@Service
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClient(){
        List<Client> listClient= (List<Client>) clientRepository.findAll( new Sort(Sort.Direction.DESC,"lastName"));

        return listClient;
    }

}
