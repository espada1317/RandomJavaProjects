import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        features = {"src/test/resources/laptopFeatures"},
        glue = {"checkForAsusLaptops"},
        monochrome = true
)
public class RunCucumberTest {
}