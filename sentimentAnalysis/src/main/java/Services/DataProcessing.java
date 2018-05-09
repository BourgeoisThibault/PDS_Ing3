package Services;

import DataDefinition.DataDefinition;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * Created by jeremy on 09/05/2018.
 */
public class DataProcessing {
    private DataDefinition dataDefiniton = new DataDefinition();
    private static final Logger logger = Logger.getLogger(DataProcessing.class);

    public void run()
    {
        logger.info("Method run - Start : Sentiment Analysis");
        Dataset<Row> dataset = dataDefiniton.RetrieveDataFromMongodb();
        dataset.cache();


    }



    public static void main( String[] args )
    {
        DataProcessing SentimentAnalysis = new DataProcessing();
        SentimentAnalysis.run();

    }


}
