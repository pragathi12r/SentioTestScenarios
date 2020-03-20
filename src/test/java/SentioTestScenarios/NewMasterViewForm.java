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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewMasterViewForm {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/Master Record - Preview.xls"); 
    static String filename = "TestValue1"; //DraftMasterRecord1
    static String filename1 = "TestValue2"; //PublishedMasterRecord2
    
	   
	   @Test (priority=1)
		public static void DraftMasterRecord1 () throws InterruptedException, IOException {	
		   SoftAssert assertion1 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4); //View Form sheet
	        //for(int i=2; i<=sheet.getLastRowNum(); i++) {	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(4).click();  //click view form
	        	Thread.sleep(5000);
	        	String publishcontent = driver.findElement(By.xpath("//*[@id=\"surveyElement\"]/parent::div/div[2]")).getText();
				String expectpublishcontent = "Publish"; 
				//AssertJUnit.assertEquals(expectpublishcontent, publishcontent);
				//assertion1.assertAll();
	        	Close();
	        //}
	   }
	 
	   @Test (priority=2)
		public static void DraftMasterRecordAddQuestion2 () throws InterruptedException, IOException {	
		   SoftAssert assertion2 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4); //View Form sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++){	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(4).click();  //click view form
	        	Thread.sleep(5000);
	        	
//	        	String publishcontent = driver.findElement(By.xpath("//*[@id=\"surveyElement\"]/parent::div/div[2]")).getText();
//				String expectpublishcontent = "Publish"; 
//				assertion.assertEquals(expectpublishcontent, publishcontent);
//				assertion.assertAll();
	        	
				driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue   	
				driver.findElement(By.xpath("//*[@class=\"svd-page-name\"][contains(text(), 'page3')]")).click(); //click page 3
	     
	        	//Form Builder
			    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon   
			    Thread.sleep(6000);
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
	            cell = sheet.getRow(i).getCell(1);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	        	Thread.sleep(15000);
	        	
	        	//back
			    driver.findElement(By.xpath("//*[@id=\"backbtn\"]")).click();
			    driver.findElement(By.xpath("//*[@id=\"close\"]/img")).click();
			    
			    driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(4).click();  //click view form
	        	Thread.sleep(5000);
	        	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
	        	driver.findElement(By.xpath("//*[@class=\"svd-page-name\"][contains(text(), 'page3')]")).click(); //click page 3
	   	     
	        	String copyverify2 = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/parent::Div/parent::Div/div[3]/div/div/h5/span[3]")).getText();
	 		    boolean expectcopyverify2 = copyverify2.contains("ADDITIONAL QUESTION"); 
				//AssertJUnit.assertTrue(expectcopyverify2, copyverify2);
		    	//assertion2.assertAll(); 		
	        	Close(); 
	        }
	   }
	
	   @Test (priority=3)
		public static void PublishedMasterRecord3 () throws InterruptedException, IOException {	
		   SoftAssert assertion3 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4); //View Form sheet
	        //for(int i=2; i<=sheet.getLastRowNum(); i++){	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(4).click();  //click view form
	        	Thread.sleep(5000);
	        	
	        	String savecontent = driver.findElement(By.xpath("//*[@class=\"left panel asset_left_panel\"]/div[2]/div[2]/div")).getText();
	        	boolean expectsavecontent = savecontent.contains("Save "); 
				//AssertJUnit.assertFalse(expectsavecontent, savecontent);
				//assertion3.assertAll();
				Close();
	        //}
	   }
	   
	   @Test (priority=4)
		public static void CheckedOutMasterRecord4 () throws InterruptedException, IOException {
		   SoftAssert assertion4 = new SoftAssert();
		   System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
//			assertion= new SoftAssert();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	        
	        	readProperty a = new readProperty();     
	            driver.get(a.getApplicationUrl());
	            driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
	            driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
	            driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	            Thread.sleep(5000);

//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));      
	            
	            
	            driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(4).click();  //click view form
	        	Thread.sleep(15000);
	        	
	        	String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
				System.out.println(msg);
		       	String expect = "Master Records"; 
				//AssertJUnit.assertEquals(expect, msg); 
				//assertion4.assertAll();
		    	Thread.sleep(5000);
	        	Close();
	
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
