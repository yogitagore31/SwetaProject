package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CoverFoxPOM.CoverFoxAddressDetailsPage;
import CoverFoxPOM.CoverFoxHealthPlan;
import CoverFoxPOM.CoverFoxHomePage;
import CoverFoxPOM.CoverFoxMembersDetailsPage;
import CoverFoxPOM.CoverFoxResultsPage;
import coverFoxBase.base;
import coverFoxUtility.Utility;


public class CoverFoxValidateErrorMsg extends base {

	CoverFoxHomePage homePage;
	CoverFoxHealthPlan healthPlanPage;
	CoverFoxMembersDetailsPage membersDetailPage;
	CoverFoxAddressDetailsPage addressDetailspage;
	CoverFoxResultsPage resultsPage;
	
	String excelPath = System.getProperty("user.dir") + "\\Data\\TestExcel.xlsx";
	String mySheet = "Sheet3";
	
	public static Logger logger;
	
	
	@BeforeMethod
	public void enterDetails() throws EncryptedDocumentException, IOException, InterruptedException
	{
		launchBrowser();
		Reporter.log("Application Luanched",true);
		
		logger= Logger.getLogger("MyMavenProject");
		PropertyConfigurator.configure("log4j.properties");
		
		homePage = new CoverFoxHomePage(driver);
		healthPlanPage = new CoverFoxHealthPlan(driver);
		membersDetailPage = new CoverFoxMembersDetailsPage(driver);		
		addressDetailspage = new CoverFoxAddressDetailsPage(driver);
		resultsPage = new CoverFoxResultsPage(driver);
		
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
	}
		
		
	@Test(priority = -1)
	public void validatePinCodeErrorMsg() throws InterruptedException, EncryptedDocumentException, IOException
	{
		logger.info("Enter mobile number..");
		addressDetailspage.enterMobNum(Utility.readExcelData(excelPath, mySheet, 0, 2));
		Thread.sleep(2000);
		logger.warn("Please enter valid mobile number");		
		logger.info("Click on continue button..");
		addressDetailspage.clickOnContinueButton();
		Thread.sleep(3000);		
		String actualErrMsgText = addressDetailspage.getPinCodeErrMsg();	
		String expectedErrMsgText = Utility.readExcelData(excelPath, mySheet, 0, 3);		
		Assert.assertEquals(actualErrMsgText, expectedErrMsgText, "Both error msgs are not same, TC Failed");
	}
	
	@Test
	
	public void validateMobileNumErrorMsg() throws InterruptedException, EncryptedDocumentException, IOException
	{
		logger.info("Enter pincode..");
		addressDetailspage.enterPincode(Utility.readExcelData(excelPath, mySheet, 0, 1));
		Thread.sleep(2000);
		logger.warn("Please enter valid pincode");				
		logger.info("Click on continue button..");
		addressDetailspage.clickOnContinueButton();
		Thread.sleep(3000);	
		String actualErrMsgText = addressDetailspage.getMobNumErrMsg();		
		String expectedErrMsgText = Utility.readExcelData(excelPath, mySheet, 0, 4);		
		Assert.assertEquals(actualErrMsgText, expectedErrMsgText, "Both error msgs are not same, TC Failed");
	}
	
	@Test(priority = 1)
	public void validateErrMsgs() throws EncryptedDocumentException, IOException, InterruptedException 
	{
		logger.info("Click on continue button..");
		addressDetailspage.clickOnContinueButton();
		Thread.sleep(3000);		
		String actualPinErrMsgText = addressDetailspage.getPinCodeErrMsg();		
		String expectedPinErrMsgText = Utility.readExcelData(excelPath, mySheet, 0, 3);		
		String actualMobNumErrMsgText = addressDetailspage.getMobNumErrMsg();
		String expectedMobNumErrMsgText = Utility.readExcelData(excelPath, mySheet, 0, 4);		
		SoftAssert soft = new SoftAssert();	
		soft.assertEquals(actualPinErrMsgText, expectedPinErrMsgText, "Both error msgs are not same, TC Failed");
		soft.assertEquals(actualMobNumErrMsgText, expectedMobNumErrMsgText, "Both error msgs are not same, TC Failed");
		soft.assertAll();
	}

	@AfterMethod
	public void closeApplication()
	{
		logger.info("Closing Browser");
		closeBrowser();
	}
}
