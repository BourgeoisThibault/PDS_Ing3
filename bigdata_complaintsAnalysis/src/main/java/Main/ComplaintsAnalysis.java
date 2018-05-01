package Main;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import DataAccess.DataAccess;
import DataHandling.DataHandling;
import org.apache.spark.sql.types.StructType;
/**
 * Created by SarahAllouche on 16/04/2018.
 */
public class ComplaintsAnalysis {

    private DataAccess dataAccess = new DataAccess();
    private DataHandling dataHandling = new DataHandling();
    // to replace with the folder on server
    private String path = "/home/esibank/Consumer_Complaints.csv";

    public void run()
    {

        StructType csvStruct = dataHandling.getSchema();

        Dataset<Row> dsCsv = dataHandling.loadData(path, csvStruct);
        Dataset<Row> dsMongoDb = dataHandling.loadDataFromMongoDb();

        dsMongoDb.cache();
        dsCsv.cache();

        dsMongoDb.createOrReplaceTempView("ConsumerComplaints");
        dsMongoDb = dataHandling.formatDate(dsMongoDb);
        dsMongoDb.printSchema();
        dsCsv.createOrReplaceTempView("ConsumerComplaints");
        dsCsv = dataHandling.formatDate(dsCsv);


        Dataset <Row> ds = dsCsv.union(dsMongoDb);
        ds.cache();

        dsCsv.unpersist();
        dsMongoDb.unpersist();

        //filters
        dataAccess.insertConsumerDisputedCount(ds);
        dataAccess.insertResponseCount(ds);
        dataAccess.insertProductCount(ds);
        dataAccess.insertIssuesPerYearCount(ds);


        ds.unpersist();

    }


    public static void main( String[] args )
    {
        ComplaintsAnalysis complaintsAnalysis = new ComplaintsAnalysis();
        complaintsAnalysis.run();

    }

}
