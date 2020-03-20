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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ErrorRedirection {
	static WebDriver driver = null;
	static Actions action = null;
	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;
	
	   static File src=new File(System.getProperty("user.dir") + "/ExcelData/Master Record - Form Builder.xls"); 	
   
	   static String filename = "TestData7";
	   
	   @Test (priority=1)
		public static void Complete1 () throws InterruptedException, IOException {	
	    	FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(0);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	//temporary code to view the form
//	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//	        	driver.findElement(By.xpath("//*[contains(text(),'TestDeviationData1')]")).click();  //Click temporary file
//	        	Thread.sleep(5000);
//	        	driver.findElements(By.className("item")).get(4).click();  //click view form
//	        	Thread.sleep(10000);
//	        	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
	       	    	       	
			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
				//Fill basic details
				cell = sheet.getRow(i).getCell(1);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(2);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(3);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(4);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(5);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				//upload assert
				cell = sheet.getRow(i).getCell(6);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download1.jpeg";
				AssertJUnit.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				assertion.assertAll(); 
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
	   		
		   	//Form Builder
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@class=\"toolBoxDiv\"]/div/div[3]")).click(); //single input
	    		
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')]")).click(); //click properties icon
	    		Thread.sleep(5000);
	    		
	    		WebElement complete = driver.findElement(By.xpath("//*[@title =\"Complete\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//complete
		    	if(!complete.isSelected())
		    		complete.click();
		    	Thread.sleep(3000);
				 	
	    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	        	Thread.sleep(3000);
	        	WebElement msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts1 = msg1.getText();
	    		String expected11 = "Request submitted.";
	    		AssertJUnit.assertEquals(expected11, texts1);  
	    		Thread.sleep(5000);
	    		//assertion.assertAll(); 	
			 	Close();
	        }
	   }
	   
	   
	   
	   
	   public static void Login() throws InterruptedException, IOException{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			assertion= new SoftAssert();
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
