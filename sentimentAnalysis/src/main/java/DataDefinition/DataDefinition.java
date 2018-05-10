package DataDefinition;

import com.mongodb.spark.MongoSpark;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

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

}
