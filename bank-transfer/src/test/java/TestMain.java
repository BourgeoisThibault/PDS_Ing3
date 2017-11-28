import Main.TransactionBank;
import Model.AccessDataTransaction;
import Service.ParserXML;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pds.esibank.models.Transaction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;

/**
 * Created by SarahAllouche on 19/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestMain {

    @Mock
    Transaction transactionMock;
    @Mock
    ParserXML parserMock;
    @Mock
    AccessDataTransaction addTransactionMock;
    @InjectMocks
    TransactionBank transferBankSUT;
    

    @Before
    public void setup() throws Exception {
        Mockito.mock(TransactionBank.class);

        List<Transaction> tabTransactionMock = new ArrayList<Transaction>();
        Date date;
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");


        //Create Transaction list for mock
        date =  formatter.parse("2017-11-20");
        Transaction transactionMocked = new Transaction();
        transactionMocked.setIdTransaction(12);
        transactionMocked.setLastNameCrediter("Dupont");
        transactionMocked.setFirstNameCrediter("Jean");
        transactionMocked.setCreditAccount("012ABC");
        transactionMocked.setImpactedbank("Societe General");
        transactionMocked.setAmountTransaction((float)13.5);
        transactionMocked.setFirstNameCustomer("Lulu");
        transactionMocked.setLastNameCustomer("Loli");
        transactionMocked.setDebitAccount("3");
        transactionMocked.setDateTransaction(date);

        Transaction transactionMocked2 = new Transaction();
        transactionMocked2.setIdTransaction(13);
        transactionMocked2.setLastNameCrediter("Duponnopo");
        transactionMocked2.setFirstNameCrediter("Jeanne");
        transactionMocked2.setCreditAccount("013ABC");
        transactionMocked2.setImpactedbank("BNP");
        transactionMocked2.setAmountTransaction((float)13.5);
        transactionMocked2.setFirstNameCustomer("Lili");
        transactionMocked2.setLastNameCustomer("Lola");
        transactionMocked2.setDebitAccount("5");
        transactionMocked2.setDateTransaction(date);

        //add transaction in the list
        tabTransactionMock.add(transactionMocked);
        tabTransactionMock.add(transactionMocked2);

		/* Mock methods for shouldSendTransaction */
		 Mockito.when(addTransactionMock.getDBTransaction()).thenReturn(tabTransactionMock);
         Mockito.when(parserMock.SetXmlDocument(any(List.class))).thenReturn(true);


    }
    /* Test if the navigation is good*/
    @Test
    public void shouldSendTransaction() throws Exception {
        boolean success;
        success = transferBankSUT.SendTransaction();
        org.junit.Assert.assertEquals(true, success);
    }

}
