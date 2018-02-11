package Main;
import java.io.IOException;
import java.util.List;

import Model.DataAccessInvestCustomer;
import Service.MakeCSV;
import org.apache.log4j.Logger;
import pds.esibank.models.InvestCustomer;


/**
 * Created by SarahAllouche on 07/02/2018.
 */
public class PredictInvestCustomer {

    private MakeCSV MakeCsv = new MakeCSV();
    private DataAccessInvestCustomer investCustomerDA = new DataAccessInvestCustomer();
    private static Logger logger = Logger.getLogger("InvestCustomer");

    public boolean MakeCsv() throws ClassNotFoundException
    {
        try{
            //Retrieve List of InvestCustomer object with call of getInvestCustomer
            List<InvestCustomer> InvestCustomer = investCustomerDA.getInvestCustomer();
            if (InvestCustomer.size() < 1) {
                logger.error("No Invest Customer");
                return false;
            }
            else
            {
                //Make Csv
                List<String[]> investForCsv = MakeCsv.BeanToRecord(InvestCustomer);
                MakeCsv.MakeCsvFromList(investForCsv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) throws ClassNotFoundException{

        PredictInvestCustomer StartPrediction = new PredictInvestCustomer();
        Boolean CsvFile;
        CsvFile = StartPrediction.MakeCsv();
        if (CsvFile)
        {
           try {
                Runtime.getRuntime().exec("RScript algo_InvestCustomer.R");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            logger.error("No Csv File Create");
    }
}
