package cucumber.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		waitUntilLoaded(driver);
	}

	@When("I search for {string}")
	public void i_search_for(String string) {
		driver.findElementByXPath("//*[@id=\"search_query_top\"]").sendKeys(string);
		driver.findElementByXPath("//*[@id=\"searchbox\"]/button").submit();
		waitUntilLoaded(driver);
	}

	@When("I click the sign-in button")
	public void i_click_the_sign_in_button() {
		driver.findElementByXPath("//*[@id='header']/div[2]/div/div/nav/div[1]/a").click();
		waitUntilLoaded(driver);
	}

	@When("I enter a new email address {string}")
	public void i_enter_a_new_email_address(String string) {
    	driver.findElementByXPath("//*[@id=\'email_create\']").clear();
    	driver.findElementByXPath("//*[@id=\'email_create\']").sendKeys(string);
	}

	@When("I click the register account button")
	public void i_click_the_register_account_button() {
		driver.findElement(By.id("SubmitCreate")).click();
    	waitUntilForm(driver);
	}

	@When("I enter new credentials:")
	public void i_enter_new_credentials(Map<String, String> dataTable) { //(io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.

		String email, firstname, lastname, password, gender, address, city, state, postcode, mobile, country, alias;
		
		email = dataTable.get("email");
		firstname = dataTable.get("firstname");
		lastname = dataTable.get("lastname");
		password = dataTable.get("password");
		gender = dataTable.get("gender");
		address = dataTable.get("address");
		city = dataTable.get("city");
		state = dataTable.get("state");
		postcode = dataTable.get("postcode");
		country = dataTable.get("country");
		mobile = dataTable.get("mobile");
		alias = dataTable.get("alias");
		
		if (gender == "m") {
			driver.findElement(By.id("id_gender1")).click();
		} else {
			driver.findElement(By.id("id_gender2")).click();
		}
    	
    	driver.findElementByXPath("//*[@id='customer_firstname']").sendKeys(firstname);
    	
    	driver.findElementByXPath("//*[@id=\'customer_lastname\']").sendKeys(lastname);
    	
    	driver.findElementByXPath("//*[@id=\'passwd\']").sendKeys(password);
    	
    	driver.findElementByXPath("//*[@id=\'firstname\']").sendKeys(firstname);
    	
    	driver.findElementByXPath("//*[@id=\'lastname\']").sendKeys(lastname);
    	
    	driver.findElementByXPath("//*[@id=\'address1\']").sendKeys(address);
    	
    	driver.findElementByXPath("//*[@id=\'city\']").sendKeys(city);
   	
   		driver.findElementByXPath("//*[@id=\'id_state\']").sendKeys(state); 
    	
    	driver.findElementByXPath("//*[@id=\'postcode\']").sendKeys(postcode);
    	
    	driver.findElementByXPath("//*[@id=\"phone_mobile\"]").sendKeys(mobile);
    	
    	driver.findElementByXPath("//*[@id=\"alias\"]").clear();
    	driver.findElementByXPath("//*[@id=\"alias\"]").sendKeys(alias);
	}
	
	@When("I submit the form")
	public void i_submit_the_form() {
		driver.findElementByXPath("//*[@id=\'submitAccount\']").click();
    	waitUntilLoaded(driver);
	}

	@Then("I can see the {string} page")
	public void i_can_see_the_page(String string) {
		String expected = string;
		String result = driver.getTitle();
		assertEquals(expected, result);
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
	
	// load waits
	public void waitUntilLoaded(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
	}
	
	public void waitUntilForm(WebDriver driver) {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("id_gender1")));
	}
}
