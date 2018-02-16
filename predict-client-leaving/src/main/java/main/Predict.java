package main;
import java.io.IOException;
import java.util.List;

import dataaccess.LPDA;
import service.CSVMaker;
import org.apache.log4j.Logger;
import pds.esibank.models.LeavingCustomer;


public class Predict {

    private CSVMaker csvMaker = new CSVMaker();
    private LPDA customersDA = new LPDA();
    private static Logger logger = Logger.getLogger("Predict");

    public boolean makeCsv()
    {
        try{
            List<LeavingCustomer> customers = customersDA.getLeavingCustomer();
            if (customers.size() < 1) {
                logger.error("No Customer found");
                return false;
            }
            else
            {
                //Make Csv
                List<String[]> customersCSV = csvMaker.beanToRecord(customers);
                csvMaker.listToCSV(customersCSV);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) throws ClassNotFoundException{
        Predict p = new Predict();
        if (p.makeCsv())
        {
           try {
                Runtime.getRuntime().exec("RScript customers_leaving.R");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            logger.error("No Csv File Created");
    }
}
