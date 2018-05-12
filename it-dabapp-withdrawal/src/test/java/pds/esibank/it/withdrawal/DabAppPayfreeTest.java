package pds.esibank.it.withdrawal;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author BOURGEOIS Thibault
 * Date     24/11/2017
 * Time     16:28
 */
//@ContextConfiguration
//@SpringBootTest
@RunWith(Cucumber.class)
@CucumberOptions(tags = "@dabapp-cucumber",
        plugin = {"pretty", "html:target/cucumber" },
        features = "src/main/resources",
        glue = "pds/esibank/it/withdrawal")
public class DabAppPayfreeTest {
}
