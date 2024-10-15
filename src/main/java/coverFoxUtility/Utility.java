package coverFoxUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

public class Utility 
{
	public static void takeScreenshot(WebDriver driver, String fileName) throws IOException
	{
		//screenshot
		String timestamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File (System.getProperty("user.dir") + "\\ScreenShot\\" +fileName+timestamp+ ".png");
		FileHandler.copy(src, dest);					
	}
	
	public static String readExcelData(String excelPath, String mySheet, int row, int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream myFile = new FileInputStream(excelPath);
		String value = WorkbookFactory.create(myFile).getSheet(mySheet).getRow(row).getCell(cell).getStringCellValue();
		return value;		
	}
	
	public static String readDataFromPropertyFile(String key) throws IOException 
	{
		Properties prop = new Properties();
		FileInputStream myFile = new FileInputStream(System.getProperty("user.dir") +"//config.properties");
		prop.load(myFile);
		String value = prop.getProperty(key);
		return value;
	}
	
	@DataProvider(name="fbData")
	public String[][] fbData()
	{
		String[][] fb= {{"Shweta","Desai","9999999999"},{"Misty","Upadhye","8888888888"},{"Guddi","Mist","7777777777"}};
		return fb;
	}
	
}

