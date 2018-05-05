package Main;

import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import DataAccess.DataAccess;
import DataHandling.DataHandling;
import org.apache.spark.sql.types.StructType;
import org.apache.log4j.BasicConfigurator;
/**
 * Created by SarahAllouche on 16/04/2018.
 */
public class ComplaintsAnalysis {

    private String url = "jdbc:mysql://192.154.88.161:3306/esibank_decisionnel";
    private DataAccess dataAccess = new DataAccess(url,"esibank", "esibankpds");
    private DataHandling dataHandling = new DataHandling();
    // to replace with the folder on server
    private String path = "/home/esibank/Consumer_Complaints.csv";
    private static final Logger logger = Logger.getLogger(ComplaintsAnalysis.class);

    public void run()
    {
        logger.info("Start analysis");
        StructType csvStruct = dataHandling.getSchema();

        Dataset<Row> dsCsv = dataHandling.loadData(path, csvStruct);
        Dataset<Row> dsMongoDb = dataHandling.loadDataFromMongoDb();

        dsMongoDb.cache();
        dsCsv.cache();

        dsCsv.createOrReplaceTempView("ConsumerComplaints");
        dsCsv = dataHandling.formatDate(dsCsv);
        Dataset<Row> ds;

        if (dsMongoDb.count() > 0)
        {
            logger.info("Loading mongoDB succeed");
            dsMongoDb.createOrReplaceTempView("ConsumerComplaints");
            dsMongoDb = dataHandling.formatDate(dsMongoDb);
            ds = dsCsv.union(dsMongoDb);
        }
        else
        {
            logger.info("Loading mongoDB failed");
            ds = dsCsv;
        }

        ds.cache();

        dsCsv.unpersist();
        dsMongoDb.unpersist();

        //filters
        dataAccess.insertConsumerDisputedCount(ds);
        dataAccess.insertResponseCount(ds);
        dataAccess.insertProductCount(ds);
        dataAccess.insertIssuesPerYearCount(ds);

        logger.info("Analysis were created");

        ds.unpersist();
        logger.info("End of program");

    }


    public static void main( String[] args )
    {
        ComplaintsAnalysis complaintsAnalysis = new ComplaintsAnalysis();
        complaintsAnalysis.run();

    }

}
