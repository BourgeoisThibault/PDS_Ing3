package pds.esibank.dataaccess.repositories;

import org.springframework.stereotype.Repository;
import pds.esibank.dataaccess.entities.InvestCustomer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by SarahAllouche on 08/02/2018.
 */
@Repository
public class InvestCustomerRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<InvestCustomer> getInvestCustomer(){


        TypedQuery<InvestCustomer> query  = entityManager.createQuery("FROM InvestCustomer",
                InvestCustomer.class);
        List<InvestCustomer> listOfInvestCustomers = query.getResultList();

        return listOfInvestCustomers;
    }


}
