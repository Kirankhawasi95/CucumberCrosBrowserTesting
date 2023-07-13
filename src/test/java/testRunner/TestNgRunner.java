package testRunner;




import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.DriverFactory.DriverFactory;
import utilities.ConfigReader;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * 
 */
@CucumberOptions

(

	features={FeaturePath.Login,FeaturePath.LoginDDt},
	
	glue={"stepDefinitions","ApplicationHooks"},
	
	plugin={"pretty","html:reports/myreport.html",
			"json:reports/myreport.json",
			"rerun:target/rerun.txt", //Mandatory to capture failure
			 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
			},
	
	 dryRun=false,
	monochrome=true,
	// tags = "@sanity"	//Scenarios tagged with @sanity,
	tags="@sanity"//Scenarios tagged with both @sanity and @regression
	//tags = "@regression"	 //Scenarios tagged with either @sanity or @regression
	//tags = "@sanity and not @regression", //Scenarios tagged with @sanity but not tagged with @regressi
	
	)

public class TestNgRunner extends AbstractTestNGCucumberTests{
	
	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
	
//	private  DriverFactory driverFactory;
//	private  WebDriver driver;
//	//private ConfigReader configReader;
//	static Properties prop;
//	
	@Parameters({"browser"})
	@BeforeClass
	public  void DefineBrowser(@Optional("chrome")String browser) {
		
		if(!browser.equals("param_value_notfound"))
		{
			ConfigReader.SetBroserType(browser);
		}
		
		
		
	}
	
}
