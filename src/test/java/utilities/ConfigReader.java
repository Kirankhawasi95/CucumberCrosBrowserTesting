package utilities;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.DriverFactory.DriverFactory;

public class ConfigReader {
	
	private Properties prop;
	public static  String Browsertype=null;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to load the properties from config.properties file
	 * @return it returns Properties prop object
	 */
	public Properties init_prop() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}
	
	public static void SetBroserType(String browser)
	{
		
		Browsertype=browser;
		
	}
	
	public static String GetBroserType()
	{
		if(Browsertype!=null)
			return 	Browsertype;
		else
			throw new RuntimeException("browser not sfecified in testng.xml");
		
	}


}
