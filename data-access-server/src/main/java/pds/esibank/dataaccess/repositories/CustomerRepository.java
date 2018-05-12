package pds.esibank.dataaccess.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pds.esibank.dataaccess.entities.Account;
import pds.esibank.dataaccess.entities.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {


    Customer save(Customer customer);
}
