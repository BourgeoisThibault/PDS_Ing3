package AutomatedTests.Definition;


import Model.AccessDataTransaction;
import Service.ParserXML;
import cucumber.api.DataTable;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.springframework.context.annotation.Scope;
import pds.esibank.models.Transaction;

/**
 * Created by SarahAllouche on 22/01/2018.
 */

@Scope("cucumber-glue")
public class StepDefinition {
    private static final Logger logger = Logger.getLogger(StepDefinition.class);
    private AccessDataTransaction accessDataTest = new AccessDataTransaction();
    private ParserXML ParserXMLTest = new ParserXML();
    private Document docXml;
    List<Transaction> listTransactionTest = new ArrayList<Transaction>();
    List<Transaction> listOfTransactionFromDoc = new ArrayList<Transaction>();


    @Given("^A transaction list$")
    public void a_transaction_list(@Format("yyyy-MM-dd")DataTable listOfTransaction) throws Throwable {
        logger.info("Given a List of Transaction (DataTable)");
        System.out.println("Given a List of Transaction (DataTable) \n");
        listTransactionTest = listOfTransaction.transpose().asList(Transaction.class);
        Assert.assertEquals("Test For method a_transaction_list not good",
                listTransactionTest.get(1).getFirstNameCrediter(), "Emma");
    }

    @When("^I receive the transactions from the database$")
    public void i_receive_the_transactions_from_the_database() throws Throwable {
        logger.info("Test Access Data Get Method");
        System.out.println("Test Access Data Get Method");
        List<Transaction> transactionFromADT = new ArrayList<Transaction>();
        transactionFromADT = accessDataTest.getDBTransaction();
        Assert.assertFalse("Test For method getDBTransaction not good", transactionFromADT.size() < 0);
    }

    @Then("^Making an XML document$")
    public void making_an_XML_document() throws Throwable {
        logger.info("Then I Make Xml Document With Transation insert in Data Table");
        System.out.println("Then I Make Xml Document With Transation insert in Data Table \n");
        Boolean testXmlDocument;
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
        Date today = new Date();
        testXmlDocument = ParserXMLTest.SetXmlDocument(listTransactionTest);
        Assert.assertTrue("Test For method SetXmlDocument not good", testXmlDocument);
        Assert.assertTrue("Test For method SetXmlDocument not good",
            new File("Transaction"+formater.format(today)+".xml").exists());
    }

    @Given("^A Xml document \"(.*?)\"$")
    public void a_Xml_document(String transactionXml) throws Throwable {
        logger.info("Given Xml Document in String");
        System.out.println("Given Xml Document in String \n");
        SAXBuilder builder = new SAXBuilder();
        try
        {
            docXml = builder.build((new StringReader(transactionXml)));
        } catch (Exception e) {
            System.out.println("I not Build The Xml Document with this string document "+ e.toString());
        }
        Assert.assertNotNull("Test For method a_Xml_document not good", docXml);
    }


    @When("^I receive the document I extract the transactions$")
    public void i_receive_the_document_I_extract_the_transactions() throws Throwable {
        logger.info("When I receive the transaction from this document I make a list");
        System.out.println("When I receive the transaction from this document I make a list \n");
        listOfTransactionFromDoc = ParserXMLTest.GetXmlTransaction(docXml);
        Assert.assertEquals("Test For method GetXmlTransaction not good",
            listOfTransactionFromDoc.get(0).getFirstNameCrediter(), "Marie");
    }

    @Then("^I insert them into the database$")
    public void i_insert_them_into_the_database() throws Throwable {
        logger.info("Then I Insert Transation Into the DataBase");
        System.out.println("Then I Insert Transation Into the DataBase \n");
        accessDataTest.InputTransaction(listOfTransactionFromDoc);
    }

}