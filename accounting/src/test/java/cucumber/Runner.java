package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/steps/cashbook_cucumber.feature", "src/test/resources/features/estudos/aprender_cucumber.feature"},
        glue = {"src/test/java/cucumber/steps", "src/test/java/cucumber/estudos"},
        plugin = "pretty",
        monochrome = true,
        snippets = CucumberOptions.SnippetType.UNDERSCORE,
        dryRun = false
)
public class Runner {

}

