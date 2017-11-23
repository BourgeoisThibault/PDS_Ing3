package pds.esibank.dataaccess.repositories;


import org.springframework.stereotype.Repository;
import pds.esibank.dataaccess.entities.Transaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SarahAllouche on 23/11/2017.
 */

@Repository
public class TransactionRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public ArrayList<Transaction> getTransactionByDate() {

        ArrayList<Transaction> tabTransaction = new ArrayList<Transaction>();
        // create entity JPA (link between Java and JPA)

        TypedQuery<Transaction> query = entityManager.createQuery("FROM Transaction t WHERE t.dateTransaction =:dateTransaction",
                Transaction.class)
                .setParameter("dateTransaction", new Date(), TemporalType.DATE);
         List<Transaction> listResult = query.getResultList();

        for (Transaction t : listResult) {
            Transaction TransactionResultatQuery = new Transaction();
            TransactionResultatQuery.setIdTransaction(t.getIdTransaction());
            TransactionResultatQuery.setLastNameCrediter(t.getLastNameCrediter());
            TransactionResultatQuery.setFirstNameCrediter(t.getFirstNameCrediter());
            TransactionResultatQuery.setCreditAccount(t.getCreditAccount());
            TransactionResultatQuery.setImpactedbank(t.getImpactedbank());
            TransactionResultatQuery.setAmountTransaction(t.getAmountTransaction());
            TransactionResultatQuery.setLastNameCustomer(t.getLastNameCustomer());
            TransactionResultatQuery.setFirstNameCustomer(t.getFirstNameCustomer());
            TransactionResultatQuery.setDebitAccount(t.getDebitAccount());
            TransactionResultatQuery.setDateTransaction(t.getDateTransaction());
            tabTransaction.add(TransactionResultatQuery);
        }
         return tabTransaction;
    }

}
