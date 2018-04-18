package Main;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import org.apache.spark.api.java.JavaRDD;

/**
 * Created by SarahAllouche on 16/04/2018.
 */
public class ComplaintsAnalysis {

    public static void main( String[] args )
    {


        //Create a SparkContext to initialize Spark
        SparkConf conf = new SparkConf().setAppName("CountProject").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //Path to replace with csv on server.
        String path = "//file:///Users/SarahAllouche/Documents/Consumer_Complaints.csv";

        JavaRDD<String> jrdd = sc.textFile(path.toString());

// Number of lines
        System.out.println("# lines: " + jrdd.count() );

// Number of lines with more than 5 words
       // System.out.println("# with mortgage : " + jrdd.filter(line -> line.split(",").equals("Mortgage") ).count() );

// Number of lines which contains the word "Spark"
        System.out.println("# contains Mortgage: " + jrdd.filter(line -> line.contains("Mortgage") ).count() );
    }

}
