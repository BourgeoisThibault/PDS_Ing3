package DataDefinition;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

/**
 * Created by jeremy on 09/05/2018.
 */
public class DataDefinition {

    private SparkSession spark;
    private JavaSparkContext jsc;

    public DataDefinition(){
        this.spark = SparkSession
                .builder()
                .appName("SentimentAnalysis")
                .master("local[*]")
                //.config("spark.mongodb.input.uri", "mongodb://192.154.88.173/tweets.tweetRaw")
                //.config("spark.mongodb.output.uri", "mongodb://192.154.88.173/tweets.tweetRaw")
                .config("spark.mongodb.input.uri", "mongodb://127.0.0.1/tweets.tweetRaw")
                .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/tweets.tweetRaw")
                .getOrCreate();
        this.jsc = new JavaSparkContext(spark.sparkContext());
    }

}
