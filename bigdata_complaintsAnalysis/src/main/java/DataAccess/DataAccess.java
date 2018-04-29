package DataAccess;

import java.time.Year;
import java.util.Properties;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.execution.datasources.jdbc.JDBCOptions;


/**
 * Created by SarahAllouche on 22/04/2018.
 */
public class DataAccess {
    private String url = "jdbc:mysql://localhost:8889/pdstrain";
    //credentials of user with java object
    private Properties connectionProperties = new Properties();

    public DataAccess()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connectionProperties.put("user", "root");
            connectionProperties.put("password", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // procedures for traitments
    
    public void insertProductCount(Dataset<Row> ds)
    {
        String table = "CountProductsProblem";
        Dataset<Row> dsProduct = ds.groupBy("Product").count();
        dsProduct.write()
                .mode(SaveMode.Append)// safe mode
                .mode("overwrite") // ecrase est recree la table
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver") //driver
                .jdbc(url, table, connectionProperties);
    }

    public void insertConsumerDisputedCount(Dataset<Row> ds)
    {
        String table = "CountConsumerDisputed";
        Dataset<Row> dsDisputed =   ds.groupBy("Consumer_disputed").count();
        dsDisputed.write()
                .mode(SaveMode.Append)// safe mode
                .mode("overwrite") // ecrase est recree la table
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver")
                .jdbc(url, table, connectionProperties);
    }

    public void insertResponseCount(Dataset<Row> ds)
    {
        String table = "ResponseCount";
        Dataset<Row> dsResponse = ds.groupBy("Company_response_to_consumer").count();
        dsResponse.write()
                .mode("overwrite") // ecrase est recree la table
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver") //driver
                .jdbc(url, table, connectionProperties);
    }

    public void insertIssuesPerYearCount(Dataset<Row> ds)
    {
        String table = "IssuesPerYearCount";
        Dataset<Row> dsDate = ds.select(org.apache.spark.sql.functions.year(ds.col("Date_received"))
                .as("YearReceived")).groupBy("YearReceived").count();
        dsDate.write()
            .mode("overwrite") // ecrase est recree la table
            .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver") //driver
            .jdbc(url, table, connectionProperties);

    }

}
