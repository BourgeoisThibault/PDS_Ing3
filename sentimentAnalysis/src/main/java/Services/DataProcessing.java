package Services;

import DataDefinition.DataDefinition;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;


/**
 * Created by jeremy on 09/05/2018.
 */
public class DataProcessing {

    private String url = "jdbc:mysql://192.154.88.161:3306/esibank_decisionnel";
    private DataAccess dataAccess = new DataAccess(url,"esibank", "esibankpds");
    private String path = "/home/esibank/surveys.csv";
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

        logger.info("Method run - Status : Analysis tweets succeded");

        dataset.unpersist();

        logger.info("Method run - Start : surveys analysis");
        StructType csvStruct = dataDefiniton.getSchema();

        Dataset<Row> datasetCsv = dataDefiniton.RetrieveDataFromCSV(path, csvStruct);
        datasetCsv.cache();

        datasetCsv.createOrReplaceTempView("surveysAnalysis");
        datasetCsv = dataDefiniton.formatDate(datasetCsv);
        dataset.unpersist();
        dataAccess.InsertsurveysCount(datasetCsv);
        dataAccess.InsertPositiveSurveys(datasetCsv);
        dataAccess.InsertNegativeSurveys(datasetCsv);
        dataAccess.insertNeutralSurveys(datasetCsv);

        datasetCsv.unpersist();

        logger.info("Method run - End");

    }

}
