package data.access;

import yahoofinance.Stock;

import java.io.IOException;
import java.util.ArrayList;


public class SharePriceAccess {
    public static void save(ArrayList<Stock> sharePrices) throws IOException {
        //Save new shares' prices
        //final String uri = "http://data-access:8080/transaction/allByDate";
        final String uri = "http://localhost:8080/transaction/allByDate";

        //return(new ObjectMapper().readValue(new URL(uri),
        //        TypeFactory.defaultInstance().constructCollectionType(List.class, Transaction.class)));
    }

}


