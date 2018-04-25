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
    private String path = "/Users/SarahAllouche/Documents/Consumer_Complaints.csv";

    public void run()
    {

       // System.setProperty("hadoop.home.dir","C:\\hadoop" );
        Dataset<Row> ds = dataHandling.loadData(path);

        ds.cache();

        ds.createOrReplaceTempView("ConsumerComplaints");

        ds = dataHandling.formatDate(ds);
        ds.printSchema();
        //filters

        dataAccess.insertConsumerDisputedCount(ds);
        dataAccess.insertResponseCount(ds);
        dataAccess.insertProductCount(ds);


        //dataAccess.insertIssuesPerYearCount(ds);


        ds.unpersist();

    }


    public static void main( String[] args )
    {
        ComplaintsAnalysis complaintsAnalysis = new ComplaintsAnalysis();
        complaintsAnalysis.run();

    }

}
