package model;
import java.io.IOException;
import pds.esibank.models.LeavingCustomer;
import java.net.URL;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


public class LPDA {

    public List<LeavingCustomer> getLeavingCustomer() throws IOException {

        final String uri = "http://192.154.88.161:8080/dataaccess/lp/findAll";
        List<LeavingCustomer> data  = (new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, LeavingCustomer.class)));
        return data;
    }
}
