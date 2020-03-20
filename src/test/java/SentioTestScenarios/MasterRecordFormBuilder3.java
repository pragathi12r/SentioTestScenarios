package SentioTestScenarios;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
//MasterRecordFormBuilder2 -> MasterRecordFormBuilder3 -> StartBatch
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

public class MasterRecordFormBuilder3 {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/BatchIssuance.xls"); 
	static String DataFieldQuestionType = "TestData1193"; 
    static String DataFieldDynamicMatrix = "TestData29";
    static String ExpressionQuestionType = "TestData1194";
    static String TimerQuestionType = "TestData1195";
    static String StopWatch = "TestData1159";
    static String AddTotalField = "TestData1197";
    static String AddValidators = "TestData934";
    static String AddVerify = "TestData1173";
    static String AddBy = "TestData1174";
    static String SetRolesToPagepublish11 = "1TestData778A";
    static String SetRolesToPagepublish13 = "1TestData880B";
    
  
	   @Test (priority=1)
		public static void DataFieldQuestionType1 () throws InterruptedException, IOException {	
		   SoftAssert assertion1 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
	        	Login();
	       
		    //create batch	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ DataFieldQuestionType +"')]")).click();  //Click temporary file
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
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
				String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
				String expect1 = "Batch Records "; 
				//AssertJUnit.assertEquals(expect1, msg1); 
				//assertion1.assertAll();
		    	Thread.sleep(5000);
	        	Close(); 
	        }
	   }
	   
	/*   @Test (priority=2)
		public static void DataFieldDynamicMatrix2 () throws InterruptedException, IOException {
		   SoftAssert assertion2 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
	        	Login();
			
	        //create batch
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ DataFieldDynamicMatrix +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(3);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(4);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class=\"type_category1 category_type_1\"]")).sendKeys("13-08-2019"); //effective date
				
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
				String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
		       	String expect1 = "Batch Records "; 
				assertion2.assertEquals(expect1, msg1); 
				assertion2.assertAll();
		    	Thread.sleep(5000);
	        	Close();
	        }
	   }*/
	   
	   @Test (priority=3)
		public static void ExpressionQuestionType3 () throws InterruptedException, IOException {
		   SoftAssert assertion3 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
	        	Login();
			
	        //create batch	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ ExpressionQuestionType +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(5);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(6);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
//				String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
//		       	String expect1 = "Batch Records "; 
				//AssertJUnit.assertEquals(expect1, msg1); 
				//assertion3.assertAll();
		    	
	        	Close();
	        }
	   }
	   
	   @Test (priority=4)
		public static void TimerQuestionType4 () throws InterruptedException, IOException {	
		   SoftAssert assertion4 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
	        	Login();
	        
	        //create batch	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ TimerQuestionType +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(7);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
	       		driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(6);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
				String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
		       	String expect1 = "Batch Records "; 
				//AssertJUnit.assertEquals(expect1, msg1); 
				//assertion4.assertAll();
		    	Thread.sleep(5000);
	        	Close();
	        }
	   }
	   
	  @Test (priority=5)
		public static void StopWatch5 () throws InterruptedException, IOException {	
		   SoftAssert assertion5 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
	        	Login();
	        
	        //create batch	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ StopWatch +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(9);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(10);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
				String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
		       	String expect1 = "Batch Records "; 
				//AssertJUnit.assertEquals(expect1, msg1); 
				//assertion5.assertAll();
		    	Thread.sleep(5000);
	        	Close();
	        }
	   }
	  
	  /* @Test (priority=6)
		public static void AddTotalField6 () throws InterruptedException, IOException {	
		   SoftAssert assertion6 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1); //MadterRecordFormBuilder3 sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
	        	Login();
	       		
		    //create batch	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ AddTotalField +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(11);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(6);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
//				String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
//				String expect1 = "Batch Records "; 
				//AssertJUnit.assertEquals(expect1, msg1); 
				//assertion6.assertAll();
		    	
	            Close();
	        }
	   }
	   */
	   @Test (priority=7)
		public static void AddValidators7 () throws InterruptedException, IOException {	
		   SoftAssert assertion7 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
	        	Login();
	       
			//create batch	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ AddValidators +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(13);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(6);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
				String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
				String expect1 = "Batch Records "; 
				//assertion7.assertEquals(expect1, msg1); 
				Thread.sleep(5000);
		    	
		   //create another batch for error deviation occured
		    	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ AddValidators +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(0);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(14);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
				String msg2 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
				String expect2 = "Batch Records"; 
				//assertion7.assertEquals(expect2, msg2); 
				//assertion7.assertAll();
		    	Thread.sleep(5000);
		    	
	        	Close();
	        }
	   }
	  
    @Test (priority=8)
	public static void AddVerify8 () throws InterruptedException, IOException {	
    	SoftAssert assertion8 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
          	
		//create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ AddVerify +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(15);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			//driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			Thread.sleep(5000);
			//cell = sheet.getRow(i).getCell(16);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			//driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(8000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
			//AssertJUnit.assertEquals(expect1, msg1); 
			//assertion8.assertAll();
	    	Thread.sleep(5000);
        	Close();
        }
   }
	
    @Test (priority=9)
	public static void AddBy9 () throws InterruptedException, IOException {	
    	SoftAssert assertion9 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
        
		//create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ AddBy +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(17);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys("TestBatch10");
			Thread.sleep(5000);
			//cell = sheet.getRow(i).getCell(18);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			//driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meenachi
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(8000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records"; 
			//AssertJUnit.assertEquals(expect1, msg1); 
			//assertion9.assertAll();
	    	Thread.sleep(5000);
        	Close();
        }
   }

    @Test (priority=10)
	public static void SetRolesToPageNew10 () throws InterruptedException, IOException {	
    	SoftAssert assertion10 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
		 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
		//Fill basic details
			cell = sheet.getRow(i).getCell(19);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(20);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
			templateCategory.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(21);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
			templateType.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(22);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(23);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
			//save
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
			//upload assert
			cell = sheet.getRow(i).getCell(24);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(5000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(3000);
			WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
			String uploadedfile1text = uploadedfile1.getText();
			String expectuploadedfile1text = "download1.jpeg";
			//assertion10.assertEquals(expectuploadedfile1text, uploadedfile1text);  
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
    			
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '.page1')]")).click(); //click .page1

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
    		//driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
//    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
//    		String expected1 = "supervisor";
//    		AssertJUnit.assertEquals(expected1, role1);  
    
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '..question1')]")).click(); //click ..question1
    		
    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
    		
    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
//    		cell = sheet.getRow(i).getCell(25);
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//    		Select verifyrole1 =new Select(driver.findElement(By.xpath("//*[contains(text(),'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/select")));  //select verify role
//    		verifyrole1.selectByVisibleText(cell.getStringCellValue());	
    		
    		driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
    		Thread.sleep(2000);
    			
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //click qa
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    			
    		String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();
    		String expected2 = "supervisor";
    		//AssertJUnit.assertEquals(expected2, verifyrole2); 
    		//assertion10.assertAll();
    		Thread.sleep(3000);
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
        	Thread.sleep(3000);
        	
        	driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
			String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
			//assertion10.assertEquals(expect, msg); 
			//assertion.assertAll();
			Thread.sleep(5000);
			
    		Close();	
        }
	}
	   
    @Test (priority=11)
	public static void SetRolesToPagepublish11 () throws InterruptedException, IOException {	
    	SoftAssert assertion11 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
        
	    //create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ SetRolesToPagepublish11 +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(26);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//cell = sheet.getRow(i).getCell(27);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
			
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign user
			driver.findElement(By.xpath("//*[contains(text(), 'shiftengineer')]/parent::tr/td/input")).click(); //assign shift engineer
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(25000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
			//AssertJUnit.assertEquals(expect1, msg1); 
			//assertion11.assertAll();
	    	Thread.sleep(5000);
        	Close(); 
        }
   }  

    @Test (priority=12)
	public static void SetRolesToPageNew12 () throws InterruptedException, IOException {	
    	SoftAssert assertion12 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
		 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
		//Fill basic details
			cell = sheet.getRow(i).getCell(28);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(29);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
			templateCategory.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(30);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
			templateType.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(31);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(32);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
			//save
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
			//upload assert
			cell = sheet.getRow(i).getCell(33);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(5000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(3000);
			WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
			String uploadedfile1text = uploadedfile1.getText();
			String expectuploadedfile1text = "download1.jpeg";
			//assertion12.assertEquals(expectuploadedfile1text, uploadedfile1text);  
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
    			
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '.page1')]")).click(); //click .page1

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		//driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected1 = "qa";
    		//AssertJUnit.assertEquals(expected1, role1);  
    
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '..question1')]")).click(); //click ..question1
    		
    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
    		
    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
//    		cell = sheet.getRow(i).getCell(34);
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//    		Select verifyrole1 =new Select(driver.findElement(By.xpath("//*[contains(text(),'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/select")));  //select verify role
//    		verifyrole1.selectByVisibleText(cell.getStringCellValue());
    		
    		driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
    		Thread.sleep(2000);
    			
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //click administrator
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    			   
    		String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();
    		String expected2 = "qa";
    		//AssertJUnit.assertEquals(expected2, verifyrole2); 
    		//assertion12.assertAll();
    		Thread.sleep(3000);
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
        	Thread.sleep(3000);
        	
        	driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
			String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
			//AssertJUnit.assertEquals(expect, msg); 
			//assertion.assertAll();
			Thread.sleep(5000);
			
  	Close();	
        }
	}
	
    @Test (priority=13)
	public static void SetRolesToPageNewpublish13 () throws InterruptedException, IOException {	
    	SoftAssert assertion13 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1); //MasterRecordFormBuilder3 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++) {	
        	Login();
        
	    //create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ SetRolesToPagepublish13 +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(35);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//cell = sheet.getRow(i).getCell(36);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meenachi
			driver.findElement(By.xpath("//*[contains(text(), 'shiftengineer')]/parent::tr/td/input")).click(); //assign shift engineer
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(25000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
			//AssertJUnit.assertEquals(expect1, msg1); 
			//assertion13.assertAll();
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
