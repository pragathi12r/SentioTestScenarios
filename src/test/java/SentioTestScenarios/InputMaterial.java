package SentioTestScenarios;

	import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.DateFormatConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
	import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.webdriven.commands.Refresh;

	public class InputMaterial {

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/Untitled 1.xlsx"); 
	
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
		report=new ExtentReports(System.getProperty("user.dir")+"/ExtReport/Inputmaterial"+fileName,true, DisplayOrder.NEWEST_FIRST);
		test = report.startTest("Input Material");
		}
			
			@Test (priority=1)
			 public static void Login() throws InterruptedException, IOException, ParseException{
						
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			
				ChromeDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get("http://sentio.pagemajik.info/");
				test.log(LogStatus.PASS, "Navigated to the specified URL");
				
			
			//login
				String username="aarthi";
				String password="sample@123";
				
				
		        driver.findElement(By.cssSelector("#_58_login")).sendKeys(username);
		        driver.findElement(By.cssSelector("#_58_password")).sendKeys(password);
		        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
		        Thread.sleep(5000);
		        System.out.println("Login Successfully "+username);
		        test.log(LogStatus.PASS, "Login Successfully Username is "+username);
		        
		        //verifyElement
		        if(driver.findElements(By.xpath("(//a[text()='Batch Records '])[1]")).size()>0){
		        	System.out.println("Verify Element(Batch Record) is Presrent");
		        	test.log(LogStatus.INFO, "Verify Element(Batch Record) is present");
		        	
		        }
		        else{
		        	System.out.println("Verify Element(Batch Record) is Absent");
		        	test.log(LogStatus.INFO, "Verify Element(Batch Record) is Not present");
		        }
		        
		        //batchrecord
		        driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
		        
		      //verifyElement
		        if(driver.findElements(By.xpath("(//a[text()='Master Records '])[1]")).size()>0){
		        	System.out.println("Verify Element(Master Record) is Presrent");
		        	test.log(LogStatus.INFO, "Verify Element(Master Record) is present");
		        	
		        }
		        else{
		        	System.out.println("Verify Element(Master Record) is Absent");
		        	test.log(LogStatus.INFO, "Verify Element(Master Record) is Not present");
		        }
		        //threedotline
		        driver.findElement(By.xpath("(//img[@class='cat_sort'])[1]")).click();
		        driver.findElement(By.xpath("(//select[@class='client-list'])[1]")).click();
		        driver.findElement(By.xpath("(//option[@label='Control Data'])[1]")).click();
		        

	       	//Double click the button to launch an alertbox
	       		Actions action = new Actions(driver);
	       		WebElement link =driver.findElement(By.xpath("(//div[contains(text(),'Input Material')])"));
	       		action.doubleClick(link).perform();
	       		Thread.sleep(2000);
    
	       		
	       	//new input material
	       		
	       		driver.findElement(By.xpath("(//span[text()='New'])[1]")).click();
	       		Thread.sleep(2000);
	       		String s = RandomStringUtils.randomAlphanumeric(5);
	       		
	       		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).click();
	       		Thread.sleep(2000);
	       		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).sendKeys(s);
	       		
	       		Thread.sleep(2000);
	       		
	       		
	       		
	       		//data picker
	      
	       		driver.findElement(By.xpath("//input[@placeholder='enter date']")).click();
	       		driver.findElement(By.xpath("//input[@placeholder='enter date']")).sendKeys("25/8/2020");
	       		/*WebElement DateReceived=driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']"));
	       		String DateReceived1=DateReceived.getText();
	       		System.out.println(DateReceived1);
	       		test.log(LogStatus.INFO, "Date Received is "+DateReceived1);*/
	       		
	       		driver.findElement(By.xpath("//button[text()='Continue']")).click();
	       		Thread.sleep(2000);
	       		
	       		//inputmaterial form
	       		
	       	if(driver.findElements(By.xpath("//div[@class='editor_page_head_fav editor_input_right']")).size()>0){
	       		
	       		WebElement inputform=driver.findElement(By.xpath("//div[@class='editor_page_head_fav editor_input_right']"));
	       		String inputformID=inputform.getText();
	       		System.out.println(inputformID);
	       		test.log(LogStatus.INFO, "Element Present Input Material is "+inputformID);
	       		
	       	}
	       	else{
	       		System.out.println("Element is not present");
	       		test.log(LogStatus.INFO, "Element Not Present in Input Material");
	       		
	       	}
	       		
	       	//form filling
	       	//description
	       	Thread.sleep(2000);
	       	driver.findElement(By.xpath("//input[@aria-label='Description *']")).click();
	       	driver.findElement(By.xpath("//input[@aria-label='Description *']")).sendKeys("Input Material");
	       	WebElement getvalue =driver.findElement(By.xpath("//input[@aria-label='Description *']"));
	       	String getvalue1=getvalue.getAttribute("value");
	       	test.log(LogStatus.INFO, "Description Value-->"+getvalue1);
	       	
	       	driver.findElement(By.xpath("//input[@aria-label='Internal Lot Number *']")).click();
	       	
	    	
	       	//complete
	       	
	       	driver.findElement(By.xpath("(//button[text()='Complete'])[2]")).click();
	       	
	       	//errormessage
	       	WebElement erroemessage=driver.findElement(By.xpath("(//span[contains(text(),'Please answer the question.')])[1]"));
	       	String errormessage1=erroemessage.getText();
	       	System.out.println(errormessage1);  	
	       	test.log(LogStatus.INFO, "Error Message-->"+errormessage1);
	     
	     driver.navigate().refresh();
	     test.log(LogStatus.INFO, "Refresh URL");
	     
	   //form filling
	       	//description
	       	Thread.sleep(2000);
	       	driver.findElement(By.xpath("//input[@aria-label='Description *']")).click();
	       	driver.findElement(By.xpath("//input[@aria-label='Description *']")).sendKeys("Input Material");
	       	
	    
	      //Internal Lot Number
	       	
	       	String s1 = RandomStringUtils.randomNumeric(5);
	    	driver.findElement(By.xpath("//input[@aria-label='Internal Lot Number *']")).click();
	       	driver.findElement(By.xpath("//input[@aria-label='Internal Lot Number *']")).sendKeys(s1);
	       	Thread.sleep(2000);
	       	
	       	//Date of Receipt 
	       	
	       	WebElement DateofReceipt=driver.findElement(By.xpath("//input[@aria-label='Date of Receipt *']"));
       		String DateofReceipt1=DateofReceipt.getText();
       		System.out.println("Date of Receipt-->"+DateofReceipt1);
       		Thread.sleep(2000);
       		
       		//Manufacturer
       		
	    	driver.findElement(By.xpath("//input[@aria-label='Manufacturer *']")).click();
	       	driver.findElement(By.xpath("//input[@aria-label='Manufacturer *']")).sendKeys("pagemajik");
	       	Thread.sleep(2000);
	       	
       		//Part Number
       		
       		String s2 = RandomStringUtils.randomNumeric(5);
	    	driver.findElement(By.xpath("//input[@aria-label='Part Number *']")).click();
	       	driver.findElement(By.xpath("//input[@aria-label='Part Number *']")).sendKeys(s2);
	       	
	       	Thread.sleep(2000);
	       	//quantity
	       	
	       	String s3 = RandomStringUtils.randomNumeric(2);
	    	driver.findElement(By.xpath("//input[@aria-label='Quantity *']")).click();
	       	driver.findElement(By.xpath("//input[@aria-label='Quantity *']")).sendKeys(s3);
	       	Thread.sleep(2000);
	       	
	       	//unit
	       	
	       	driver.findElement(By.xpath("//input[@aria-label='Unit *']")).click();
	       	driver.findElement(By.xpath("//input[@aria-label='Unit *']")).sendKeys("l");
	       	Thread.sleep(2000);
	       	
	       	//complete
	       	
	       	driver.findElement(By.xpath("(//button[text()='Complete'])[2]")).click();
	       	
	       	driver.close();
	       	
       		
	}
			 @AfterClass
			   	public static void endTest()
			   	{
			   	report.endTest(test);
			   	report.flush();
			   	
			   	
			   	}
			   	}



