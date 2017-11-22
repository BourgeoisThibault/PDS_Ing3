package Model;

import Beans.Transaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SarahAllouche on 19/11/2017.
 */
public class modelJPA {
    public ArrayList<Transaction> getTransaction() {
        ArrayList<Transaction> tabTransaction = new ArrayList<Transaction>();
        EntityManagerFactory transactionfacory = Persistence.createEntityManagerFactory("TransactionProperty");
        // create entity JPA (link between Java and JPA)
        EntityManager transactionentity = transactionfacory.createEntityManager();
// :dateTransaction  ,
        TypedQuery<Transaction> query = transactionentity.createQuery("Select t from Transaction t"
                + " WHERE t.dateTransaction =:dateTransaction", Transaction.class)
                .setParameter("dateTransaction", new Date(), TemporalType.DATE);

        List<Transaction> listTransaction = query.getResultList();
        //retrieve transaction from the listTransaction
        for (Transaction t : listTransaction) {
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
        //close entity
        transactionentity.close();
        //close connection TransactionTest
        transactionfacory.close();
        return tabTransaction;
    }
    }
