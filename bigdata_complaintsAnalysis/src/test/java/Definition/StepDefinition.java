package Definition;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.Assert;
import DataHandling.DataHandling;
import DataAccess.DataAccess;

import org.springframework.context.annotation.Scope;

import cucumber.api.DataTable;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by SarahAllouche on 05/05/2018.
 */
@Scope("cucumber-glue")
public class StepDefinition {

    private DataHandling dataHandlingTest = new DataHandling();
    private StructType StrucTest;
    private Dataset <Row> dataSetTest;
    private String path;
    private File file;
    @Given("^Here is \"(.*?)\" file with the following lines$")
    public void here_is_file_with_the_following_lines(String path, DataTable lines) throws Throwable {
        this.path = path;
         file = new File(this.path);
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        for (Map<String, String> j : lines.asMaps(String.class, String.class)) {
            writer.println(j.get("Line"));
        }
        writer.close();
    }

    @Then("^I get the structType$")
    public void i_get_the_structType() throws Throwable {
        StrucTest = dataHandlingTest.getSchema();
        StructField[] structFieldTest  = StrucTest.fields();
        Assert.assertEquals("Error Not Good StructType",structFieldTest.length, 18);
        Assert.assertEquals("Error FieldName on structTypeTest", structFieldTest[14].dataType(), DataTypes.StringType);

    }

    @When("^I have the csv structType I load the csv$")
    public void i_have_the_csv_structType_I_load_the_csv() throws Throwable {
        dataSetTest = dataHandlingTest.loadData(path, StrucTest);
        dataSetTest.cache();
        long dsCount = dataSetTest.count();
        Assert.assertEquals("Error loading data", dsCount, 1);
    }

    @When("^I load the csv I create the tableview$")
    public void i_load_the_csv_I_create_the_tableview() throws Throwable {
        dataSetTest.createOrReplaceTempView("ConsumerComplaints");
    }

    @Then("^I format the Date$")
    public void i_format_the_Date() throws Throwable {
        dataSetTest = dataHandlingTest.formatDate(dataSetTest);
        StructType formatDate = dataSetTest.schema();
        StructField[] structFieldTest = formatDate.fields();
        Assert.assertEquals("Error Format data get name", structFieldTest[0].name(), "Date_received");
        Assert.assertEquals("Error Format data get name", structFieldTest[0].dataType(), DataTypes.DateType);

    }

    @When("^It s formatted I do treatment and insert in uat database$")
    public void it_s_formatted_I_do_treatment_and_insert_in_uat_database(DataTable connectionProperties) throws Throwable {
        List<String> connectionPropertie = connectionProperties.asList(String.class);
        String url = connectionPropertie.get(1);
        String user = connectionPropertie.get(3);
        String password = connectionPropertie.get(5);
        DataAccess dataAccessTest = new DataAccess(url, user, password);
        dataAccessTest.insertProductCount(dataSetTest);
        dataSetTest.unpersist();
        if(file.exists())
        {
            file.delete();
        }
    }

}
