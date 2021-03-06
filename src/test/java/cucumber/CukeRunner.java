package cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/cuke",
		tags = "not @ignore",
		stepNotifications = true,
		glue = "cucumber.stepdefs",
		plugin = {"pretty", "html:target/cucumber-reports.html"},
		monochrome = true)
public class CukeRunner {

}
