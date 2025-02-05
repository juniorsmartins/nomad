package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/steps", "src/test/resources/features/estudos"},
        glue = {"src/test/java/cucumber/steps", "src/test/java/cucumber/estudos"},
        plugin = {"pretty", "html:src/test/java/cucumber/relatorios", "json:src/test/java/cucumber/relatorios/report.json"},
        monochrome = true,
        snippets = CucumberOptions.SnippetType.UNDERSCORE,
        dryRun = false
)
public class RunnerTest {

}

