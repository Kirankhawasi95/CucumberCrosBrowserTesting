package com.DriverFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
	
	
	public static WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thradlocal driver on the basis of given
	 * browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	
	
	
	public static   WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);
		
		
		
		if (browser.equalsIgnoreCase("chrome")) {
           
	            //System.setProperty("webdriver.chrome.driver", rb.getString("Chromepath"));
			tlDriver.set(new ChromeDriver());
		
			
	        } else if (browser.equalsIgnoreCase("firefox")) {
	            
	            //System.setProperty("webdriver.edge.driver", "path/to/msedgedriver");
	        	tlDriver.set(new FirefoxDriver());
	        	
	           
	        } else if (browser.equalsIgnoreCase("edge")) {
	           
	        	tlDriver.set(new EdgeDriver());
	            //System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
	        	
	        	
	        } else {
	            System.out.println("Invalid browser specified.");
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

}
