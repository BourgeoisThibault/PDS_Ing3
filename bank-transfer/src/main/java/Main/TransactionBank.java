package Main;

import Model.AccessDataTransaction;
import Model.GetDataTransaction;
import Model.SendDataTransaction;
import Service.ParserXML;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import pds.esibank.models.Transaction;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




/**
 * Created by SarahAllouche on 22/11/2017.
 */
public class TransactionBank {
    private ParserXML parser = new ParserXML();
    private List<Transaction> tabTransaction = new ArrayList<Transaction>();
    private AccessDataTransaction addTransaction = new AccessDataTransaction();

    private final Logger logger = Logger.getLogger(TransactionBank.class);



    public boolean SendTransaction() {
        BasicConfigurator.configure();
        Boolean creationXml;
        logger.info("////////// LOGS BATCH BANK-TRANSFER \\\\\\\\\\\\\\");
        logger.info("In SendTransaction method");
        try {
            tabTransaction = addTransaction.getDBTransaction();
        } catch (IOException e) {
            logger.error("Error in retrieve data");
            // e.printStackTrace();
        }
		/*Test tab not Empty*/
        if(!tabTransaction.isEmpty())
        {
			/*Insert Transactions on the Xml document*/
            creationXml = parser.SetXmlDocument(tabTransaction);
            if (!creationXml)
            {
				/*Error Create Xml File*/
                logger.info("Xml file not created");
                return creationXml;
            }
            else
            {
				/* Good XML*/
                logger.info("Good job");

                return creationXml;
            }
        }
        else
        {
			/*Tab Transaction Empty*/
            logger.info("Any Transaction for this date");

        }
        return false;
    }


    public void recoveryTransaction() throws IOException, ParseException{

        logger.info("recover Transaction of "+ new Date());
        boolean insertIntoBD;

        //appel méthode jérémy pour donner un document
        Document document = parser.GetXmlDocument();


        // Ma méthode attend un document xml
        tabTransaction = parser.GetXmlTransaction(document);
		/*Test tab not Empty */
        if(!tabTransaction.isEmpty())
        {
            logger.info("the XML file contains " + tabTransaction.size() + "Transaction");
            insertIntoBD = true;
            addTransaction.InputTransaction(tabTransaction);
            if(insertIntoBD)
            {
                logger.info("Transaction Insert Into DataBase");

            }
            else
            {
                logger.error("Bug Into Insert");
            }
        }
        else
        {
			/*Tab Transaction Empty*/
            logger.info("No Transaction for today");
        }

    }

    public static void main(String[] args) throws ParseException, IOException {

        TransactionBank ConstructTransaction = new TransactionBank();
        boolean goodJob;
        goodJob = ConstructTransaction.SendTransaction();
        if (goodJob){
            SendDataTransaction send = new SendDataTransaction();
            send.sendFile();
        }
        GetDataTransaction get = new GetDataTransaction();

        get.GetDataTransaction();

        ConstructTransaction.recoveryTransaction();

    }
}
