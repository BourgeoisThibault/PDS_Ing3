package Definition;

import DataDefinition.DataDefinition;
import Services.DataAccess;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.Assert;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by jeremy on 15/05/2018.
 */
@Scope("cucumber-glue")
public class StepDefinition {

    private DataDefinition dataDefinitionTest = new DataDefinition();
    private StructType StrucTest;
    private Dataset <Row> dataSetTest;
    private String file_path;
    private File file;
    @Given("^Here is \"(.*?)\" file with the following lines$")
    public void here_is_file_with_the_following_lines(String path, DataTable dt) throws Throwable {
        this.file_path = path;
        file = new File(this.file_path);
        PrintWriter writer = new PrintWriter(file_path, "UTF-8");
        for (Map<String, String> line : dt.asMaps(String.class, String.class)) {
            writer.println(line.get("Line"));
        }
        writer.close();
    }

    @Then("^I get the structType$")
    public void i_get_the_structType() throws Throwable {
        StrucTest = dataDefinitionTest.getSchema();
        StructField[] structTypeTest  = StrucTest.fields();
        Assert.assertEquals("Error on the structType",structTypeTest.length, 4);
        Assert.assertEquals("Error on the FieldName of structTypeTest", structTypeTest[3].dataType(), DataTypes.StringType);

    }

    @When("^I have the csv structType I load the csv$")
    public void i_have_the_csv_structType_I_load_the_csv() throws Throwable {
        dataSetTest = dataDefinitionTest.RetrieveDataFromCSV(file_path, StrucTest);
        dataSetTest.cache();
        long dsCount = dataSetTest.count();
        Assert.assertEquals("Error loading data", dsCount, 1);
    }

    @When("^I load the csv I create the tableview$")
    public void i_load_the_csv_I_create_the_tableview() throws Throwable {
        dataSetTest.createOrReplaceTempView("surveysAnalysis");
    }

    @Then("^I format the Date$")
    public void i_format_the_Date() throws Throwable {
        dataSetTest = dataDefinitionTest.formatDate(dataSetTest);
        StructType formatDate = dataSetTest.schema();
        StructField[] structFieldTest = formatDate.fields();
        Assert.assertEquals("Error on the name of the recovered data", structFieldTest[0].name(), "date");
        Assert.assertEquals("Error on the type of the recovered data", structFieldTest[0].dataType(), DataTypes.DateType);

    }

    @When("^It s formatted I do treatment and insert in uat database$")
    public void it_s_formatted_I_do_treatment_and_insert_in_uat_database(DataTable connectionProperties) throws Throwable {
        List<String> connectionPropertie = connectionProperties.asList(String.class);
        String url = connectionPropertie.get(1);
        String user = connectionPropertie.get(3);
        String password = connectionPropertie.get(5);
        DataAccess dataAccessTest = new DataAccess(url, user, password);
        dataAccessTest.InsertsurveysCount(dataSetTest);
        dataSetTest.unpersist();
        if(file.exists())
        {
            file.delete();
        }
    }

}
