package coverFoxUtility;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import coverFoxBase.base;

public class Listner extends base implements ITestListener
{

	@Override
	public void onTestSuccess(ITestResult result)
	{
		Reporter.log("TC " +result.getName()+ " Passed" , true);
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		Reporter.log("TC " +result.getName()+ " Failed" , true);
		try {
			Reporter.log("Taking screenshot of failure", true);
			Utility.takeScreenshot(driver, "Test123");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
