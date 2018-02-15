package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.Client;
import pds.esibank.dataaccess.entities.ClientRule;
import pds.esibank.dataaccess.repositories.ClientRepository;
import pds.esibank.dataaccess.repositories.ClientRuleRepository;

import java.util.List;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
@Service
public class ClientRuleService {
    @Autowired
    private final ClientRuleRepository clientRuleRepository;
    @Autowired
    public ClientRuleService(ClientRuleRepository clientRuleRepository) {
        this.clientRuleRepository = clientRuleRepository;
    }

    public List<ClientRule> getAllClient(){
        List<ClientRule> listClient= (List<ClientRule>) clientRuleRepository.findAll();
        return listClient;
    }
    public ClientRule  addClientRule(ClientRule clientRule){
        return clientRuleRepository.save(clientRule);
    }
}
