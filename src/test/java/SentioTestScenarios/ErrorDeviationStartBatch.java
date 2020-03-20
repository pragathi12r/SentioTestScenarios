package SentioTestScenarios;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ErrorDeviationStartBatch {
	static WebDriver driver = null;
	static Actions action = null;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/BatchIssuance.xls"); 

    static String AddValidators = "TestBatch90a";
    
    
 
    @Test (priority=1) 
	public static void AddValidatorsErrorDeviation1 () throws InterruptedException, IOException {	
    	SoftAssert assertion1 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(2); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
       	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ AddValidators +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	driver.findElement(By.xpath("//*[@class=\"sv_q_text_root\"]")).click();
    
        	cell = sheet.getRow(i).getCell(22);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
	        
	        
			driver.findElement(By.xpath("//*[@class=\"sv_q_text_root\"]")).sendKeys(cell.getStringCellValue()); //new question 1
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]")).click(); //click label
			driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//AssertJUnit.assertEquals(expect1, msg1); 
			//assertion1.assertAll();
	    	Thread.sleep(5000);
        	Close();       	
        }
    }

    @Test (priority=2) 
	public static void BeforeCompleteErrorDeviationCloseBatch2 () throws InterruptedException, IOException {	
    	SoftAssert assertion2 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(6); //error deviation sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	//login as qa
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			readProperty a = new readProperty();     
			driver.get(a.getApplicationUrl());
			driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
			driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
			driver.findElement(By.cssSelector("#_58_fm > button")).click();    
			Thread.sleep(5000);
           
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));

        	driver.findElement(By.xpath("//*[contains(text(),'"+ AddValidators +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click close batch
        	//Thread.sleep(10000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect1 = "Batch Freezed"; 
			//AssertJUnit.assertEquals(expect1, msg1);
			//assertion2.assertAll();
        	Thread.sleep(5000);
			Close();	 	
        }
   }
   
    @Test (priority=3) 
	public static void CompleteErrorDeviationAndAssignToPage3 () throws InterruptedException, IOException {	
    	SoftAssert assertion3 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(6); //error deviation sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	//login as qa
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			readProperty a = new readProperty();     
			driver.get(a.getApplicationUrl());
			driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
			driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
			driver.findElement(By.cssSelector("#_58_fm > button")).click();    
			Thread.sleep(5000);
           

			
			driver.findElement(By.xpath("//*[@id=\"status_Portlet\"]/div[1]")).click(); //click work flow status
        	Thread.sleep(2000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"myworkflow-main\"]/div[1]/p")).getText();
        	System.out.println(msg);
	       	String expect = "Workflow Tasks"; 
			//assertion3.assertEquals(expect, msg); 
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"search-workflow-button\"]")).click();
			Thread.sleep(2000);
			Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"tasks-options-list\"]")));
			templateCategory.selectByVisibleText("Error_Process"); // select error_process 
			driver.findElement(By.xpath("//*[@id=\"filter_tasks\"]")).click(); //click ok
			
			
			
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//*[contains(text(), 'TestBatch9a')]/following-sibling::td/*[contains(text(), 'Error_Process')]/parent::td/parent::tr/td[7]/div/img")).click(); //click image icon
			
			driver.findElement(By.xpath("//*[@class=\"workflow-action assign-to-me preLoaded\"]")).click(); //click assign to me
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[contains(text(), 'TestBatch9a')]/parent::tr/td[7]/div/img")).click(); //click image icon
			driver.findElement(By.xpath("//*[@class=\"workflow-action editor preLoaded\"]")).click(); //click edit
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[contains(text(), 'Error Details')]/parent::h5/parent::div/following-sibling::div/input")).sendKeys("closebatchdeviation1"); //type error details
			Thread.sleep(500);
			driver.findElement(By.xpath("//span[contains(text(), 'Remarks')]/parent::h5/parent::div/following-sibling::div/input")).sendKeys("remark"); //type remarks

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
			Thread.sleep(5000);
			
			//driver.findElement(By.xpath("//*[contains(text(), 'qa_approve')]/parent::tr/td/input")).click(); //click qa_approve
			driver.findElement(By.xpath("//*[contains(text(), 'page1')]/parent::tr/td/input")).click(); //click page1 
			driver.findElement(By.xpath("//*[@id=\"selectStg\"]")).click(); //click continue
			
			String msg2 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect2 = "Batch Records"; 
//			AssertJUnit.assertEquals(expect2, msg2); 
//			Thread.sleep(5000);
			//assertion3.assertAll();
	    	Thread.sleep(5000);
	    	Close();        
    
        }
    }
  
    @Test (priority=4) 
	public static void RecheckBeforeCompleteErrorDeviationCloseBatch4 () throws InterruptedException, IOException {	
    	SoftAssert assertion4 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(6); //error deviation sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	//login as qa
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			readProperty a = new readProperty();     
			driver.get(a.getApplicationUrl());
			driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
			driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
			driver.findElement(By.cssSelector("#_58_fm > button")).click();    
			Thread.sleep(5000);
           
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));

        	driver.findElement(By.xpath("//*[contains(text(),'"+ AddValidators +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click close batch
        	Thread.sleep(10000);
        	
			driver.findElement(By.xpath("//*[@class=\"sv_q_text_root\"]")).sendKeys("456"); //new question 1
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]")).click(); //click label
			driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete
		    Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//AssertJUnit.assertEquals(expect1, msg1); 
			//assertion4.assertAll();
	    	Thread.sleep(5000);
			Close();	 	
        }
   }
   
    @Test (priority=5) 
	public static void CompleteErrorDeviationAndAssignToQAApprove5 () throws InterruptedException, IOException {
    	SoftAssert assertion5 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(6); //error deviation sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	//login as qa
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			readProperty a = new readProperty();     
			driver.get(a.getApplicationUrl());
			driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
			driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
			driver.findElement(By.cssSelector("#_58_fm > button")).click();    
			Thread.sleep(5000);
           

			
			driver.findElement(By.xpath("//*[@id=\"status_Portlet\"]/div[1]")).click(); //click work flow status
        	Thread.sleep(2000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"myworkflow-main\"]/div[1]/p")).getText();
        	System.out.println(msg);
	       	String expect = "Workflow Tasks"; 
			//assertion5.assertEquals(expect, msg); 
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"search-workflow-button\"]")).click();
			Thread.sleep(2000);
			Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"tasks-options-list\"]")));
			templateCategory.selectByVisibleText("Error_Process"); // select error_process 
			driver.findElement(By.xpath("//*[@id=\"filter_tasks\"]")).click(); //click ok
			
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//*[contains(text(), 'TestBatch9a')]/parent::tr/td[7]/div/img")).click(); //click image icon
			
			driver.findElement(By.xpath("//*[@class=\"workflow-action assign-to-me preLoaded\"]")).click(); //click assign to me
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[contains(text(), 'TestBatch9a')]/following-sibling::td/*[contains(text(), 'Error_Process')]/parent::td/parent::tr/td[7]/div/img")).click(); //click image icon
			driver.findElement(By.xpath("//*[@class=\"workflow-action editor preLoaded\"]")).click(); //click edit
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[contains(text(), 'Error Details')]/parent::h5/parent::div/following-sibling::div/input")).sendKeys("closebatchdeviation1"); //type error details
			Thread.sleep(500);
			driver.findElement(By.xpath("//span[contains(text(), 'Remarks')]/parent::h5/parent::div/following-sibling::div/input")).sendKeys("remark"); //type remarks

			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//*[contains(text(), 'qa_approve')]/parent::tr/td/input")).click(); //click qa_approve
			//driver.findElement(By.xpath("//*[contains(text(), 'page1')]/parent::tr/td/input")).click(); //click page1 
			driver.findElement(By.xpath("//*[@id=\"selectStg\"]")).click(); //click continue
//			
//			String msg2 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
//	       	String expect2 = "Batch Records"; 
//			//AssertJUnit.assertEquals(expect2, msg2); 
			
			//assertion5.assertAll();
	    	Thread.sleep(5000);
	    	Close();        
    
        }
    }
    
    
    
    
    @Test (priority=6) 
	public static void AfterCompleteErrorDeviationCloseBatch6 () throws InterruptedException, IOException {	
    	SoftAssert assertion6 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(6); //error deviation sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	//login as qa
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			readProperty a = new readProperty();     
			driver.get(a.getApplicationUrl());
			driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
			driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
			driver.findElement(By.cssSelector("#_58_fm > button")).click();    
			Thread.sleep(5000);
           
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));

        	driver.findElement(By.xpath("//*[contains(text(),'"+ AddValidators +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click close batch
        	Thread.sleep(15000);
        	
        	driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).click(); //click close
        	Thread.sleep(5000);
        	
        //fill close popup details
            cell = sheet.getRow(i).getCell(4);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //batch name
		    
//		    cell = sheet.getRow(i).getCell(5);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    driver.findElement(By.xpath("//*[@id=\"type_name2\"]")).sendKeys(cell.getStringCellValue()); //deviation name
//		    
//		    cell = sheet.getRow(i).getCell(6);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    driver.findElement(By.xpath("//*[@id=\"type_name3\"]")).sendKeys(cell.getStringCellValue()); //title
		    Thread.sleep(5000);
		    
		    driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); //click ok
		    Thread.sleep(8000);
		    String msg = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expect = "Closed"; 
			//AssertJUnit.assertEquals(expect, msg);
     
			Thread.sleep(5000);
			//assertion6.assertAll();
			Close();	 		 	
        }
   }
    
    public static void Login() throws InterruptedException, IOException{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        readProperty a = new readProperty();     
        driver.get(a.getApplicationUrl());
        driver.findElement(By.cssSelector("#_58_login")).sendKeys("meenachi");
        driver.findElement(By.cssSelector("#_58_password")).sendKeys("test@123");
        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
        Thread.sleep(5000);

	}

	public static void Close() throws InterruptedException, IOException{	
		driver.quit();
	}
}
