package cucumber.config;

import com.nomad.accounting.AccountingApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AccountingApplication.class)
@ActiveProfiles("test")
public class CucumberSpringConfiguration {

}

