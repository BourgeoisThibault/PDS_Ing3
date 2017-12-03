package Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import pds.esibank.models.Transaction;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by SarahAllouche on 26/11/2017.
 */
public class AccessDataTransaction {

    public List<Transaction> getDBTransaction() throws IOException {
        //Get Transaction from the model on ArrayList
        final String uri = "http://192.154.88.161:8080/transaction/allByDate";
        //final String uri = "http://localhost:8080/transaction/allByDate";

        return(new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Transaction.class)));
    }

}
