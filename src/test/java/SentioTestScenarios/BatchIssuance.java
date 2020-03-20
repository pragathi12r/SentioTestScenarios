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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BatchIssuance {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/BatchIssuance.xls"); 
	//*******************need to publish files******************************************************
    static String filename = "TestData930"; //NewVersionOnADraftMasterRecord1
    static String filename1 = "TestData931"; //NewVersionOnAPublishedMasterRecord2
	   
	   @Test (priority=1) 
		public static void CreateBatchAndClose1 () throws InterruptedException, IOException {	
		   SoftAssert assertion1 = new SoftAssert();
	        FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(0); //create batch sheet
	        //for(int i=2; i<=sheet.getLastRowNum(); i++){	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(5).click();  //click create batch
	        	Thread.sleep(5000);
	        	driver.findElement(By.xpath("//button[contains(text(),'×')]")).click();     //close
	        	Thread.sleep(3000);
	        	
	        	String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
		       	String expect = "Master Records"; 
				//AssertJUnit.assertEquals(expect, msg); 
				//assertion1.assertAll();
		    	Thread.sleep(5000);
	        	Close();
	        //}
	   }
	   
	   @Test (priority=2) 
		public static void CreateBatchAndContinue2 () throws InterruptedException, IOException {
		   SoftAssert assertion2 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(0); //create batch sheet
	        //for(int i=2; i<=sheet.getLastRowNum(); i++){	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(5).click();  //click create batch
	        	Thread.sleep(15000);    	
	        	driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue	
	        	
	        	WebElement successmsg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]"));
	    		String text1 = successmsg1.getText();
	    		String expect1 = "Enter the value";
	    		//AssertJUnit.assertEquals(expect1, text1);  
	    		//assertion2.assertAll(); 
	    		Thread.sleep(5000);
	    		Close();
	        //}
	   }
	   
	   @Test (priority=3) 
		public static void CreateBatchContinueAndCloseUserAssign3 () throws InterruptedException, IOException {	
		   SoftAssert assertion3 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(0); //create batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(5).click();  //click create batch
	        	Thread.sleep(8000);    	
	        	
				cell = sheet.getRow(i).getCell(1);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@class=\"yui3-widget modal yui3-widget-positioned yui3-widget-stacked yui3-widget-modal modal-focused yui3-dd-draggable\"]/div/div/div/button")).click(); //click close
				Thread.sleep(5000);	
				
				String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
		       	String expect = "Master Records"; 
				//AssertJUnit.assertEquals(expect, msg); 
				//assertion3.assertAll();
		    	Thread.sleep(5000);
	        	Close();
	        }
	   }
	   
	   @Test (priority=4) 
		public static void CreateBatchAndContinue4 () throws InterruptedException, IOException {	
		   SoftAssert assertion4 = new SoftAssert(); 
		   FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(0); //create batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	driver.findElement(By.xpath("(//*[contains(text(),'New Batch')])[1]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	
	        	driver.findElements(By.className("item")).get(5).click();  //click create batch
	        	Thread.sleep(5000);    	
	       	
	        	cell = sheet.getRow(i).getCell(3);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
	    		String text = driver.findElement(By.xpath("//*[@id=\"bookshelf\"]")).getText();
		       	String expect = "Batch Records"; 
		       //	AssertJUnit.assertEquals(expect, text);  
	    		//assertion4.assertAll();
		    	Thread.sleep(5000);
	       	Close();      
	        }
	   }
	
	   
	   
	   
	   public static void Login() throws InterruptedException, IOException{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
//			assertion= new SoftAssert();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	        readProperty a = new readProperty();     
	        driver.get(a.getApplicationUrl());
	        driver.findElement(By.cssSelector("#_58_login")).sendKeys(a.username());
	        driver.findElement(By.cssSelector("#_58_password")).sendKeys(a.password());
	        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	        Thread.sleep(5000);
	        
//	        driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[3]")).click(); //click my sites
//	        driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records	        
//	        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//	        driver.switchTo().window(tabs.get(1));
		}

		public static void Close() throws InterruptedException, IOException{	
			driver.quit();
		}
}
