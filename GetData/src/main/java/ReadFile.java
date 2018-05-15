import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;


public class ReadFile {

    private SparkSession spark;
    private JavaSparkContext jsc;
    private SparkConf conf;

    public ReadFile(){

       this.spark = SparkSession.builder()
                .master("local")
                .appName("MongoSparkConnectorIntro")
                .config("spark.mongodb.input.uri", "mongodb://192.154.88.173/ComplaintDB.consumer")
                .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/ComplaintDB.consumer")
                .config("spark.sql.warehouse.dir", "file:///C:/tmp/spark-warehouse")
                .getOrCreate();
       this.conf = new SparkConf().setAppName("Job Mariam").setMaster("local[*]");
       this.jsc = new JavaSparkContext(spark.sparkContext());
    }

    public JavaSparkContext getJSC(){
        return jsc;

    }

}


