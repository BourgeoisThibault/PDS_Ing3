package data.access;

import pds.esibank.models.shares.SharePrice;

import java.io.IOException;
import java.util.ArrayList;


public class SharePriceAccess {
    public static void save(ArrayList<SharePrice> sharePrices) throws IOException {
        //Save new shares' prices
        //final String uri = "http://data-access:8080/transaction/allByDate";
        final String uri = "http://localhost:8080/shareprice/save";

        //return(new ObjectMapper().readValue(new URL(uri),
        //        TypeFactory.defaultInstance().constructCollectionType(List.class, Transaction.class)));
    }

}


