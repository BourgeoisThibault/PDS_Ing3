package AutomatedTests;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import cucumber.api.*;

/**
 * Created by SarahAllouche on 22/01/2018.
 */

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, tags = "@Transaction", features = "src/test/resources", plugin = {
        "pretty", "html:target/cucumber"}, glue = "definition" )
public class TransactionTA {

}
