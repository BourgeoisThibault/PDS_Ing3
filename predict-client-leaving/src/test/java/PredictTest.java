
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
public class PredictTest {
    @Mock
    CSVMaker makerMock;
    @Mock
    LPDA lpdaMock;

    @InjectMocks
    Predict predictMock;

    private List<LeavingCustomer> lcList = new ArrayList<>();

    @Before
    public void setup() throws Exception {
        Mockito.mock(Predict.class);

		//Fixing mocked methods' behaviour
        Mockito.when(lpdaMock.getLeavingCustomer()).thenReturn(lcList);
        Mockito.when(makerMock.beanToRecord(Matchers.anyListOf(LeavingCustomer.class))).thenReturn(null);
        Mockito.doNothing().when(makerMock).listToCSV(Matchers.anyListOf(String[].class));
    }

    @Test
    public void shouldFindNoLeavingCustomer() throws Exception {
        Assert.assertFalse("Leaving customer found", predictMock.makeCsv());
    }

    @Test
    public void shouldFindALeavingCustomer() throws Exception {
        //Mock a leaving customer
        LeavingCustomer lc = new LeavingCustomer();
        lc.setConnections(10);
        lcList.add(lc);
        Assert.assertTrue("Leaving customer found", predictMock.makeCsv());
    }
}
