package SentioTestScenarios;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class manuScript {
	
	static WebDriver driver = null;
	static Actions action = null;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;
		 
	static ExtentTest test; 
	static ExtentReports report;
	
			@BeforeClass
			public static void startTest()
		{
			Date d=new Date();
	        String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
		//report = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReportResults.html");
		report=new ExtentReports(System.getProperty("user.dir")+"/ExtReport/Manuscript"+fileName,true, DisplayOrder.NEWEST_FIRST);
		test = report.startTest("Input Material");
		}
			
			@Test (priority=1)
			 public static void Login() throws InterruptedException, IOException, ParseException{
						
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			
				ChromeDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get("http://editorial.pagemajik.info/home");
				test.log(LogStatus.PASS, "Navigated to the specified URL");
			
			
			//login
			String username="pragathi";
			String password="1234";
			
			
	        driver.findElement(By.cssSelector("#_58_login")).sendKeys(username);
	        driver.findElement(By.cssSelector("#_58_password")).sendKeys(password);
	        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	        Thread.sleep(5000);
	        System.out.println("Login Successfully "+username);
	        test.log(LogStatus.PASS, "Login Successfully Username is "+username);
			
			driver.findElement(By.xpath("(//span[text()='Manuscript'])[2]")).click();
			Thread.sleep(2000);
			//driver.switchTo().frame(0);
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='bookform-iframe']")));
			
			
			driver.findElement(By.xpath("//select[@name='category']")).click();
			
			
			//Thread.sleep(2000);
			driver.findElement(By.xpath("//select[@name='category']//option[text()='Book1']")).click();
		
			
	        Thread.sleep(2000);
			driver.findElement(By.xpath("//input[contains(@id,'bookName')]")).click();
	        driver.findElement(By.xpath("//input[contains(@id,'bookName')]")).sendKeys("Test12");
	        WebElement bookname1=driver.findElement(By.xpath("//input[@id='bookName']"));
	        String bookname2=bookname1.getAttribute("value");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//button[text()='Create']")).click();
	        System.out.println("Login Successfully "+bookname2);
		    test.log(LogStatus.INFO, "Book Name is "+bookname2);
			driver.switchTo().defaultContent();
			/*test.log(LogStatus.PASS, "Navigate to Workflow Task page");
			test.log(LogStatus.PASS, "Selecting the New book from Assigned to My Role section");
			test.log(LogStatus.PASS, "Schedule and verify the chapters are listed in add schedulted popup and verify the save successfil popup as-->Schedule created successfully.");
			test.log(LogStatus.PASS, "Selects the author and verify the   selected author is saved");
			test.log(LogStatus.PASS, "Assign a authour and verify the email inivitaion is send to assigne");
			test.log(LogStatus.PASS, "Verify the book name is listed on email");
			test.log(LogStatus.PASS, "Upload the the docx in authour submission screan");
			test.log(LogStatus.PASS, "Verify the uploded docx is listed on the Chaper screen");*/

			
	        //workflow status
			 Thread.sleep(2000);
	        driver.findElement(By.xpath("//div[text()='WORKFLOW STATUS']")).click();
	        Thread.sleep(2000);
	        //driver.findElement(By.xpath("(//button[contains(text(),'ASSIGNED TO MY ROLE')]//following::input[contains(@type,'checkbox')])[2]")).click();
	        driver.findElement(By.xpath("(//button[contains(text(),'ASSIGNED TO MY ROLE')]//following::div[@class='wf-action-btn click'])[1]")).click();
	        Thread.sleep(3000);
	        
	        //schedule
	        driver.findElement(By.xpath("//span[text()='Schedule']//parent::li")).click();
	        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='_145_scheduler_iframe_']")));
	        driver.findElement(By.xpath("//select[@name='tempName']")).click();
	        driver.findElement(By.xpath("//option[contains(text(),'schedule1')]")).click();
	        driver.findElement(By.xpath("//input[contains(@name,'stDate')]")).sendKeys("21/03/2020");
	        driver.findElement(By.xpath("//input[contains(@name,'chCount')]")).sendKeys("2");
	        driver.findElement(By.xpath("//button[contains(text(),'Continue ')]")).click();
	        driver.switchTo().defaultContent();
	        
	      /*  //selectauthor
	        driver.findElement(By.xpath("(//span[text()='Select Author'])")).click();
	        Thread.sleep(5000);
//	        driver.findElement(By.xpath("//span[text()=' Existing Author']")).click();
//	        Thread.sleep(2000);
	        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='Editorial_iframe_']")));
	        driver.findElement(By.xpath("//input[@placeholder='Enter the screen name here']")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//input[@placeholder='Enter the screen name here']")).sendKeys("P");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//span[text()='pragathi']")).click();
	        driver.findElement(By.xpath(" //button[text()='ADD']")).click();
	        driver.findElement(By.xpath(" //button[text()='Submit']")).click();
	        
	        //verifyelement
	       
	        if(driver.findElements(By.xpath("//h2[text()='Author(s) saved successfully.']")).size()>0){
	        	driver.findElement(By.xpath("//button[text()='OK']")).click();
	        	System.out.println("Verify Element is Presrent");
	        	test.log(LogStatus.INFO, "Verify Element is present");
	        }
	        else{
	        	
	        	test.log(LogStatus.INFO, "Verify Element is Not Present");
	        }
	       
			
		    
		    //assignauthor
	        driver.navigate().refresh();
	        driver.findElement(By.xpath("(//div[@class='wf-action-btn click'])[2]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("(//span[text()='Schedule'])")).click();
	        Thread.sleep(3000);*/
	        
		   
	        
	       
	        
		  
		    
			}
						
			
			 @AfterClass
			   	public static void endTest()
			   	{
			   	report.endTest(test);
			   	report.flush();
			   	
			   	
			   	}

}
