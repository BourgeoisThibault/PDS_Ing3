import DataDefinition.DataDefinition;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jeremy on 14/05/2018.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class TestDataAccess {


    @InjectMocks
    DataDefinition dataDefinitionSUT = new DataDefinition();

    private Dataset<Row> dsTest;
    private String path;
    private StructType structMock;

    @Before
    public void setup() throws Exception {
        Mockito.mock(DataDefinition.class);
        SparkSession sparkMock = SparkSession.builder()
                .appName("SparkTest")
                .master("local[*]")
                .getOrCreate();
        dsTest = sparkMock.emptyDataFrame();
        dsTest.cache();
        path = "Mock_surveys.csv";
        structMock = new StructType(new StructField[]{
                new StructField("id", DataTypes.LongType, false, Metadata.empty()),
                new StructField("date", DataTypes.StringType, false, Metadata.empty()),
                new StructField("note", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("commentaire", DataTypes.StringType, false, Metadata.empty())
        });

        List<Row> data = new ArrayList<>();
        data.add(RowFactory.create(1,"03/05/2018", 5, "C'est une banque très fiable"));

        // headers
        dsTest = sparkMock.createDataFrame(data, structMock).toDF("id", "date", "note", "commentaire");
    }

    @Test
    public void shouldGetSchema() throws Exception {
        StructType structTypeMock = dataDefinitionSUT.getSchema();
        StructField[] structFieldMock  = structTypeMock.fields();
        Assert.assertEquals("Error while retrieving data", structFieldMock[1].dataType(), DataTypes.StringType);
    }

    @Test
    public void shouldRetrieveDataFromCSV() throws Exception {
        long dsCount;
        Dataset<Row> dsTestLoad = dataDefinitionSUT.RetrieveDataFromCSV(path, structMock);
        dsCount = dsTestLoad.count();
        Assert.assertEquals("Error retrieve data", dsCount, 4);
    }

    @Test
    public void shouldFormatDate() throws Exception {
        dsTest.createOrReplaceTempView("surveysAnalysis");
        Dataset<Row> ds = dataDefinitionSUT.formatDate(dsTest);
        StructType structTypeMock = ds.schema();
        StructField[] structFieldMock = structTypeMock.fields();
        Assert.assertEquals("Error in the format of the name", structFieldMock[0].name(), "date");

        // Test the transformation of the string to date
        Assert.assertEquals("Error formating date", structFieldMock[0].dataType(), DataTypes.DateType);
        dsTest.unpersist();
    }
}
