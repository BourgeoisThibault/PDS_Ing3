import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by jeremy on 15/05/2018.
 */
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, tags = "@Sentiments", features = "src/test/resources", plugin = {
        "pretty", "html:target/cucumber"}, glue = "definition" )
public class RunSentimentsAnalysisTA {
}