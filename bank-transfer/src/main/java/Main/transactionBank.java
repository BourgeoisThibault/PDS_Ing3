package Main;

import ServiceXml.ParserXML;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import pds.esibank.models.Transaction;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SarahAllouche on 22/11/2017.
 */
public class transactionBank {
   // private modelJPA Modele = new modelJPA();
    private ParserXML parser = new ParserXML();
    private ArrayList<Transaction> tabTransaction;

    public void SendTransaction() throws IOException{

        Boolean creationXml;
        //Get Transaction from the model on ArrayList
        final String uri = "http://data-access:8080/transaction/allByDate";
        //final String uri = "http://localhost:8080/transaction/allByDate";

        ArrayList<Transaction> list = new ObjectMapper().readValue(new URL(uri),
                TypeFactory.defaultInstance().constructCollectionType(List.class, Transaction.class));


        tabTransaction = list;
		/*Test tab not Empty*/
        if(!tabTransaction.isEmpty())
        {
			/*Insert Transactions on the Xml document*/
            creationXml = parser.SetXmlDocument(tabTransaction);
            if (!creationXml)
            {
				/*Error Create Xml File*/
                System.out.println("Xml file not created");
            }
            else
            {
				/* Good XML*/
                System.out.println("Good job ");
            }
        }
        else
        {
			/*Tab Transaction Empty*/
            System.out.println("Any Transaction for this date");
        }
    }
    public static void main(String[] args) throws ParseException, IOException {

        transactionBank integrationTest = new transactionBank();
        integrationTest.SendTransaction();

    }

}
