package ApplicationHooks;

import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import com.DriverFactory.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;

public class Hooks {
	
	private  DriverFactory driverFactory;
	private  WebDriver driver;
	private ConfigReader configReader;
	static Properties prop;
	
	

	@Before(order = 1)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
	
	
	@Before(order = 1)
	public  void launchBrowser() {
		
		//String browserName = prop.getProperty("browser");
		
		driverFactory = new DriverFactory();
		
		String browserName =ConfigReader.GetBroserType();
		driver = DriverFactory.init_driver(browserName);
		
	}

	@After(order = 0)
	public  void quitBrowser() {
		driver.quit();
	}
	
	

	@After(order = 1)
	public  void tearDown(Scenario scenario) {
		 System.out.println("Scenario status ======>"+scenario.getStatus());
		if (scenario.isFailed()) {
			
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "http://localhost:8082/reports/AutomationTest-ExtentReport.html", scenario.getName());
			
//			try {
//				scenario.attach(sourcePath, "http://localhost:8082/reports/AutomationTest-ExtentReport.html" + "image/png", scenario.getName());
//			} catch (Exception e) {
//				e.getMessage();
//			}
//			// This new path for jenkins
//			String newImageString = "http://localhost:8082/reports/AutomationTest-ExtentReport.html" + "image/png";
//			//return newImageString;
//
		}
		driver.quit();
	}

}
