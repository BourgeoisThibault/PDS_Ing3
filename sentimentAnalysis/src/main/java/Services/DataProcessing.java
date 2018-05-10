package Services;

import DataDefinition.DataDefinition;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Component;


/**
 * Created by jeremy on 09/05/2018.
 */
@Component
public class DataProcessing {
/*
    public static String DATA_ACCESS_URI;
    public static String SECURE_LOGIN;
    public static String SECURE_PASSWORD;


    @Value("${uri.secure}")
    public void setSECURE_URI(String DATA_ACCESS_URI) {
        this.DATA_ACCESS_URI = DATA_ACCESS_URI;
    }

    @Value("${db.mysql-login}")
    public void setSECURE_LOGIN(String SECURE_LOGIN) {
        this.SECURE_LOGIN = SECURE_LOGIN;
    }

    @Value("${db.mysl-password}")
    public void setSECURE_PASSWORD(String SECURE_PASSWORD) {
        this.SECURE_PASSWORD = SECURE_PASSWORD;
    }*/

    //private String url = "jdbc:mysql://192.154.88.161:3306/esibank_decisionnel";
    private String url = "jdbc:mysql://127.0.0.1:8889/esibank_decisionnel";
    private DataAccess dataAccess = new DataAccess(url,"esibank", "esibankpds");
    //private DataAccess dataAccess = new DataAccess(DATA_ACCESS_URI,SECURE_LOGIN, SECURE_PASSWORD);

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
