import Main.ComplaintsAnalysis;
import com.sun.prism.PixelFormat;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import DataHandling.DataHandling;
import DataAccess.DataAccess;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)

/**
 * Created by SarahAllouche on 25/04/2018.
 */
public class TestComplaintsAnalysis {


    @Mock
    DataHandling dataHandlingMock;
    @Mock
    DataAccess dataAccess;
    @InjectMocks
    ComplaintsAnalysis consumerComplaintsSUT;

    private Dataset<Row> dsTest;
    @Before
    public void setup() throws Exception {
        Mockito.mock(ComplaintsAnalysis.class);
        SparkSession sparkMock = SparkSession.builder()
                .appName("ComplaintsTests")
                .master("local[*]")
                .getOrCreate();

        StructType structMock = new StructType(new StructField[]{
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
                new StructField("ComplaintID", DataTypes.DoubleType, false, Metadata.empty())
        });


        List<Row> data = new ArrayList<>();
        data.add(RowFactory.create("03/12/2014", "Mortgage", "Other mortgage", "Loan modification collection foreclosure", "",
                "", "", "M&T BANK CORPORATION", "MI", "48382", "","N/A","Referral", "03/17/2014","Closed with explanation", "Yes", "No",3759217.0));

        dsTest = sparkMock.createDataFrame(data, structMock).toDF("Date_received", "Product", "Sub_product", "Issue", "Sub_issue","Consumer_complaint_narrative",
                "Company_public_response", "Company" , "State", "ZIP_code", "Tags", "Consumer_consent_provided", "Submitted_via",
                "Date_sent_to_company", "Company_response_to_consumer", "Timely_response", "Consumer_disputed", "ComplaintID");


        dsTest.cache();

        Mockito.doNothing().when(dataAccess).insertConsumerDisputedCount(Matchers.any(Dataset.class));
        Mockito.doNothing().when(dataAccess).insertIssuesPerYearCount(Matchers.any(Dataset.class));
        Mockito.doNothing().when(dataAccess).insertProductCount(Matchers.any(Dataset.class));
        Mockito.doNothing().when(dataAccess).insertResponseCount(Matchers.any(Dataset.class));

        Mockito.when(dataHandlingMock.getSchema()).thenReturn(structMock);
        Mockito.when(dataHandlingMock.loadData(Matchers.any(String.class), Matchers.any(StructType.class))).thenReturn(dsTest);
        Mockito.when(dataHandlingMock.loadDataFromMongoDb()).thenReturn(dsTest);
        Mockito.when(dataHandlingMock.formatDate(Matchers.any(Dataset.class))).thenReturn(dsTest);
    }

    @Test
    public void shouldRunJob() throws Exception {
        consumerComplaintsSUT.run();
        dsTest.unpersist();

    }
}
