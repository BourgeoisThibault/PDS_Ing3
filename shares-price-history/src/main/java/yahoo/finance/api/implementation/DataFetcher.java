package yahoo.finance.api.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataFetcher {
    private static Logger logger = LoggerFactory.getLogger(DataFetcher.class);
    private static ArrayList<Stock> stockList = new ArrayList<Stock>();

    public static ArrayList<Stock> fetchData() {
        HashMap<String, String> s = new Symbols().getMappingCompaniesSymbols();
        logger.info("SPH/DataFetcher : symbols fetched");
        s.forEach((com, sym) -> {
            try{
                stockList.add(YahooFinance.get(sym));
                logger.info("SPH/DataFetcher : stock added " +com);
            } catch (IOException e){
                logger.error("SPH/DataFetcher error : " + e.toString());
            }
        });
        return stockList;
    }
}
