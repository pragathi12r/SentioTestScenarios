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

public class MasterRecordDashboard {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/BatchIssuance.xls"); 

	
	@Test (priority=1)
	public static void OpenBatchRecords1 () throws InterruptedException, IOException {	
		SoftAssert assertion1 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //Dashboard sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[contains(text(), 'Open Batch Records')]")).click(); //click open batch records
			Thread.sleep(6000);
			
			String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
			AssertJUnit.assertEquals(expect, msg); 
			//assertion1.assertAll();
	    	Thread.sleep(5000);
	    	Close();
        }
	}
	
	@Test (priority=2)
	public static void SearchFilterUsingCategory2 () throws InterruptedException, IOException {	
		SoftAssert assertion2 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //Dashboard sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[@id=\"sort\"]")).click(); //click sort icon
			Thread.sleep(3000);
			
			Select Category =new Select(driver.findElement(By.xpath("//*[@id=\"client-list1\"]")));
			Category.selectByIndex(1);
			
			String master = driver.findElement(By.xpath("//*[@id=\"c-position\"]")).getText();
    		boolean masterexpect = driver.getPageSource().contains("TestData17"); 
			AssertJUnit.assertSame(masterexpect, master); 
			assertion2.assertAll();
	    	Thread.sleep(1000);
	    	Close();
        }
	}
	
	@Test (priority=3)
	public static void SearchFilterUsingRecordName3 () throws InterruptedException, IOException {	
		SoftAssert assertion3 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //Dashboard sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[@id=\"sort\"]")).click(); //click sort icon
			Thread.sleep(3000);
			
			cell = sheet.getRow(i).getCell(1);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"book-filter\"]")).sendKeys(cell.getStringCellValue()); //enter record name
			
			String master = driver.findElement(By.xpath("//*[@id=\"c-position\"]")).getText();
    		boolean masterexpect = driver.getPageSource().contains("TestDataa15"); 
			AssertJUnit.assertSame(masterexpect, master); 
			assertion3.assertAll();
	    	Thread.sleep(1000);
	    	Close();
        }
	}
	
	@Test (priority=4)
	public static void SearchFilterUsingRecordNamePartially4 () throws InterruptedException, IOException {	
		SoftAssert assertion4 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //Dashboard sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[@id=\"sort\"]")).click(); //click sort icon
			Thread.sleep(3000);
			
			cell = sheet.getRow(i).getCell(2);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"book-filter\"]")).sendKeys(cell.getStringCellValue()); //enter record name
			
			String master = driver.findElement(By.xpath("//*[@id=\"c-position\"]")).getText();
			
    		boolean masterexpect = driver.getPageSource().contains("TestDataa15"); 
			AssertJUnit.assertSame(masterexpect, master); 
			//assertion4.assertAll();
	    	Thread.sleep(1000);
	    	Close();
        }
	}

	@Test (priority=5)
	public static void CheckBackButtonLink5 () throws InterruptedException, IOException {	
		SoftAssert assertion5 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //Dashboard sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
        	driver.findElement(By.xpath("(//div[@data-toggle='dropdown'])")).click();  //click dropdown icon
        	driver.findElement(By.xpath("//*[@class=\"dropdown-menu batch_dropdown\"]/li[2]")).click(); //click batch record menu
        	Thread.sleep(1000);
        	
        	String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
			assertion5.assertEquals(expect, msg); 
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("/html/body/ul/li/a")).click(); //back	    	
	    	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	    	
	    	String msg2 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect2 = "Master Records"; 
			assertion5.assertEquals(expect2, msg2); 
	    	Thread.sleep(5000);

	    	driver.findElement(By.xpath("/html/body/ul/li[1]/a")).click(); //back to batch record
	    	//assertion5.assertEquals(expect, msg); 
	    	
	    	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
        	driver.findElement(By.xpath("(//div[@data-toggle='dropdown'])")).click();  //click dropdown icon
        	driver.findElement(By.xpath("//*[@class=\"dropdown-menu batch_dropdown\"]/li[1]")).click(); //click master record menu
        	Thread.sleep(1000);
        	assertion5.assertEquals(expect2, msg2); 
        	Thread.sleep(1000);
	    	
        	driver.findElement(By.xpath("/html/body/ul/li[1]/a")).click(); //back to batch record
	    	AssertJUnit.assertEquals(expect, msg); 	
	    	//assertion5.assertAll();
	    	Thread.sleep(5000);
	    	Close();
        }
	}

	@Test (priority=6)
	public static void CalendarTodoAndReportsRedirection6 () throws InterruptedException, IOException {	
		SoftAssert assertion6 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //Dashboard sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
        	driver.findElement(By.xpath("//*[@id=\"1\"]/div[1]")).click();  //click calendar
        	Thread.sleep(3000);
        	String msg = driver.findElement(By.xpath("//*[@class=\"tab active\"]/a")).getText();
        	System.out.println(msg);
	       	String expect = "Calendar"; 
			AssertJUnit.assertEquals(expect, msg); 
			driver.findElement(By.xpath("//*[@class=\"breadcrumb2\"]/li[1]/a")).click(); //back to batch records
			Thread.sleep(3000);
/*			
			driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[@id=\"taskNotify_Portlet\"]/div[1]")).click();  //click todo
        	Thread.sleep(3000);
        	String msg2 = driver.findElement(By.xpath("//*[@id=\"task-title\"]/span")).getText();
        	System.out.println(msg2);
	       	String expect2 = "To do Today"; 
			assertion.assertEquals(expect2, msg2); 
			driver.findElement(By.xpath("//*[@class=\"breadcrumb2\"]/li[1]/a")).click(); //back to batch records
			Thread.sleep(2000);
*/			
//			driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//			driver.findElement(By.xpath("//*[@id=\"reports_Portlet\"]/div[1]")).click();  //click Reports
//        	Thread.sleep(3000);
//        	String msg3 = driver.findElement(By.xpath("//*[@id=\"task-title\"]/span")).getText();
//        	System.out.println(msg3);
//	       	String expect3 = "Reports"; 
//			assertion.assertEquals(expect3, msg3); 
//			driver.findElement(By.xpath("//*[@class=\"breadcrumb2\"]/li[1]/a")).click(); //back to batch records
//			Thread.sleep(2000);
			
			//assertion6.assertAll();
	    	Thread.sleep(5000);
	    	Close();
        }
	}

	@Test (priority=7)
	public static void LoginBasedOnAccessRoleQA7 () throws InterruptedException, IOException {	
		SoftAssert assertion7 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(5); //Dashboard sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
      //login as qa  	
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
    		driver = new ChromeDriver();
    		action = new Actions(driver);
 //   		assertion= new SoftAssert();
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
        	
            String newbatch = driver.findElement(By.xpath("//*[@id=\"p_p_id_projectviewer_WAR_SentioDashboardportlet_\"]/div[1]/div[2]")).getText();
 	        boolean expectnewbatch = newbatch.contains("New Batch"); 
 			AssertJUnit.assertNotSame(expectnewbatch, newbatch);
 			//assertion7.assertAll();
	    	Thread.sleep(5000);
	    	Close();
        }
	}
			
    @Test (priority=8)
	public static void LoginBasedOnAccessRoleUser8 () throws InterruptedException, IOException {
    	SoftAssert assertion8 = new SoftAssert();
 	     FileInputStream finput = new FileInputStream(src);
         workbook = new HSSFWorkbook(finput);
         sheet= workbook.getSheetAt(5); //Dashboard sheet
         for(int i=2; i<=sheet.getLastRowNum(); i++) {	
 	   //login as user1  	
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
    		driver = new ChromeDriver();
    		action = new Actions(driver);
//    		assertion= new SoftAssert();
    		driver.manage().window().maximize();
    		wait = new WebDriverWait(driver,30);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            readProperty a = new readProperty();     
            driver.get(a.getApplicationUrl());
            driver.findElement(By.cssSelector("#_58_login")).sendKeys("meenachi");
            driver.findElement(By.cssSelector("#_58_password")).sendKeys("test@123");
            driver.findElement(By.cssSelector("#_58_fm > button")).click();    
            Thread.sleep(5000);
            
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));
        	
            String newbatch = driver.findElement(By.xpath("//*[@id=\"p_p_id_projectviewer_WAR_SentioDashboardportlet_\"]/div[1]/div[2]")).getText();
 	        boolean expectnewbatch = newbatch.contains("New Batch"); 
 			//AssertJUnit.assertFalse(expectnewbatch, newbatch);
 	
			//assertion8.assertAll();
	    	Thread.sleep(5000);
	    	Close();
        }
	}

	
	
	public static void Login() throws InterruptedException, IOException{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
		driver = new ChromeDriver();
		action = new Actions(driver);
//		assertion= new SoftAssert();
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
