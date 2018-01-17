package pds.esibank.historisation;

import org.springframework.web.client.RestTemplate;
import pds.esibank.models.shares.SharePrice;

import java.util.List;

/**
 * Created by maria on 14/01/2018.
 */
public class ConnectionDataAccess {

    public static List<SharePrice> getSharePrice() {

        final String uri = "http://localhost:8080/sharePriceHistorisation/Historisation";

        RestTemplate restTemplate = new RestTemplate();
        List<SharePrice> list = restTemplate.getForObject(uri,List.class);

        return list;
    }

//   public List<SharePrice> getDBTransaction() throws IOException {
//
//        final String uri = "http://localhost:8080/sharePriceHistorisation/Historisation";
//
//
//        return(new ObjectMapper().readValue(new URL(uri),
//                TypeFactory.defaultInstance().constructCollectionType(List.class, SharePrice.class)));
//    }

}
