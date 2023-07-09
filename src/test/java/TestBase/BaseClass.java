package TestBase;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BaseClass {

	public static WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	
	@BeforeClass
	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			
			tlDriver.set(new ChromeDriver());
			
		} else if (browser.equals("firefox")) {
			
			tlDriver.set(new FirefoxDriver());
			
		} else if (browser.equals("edge")) {
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get("http://localhost/opencart/upload");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		return getDriver();

	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	
	//Junit hook - executes once
	
//	@AfterAll
//	public static void Application_Close() {
//		driver.quit();
//	}
//	
	 @After
	    public static void tearDown(Scenario scenario) {
	        System.out.println("Scenario status ======>"+scenario.getStatus());
	        if(scenario.isFailed()) {
	             byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	            scenario.attach(screenshot, "image/png",scenario.getName());
	            }
	       driver.quit();
	    }
	  
	 


}
