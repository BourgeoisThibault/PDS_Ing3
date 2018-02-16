package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.ClientNotif;
import pds.esibank.dataaccess.repositories.ClientNotifRepository;

import java.util.List;

/**
 * Created by Abbdoulaye on 13/02/2018.
 */
@Service
public class ClientNotifService {
    @Autowired
    ClientNotifRepository clientNotifRepository;

    @Autowired
    public ClientNotifService(ClientNotifRepository clientNotifRepository) {
         this.clientNotifRepository= clientNotifRepository;
    }
     public List<ClientNotif> getAllNotifClient(){
         List<ClientNotif> listNotifClient= (List<ClientNotif>) clientNotifRepository.findAll(new Sort(Sort.Direction.DESC,"idClient"));

         return listNotifClient;
     }
    public ClientNotif save(ClientNotif clientNotif){

          return clientNotifRepository.save(clientNotif);
    }

}
