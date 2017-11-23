import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author BOURGEOIS Thibault
 * Date     22/11/2017
 * Time     20:48
 */
@RunWith(Cucumber.class)
@CucumberOptions(tags = "@thibault-example-cucumber",
        features = "src/main/resources",
        glue = "pds/esibank/it/example")
public class CucumberTest {
}
