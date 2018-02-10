package Service;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import pds.esibank.models.Transaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Created by SarahAllouche on 17/11/2017.
 */

public class ParserXML {

    private static Logger logger = Logger.getLogger("BankTransaction");
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
    public String getNameFile()
    {
        //formater de date
        // set Today
        //- not / car sinon error expect file
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
        Date today = new Date();
        return "Transaction"+formater.format(today)+".xml";
    }


    // recupere le xml et retourne un document xml
    //méthode qui va disparaitre avec Implémentation Jérémy
    public Document GetXmlDocument(){

        Document document = null;
        SAXBuilder sxb = new SAXBuilder();
        try
        {
            //On creer un nouveau document JDOM avec en argument le fichier XML
            document = sxb.build(new File(getNameFile()));

            return document;
        }
        catch(Exception e){
	    	/*Erreur recuperation fichier*/
            e.printStackTrace();
        }
        return document;
    }


    // passe en paramètre un document xml
    // traitement du xml
    public List <Transaction> GetXmlTransaction(final Document docXmlTransaction) throws ParseException {

        List<Transaction> tableauTransaction = new ArrayList<Transaction>();
        Element Transaction = docXmlTransaction.getRootElement();
        List<Element> listVirement = Transaction.getChildren("id_transaction");
		/*creation iterator pour parcourir la list*/
        Iterator<Element> i = listVirement.iterator();
        logger.info("Get Transaction From XML");

        while(i.hasNext())
        {
            Element noeud = (Element)i.next();
            Transaction recoverTransaction = new Transaction();
            recoverTransaction.setLastNameCrediter(noeud.getChild("lastname_crediter").getText());
            recoverTransaction.setFirstNameCrediter(noeud.getChild("firstname_crediter").getText());
            recoverTransaction.setCreditAccount(noeud.getChild("credit_account").getText());
            recoverTransaction.setImpactedbank(noeud.getChild("impacted_bank").getText());
            recoverTransaction.setAmountTransaction(Float.parseFloat(noeud.getChild("amount_transaction").getText()));
            recoverTransaction.setLastNameCustomer(noeud.getChild("lastname_customer").getText());
            recoverTransaction.setFirstNameCustomer(noeud.getChild("firstname_customer").getText());
            recoverTransaction.setDebitAccount(noeud.getChild("debit_account").getText());
            recoverTransaction.setDateTransaction(java.sql.Date.valueOf(noeud.getChild("date_transaction").getText()));
            tableauTransaction.add(recoverTransaction);
        }
        return tableauTransaction;
    }

}
