import java.util.ArrayList;
import java.util.List;

import main.Predict;
import dataaccess.LPDA;
import service.CSVMaker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pds.esibank.models.LeavingCustomer;

@RunWith(MockitoJUnitRunner.class)
public class CSVMakerTest {
    @InjectMocks
    CSVMaker makerMock;

    private List<LeavingCustomer> lcList = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        Mockito.mock(CSVMaker.class);

        //Creating a fake LeavingCustomer "lc"
        LeavingCustomer lc = new LeavingCustomer();
        //Setting a value to one of each attributes to test it
        lc.setTransfers(100);
        lcList.add(lc);
    }

    @Test
    public void shouldMakeStringsWithListEntity() throws Exception {
        //Convert the LeavingCustomer object attributes to array of Strings
        List<String[]> lcStringList = makerMock.beanToRecord(lcList);
        //Transfers parameter is the 6th in the list of leavingCustomer object
        Assert.assertEquals("Bean to Array of strings", lcStringList.get(1)[5], "100");
    }
}


