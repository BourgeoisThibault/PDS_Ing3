import DataDefinition.DataDefinition;
import Services.DataAccess;
import Services.DataProcessing;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)


/**
 * Created by jeremy on 14/05/2018.
 */
public class TestDataProcessing {


    @Mock
    DataDefinition dataDefinitionMock;
    @Mock
    DataAccess dataAccess;
    @InjectMocks
    DataProcessing dataProcessingSUT;

    private Dataset<Row> dsTest;
    private Dataset<Row> dsTest2;
    @Before
    public void setup() throws Exception {
        Mockito.mock(DataProcessing.class);
        SparkSession sparkMock = SparkSession.builder()
                .appName("AnalysisTests")
                .master("local[*]")
                .getOrCreate();

        StructType structMock = new StructType(new StructField[]{
                new StructField("id", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("date", DataTypes.StringType, false, Metadata.empty()),
                new StructField("note", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("commentaire", DataTypes.StringType, false, Metadata.empty())
        });
                StructType structMock2 = new StructType(new StructField[]{
                new StructField("_id", DataTypes.StringType, false, Metadata.empty()),
                new StructField("date", DataTypes.StringType, false, Metadata.empty()),
                new StructField("tweetId", DataTypes.StringType, false, Metadata.empty()),
                new StructField("tweet", DataTypes.StringType, false, Metadata.empty()),
                new StructField("user_id", DataTypes.StringType, false, Metadata.empty()),
                new StructField("screen_name", DataTypes.StringType, false, Metadata.empty()),
                new StructField("RT", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("source", DataTypes.StringType, false, Metadata.empty()),
                new StructField("URLs", DataTypes.StringType, false, Metadata.empty()),
                new StructField("isretweet", DataTypes.BooleanType, false, Metadata.empty()),
                new StructField("hashtags", DataTypes.StringType, false, Metadata.empty()),
                new StructField("mentions", DataTypes.StringType, false, Metadata.empty()),
                });



        List<Row> data = new ArrayList<>();
        data.add(RowFactory.create(1,"03/05/2018", 5, "C'est une banque très fiable"));

        List<Row> data2 = new ArrayList<>();
        data2.add(RowFactory.create("5af4c90b61b17c8f4f9b7fa","08-05-2018","993626451948834816","C'est une banque très fiable","993576560383971328","JeremyEsibank",0,"http://source.fr", "",false,"",""));

        // headers
        dsTest = sparkMock.createDataFrame(data, structMock).toDF("id", "date", "note", "commentaire");
        dsTest.cache();
        dsTest2 = sparkMock.createDataFrame(data2, structMock2).toDF("_id", "date", "tweetId", "tweet","user_id","screen_name","RT","source","URLs","isretweet","hashtags","mentions");
        dsTest2.cache();

        Mockito.doNothing().when(dataAccess).InsertPositiveSurveys(Matchers.any(Dataset.class));
        Mockito.doNothing().when(dataAccess).insertNeutralSurveys(Matchers.any(Dataset.class));
        Mockito.doNothing().when(dataAccess).InsertNegativeSurveys(Matchers.any(Dataset.class));

        Mockito.when(dataDefinitionMock.getSchema()).thenReturn(structMock);
        Mockito.when(dataDefinitionMock.RetrieveDataFromCSV(Matchers.any(String.class), Matchers.any(StructType.class))).thenReturn(dsTest);
        Mockito.when(dataDefinitionMock.RetrieveDataFromMongodb()).thenReturn(dsTest2);
        Mockito.when(dataDefinitionMock.formatDate(Matchers.any(Dataset.class))).thenReturn(dsTest);
    }

    @Test
    public void shouldRunJob() throws Exception {
        dataProcessingSUT.run();
        dsTest.unpersist();
        dsTest2.unpersist();

    }
}