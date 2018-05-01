import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.Metadata;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SarahAllouche on 23/04/2018.
 */


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class TestDataHandling {


    @InjectMocks
    DataHandling dataHandlingSUT = new DataHandling();

    private Dataset<Row> dsTest;
    private String path;
    private StructType structMock;

    @Before
    public void setup() throws Exception {
        Mockito.mock(DataHandling.class);
        SparkSession sparkMock = SparkSession.builder()
                .appName("SparkTest")
                .master("local[*]")
                .getOrCreate();
        dsTest = sparkMock.emptyDataFrame();
        dsTest.cache();
        path = "Mock_ConsumersComplaints.csv";
        structMock = new StructType(new StructField[]{
                new StructField("Date_received", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Product", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Sub_product", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Issue", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Sub_issue", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Consumer_complaint_narrative", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Company_public_response", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Company", DataTypes.StringType, false, Metadata.empty()),
                new StructField("State", DataTypes.StringType, false, Metadata.empty()),
                new StructField("ZIP_code", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Tags", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Consumer_consent_provided", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Submitted_via", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Date_sent_to_company", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Company_response_to_consumer", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Timely_response", DataTypes.StringType, false, Metadata.empty()),
                new StructField("Consumer_disputed", DataTypes.StringType, false, Metadata.empty()),
                new StructField("ComplaintID", DataTypes.DoubleType, false, Metadata.empty())});

        List<Row> data = new ArrayList<>();
        data.add(RowFactory.create("03/12/2014", "Mortgage", "Other mortgage", "Loan modification collection foreclosure", "",
                "", "", "M&T BANK CORPORATION", "MI", "48382", "","N/A","Referral", "03/17/2014","Closed with explanation", "Yes", "No",759217));

        dsTest = sparkMock.createDataFrame(data, structMock).toDF("Date_received", "Product", "Sub_product", "Issue", "Sub_issue","Consumer_complaint_narrative",
                "Company_public_response", "Company" , "State", "ZIP_code", "Tags", "Consumer_consent_provided", "Submitted_via",
                "Date_sent_to_company", "Company_response_to_consumer", "Timely_response", "Consumer_disputed", "ComplaintID");
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
        Dataset<Row> dsTestLoad = dataHandlingSUT.loadData(path, structMock);
        dsCount = dsTestLoad.count();
        Assert.assertEquals("Error loading data", dsCount, 4);
    }

    @Test
    public void shouldzFormatDate() throws Exception {
        dsTest.createOrReplaceTempView("ConsumerComplaints");
        Dataset<Row> dsTest2 = dataHandlingSUT.formatDate(dsTest);
        StructType structTypeMock = dsTest2.schema();
        StructField[] structFieldMock = structTypeMock.fields();
        // test name
        Assert.assertEquals("Error Format data get name", structFieldMock[0].name(), "Date_received");
        // test to transform string to date
        Assert.assertEquals("Error Format data get name", structFieldMock[0].dataType(), DataTypes.DateType);
        dsTest.unpersist();
    }
}
