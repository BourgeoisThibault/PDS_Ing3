package DataDefinition;

import com.mongodb.spark.MongoSpark;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

/**
 * Created by jeremy on 09/05/2018.
 */
public class DataDefinition {

    private SparkSession spark;
    private JavaSparkContext jsc;
    private static final Logger logger = Logger.getLogger(DataDefinition.class);


    public DataDefinition(){
        this.spark = SparkSession
                .builder()
                .appName("SentimentAnalysis")
                .master("local[*]")
                //.config("spark.mongodb.input.uri", "mongodb://192.154.88.173/tweets.tweetRaw")
                //.config("spark.mongodb.output.uri", "mongodb://192.154.88.173/tweets.tweetRaw")
                .config("spark.mongodb.input.uri", "mongodb://localhost/tweets.tweetRaw")
                .config("spark.mongodb.output.uri", "mongodb://localhost/tweets.tweetRaw")

                //.config("spark.mongodb.input.uri", "mongodb://192.154.88.173/avis.Raw")
                //.config("spark.mongodb.output.uri", "mongodb://192.154.88.173/avis.Raw")
                .getOrCreate();
        this.jsc = new JavaSparkContext(spark.sparkContext());
    }
    public Dataset<Row> RetrieveDataFromMongodb()
    {
        logger.info("Retrieve multiple data from Mongodb into Dataset");
        return MongoSpark.load(jsc).toDF();
    }

    public Dataset<Row> RetrieveDataFromCSV(final String path, final StructType schemaCsv){
        logger.info("Method RetrieveDataFromCSV - Start");

        return spark.read()
                .option("delimiter", ",")
                .option("inferSchema", true)
                .option("multiline", true)
                .option("header", true)
                .schema(schemaCsv)
                .csv(path);


    }
    public StructType getSchema()
    {
        logger.info("Method getSchema - Start : Get schema");

        return new StructType(new StructField[]{
                new StructField("id", DataTypes.LongType, false, Metadata.empty()),
                new StructField("date", DataTypes.DateType, false, Metadata.empty()),
                new StructField("note", DataTypes.IntegerType, false, Metadata.empty()),
                new StructField("commentaire", DataTypes.StringType, false, Metadata.empty())
        });
    }

}
