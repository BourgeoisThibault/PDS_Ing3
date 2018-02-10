import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Service.MakeCSV;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pds.esibank.models.InvestCustomer;


/**
 * Created by SarahAllouche on 09/02/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMakeCsv {
    private List<String[]> listMock = new ArrayList<String[]>();
    private List<InvestCustomer> listOfCustomerMock = new ArrayList<InvestCustomer>();

    @InjectMocks
    MakeCSV makeCsvMock;

    @Before
    public void setup() throws Exception {
        Mockito.mock(MakeCSV.class);
        ///Mock Entity
        InvestCustomer firstCustomerMock = new InvestCustomer();
        firstCustomerMock.SetId(0);
        firstCustomerMock.SetFirstName("Sarah");
        firstCustomerMock.SetLastName("Allouche");
        firstCustomerMock.SetSalary(1900.80f);
        firstCustomerMock.SetSold(10000f);
        firstCustomerMock.SetSold1(12000.50f);
        firstCustomerMock.SetSold2(9000f);
        firstCustomerMock.SetStatus(1);

        InvestCustomer secondCustomerMock = new InvestCustomer();
        secondCustomerMock.SetId(1);
        secondCustomerMock.SetFirstName("Alex");
        secondCustomerMock.SetLastName("Martin");
        secondCustomerMock.SetSalary(1000.20f);
        secondCustomerMock.SetSold(100f);
        secondCustomerMock.SetSold1(1000.90f);
        secondCustomerMock.SetSold2(1500f);
        secondCustomerMock.SetStatus(2);
        // Add This Entity on List
        listMock.add(firstCustomerMock.ToString());
        listMock.add(secondCustomerMock.ToString());
        listOfCustomerMock.add(firstCustomerMock);
        listOfCustomerMock.add(secondCustomerMock);
    }

    @Test
    public void shouldMakeCsvFileFromList() throws Exception {
        Path p = Paths.get("InvestCustomer.csv");
        Boolean exists = Files.exists(p);
        if (exists) {
            Files.delete(p);
        }
        makeCsvMock.MakeCsvFromList(listMock);
        exists = Files.exists(p);
        Assert.assertTrue("BeanToRecord does not work", exists);
       if (exists) {
            Files.delete(p);
        }
    }

    @Test
    public void shouldMakeStringsWithListEntity() throws Exception {
        List<String[]> listTest = new ArrayList<String[]>();
        listTest = makeCsvMock.BeanToRecord(listOfCustomerMock);
        Assert.assertEquals("BeanToRecord does not work", listTest.get(1)[1], "Sarah");
    }

}
