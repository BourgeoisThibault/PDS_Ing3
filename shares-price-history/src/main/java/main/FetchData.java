package main;

import data.access.SharePriceAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pds.esibank.models.shares.SharePrice;
import services.StockShareConverter;
import yahoo.finance.api.implementation.DataFetcher;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.ArrayList;

public class FetchData {
    private static Logger logger = LoggerFactory.getLogger(FetchData.class);

    public static void main(String[] args) {
        try {
            ArrayList<Stock> stockList = DataFetcher.fetchData();
            logger.info("SPH/FetchData : stockList fetched");

            ArrayList<SharePrice> sharePriceList = StockShareConverter.toSharePrice(stockList);
            logger.info("SPH/FetchData : stockList converted to sharePriceList");

            SharePriceAccess.save(sharePriceList);
            logger.info("SPH/FetchData : shares' prices updated");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("SPH/FetchData error : " + e.toString());
        }
    }

}
