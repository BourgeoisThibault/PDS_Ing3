package Services;

import DataDefinition.DataDefinition;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * Created by jeremy on 09/05/2018.
 */
@Configuration
public class DataProcessing {
   // @Value("${url.secure}")
   // private String url;

    @Value("${db.mysql-login}")
    private String login;

    @Value("${db.mysl-password}")
    private String password;

    //private String url = "jdbc:mysql://192.154.88.161:3306/esibank_decisionnel";
    private String url = "jdbc:mysql://localhost:8889/esibank_decisionnel";
    private DataAccess dataAccess = new DataAccess(url,"esibank", "esibankpds");
    private DataDefinition dataDefiniton = new DataDefinition();
    private static final Logger logger = Logger.getLogger(DataProcessing.class);

    public void run()
    {
        logger.info("Method run - Start : Sentiment Analysis");
        Dataset<Row> dataset = dataDefiniton.RetrieveDataFromMongodb();
        dataset.cache();

        if (dataset.count() > 0)
        {
            logger.info("Method run - Status : Loading data succeed");
            dataset.createOrReplaceTempView("tweets");
        }
        else
        {
            logger.info("Method run - Status : Loading data failed");
        }

        dataset.unpersist();

        dataAccess.InsertTweetsCount(dataset);

        logger.info("Method run - Status : Analysis succeded");

        dataset.unpersist();
        logger.info("Method run - End");

    }

    public static void main( String[] args )
    {
        DataProcessing SentimentAnalysis = new DataProcessing();
        SentimentAnalysis.run();

    }
}
