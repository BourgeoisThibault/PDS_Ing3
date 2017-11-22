package Main;

import Beans.Transaction;
import Model.modelJPA;
import ServiceXml.ParserXML;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by SarahAllouche on 22/11/2017.
 */
public class transactionBank {
    private modelJPA Modele = new modelJPA();
    private ParserXML parser = new ParserXML();
    private ArrayList<Transaction> tabTransaction;

    public void SendTransaction(){

        Boolean creationXml;
        //Get Transaction from the model on ArrayList
        tabTransaction = Modele.getTransaction();
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
    public static void main(String[] args) throws ParseException {

        transactionBank integrationTest = new transactionBank();
        integrationTest.SendTransaction();

    }

}
