package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/cucumberTest/resources/features", "classpath:features"},
        glue = {"cucumber.config",
                "cucumber.steps"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html", // Relatório HTML
                "json:target/cucumber-reports/cucumber.json" // Relatório JSON
        },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.UNDERSCORE,
        dryRun = false // Defina como true para verificar se todos os steps estão implementados sem executar os testes
)
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberRunnerTest {

}

