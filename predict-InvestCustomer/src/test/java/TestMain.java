import java.util.ArrayList;
import java.util.List;

import Main.PredictInvestCustomer;
import Model.DataAccessInvestCustomer;
import Service.MakeCSV;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pds.esibank.models.InvestCustomer;

/**
 * Created by SarahAllouche on 09/02/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMain {
    @Mock
    MakeCSV makeCsvMock;
    @Mock
    DataAccessInvestCustomer investCustomerDAMock;

    @InjectMocks
    PredictInvestCustomer MainCsvMock;

    @Before
    public void setup() throws Exception {
        Mockito.mock(PredictInvestCustomer.class);
        ///Mock Entity
        InvestCustomer firstCustomerMock = new InvestCustomer();
        firstCustomerMock.SetId(1);
        firstCustomerMock.SetFirstName("Sarah");
        firstCustomerMock.SetLastName("Allouche");
        firstCustomerMock.SetSalary(1900.80f);
        firstCustomerMock.SetStatus(1);
        firstCustomerMock.SetSold(10000f);
        firstCustomerMock.SetSold1(12000.50f);
        firstCustomerMock.SetSold2(9000f);

        InvestCustomer secondCustomerMock = new InvestCustomer();
        secondCustomerMock.SetId(2);
        secondCustomerMock.SetFirstName("Alex");
        secondCustomerMock.SetLastName("Martin");
        secondCustomerMock.SetSalary(1000.20f);
        secondCustomerMock.SetStatus(2);
        secondCustomerMock.SetSold(100f);
        secondCustomerMock.SetSold1(1000.9f);
        secondCustomerMock.SetSold2(1500f);

        // Add This Entity on List
        List<String[]> listMock = new ArrayList<String[]>();
        List<InvestCustomer> listOfInvestCustomer = new ArrayList<InvestCustomer>();
        listMock.add(firstCustomerMock.ToString());
        listMock.add(secondCustomerMock.ToString());

        listOfInvestCustomer.add(firstCustomerMock);
        listOfInvestCustomer.add(secondCustomerMock);

		/*Mock Procedure Call*/

        Mockito.when(investCustomerDAMock.getInvestCustomer()).thenReturn(listOfInvestCustomer);
        Mockito.when(makeCsvMock.BeanToRecord(Matchers.anyListOf(InvestCustomer.class))).thenReturn(listMock);
        Mockito.doNothing().when(makeCsvMock).MakeCsvFromList(Matchers.anyListOf(String[].class));
    }

    @Test
    public void shouldCreateCsvFile() throws Exception {
        Boolean makeCsv;
        makeCsv = MainCsvMock.MakeCsv();
        Assert.assertTrue("Main does not work", makeCsv);
    }
}
