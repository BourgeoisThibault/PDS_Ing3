import Beans.Transaction;
import Main.transactionBank;
import Model.modelJPA;
import ServiceXml.ParserXML;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SarahAllouche on 19/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestMain {

    @Mock
    Transaction transactionMock;
    @Mock ParserXML parserMock;
    @Mock modelJPA modelMock;

    @InjectMocks
    transactionBank transferBankSUT;


    @Before
    public void setup() throws Exception {
        Mockito.mock(transactionBank.class);
        ArrayList <Transaction> tabTransactionMock = new ArrayList <Transaction>();
        Date d;
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        d =  formatter.parse("2017-11-20");
        //Create Transaction Mock
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
        transactionMocked.setDateTransaction(d);

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
        transactionMocked2.setDateTransaction(d);
        //ajout des virement a l'arraylist
        tabTransactionMock.add(transactionMocked);
        tabTransactionMock.add(transactionMocked2);

		/*Comportement Mock for shouldSendTransaction*/
        Mockito.when(modelMock.getTransaction()).thenReturn(tabTransactionMock);
        Mockito.when(parserMock.SetXmlDocument(Matchers.any(ArrayList.class))).thenReturn(true);


    }
    /*test to send an XML*/
    @Test
    public void shouldSendTransaction() throws Exception {

        transferBankSUT.SendTransaction();
    }

}
