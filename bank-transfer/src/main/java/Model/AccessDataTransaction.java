package Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import pds.esibank.models.Transaction;

import javax.ws.rs.core.MediaType;
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
        //final String uri = "http://192.154.88.161:8080/dataaccess/transaction/allByDate";
        final String uri = "http://localhost:8080/transaction/allByDate";

        return(new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Transaction.class)));
    }

  /*  @RequestMapping(value="/transaction/PostAllTransaction", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List <Transaction> setDBTransaction(final List<Transaction> tabTransaction) throws IOException {
        return tabTransaction;
    }
    */
  public void setDBTransaction(final List<Transaction> tabTransaction) throws IOException {
      final String uri = "http://localhost:8080/transaction/InputTransaction";

      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject(uri, tabTransaction, List.class);
  }
}
