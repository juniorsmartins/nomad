package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/steps", "src/test/resources/features/estudos"},
        glue = {"cucumber.steps", "cucumber.estudos"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html", // Relatório HTML
                "json:target/cucumber-reports/cucumber.json" // Relatório JSON
        },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.UNDERSCORE,
        dryRun = false // Defina como true para verificar se todos os steps estão implementados sem executar os testes
)
public class RunnerTest {

}

