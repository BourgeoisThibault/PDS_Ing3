package pds.esibank.dataaccess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pds.esibank.dataaccess.entities.Transaction;
import java.util.List;

/**
 * Created by SarahAllouche on 23/11/2017.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, CrudRepository<Transaction, Long> {

    List<Transaction> getTransactionByDate();
}
