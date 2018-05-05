import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Created by SarahAllouche on 05/05/2018.
 */

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, tags = "@Complaints", features = "src/test/resources", plugin = {
        "pretty", "html:target/cucumber"}, glue = "definition" )
public class RunComplaintsTA {
}
