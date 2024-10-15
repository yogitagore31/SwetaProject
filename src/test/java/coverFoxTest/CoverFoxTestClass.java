package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CoverFoxPOM.CoverFoxAddressDetailsPage;
import CoverFoxPOM.CoverFoxHealthPlan;
import CoverFoxPOM.CoverFoxHomePage;
import CoverFoxPOM.CoverFoxMembersDetailsPage;
import CoverFoxPOM.CoverFoxResultsPage;
import coverFoxBase.base;
import coverFoxUtility.Utility;

public class CoverFoxTestClass extends base {

	CoverFoxHomePage homePage;
	CoverFoxHealthPlan healthPlanPage;
	CoverFoxMembersDetailsPage membersDetailPage;
	CoverFoxAddressDetailsPage addressDetailspage;
	CoverFoxResultsPage resultsPage;
	
	String excelPath = System.getProperty("user.dir") + "\\Data\\TestExcel.xlsx";
	String mySheet = "Sheet3";
	
	public static Logger logger;
	
	
	@BeforeClass
	public void launchApplication() throws IOException
	{
	   launchBrowser();
	}
	
	
	@BeforeMethod
	public void enterDetails() throws EncryptedDocumentException, IOException, InterruptedException
	{
		homePage = new CoverFoxHomePage(driver);
		healthPlanPage = new CoverFoxHealthPlan(driver);
		membersDetailPage = new CoverFoxMembersDetailsPage(driver);		
		addressDetailspage = new CoverFoxAddressDetailsPage(driver);
		resultsPage = new CoverFoxResultsPage(driver);
		
		logger= Logger.getLogger("MyMavenProject");
		PropertyConfigurator.configure("log4j.properties");
		
		logger.info("Click on gender button..");
		homePage.clickOnGender();
		Thread.sleep(2000);
		
		logger.info("Click on next button..");
		healthPlanPage.ClickOnNextButton();
		Thread.sleep(2000);
		
		logger.info("Handling age dropdown");
		membersDetailPage.handelingAgeDropdown(Utility.readExcelData(excelPath, mySheet, 0, 0));
		Thread.sleep(2000);
		
		logger.info("click on next button..");
		membersDetailPage.clickOnNextButton();
		Thread.sleep(2000);

		logger.info("Enter pincode..");
		addressDetailspage.enterPincode(Utility.readExcelData(excelPath, mySheet, 0, 1));
		Thread.sleep(2000);
		logger.warn("Please enter valid pincode");
		
		logger.info("Enter mobile number..");
		addressDetailspage.enterMobNum(Utility.readExcelData(excelPath, mySheet, 0, 2));
		Thread.sleep(2000);
		logger.warn("Please enter valid mobile number");
		
		logger.info("Click on continue button..");
		addressDetailspage.clickOnContinueButton();
		Thread.sleep(3000);
	}
		
		
	@Test
	public void validatePolicyCount() throws InterruptedException
	{
		
		int textCount = resultsPage.getCountFromText();
		int bannerCount = resultsPage.getCountFromBannerPage();
		Assert.assertEquals(textCount, bannerCount, "text count not matching with banner count, TC failed");
		Thread.sleep(3000);
	}

	@AfterClass
	public void closeApplication()
	{
		closeBrowser();
	}
}
