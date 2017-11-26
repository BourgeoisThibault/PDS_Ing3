package Service;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import pds.esibank.models.Transaction;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SarahAllouche on 17/11/2017.
 */

public class ParserXML {


    public Boolean SetXmlDocument(final List<Transaction> tabTransaction)
    {
        int i;
        Document document;
        SimpleDateFormat formater =  new SimpleDateFormat("YYYY-MM-dd");
		/*noeud Le Plus Haut*/
        Element transaction = new Element("Transaction");
		/*Add Grand noeud dans xml*/
        document = new Document(transaction);

        for(i = 0; i < tabTransaction.size(); i++)
        {
			/*id transaction*/
            Element idTransaction = new Element("id_transaction");
            transaction.addContent(idTransaction);
            Attribute attribuId = new Attribute("id", Long.toString(tabTransaction.get(i).getIdTransaction()));
            idTransaction.setAttribute(attribuId);

			/*Last Name Crediter*/
            Element lastnameCrediter = new Element("lastname_crediter");
            lastnameCrediter.setText(tabTransaction.get(i).getLastNameCrediter());
            idTransaction.addContent(lastnameCrediter);

			/*First Name Crediter*/
            Element firstNameCrediter = new Element("firstname_crediter");
            firstNameCrediter.setText(tabTransaction.get(i).getFirstNameCrediter());
            idTransaction.addContent(firstNameCrediter);

			/*Credit Account*/
            Element creditAccount = new Element("credit_account");
            creditAccount.setText(tabTransaction.get(i).getCreditAccount());
            idTransaction.addContent(creditAccount);

			/*Impacted Bank*/
            Element impactedBank = new Element("impacted_bank");
            impactedBank.setText(tabTransaction.get(i).getImpactedbank());
            idTransaction.addContent(impactedBank);

			/*Amount Transaction*/
            Element amountTransaction = new Element("amount_transaction");
            amountTransaction.setText(Float.toString(tabTransaction.get(i).getAmountTransaction()));
            idTransaction.addContent(amountTransaction);

			/*Last Name Customer*/
            Element lastNameCustomer = new Element("lastname_customer");
            lastNameCustomer.setText(tabTransaction.get(i).getLastNameCustomer());
            idTransaction.addContent(lastNameCustomer);

			/*First Name Customer*/
            Element firstnameCustomer = new Element("firstname_customer");
            firstnameCustomer.setText(tabTransaction.get(i).getFirstNameCustomer());
            idTransaction.addContent(firstnameCustomer);

			/*Debit Account*/
            Element debitAccount = new Element("debit_account");
            debitAccount.setText(tabTransaction.get(i).getDebitAccount());
            idTransaction.addContent(debitAccount);

			/*Date Transaction*/
            Element dateTransaction = new Element("date_transaction");
            dateTransaction.setText(formater.format(tabTransaction.get(i).getDateTransaction()));
            idTransaction.addContent(dateTransaction);
        }
        try
        {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			/*  a la place de  new FileOutputStream(getNameFile()) metre system.out pour test*/
            sortie.output(document, new FileOutputStream(getNameFile()));
            return true;
        }
        catch (IOException e){
			   /* Erreur creation fichier*/
            e.printStackTrace();
            return false;
        }
    }

    //Return file name
    private String getNameFile()
    {
        //formater de date
        // set Today
        //- not / car sinon error expect file
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
        Date today = new Date();
        return "Transaction"+formater.format(today)+".xml";
    }



}
