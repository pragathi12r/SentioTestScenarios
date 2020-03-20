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

public class MasterRecordNewVersion {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/Master Record - Preview.xls"); 
    static String filename = "TestValue1"; //NewVersionOnADraftMasterRecord1
    static String filename1 = "TestValue2"; //NewVersionOnAPublishedMasterRecord2
	   
	   @Test (priority=1) 
		public static void NewVersionOnADraftMasterRecord1 () throws InterruptedException, IOException {
		   SoftAssert assertion1 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(3); //New version sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(6).click();  //click new version
	        	Thread.sleep(5000);
	        	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
	        
	        //click page 2
	        	driver.findElement(By.xpath("//*[@class=\"svd-page-name\"][contains(text(), 'page2')]")).click(); //click page 2
	    		
		    	//Form Builder
//		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
//		    	Thread.sleep(5000);
//		    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(),'Comment')]")));
//		    	
//		    	driver.findElement(By.xpath("//span[contains(text(),'Comment')]")).click(); //Comment
//		    	Thread.sleep(5000);
		    	
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    		
		    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
		    	cell = sheet.getRow(i).getCell(1);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
				
	    	//add new page
		    	driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click();
		    	
		    	//Form Builder
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Image picker')]")).click(); //Image picker
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    		
		    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
		    	cell = sheet.getRow(i).getCell(2);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
						
		    	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
		    	
	    		WebElement msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts = msg.getText();
	    		String expected = "Master record details saved successfully.";
	    		//assertion1.assertNotEquals(expected, texts);  
	    		//assertion1.assertAll();
	    		Thread.sleep(5000);
	        	Close();
	        }
	   }
	   
	   @Test (priority=2)
		public static void NewVersionOnAPublishedMasterRecord2 () throws InterruptedException, IOException {	
		   SoftAssert assertion2 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(3); //New version sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(6).click();  //click new version
	        	Thread.sleep(5000);
	        	//driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
	        
	        //click page 2
	        	driver.findElement(By.xpath("//*[@class=\"svd-page-name\"][contains(text(), 'page2')]")).click(); //click page 2
	    		
		    	//Form Builder
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Comment')]")).click(); //Comment
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    		
		    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
		    	Thread.sleep(3000);
		    	cell = sheet.getRow(i).getCell(3);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
				
	    	//add new page
		    	driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click();
		    	
		    	//Form Builder
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Image picker')]")).click(); //Image picker
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    		
		    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
		    	Thread.sleep(3000);
		    	cell = sheet.getRow(i).getCell(4);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
						
		    	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	    		
	    		WebElement msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts = msg.getText();
	    		String expected = "Master record details saved successfully.";
	    		//AssertJUnit.assertEquals(expected, texts);  
	    		Thread.sleep(5000);
	    		//assertion2.assertAll();
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
