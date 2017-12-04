package data.access;

import org.springframework.web.client.RestTemplate;
import pds.esibank.models.shares.SharePrice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class SharePriceAccess {
    public static void save(ArrayList<SharePrice> sharePriceList) throws IOException {
        //Save new shares' prices
        final String uri = "http://localhost:8080/shareprice/update";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, sharePriceList, ArrayList.class);
    }
}


