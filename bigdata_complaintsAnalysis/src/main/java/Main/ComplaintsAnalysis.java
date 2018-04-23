package Main;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import DataAccess.DataAccess;
import DataHandling.DataHandling;


/**
 * Created by SarahAllouche on 16/04/2018.
 */
public class ComplaintsAnalysis {

    private DataAccess dataAccess = new DataAccess();
    private DataHandling dataHandling = new DataHandling();

    public void run()
    {

        /*
        //Create a SparkContext to initialize Spark
        SparkConf conf = new SparkConf().setAppName("CountProject").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //Path to replace with csv on server.
        String path = "//file:///Users/SarahAllouche/Documents/Consumer_Complaints.csv";

        JavaRDD<String> jrdd = sc.textFile(path);

        // Number of lines
        System.out.println("# lines: " + jrdd.count() );

        // Number of lines with Mortgage
       // System.out.println("# with mortgage : " + jrdd.filter(line -> line.split(",").equals("Mortgage") ).count() );

        // Number of lines which contains the word "Spark"
        System.out.println("# contains Mortgage: " + jrdd.filter(line -> line.contains("Mortgage") ).count() );
        */


        Dataset<Row> ds = dataHandling.loadData();
        //Charge le cache
        ds.cache();

        // Create Temp View for sql
        ds.createOrReplaceTempView("ConsumerComplaints");
        // sql for cast date
        ds = dataHandling.formatDate(ds);


       // ds.groupBy("Consumer_disputed").count().show();
       // ds.filter(ds.col("Timely_response").equalTo("No")).show();

        // print shema
       // ds.printSchema();
        // show 20 first rows
        ds.show();
        // insert filtre TimelyResponse no
         //dataAccess.insertConsumerDisputedCount(ds);
        //insert filtre on ConsumerDisputed yes
        // dataAccess.insertYesConsumerDisputed(ds);
        // decharge le cache
        ds.unpersist();

    }


    public static void main( String[] args )
    {
        ComplaintsAnalysis complaintsAnalysis = new ComplaintsAnalysis();
        complaintsAnalysis.run();

    }

}
