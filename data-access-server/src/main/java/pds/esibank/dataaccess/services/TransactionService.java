package pds.esibank.dataaccess.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.Transaction;
import pds.esibank.dataaccess.repositories.TransactionRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SarahAllouche on 23/11/2017.
 */
@Service
public class TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public List<Transaction> getTransactionByDate(){
        return this.transactionRepository.getTransactionByDate();
    }
}
