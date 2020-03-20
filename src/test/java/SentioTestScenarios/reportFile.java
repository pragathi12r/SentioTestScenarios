package SentioTestScenarios;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class reportFile {
	
	static ExtentTest test;
	static ExtentReports report;
	@BeforeClass
	public static void startTest()
	{
		Date d=new Date();
        String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
	//report = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReportResults.html");
	report=new ExtentReports(System.getProperty("user.dir")+"/ExtReport/"+fileName,true, DisplayOrder.NEWEST_FIRST);
	test = report.startTest("ExtentDemo");
	}
	@Test
	public void extentReportsDemo()
	{
		 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
		 
		 WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
		
	driver.get("https://www.google.co.in");
	if(driver.getTitle().equals("Google"))
	{
	test.log(LogStatus.PASS, "Navigated to the specified URL");
	}
	else
	{
	test.log(LogStatus.FAIL, "Test Failed");
	}
	}
	@AfterClass
	public static void endTest()
	{
	report.endTest(test);
	report.flush();
	}
	}


