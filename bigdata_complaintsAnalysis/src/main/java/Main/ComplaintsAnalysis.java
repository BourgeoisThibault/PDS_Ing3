package Main;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.catalyst.expressions.Year;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import org.apache.spark.api.java.JavaRDD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Created by SarahAllouche on 16/04/2018.
 */
public class ComplaintsAnalysis {



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

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SparkSession spark = SparkSession
                .builder()
                .appName("Spark Sarah")
                .master("local[*]")
                .getOrCreate();

        Dataset<Row> ds = spark.read().option("delimiter", ",")
                .option("header", true)
                .option("Date_received", "dd/MM/yyyy")
                .csv("/Users/SarahAllouche/Documents/Consumer_Complaints.csv");
        //Charge le cache
        //ds.cache();
        ds.groupBy("Product").count().show();
        ds.groupBy("Consumer_disputed").count().show();
        ds.groupBy("Company_response_to_consumer").count().show();

        // double count = ds.filter(to_date(col("Date_received").cast(date_format())).gt(01/01/2016)).count();
        //ds.select(to_date(col("Date_received"),"yyyy") == 2016);
       // ds.select(to_date(ds.col("Date_received"),"y")==2010);
                //Year($"Date").geq(lit(2016)));
                //data.filter(year($"date").geq(lit(2016)))

        //double count = ds.count();
        //System.out.println("le count " + count);

        //ds.show();
        // decharge le cache
        ds.unpersist();
    }


    public static void main( String[] args )
    {
        ComplaintsAnalysis j = new ComplaintsAnalysis();
        j.run();

    }

}
