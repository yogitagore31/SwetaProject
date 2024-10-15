package coverFoxBase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;

import coverFoxUtility.Utility;

public class base 
{	
	protected static WebDriver driver;
	
	public void launchBrowser() throws IOException
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(Utility.readDataFromPropertyFile("url"));
		Reporter.log("Launching Application...", true);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2000));
		Reporter.log("Waiting for application launch..." , true);
	}
	
	public void closeBrowser()
	{
		driver.quit();
		Reporter.log("Closing Application...", true);
	}

}
