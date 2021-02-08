package acceptance

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

/**
 * @author : hospicehounsou
 * @created : 08/02/2021
 * @linkedin: https://www.linkedin.com/in/hospicehounsou/
 **/

@RunWith(Cucumber::class)
@CucumberOptions(features = ["src/test/resources/cucumber"],
        plugin = ["pretty", "html:FeaturesReport.html"])
class RunKukesTest