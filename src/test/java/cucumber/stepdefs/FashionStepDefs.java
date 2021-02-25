package cucumber.stepdefs;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FashionStepDefs {

	private static RemoteWebDriver driver;
	// private static WebElement target;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Given("that I can navigate to {string}")
	public void that_i_can_navigate_to(String string) {
		driver.get(string);
	}

	@When("I search for {string}")
	public void i_search_for(String string) {
		driver.findElementByXPath("//*[@id=\"search_query_top\"]").sendKeys(string);
		driver.findElementByXPath("//*[@id=\"searchbox\"]/button").submit();
	}

	@Then("I can find a {string} in the returned terms.")
	public void i_can_find_a_in_the_returned_terms(String string) {
		driver.findElementsByXPath("//*[@id='center_column']/ul/li[1]/div/div[2]/h5/child").forEach(item -> {
			assertTrue(item.getCssValue("title").contains(string));
		});
	}

	@After
	public static void tearDown() {
		driver.quit();
		System.out.println("driver closed");
	}
}
