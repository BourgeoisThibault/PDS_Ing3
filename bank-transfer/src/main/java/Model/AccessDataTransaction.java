package Model;

import Main.TransactionBank;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import pds.esibank.models.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SarahAllouche on 26/11/2017.
 */
public class AccessDataTransaction {

    private final Logger logger = Logger.getLogger(TransactionBank.class);

    public List<Transaction> getDBTransaction() throws IOException {
        //Get Transaction from the model on ArrayList
        final String uri = "http://192.154.88.161:8080/dataaccess/transaction/allByDate";
        //final String uri = "http://localhost:8080/transaction/allByDate";
        logger.info("Get Transaction on "+ uri);
        List<Transaction> listOfTransaction = (new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Transaction.class)));
        return listOfTransaction;
    }


    public void InputTransaction(List<Transaction> tabTransaction) throws IOException {
        //Save transactions
        //final String uri = "http://localhost:8080/transaction/inputTransaction";
        final String uri = "http://192.154.88.161:8080/dataaccess/transaction/inputTransaction";
        logger.info("Post transaction " + uri);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, tabTransaction, List.class);
    }
}
