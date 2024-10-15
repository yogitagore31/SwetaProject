package CoverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxHomePage 
{
	//Variable Declaration 
	@FindBy (xpath = "//div[text()='Female']") private WebElement gender;
	
	//constructor
	public CoverFoxHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	} 

	
	//Methods
	public void clickOnGender()
	{
		Reporter.log("Clicking on gender button...", true);	
		gender.click();
	}
}
