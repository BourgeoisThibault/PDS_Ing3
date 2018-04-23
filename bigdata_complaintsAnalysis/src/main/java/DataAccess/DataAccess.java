package DataAccess;

import java.util.Properties;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
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
        Dataset<Row> dsno = ds.groupBy("Product").count();
        dsno.write()
                .mode(SaveMode.Append)// safe mode
                .mode("overwrite") // ecrase est recree la table
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver") //driver
                .jdbc(url, table, connectionProperties);
    }

    public void insertConsumerDisputedCount(Dataset<Row> ds)
    {
        String table = "CountConsumerDisputed";
        Dataset<Row> dsyes =   ds.groupBy("Consumer_disputed").count();
        dsyes.write()
                .mode(SaveMode.Append)// safe mode
                .mode("overwrite") // ecrase est recree la table
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver")
                .jdbc(url, table, connectionProperties);
    }

    public void insertResponseCount(Dataset<Row> ds)
    {
        String table = "ResponseCount";
        Dataset<Row> dsno = ds.groupBy("Company_response_to_consumer").count();
        dsno.write()
                .mode("overwrite") // ecrase est recree la table
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver") //driver
                .jdbc(url, table, connectionProperties);
    }

    public void insertIssuesPerYearCount(Dataset<Row> ds)
    {
        String table = "IssuesPerYearCount";
       /* Dataset<Row> dsno = ds.filter(ds.col("d").geq(2016)).show();
                dsno.write()
                .mode("overwrite") // ecrase est recree la table
                .option(JDBCOptions.JDBC_DRIVER_CLASS(), "com.mysql.jdbc.Driver") //driver
                .jdbc(url, table, connectionProperties);*/
    }

    /*

        ds.groupBy("Consumer_disputed").count().show();
        .show();

        // double count = ds.filter(to_date(col("Date_received").cast(date_format())).gt(01/01/2016)).count();
        //ds.select(to_date(col("Date_received"),"yyyy") == 2016);
       // ds.select(to_date(ds.col("Date_received"),"y")==2010);
                //Year($"Date").geq(lit(2016)));
                //data.filter(year($"date").geq(lit(2016)))
     */
}
