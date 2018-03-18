package pds.esibank.restserver.services;

import org.springframework.stereotype.Service;
import pds.esibank.models.dab.AccountDto;
import pds.esibank.restserver.rest.RestDab;

import java.util.List;

@Service
public class DabManagementService implements IDabManagementService{

    public boolean checkAccountValid(long card_id) {

        List<AccountDto> accountDtoList = RestDab.getAccountsByCardId(card_id);
        if (!accountDtoList.isEmpty()){
            System.out.print("INFO checkAccountValid: "+accountDtoList.toString());
            return true;
        }else
            System.out.print("INFO checkAccountValid: "+accountDtoList.toString());
        return false;
    }

}
