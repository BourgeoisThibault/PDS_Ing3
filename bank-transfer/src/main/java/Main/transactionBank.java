package Main;

import Model.AccessDataTransaction;
import Service.ParserXML;
import pds.esibank.models.Transaction;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


/**
 * Created by SarahAllouche on 22/11/2017.
 */
public class transactionBank {
    private ParserXML parser = new ParserXML();
    private List<Transaction> tabTransaction;
    private AccessDataTransaction addTransaction = new AccessDataTransaction();


    public boolean SendTransaction() throws IOException{

        Boolean creationXml;

        tabTransaction = addTransaction.getDBTransaction();
		/*Test tab not Empty*/
        if(!tabTransaction.isEmpty())
        {
			/*Insert Transactions on the Xml document*/
            creationXml = parser.SetXmlDocument(tabTransaction);
            if (!creationXml)
            {
				/*Error Create Xml File*/
                System.out.println("Xml file not created");
                return creationXml;
            }
            else
            {
				/* Good XML*/
                System.out.println("Good job ");
                return creationXml;
            }
        }
        else
        {
			/*Tab Transaction Empty*/
            System.out.println("Any Transaction for this date");

        }
        return false;
    }

    public static void main(String[] args) throws ParseException, IOException {

        transactionBank integrationTest = new transactionBank();
        boolean goodJob;
        goodJob = integrationTest.SendTransaction();
        if (goodJob){
            // return in rest the document;
        }
    }

}
