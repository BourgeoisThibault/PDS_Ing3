package pds.esibank.dataaccess.repositories;

import org.springframework.stereotype.Repository;
import pds.esibank.dataaccess.entities.Transaction;
import javax.persistence.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by SarahAllouche on 23/11/2017.
 */

@Repository
public class TransactionRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Transaction> getTransactionByDate() {

        // create entity JPA (link between Java and JPA)

        TypedQuery<Transaction> query = entityManager.createQuery("FROM Transaction t WHERE t.dateTransaction =:dateTransaction",
                Transaction.class)
                .setParameter("dateTransaction", new Date(), TemporalType.DATE);
         List<Transaction> listResult = query.getResultList();
        return listResult;

    }


    //fonction qui insert les transaction dans la bd
    public void InputTransaction(final List <Transaction> tabTransaction)
    {
        System.out.println("Dans input");
        Iterator<Transaction> i = tabTransaction.iterator();
        do
        {
            entityManager.getTransaction().begin();
            Transaction insertTransaction = new Transaction();
            insertTransaction = i.next();
            entityManager.persist(insertTransaction);
            entityManager.getTransaction().commit();

        }while(i.hasNext());
        entityManager.close();

    }

}
