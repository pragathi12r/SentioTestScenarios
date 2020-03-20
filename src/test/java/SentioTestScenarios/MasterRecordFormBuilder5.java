package SentioTestScenarios;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
//DeviationForm -> MasterRecordFormBuilder5 -> StartBatch1
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MasterRecordFormBuilder5 {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/DeviationForm.xls"); 
	static String Complete = "TestDeviationData65"; 
    static String CompleteWithRequiredField = "TestDeviationData64";
    static String CompleteWithRequiredFieldsAndDeviation = "TestDeviationData63";
    static String MasterFieldQuestionType = "TestDeviationData62";
    static String MasterFieldDynamicMatrix = "TestDeviationData5";
    static String Validation = "TestDeviationData61";
    static String CompleteBydateVerifyFlow = "TestDeviationData60";
    
  
	   @Test (priority=1)
		public static void Complete1 () throws InterruptedException, IOException {	
		   SoftAssert assertion1 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(3); //Deviation complete publish sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
	        	Login();
		    //create batch	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ Complete +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(1);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(2);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meenachi
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
				String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
				String expect1 = "Batch Records"; 
//				AssertJUnit.assertEquals(expect1, msg1); 
//				assertion1.assertAll();
		    	Thread.sleep(5000);
	        	Close(); 
	        }
	   }
	   
    @Test (priority=2)
	public static void CompleteWithRequiredField2 () throws InterruptedException, IOException {	
    	SoftAssert assertion2 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3); //Deviation complete publish sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
  
	    //create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ CompleteWithRequiredField +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(3);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//cell = sheet.getRow(i).getCell(4);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("12/08/2021"); //effective date
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meenachi
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(8000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
//			AssertJUnit.assertEquals(expect1, msg1); 
//			assertion2.assertAll();
	    	Thread.sleep(5000);
        	Close(); 
        }
   }
    
    @Test (priority=3)
	public static void CompleteWithRequiredFieldsAndDeviation3 () throws InterruptedException, IOException {	
    	SoftAssert assertion3 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3); //Deviation complete publish sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
  
	    //create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ CompleteWithRequiredFieldsAndDeviation +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(5);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//cell = sheet.getRow(i).getCell(6);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("13/08/2021"); //effective date
			
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meenachi
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(8000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
//			AssertJUnit.assertEquals(expect1, msg1); 
//			assertion3.assertAll();
	    	Thread.sleep(5000);
        	Close(); 
        }
   }
   
    @Test (priority=4)
	public static void MasterFieldQuestionType4 () throws InterruptedException, IOException {
    	SoftAssert assertion4 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3); //Deviation complete publish sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();

	    //create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ MasterFieldQuestionType +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(7);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//cell = sheet.getRow(i).getCell(8);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("14/08/2021"); //effective date
			
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meenachi
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(8000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
//			AssertJUnit.assertEquals(expect1, msg1); 
//			assertion4.assertAll();
	    	Thread.sleep(5000);
        	Close(); 
        }
   }
    
    @Test (priority=5)
	public static void MasterFieldDynamicMatrix5 () throws InterruptedException, IOException {
    	SoftAssert assertion5 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3); //Deviation complete publish sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
 
	    //create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ MasterFieldDynamicMatrix +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(9);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//cell = sheet.getRow(i).getCell(10);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("15/08/2021"); //effective date
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meenachi
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(8000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
//			AssertJUnit.assertEquals(expect1, msg1); 
//			assertion5.assertAll();
	    	Thread.sleep(5000);
        	Close(); 
        }
   }

    @Test (priority=6)
	public static void Validation6 () throws InterruptedException, IOException {	
    	SoftAssert assertion6 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3); //Deviation complete publish sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
  
	    //create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ Validation +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(11);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//cell = sheet.getRow(i).getCell(12);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("16/08/2021"); //effective date
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meenachi
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(8000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
//			AssertJUnit.assertEquals(expect1, msg1); 
//			assertion6.assertAll();
	    	Thread.sleep(5000);
        	Close(); 
        }
   }
    
    @Test (priority=7)
   	public static void CompleteBydateVerifyFlow7 () throws InterruptedException, IOException {	
    	SoftAssert assertion7 = new SoftAssert();
   	    FileInputStream finput = new FileInputStream(src);
           workbook = new HSSFWorkbook(finput);
           sheet= workbook.getSheetAt(3); //Deviation complete publish sheet
           for(int i=2; i<=sheet.getLastRowNum(); i++) {	
           	Login();
  
   	    //create batch	
           	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
          		driver.findElement(By.xpath("//*[contains(text(),'"+ CompleteBydateVerifyFlow +"')]")).click();  //Click temporary file
          		Thread.sleep(5000);
          		
          		driver.findElements(By.className("item")).get(5).click();  //click create batch
          		Thread.sleep(5000);    	
          	
          		cell = sheet.getRow(i).getCell(13);
          		cell.setCellType(Cell.CELL_TYPE_STRING);
   			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
   			//cell = sheet.getRow(i).getCell(14);
   			//cell.setCellType(Cell.CELL_TYPE_STRING);
   			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("17/08/2021"); //effective date
   			Thread.sleep(3000);
   			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
   			Thread.sleep(3000);			
   			
   			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meenachi
   			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
   			Thread.sleep(3000);
   			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
   			Thread.sleep(8000);
   			
   			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
   			String expect1 = "Batch Records "; 
//   			AssertJUnit.assertEquals(expect1, msg1); 
//   			assertion7.assertAll();
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
