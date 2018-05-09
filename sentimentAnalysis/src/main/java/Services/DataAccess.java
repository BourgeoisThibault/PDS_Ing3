package Services;

import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.execution.datasources.jdbc.JDBCOptions;

import java.util.Properties;

/**
 * Created by jeremy on 09/05/2018.
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
    public void InsertTweetsCount(Dataset<Row> dataset)
    {
        logger.info("Methode InsertTweetsCount - Start");
        String table = "TweetsCount";
        System.out.println("Strucutre : " + dataset.toString());

        Dataset<Row> dsNbTweets = dataset.groupBy("_id").count();
        System.out.println("Strucutre : " + dsNbTweets.toString());
        System.out.println("Nb : " + dsNbTweets);

        dsNbTweets.write()
                .mode("overwrite") // to overwrite a table
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver")
                .jdbc(url, table, connectionProperties);
    }

    public void InsertPositiveTweetsCount(Dataset<Row> dataset)
    {
        //Text mining
    }

    public void InsertNegativeTweetsCount(Dataset<Row> dataset)
    {
        //Text Mining
    }


}
