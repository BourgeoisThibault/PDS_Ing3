package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.InvestCustomer;
import pds.esibank.dataaccess.repositories.InvestCustomerRepository;



import java.util.List;

/**
 * Created by SarahAllouche on 08/02/2018.
 */
@Service
public class InvestCustomerService {

    @Autowired
    private final InvestCustomerRepository investCustomerRepository;

    @Autowired
    public InvestCustomerService(InvestCustomerRepository investCustomerRepository) {
        this.investCustomerRepository = investCustomerRepository;
    }


    public List<InvestCustomer> getInvestCustomer(){return this.investCustomerRepository.getInvestCustomer();}


}
