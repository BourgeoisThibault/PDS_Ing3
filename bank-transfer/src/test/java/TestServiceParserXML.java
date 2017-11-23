/**
 * Created by SarahAllouche on 22/11/2017.
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.mockito.*;
import org.mockito.runners.*;

import ServiceXml.ParserXML;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.Assert;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class TestServiceParserXML {
/*
    @Mock
    Transaction VirementMock;

    @InjectMocks
    ParserXML ParserXMLSUT;

    ArrayList<Transaction> tabTransactionMock = new ArrayList<Transaction>();

    @Before
    public void setup() throws Exception {

        Mockito.mock(ParserXML.class);
        Date d;
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        d = formatter.parse("2017-11-20");
        //Create Transaction Mock
        Transaction transactionMocked = new Transaction();
        transactionMocked.setIdTransaction(12);
        transactionMocked.setLastNameCrediter("Dupont");
        transactionMocked.setFirstNameCrediter("Jean");
        transactionMocked.setCreditAccount("012ABC");
        transactionMocked.setImpactedbank("Societe General");
        transactionMocked.setAmountTransaction((float) 13.5);
        transactionMocked.setFirstNameCustomer("Lulu");
        transactionMocked.setLastNameCustomer("Loli");
        transactionMocked.setDebitAccount("3");
        transactionMocked.setDateTransaction(d);

        Transaction transactionMocked2 = new Transaction();
        transactionMocked2.setIdTransaction(13);
        transactionMocked2.setLastNameCrediter("Duponnopo");
        transactionMocked2.setFirstNameCrediter("Jeanne");
        transactionMocked2.setCreditAccount("013ABC");
        transactionMocked2.setImpactedbank("BNP");
        transactionMocked2.setAmountTransaction((float) 13.5);
        transactionMocked2.setFirstNameCustomer("Lili");
        transactionMocked2.setLastNameCustomer("Lola");
        transactionMocked2.setDebitAccount("5");
        transactionMocked2.setDateTransaction(d);

        //Add Transaction Mock in the tab
        tabTransactionMock.add(transactionMocked);
        tabTransactionMock.add(transactionMocked2);

    }

    @Test /*test creation of an XML */
   /* public void shouldCreateXml() throws Exception {
        boolean Creation;
        Creation = ParserXMLSUT.SetXmlDocument(tabTransactionMock);
        Assert.assertEquals(true, Creation);
    }*/
}