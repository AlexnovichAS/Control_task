import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"ru.ozon.utils.AllureListener"},
        glue = {"ru.ozon.steps"},
        features = {"src/test/resources/"},
        tags = {"@firstTest"}
)
public class CucumberRunnerTest {
}
