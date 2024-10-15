package CoverFoxPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxResultsPage 
{
	@FindBy (xpath = "//div[contains(text(),'Insurance Plans')]") private WebElement resultText;
	@FindBy (className = "plan-card-container") private List<WebElement> planCard;
	
	public CoverFoxResultsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public int getCountFromText()
	{
		Reporter.log("Getting policy count from text displayed...", true);
		String resultInString = resultText.getText().substring(0, 2);
		int CountFromText = Integer.parseInt(resultInString);
		return CountFromText;
	}
		
	public int getCountFromBannerPage()
	{
		Reporter.log("Getting policy count from banners displayed...", true);
		int CountFromBannerPage = planCard.size();
		return CountFromBannerPage;
	}
}
