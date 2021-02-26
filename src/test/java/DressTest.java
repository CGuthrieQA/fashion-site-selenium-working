
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DressTest {

    private static RemoteWebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(); //chromeCfg());

    }
    
    @Test
    public void accountCreation() {
    	System.out.println("starting test\n");
    	System.out.println("navigating to site http://automationpractice.com/index.php");
        driver.get("http://automationpractice.com/index.php");

    	waitUntilLoaded(driver);
        
    	System.out.println("click the sign in button");
    	driver.findElementByXPath("//*[@id='header']/div[2]/div/div/nav/div[1]/a").click();
    	
    	waitUntilLoaded(driver);
    	
    	System.out.println("entering email 1111foo@1111bar.com in the email box");
    	driver.findElementByXPath("//*[@id=\'email_create\']").sendKeys("21111foo@21111bar.com");

    	System.out.println("clicking the submit button");
    	driver.findElement(By.id("SubmitCreate")).click();

    	waitUntilForm(driver);
    	
//    	try {
//			Thread.sleep(6000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
//    	WebDriverWait stateClickableWait = new WebDriverWait(driver, 10);
//    	stateClickableWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'id_state\']")));
    	
    	System.out.println("selecting male radio button");
    	driver.findElement(By.id("id_gender1")).click();
    	System.out.println("enter first name foo");
    	driver.findElementByXPath("//*[@id='customer_firstname']").sendKeys("foo");
    	System.out.println("enter last name bar");
    	driver.findElementByXPath("//*[@id=\'customer_lastname\']").sendKeys("bar");
    	System.out.println("enter password password");
    	driver.findElementByXPath("//*[@id=\'passwd\']").sendKeys("password");
    	System.out.println("enter address first name foo");
    	driver.findElementByXPath("//*[@id=\'firstname\']").sendKeys("foo");
    	System.out.println("enter address last name bar");
    	driver.findElementByXPath("//*[@id=\'lastname\']").sendKeys("bar");
    	System.out.println("enter address line one somwhere");
    	driver.findElementByXPath("//*[@id=\'address1\']").sendKeys("somewhere");
    	System.out.println("enter address city england");
    	driver.findElementByXPath("//*[@id=\'city\']").sendKeys("england");
    	
    	
//    	Select dropState = new Select (driver.findElementByXPath("//*[@id=\'id_state\']"));
//    	dropState.selectByValue("5");
   	
    	System.out.println("state select");
   		driver.findElementByXPath("//*[@id=\'id_state\']").sendKeys("c"); //  "//*[@id=\'id_state\']").sendKeys("c");
    	
    	System.out.println("entering cali zip");
    	driver.findElementByXPath("//*[@id=\'postcode\']").sendKeys("91345");
    	
    	System.out.println("entering scuffed mobile 818 999 9999");
    	driver.findElementByXPath("//*[@id=\"phone_mobile\"]").sendKeys("818 999 9999");
    	
    	System.out.println("entering email alias");
    	driver.findElementByXPath("//*[@id=\"alias\"]").clear();
    	driver.findElementByXPath("//*[@id=\"alias\"]").sendKeys("lorem@ipsum.com");
    	
    	//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	System.out.println("submit the register button");
    	//driver.findElementByXPath("//*[@id=\'submitAccount\']").submit();
    	driver.findElementByXPath("//*[@id=\'submitAccount\']").click();
    	

    	waitUntilLoaded(driver);
    	
    	System.out.println("My account");
    	String expected = "/html/body/div/div[2]/div/div[3]/div/h1";
    	String result = driver.findElementByXPath("/html/body/div/div[2]/div/div[3]/div/h1").getText();
    	assertEquals(expected, result);
//    	
//    	System.out.println("on the new page hit the login button");
//    	//driver.findElementByXPath("//*[@id=\"SubmitLogin\"]").click();
//    	driver.findElementByXPath("//*[@id=\"SubmitLogin\"]").submit();
    	
    }

//    @Test
//    public void test() {
//    	System.out.println("starting test");
//    	System.out.println("navigating to site http://automationpractice.com/index.php");
//        driver.get("http://automationpractice.com/index.php");
//        System.out.println("searching for dress");
//        driver.findElementByXPath("//*[@id=\"search_query_top\"]").sendKeys("dress");
//        System.out.println("submitting the search");
//        driver.findElementByXPath("//*[@id=\"searchbox\"]/button").submit();
//        System.out.println("checking for dress in name of items returned");
//        driver.findElementsByXPath("//*[@id='center_column']/ul/li[1]/div/div[2]/h5/child").forEach(item -> {
//        	 assertTrue(item.getCssValue("title").contains("Dress"));
//        });
//    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
        System.out.println("\ndriver closed");
    }
    
    // Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
	public static ChromeOptions chromeCfg() {
	     Map<String, Object> prefs = new HashMap<String, Object>();
	     ChromeOptions cOptions = new ChromeOptions();
	      
	     // Settings
	     prefs.put("profile.default_content_setting_values.cookies", 2);
	     prefs.put("network.cookie.cookieBehavior", 2);
	     prefs.put("profile.block_third_party_cookies", true);
	
	     // Create ChromeOptions to disable Cookies pop-up
	     cOptions.setExperimentalOption("prefs", prefs);
	
	     return cOptions;
	}
	
	public void waitUntilLoaded(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
	}
	
	public void waitUntilForm(WebDriver driver) {
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("id_gender1")));
	}
	
}