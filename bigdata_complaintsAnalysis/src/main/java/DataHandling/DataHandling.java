package DataHandling;
import com.mongodb.spark.MongoSpark;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class DataHandling {

    private SparkSession spark;
    private JavaSparkContext jsc;

    public DataHandling(){
        this.spark = SparkSession
                .builder()
                .appName("SparkComplaints")
                .master("local[*]")
                .config("spark.mongodb.input.uri", "mongodb://127.0.0.1/ConsumerComplaint.Complaints")
                .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/ConsumerComplaint.Complaints")
                .getOrCreate();
                this.jsc = new JavaSparkContext(spark.sparkContext());
    }

    public Dataset<Row> loadData(final String path, final StructType schemaCsv){

        return spark.read()
                .option("delimiter", ",")
                .option("inferSchema", true)
                .option("multiline", true)
                .option("header", true)
                .schema(schemaCsv)
                .csv(path);
    }

    public Dataset<Row> loadDataFromMongoDb()
    {
        return MongoSpark.load(jsc).toDF();
    }


    // create initial document structure
    public StructType getSchema()
    {
        return new StructType(new StructField[]{
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
    }

    //return new format of dataset and dataset columns: with date
    public  Dataset<Row> formatDate(Dataset<Row> ds)
    {
        return ds.sqlContext().sql("SELECT TO_DATE(CAST(UNIX_TIMESTAMP(Date_received, 'MM/dd/yyyy') AS TIMESTAMP)) As Date_received,"
                + "Product, Sub_product, Issue, Sub_issue, Consumer_complaint_narrative, Company_public_response, Company, State, ZIP_code,"
                + "Tags, Consumer_consent_provided, Submitted_via, TO_DATE(CAST(UNIX_TIMESTAMP(Date_sent_to_company, 'MM/dd/yyyy') AS TIMESTAMP)) As Date_sent_to_company,"
                + "Company_response_to_consumer, Timely_response, Consumer_disputed, ComplaintID  FROM ConsumerComplaints");
    }
}