import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.Assert;

import DataHandling.DataHandling;

/**
 * Created by SarahAllouche on 23/04/2018.
 */

/*
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class TestDataHandling {
    @InjectMocks
    DataHandling dataHandlingSUT = new DataHandling();

    private Dataset<Row> dsTest;

    @Before
    public void setup() throws Exception {
        Mockito.mock(DataHandling.class);
        dsTest = dataHandlingSUT.loadData();
    }

    @Test
    public void shouldGetSchema() throws Exception {
        StructType structTypeMock = dataHandlingSUT.getSchema();
        StructField[] structFieldMock  = structTypeMock.fields();
        Assert.assertEquals("Error FieldName on structTypeMock", structFieldMock[14].dataType(), DataTypes.StringType);
    }

    @Test
    public void shouldLoad() throws Exception {
        long dsCount;
        Dataset<Row> dsTestLoad = dataHandlingSUT.loadData();
        dsCount = dsTestLoad.count();
        Assert.assertEquals("Error loading data", dsCount, 1012375);
    }

    @Test
    public void shouldzFormatDate() throws Exception {
        dsTest.createOrReplaceTempView("ConsumerComplaints");
        Dataset<Row> dsTest2 = dataHandlingSUT.formatDate(dsTest);
        StructType structTypeMock = dsTest2.schema();
        StructField[] structFieldMock = structTypeMock.fields();
        // test le nom
        Assert.assertEquals("Error Format data get name", structFieldMock[0].name(), "Date_received");
        // test changement de type du champ strign -> date
        Assert.assertEquals("Error Format data get name", structFieldMock[0].dataType(), DataTypes.DateType);
        dsTest.unpersist();
    }

}*/
