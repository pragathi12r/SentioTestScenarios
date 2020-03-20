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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CloseBatch {
	//MasterRecordFormBuilder4 is used for close batch
	static WebDriver driver = null;
	static Actions action = null;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/DeviationForm.xls"); 
    static String filename = "TestCloseBatch1";
    static String filename1 = "TestCloseBatch2";
    static String filename2 = "TestDeviationBatch6";
	   
	   @Test (priority=1) 
		public static void LoginAsDifferentUserFillFinalStageAndCloseBatch1 () throws InterruptedException, IOException {
		   SoftAssert assertion1 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(5); //close batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(1).click();  //click close batch
	        	
	        	String msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
		       	String expect1 = "Unauthorized User"; 
				//assertion1.assertEquals(expect1, msg1);
				
	        	Thread.sleep(5000);	        	
	        	String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
		       	String expect = "Batch Records"; 
				AssertJUnit.assertEquals(expect, msg); 
				assertion1.assertAll();
				Thread.sleep(5000);	 
				Close();
	        }
	   }
	   
	   @Test (priority=2) 
		public static void LoginAsQAAndClose2 () throws InterruptedException, IOException {	
		   SoftAssert assertion2 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(5); //close batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
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

	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(1).click();  //click close batch
	        	Thread.sleep(5000);
	        	
	        	cell = sheet.getRow(i).getCell(10);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//*[@aria-label=\"question3\"]")).sendKeys(cell.getStringCellValue());  //question3
			   		    	    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
		    	Thread.sleep(5000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
	        	Thread.sleep(5000);
	        	
	        	String msg1 = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
		       	String expect1 = "Close"; 
				//assertion2.assertEquals(expect1, msg1);
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).click(); //click close
				Thread.sleep(5000);
				
				//fill close popup details
	            cell = sheet.getRow(i).getCell(11);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //batch name
			    
//			    cell = sheet.getRow(i).getCell(12);
//			    cell.setCellType(Cell.CELL_TYPE_STRING);
//			    driver.findElement(By.xpath("//*[@id=\"type_name2\"]")).sendKeys(cell.getStringCellValue()); //deviation name
//			    
//			    cell = sheet.getRow(i).getCell(13);
//			    cell.setCellType(Cell.CELL_TYPE_STRING);
//			    driver.findElement(By.xpath("//*[@id=\"type_name3\"]")).sendKeys(cell.getStringCellValue()); //title
//			    Thread.sleep(5000);
			    
			    driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); //click ok
			    Thread.sleep(8000);
			    String msg = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
		       	String expect = "Closed"; 
				AssertJUnit.assertEquals(expect, msg); 
				assertion2.assertAll();
				
	        	Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"surveyComplete\"]")).click(); //click exit
				Thread.sleep(5000);	 	
	        }
	   }
	   
	   @Test (priority=3) 
		public static void LoginAsQAAndExitBatch3 () throws InterruptedException, IOException {	
		   SoftAssert assertion3 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(5); //close batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
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

	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(1).click();  //click close batch
	        	Thread.sleep(5000);
	        	
	        	String msg1 = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
		       	String expect1 = "Close"; 
				AssertJUnit.assertEquals(expect1, msg1);
				assertion3.assertAll();
				Thread.sleep(3000);
				
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
				Thread.sleep(5000);
				
				driver.findElement(By.xpath("//*[@id=\"surveyComplete\"]")).click(); //click exit
				Thread.sleep(5000);		         	
	        }
	   }

    @Test (priority=4) 
	public static void LoginAsAdminAndCheckBeforeCloseBatch4 () throws InterruptedException, IOException {	
    	SoftAssert assertion4 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //close batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click close batch
        	Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect1 = "Unauthorized User"; 
			//assertion4.assertEquals(expect1, msg1);
        		        	
        	String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
			AssertJUnit.assertEquals(expect, msg); 
			Thread.sleep(5000);	 
			//assertion4.assertAll();
			Close();
        }
   }
   
   @Test (priority=5) 
	public static void LoginAsQAAndCheckBeforeCloseBatch5 () throws InterruptedException, IOException {	
	   SoftAssert assertion5 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //close batch sheet
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

        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click close batch
        	Thread.sleep(5000);
        	
        	String msg2 = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expect2 = "Close"; 
			AssertJUnit.assertEquals(expect2, msg2);
			Thread.sleep(3000);
			//assertion5.assertAll();
			Close();
        }
   }

   
   @Test (priority=6) 
	public static void LoginAsAdminAndCheckAfterCloseBatch6 () throws InterruptedException, IOException {	
	   SoftAssert assertion6 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //close batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click close batch
        	Thread.sleep(5000);
        	
        	String msg2 = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expect2 = "Closed"; 
			AssertJUnit.assertEquals(expect2, msg2);
			Thread.sleep(5000);
			//assertion6.assertAll();
			//Close();	 	
        }
   }
  
   @Test (priority=7) 
	public static void LoginAsQAAndCheckAfterCloseBatch7 () throws InterruptedException, IOException {	
	   SoftAssert assertion7 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
       workbook = new HSSFWorkbook(finput);
       sheet= workbook.getSheetAt(5); //close batch sheet
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

			driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
			Thread.sleep(5000);
			driver.findElements(By.className("item")).get(1).click();  //click close batch
			Thread.sleep(5000);
       	
			String msg3 = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expect3 = "Closed"; 
			AssertJUnit.assertEquals(expect3, msg3);
			Thread.sleep(3000);
			//assertion7.assertAll();
			Close();	 	
       }
  }

    @Test (priority=8) 
	public static void BeforeCompleteDeviationCloseBatch8 () throws InterruptedException, IOException {	
    	SoftAssert assertion8 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //close batch sheet
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

        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename2 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click close batch
        	Thread.sleep(10000);
        	
//        	String msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
//	       	String expect1 = "Deviation Not Yet Completed"; 
//			assertion.assertEquals(expect1, msg1);
//        	Thread.sleep(5000);
      //need to add error msg code  	
        	
        	String msg2 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect2 = "Batch Records"; 
			AssertJUnit.assertEquals(expect2, msg2); 
			Thread.sleep(5000);
			//assertion8.assertAll();
			Close();	 	
        }
   }
    
    @Test (priority=9) 
	public static void CompleteDeviation9 () throws InterruptedException, IOException {	
    	SoftAssert assertion9 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //close batch sheet
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
			
			driver.findElement(By.xpath("//*[@id=\"status_Portlet\"]/div[1]")).click(); //click work flow status
        	Thread.sleep(2000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"myworkflow-main\"]/div[1]/p")).getText();
        	System.out.println(msg);
	       	String expect = "Workflow Tasks"; 
			//assertion9.assertEquals(expect, msg); 
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"search-workflow-button\"]")).click();
			Thread.sleep(2000);
			Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"tasks-options-list\"]")));
			templateCategory.selectByVisibleText("Qa_approve"); // select Qa_approve 
			driver.findElement(By.xpath("//*[@id=\"filter_tasks\"]")).click(); //click ok
			
			
			
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"workflow-tasks\"]/button[2]/following-sibling::div/*[@id=\"pagination\"]/ul/li[3]/a/img")).click(); //click next
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//*[contains(text(), 'TestDeviationBatch6')]/parent::tr/td[7]/div/img")).click(); //click image icon
			
			driver.findElement(By.xpath("//*[@class=\"workflow-action assign-to-me preLoaded\"]")).click(); //click assign to me
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[contains(text(), 'TestDeviationBatch6')]/parent::tr/td[7]/div/img")).click(); //click image icon
			driver.findElement(By.xpath("//*[@class=\"workflow-action editor preLoaded\"]")).click(); //click edit
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[contains(text(), 'Remarks')]/parent::h5/parent::div/following-sibling::div/input")).sendKeys("remark"); //type remarks
			driver.findElement(By.xpath("//span[contains(text(), 'Deviation')]/parent::h5/parent::div/following-sibling::div/input")).sendKeys("closebatchdeviation1"); //type deviation
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
			Thread.sleep(5000);
			String msg2 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect2 = "Batch Records"; 
			AssertJUnit.assertEquals(expect2, msg2); 
			Thread.sleep(5000);
			//assertion9.assertAll();
	    	Thread.sleep(5000);
	  //  	Close();
    
        }
    }
   
    
    @Test (priority=10) 
	public static void AfterCompleteDeviationCloseBatch10 () throws InterruptedException, IOException {	
    	SoftAssert assertion10 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //close batch sheet
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

        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename2 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click close batch
        	Thread.sleep(15000);
        	
        	driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).click(); //click close
        	Thread.sleep(5000);
        	
        //fill close popup details
            cell = sheet.getRow(i).getCell(11);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //batch name
		    
//		    cell = sheet.getRow(i).getCell(12);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    driver.findElement(By.xpath("//*[@id=\"type_name2\"]")).sendKeys(cell.getStringCellValue()); //deviation name
//		    
//		    cell = sheet.getRow(i).getCell(13);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    driver.findElement(By.xpath("//*[@id=\"type_name3\"]")).sendKeys(cell.getStringCellValue()); //title
		    Thread.sleep(5000);
		    
		    driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); //click ok
		    Thread.sleep(8000);
		    String msg = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expect = "Closed"; 
			AssertJUnit.assertEquals(expect, msg);
     
			Thread.sleep(5000);
			//assertion10.assertAll();
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
        driver.findElement(By.cssSelector("#_58_login")).sendKeys(a.username());
        driver.findElement(By.cssSelector("#_58_password")).sendKeys(a.password());
        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
        Thread.sleep(5000);
        
//        driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[3]")).click(); //click my sites
//        driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(1));
	}

	public static void Close() throws InterruptedException, IOException{	
		driver.quit();
	}
	
}
