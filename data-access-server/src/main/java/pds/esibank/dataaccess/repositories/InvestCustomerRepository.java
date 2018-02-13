package pds.esibank.dataaccess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pds.esibank.dataaccess.entities.InvestCustomer;
import java.util.List;

/**
 * Created by SarahAllouche on 08/02/2018.
 */
@Repository
public interface InvestCustomerRepository extends JpaRepository<InvestCustomer, Long>{

    List<InvestCustomer> getInvestCustomer();
}
