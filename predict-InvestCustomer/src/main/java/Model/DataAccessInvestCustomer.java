package Model;
import java.io.IOException;
import pds.esibank.models.InvestCustomer;
import java.net.URL;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


/**
 * Created by SarahAllouche on 07/02/2018.
 */
public class DataAccessInvestCustomer {


    public List<InvestCustomer> getInvestCustomer() throws IOException {
        
        final String uri = "http://192.154.88.161:8080/dataaccess/investcustomer/allInvestCustomer";
        List<InvestCustomer> data  = (new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, InvestCustomer.class)));
        return data;
    }
}
