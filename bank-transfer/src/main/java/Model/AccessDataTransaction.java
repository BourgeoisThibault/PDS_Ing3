package Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

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

    public List<Transaction> getDBTransaction() throws IOException {
        //Get Transaction from the model on ArrayList
        final String uri = "http://192.154.88.161:8080/dataaccess/transaction/allByDate";
        //final String uri = "http://localhost:8080/transaction/allByDate";

        return(new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Transaction.class)));
    }


    public static void InputTransaction(List<Transaction> tabTransaction) throws IOException {
        //Save transactions
        //final String uri = "http://localhost:8080/transaction/InputTransaction";
        final String uri = "http://192.154.88.161:8080/dataaccess/transaction/InputTransaction";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, tabTransaction, List.class);
    }
}
