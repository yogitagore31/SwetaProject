package CoverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxAddressDetailsPage 
{
	@FindBy (xpath = "(//input[@type='number'])[1]") private WebElement pin;
	@FindBy (xpath = "(//input[@type='number'])[2]") private WebElement phone;
	@FindBy (className = "next-btn") private WebElement continueButton;
	@FindBy (xpath = "//div[text() = ' Please enter a valid pincode ']") private WebElement pinErrMsg;
	@FindBy (xpath = "//div[text() = ' Please enter a valid mobile no. ']") private WebElement mobNumErrMsg;
	
	public CoverFoxAddressDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}
	
	public void enterPincode(String pincode)
	{
		Reporter.log("Entering pincode...", true);
		pin.sendKeys(pincode);
	}
	
	public void enterMobNum(String mobNum)
	{
		Reporter.log("Entering mobile number...", true);
		phone.sendKeys(mobNum);	
	}
	
	public void clickOnContinueButton()
	{		
		Reporter.log("Clicking on continue button...", true);
		continueButton.click();	
	}
	
	public String getPinCodeErrMsg()
	{
		String actualText = pinErrMsg.getText();
		return actualText;
	}
	
	public String getMobNumErrMsg()
	{
		String actualText = mobNumErrMsg.getText();
		return actualText;
	}

}
