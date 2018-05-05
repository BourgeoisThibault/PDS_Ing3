package DataAccess;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.execution.datasources.jdbc.JDBCOptions;

/**
 * Created by SarahAllouche on 22/04/2018.
 */
public class DataAccess {

    private Properties connectionProperties = new Properties();
    private String url;
    private static final Logger logger = Logger.getLogger(DataAccess.class);

    public DataAccess(final String url, final String user, final String password)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connectionProperties.put("user", user);
            connectionProperties.put("password", password);
            this.url = url;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // procedures for traitments
    public void insertProductCount(Dataset<Row> ds)
    {
        logger.info("Start insertProductCount");
        String table = "CountProductsProblem";
        Dataset<Row> dsProduct = ds.groupBy("Product").count();
        dsProduct.write()
                .mode("overwrite") // to overwrite a table
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver")
                .jdbc(url, table, connectionProperties);
    }

    public void insertConsumerDisputedCount(Dataset<Row> ds)
    {
        logger.info("Start insertConsumerDisputedCount");
        String table = "CountConsumerDisputed";
        Dataset<Row> dsDisputed =   ds.groupBy("Consumer_disputed").count();
        dsDisputed.write()
                .mode("overwrite")
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver")
                .jdbc(url, table, connectionProperties);
    }

    public void insertResponseCount(Dataset<Row> ds)
    {
        logger.info("Start analysis of responses received number");
        String table = "ResponseCount";
        Dataset<Row> dsResponse = ds.groupBy("Company_response_to_consumer").count();
        dsResponse.write()
                .mode("overwrite")
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver")
                .jdbc(url, table, connectionProperties);
    }

    public void insertIssuesPerYearCount(Dataset<Row> ds)
    {
        logger.info("Start analysis on issues per year");
        String table = "IssuesPerYearCount";
        Dataset<Row> dsDate = ds.select(org.apache.spark.sql.functions.year(ds.col("Date_received"))
                .as("YearReceived")).groupBy("YearReceived").count();
        dsDate.write()
            .mode("overwrite")
            .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver")
            .jdbc(url, table, connectionProperties);

    }

}
