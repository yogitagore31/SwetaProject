package CoverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class CoverFoxMembersDetailsPage 
{
	@FindBy (id = "Age-You") private WebElement me;
	@FindBy (className = "next-btn") private WebElement nextButton;
	
	public CoverFoxMembersDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}
	
	public void handelingAgeDropdown(String age)
	{
		Reporter.log("Selecting age...", true);
		Select m = new Select(me);
		m.selectByValue(age+"y");
	}		
	
	public void clickOnNextButton()
	{
		Reporter.log("Clicking on next button...", true);
		nextButton.click();
		
	}

}
