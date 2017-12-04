package services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pds.esibank.models.shares.SharePrice;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.Date;

public class StockShareConverter {
    private static Logger logger = LoggerFactory.getLogger(StockShareConverter.class);
    private static ArrayList<SharePrice> sharePriceList = new ArrayList<SharePrice>();

    public static ArrayList<SharePrice> toSharePrice (ArrayList<Stock> stockList){
        stockList.forEach((stock) -> {
            //Create SharePrice (including Share data) from Stock data
            SharePrice sp = new SharePrice(stock.getName(),
                    stock.getSymbol(),
                    stock.getCurrency(),
                    stock.getQuote().getPrice(),
                    stock.getQuote().getChangeInPercent(),
                    new Date());

            //add the created sharePrice to the list
            sharePriceList.add(sp);
            logger.info("SPH/StockShareConverter : stock added " +stock.getName());
        });
        return sharePriceList;
    }
}
