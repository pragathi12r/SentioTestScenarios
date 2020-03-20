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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StartBatch {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/BatchIssuance.xls"); 
    static String DataFieldQuestionType = "TestBatch900"; 
    static String DataFieldDynamicMatrix = "TestBatch901";
    static String ExpressionQuestionType = "TestBatch902";
    static String TimerQuestionType = "TestBatch903";
    static String StopWatch = "TestBatch904";
    static String AddTotalField = "TestBatch905";
    static String AddValidators = "TestBatch906";
    static String AddVerify = "TestBatch907";
    static String AddBy = "TestBatch908";
    
    
    
  
	   @Test (priority=1) 
		public static void DataFieldQuestionType1 () throws InterruptedException, IOException {
		   SoftAssert assertion1 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2); //start batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++){	
	        	Login();
	       	
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ DataFieldQuestionType +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
	        	Thread.sleep(5000);
	        	
	        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
		       	String expect = "TestBatch3"; 
				//assertion1.assertEquals(expect, msg); 
				//assertion1.assertAll();
		    	Thread.sleep(5000);
		    	
		    	cell = sheet.getRow(i).getCell(1);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select celltype3 =new Select(driver.findElement(By.xpath("//*[@aria-label='RawMaterial']"))); //question1
			    celltype3.selectByVisibleText(cell.getStringCellValue());
		    	    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
		    	Thread.sleep(5000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
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
		public static void DataFieldDynamicMatrix2 () throws InterruptedException, IOException {	
		   SoftAssert assertion2 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2); //start batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++){	
	        	Login();
	        	
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ DataFieldDynamicMatrix +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
	        	Thread.sleep(5000);
	        	
	        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
		       	String expect = "TestBatch4"; 
				//assertion2.assertEquals(expect, msg); 
		    	Thread.sleep(5000);
		    	
		    	cell = sheet.getRow(i).getCell(9);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select batchNumber =new Select(driver.findElement(By.xpath("//*[@aria-label=\"Batch Number\"]"))); //batchNumber
			    batchNumber.selectByVisibleText(cell.getStringCellValue());
			    Thread.sleep(2000);
			    
			    cell = sheet.getRow(i).getCell(10);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select materialName =new Select(driver.findElement(By.xpath("//*[@aria-label=\"Raw Material\"]"))); //rawMaterial
			    materialName.selectByVisibleText(cell.getStringCellValue());
			    Thread.sleep(2000);
			    
			    cell = sheet.getRow(i).getCell(11);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select releaseDate =new Select(driver.findElement(By.xpath("//*[@aria-label=\"Release Date\"]"))); //releaseDate
			    releaseDate.selectByVisibleText(cell.getStringCellValue());
			    Thread.sleep(2000);
			    
			    cell = sheet.getRow(i).getCell(12);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select expirationDate =new Select(driver.findElement(By.xpath("//*[@aria-label=\"Expiration Date\"]"))); //expirationDate
			    expirationDate.selectByVisibleText(cell.getStringCellValue());
			    Thread.sleep(2000);
			    
			    cell = sheet.getRow(i).getCell(13);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select quantity =new Select(driver.findElement(By.xpath("//*[@aria-label=\"Quantity\"]"))); //quantity
			    quantity.selectByVisibleText(cell.getStringCellValue());
			    Thread.sleep(2000);
		 
//			    cell = sheet.getRow(i).getCell(14);
//			    cell.setCellType(Cell.CELL_TYPE_STRING);
//			    driver.findElement(By.xpath("//*[@aria-label=\"Sum\"]")).sendKeys(cell.getStringCellValue()); //Sum		    	
			    
			    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody//tr[2]/td[7]/button")).click(); //click remove
			    Thread.sleep(5000);
			    		    
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
		    	Thread.sleep(5000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
	            Thread.sleep(5000);
	        	
	        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
		       	String expect1 = "Batch Records"; 
				//AssertJUnit.assertEquals(expect1, msg1); 
				//assertion2.assertAll();
		    	Thread.sleep(5000);
	        	Close();           
	        }
	   }
	

    @Test (priority=3) 
	public static void ExpressionQuestionType3 () throws InterruptedException, IOException {	
    	SoftAssert assertion3 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(2); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ ExpressionQuestionType +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestBatch5"; 
			//assertion3.assertEquals(expect, msg); 
			//assertion3.assertAll();
	    	Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(2);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //new question 1
	    	
	    	cell = sheet.getRow(i).getCell(3);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@aria-label=\"question2\"]")).sendKeys(cell.getStringCellValue()); //new question 2
	    	    	
		    driver.findElement(By.xpath("//*[@data-bind=\"text:question.koDisplayValue, css: question.koCss().root\"]")).click();
		    
		    String msg2 = driver.findElement(By.xpath("//*[@data-bind=\"text:question.koDisplayValue, css: question.koCss().root\"]")).getText();
	       	String expect2 = "47"; 
			//AssertJUnit.assertEquals(expect2, msg2); 
			//assertion3.assertAll();
	    	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//assertion3.assertEquals(expect1, msg1); 
			//assertion3.assertAll();
	    	Thread.sleep(5000);
        	Close();           
        }
   }


    @Test (priority=4) 
	public static void TimerQuestionType4 () throws InterruptedException, IOException {	
    	SoftAssert assertion4 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(2); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ TimerQuestionType +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestBatch6"; 
			//assertion4.assertEquals(expect, msg); 
	    	Thread.sleep(5000);
	    	
	    	WebElement msg22 = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]"));
		   	String texts22 = msg22.getText();
		   	String expected22 = "Start your Timer";
		   	//assertion4.assertEquals(expected22, texts22);  
		   	Thread.sleep(5000);
		   	
		   	String start = driver.findElement(By.xpath("//*[@class=\"btn btn-info btn-xs cmplt\"]")).getText();
 	        boolean expectstart = start.contains("Start"); 
 			//assertion4.assertTrue(expectstart, start);
		   	
		   	driver.findElement(By.xpath("//*[@class=\"btn btn-info btn-xs cmplt\"]")).click(); //start
		   	Thread.sleep(5000);
		  
		   	String started = driver.findElement(By.xpath("//*[contains(text(),'Started')]")).getText();
 	        boolean expectstarted = started.contains("Started"); 
 			//assertion4.assertTrue(expectstarted, started);
 			Thread.sleep(60000);
 			String stop = driver.findElement(By.xpath("//*[contains(text(),'Stop')]")).getText();
 	        boolean expectstop = stop.contains("Stop"); 
 			//AssertJUnit.assertTrue(expectstop, stop);
 		
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//assertion4.assertEquals(expect1, msg1); 
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
        sheet= workbook.getSheetAt(2); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ StopWatch +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestBatch7"; 
			//assertion5.assertEquals(expect, msg); 
	    	Thread.sleep(5000);
	    	
	    	WebElement msg22 = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]"));
		   	String texts22 = msg22.getText();
		   	String expected22 = "Start your stopwatch";
		   	//assertion5.assertEquals(expected22, texts22);  
		   	Thread.sleep(5000);
		   	
		   	String start = driver.findElement(By.xpath("//*[@class=\"btn btn-info btn-xs cmplt\"]")).getText();
 	        boolean expectstart = start.contains("Start"); 
 			//assertion5.assertTrue(expectstart, start);
 			Thread.sleep(3000);
		   	
		   	driver.findElement(By.xpath("//*[@class=\"btn btn-info btn-xs cmplt\"]")).click(); //start
		   	Thread.sleep(5000);
		  
		   	String started = driver.findElement(By.xpath("//*[contains(text(),'Started')]")).getText();
 	        boolean expectstarted = started.contains("Started"); 
 			//assertion5.assertTrue(expectstarted, started);
 			Thread.sleep(5000);
 			
 			driver.findElement(By.xpath("//*[contains(text(),'Started')]")).click(); //click started to stop
 			Thread.sleep(3000);
 			
 			String stop = driver.findElement(By.xpath("//*[contains(text(),'Stop')]")).getText();
 	        boolean expectstop = stop.contains("Stop"); 
 			//assertion5.assertTrue(expectstop, stop);
 			Thread.sleep(3000);
 					
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//AssertJUnit.assertEquals(expect1, msg1);  
			//assertion5.assertAll();
	    	Thread.sleep(5000);
        	Close();           
        }
   }   
	   
    @Test (priority=6) 
	public static void AddTotalField6 () throws InterruptedException, IOException {	
    	SoftAssert assertion6 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(2); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ AddTotalField +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
      
        	driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[1]/div/div[2]/select")).click(); //batch number
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[1]/div/div[2]/select/option[2]")).click(); 
        	
        	driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[2]/div/div[2]/select")).click(); //materialName
        	Thread.sleep(2000);
        	driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[2]/div/div[2]/select/option[2]")).click();
        	
//        	cell = sheet.getRow(i).getCell(15);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    Select batchNumber1 =new Select(driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[1]/div/div[2]/select"))); //batchNumber
//		    batchNumber1.selectByVisibleText(cell.getStringCellValue());
//		    Thread.sleep(2000);		    
//		    cell = sheet.getRow(i).getCell(16);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    Select materialName1 =new Select(driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[2]/div/div[2]/select"))); //materialName
//		    materialName1.selectByVisibleText(cell.getStringCellValue());
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(17);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[3]/div/input")).sendKeys(cell.getStringCellValue()); //Sum		
		    
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[1]/div/div[2]/select")).click(); //batchNumber
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[1]/div/div[2]/select/option[2]")).click();
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[2]/div/div[2]/select")).click(); //materialName
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[2]/div/div[2]/select/option[2]")).click();
		    
//		    cell = sheet.getRow(i).getCell(18);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    Select batchNumber2 =new Select(driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[1]/div/div[2]/select"))); //batchNumber
//		    batchNumber2.selectByVisibleText(cell.getStringCellValue());
//		    Thread.sleep(2000); 
//		    cell = sheet.getRow(i).getCell(19);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    Select materialName2 =new Select(driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[2]/div/div[2]/select"))); //materialName
//		    materialName2.selectByVisibleText(cell.getStringCellValue());
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(20);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[3]/div/input")).sendKeys(cell.getStringCellValue()); //Sum		
		    Thread.sleep(5000);
		    
		    String total = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[2]/div[3]/table/tfoot/tr/td[3]/td/div/div[2]")).getText();
	       	String expecttotal = "Total:68"; 
			//AssertJUnit.assertEquals(expecttotal, total); 
			//assertion6.assertAll();
		    		    
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//assertion6.assertEquals(expect1, msg1); 
	    	Thread.sleep(5000);
        	Close();              
        }
   }
	   
    @Test (priority=7) 
	public static void AddValidators7 () throws InterruptedException, IOException {	
    	SoftAssert assertion7 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(2); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
       	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ AddValidators +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
    
        	cell = sheet.getRow(i).getCell(21);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class=\"sv_q_text_root\"]")).sendKeys(cell.getStringCellValue()); //new question 1
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]")).click(); //click label
			driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete

			
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//AssertJUnit.assertEquals(expect1, msg1); 
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
        sheet= workbook.getSheetAt(2); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
       	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ AddVerify +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);  
	   
        	cell = sheet.getRow(i).getCell(4);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@aria-label=\"New Question\"]")).sendKeys(cell.getStringCellValue()); //question 1
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).click(); //click verify
			Thread.sleep(3000);
			
			cell = sheet.getRow(i).getCell(5);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys(cell.getStringCellValue()); //username
			Thread.sleep(2000);
			
			cell = sheet.getRow(i).getCell(6);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_password\"]")).sendKeys(cell.getStringCellValue()); //password
			Thread.sleep(2000);
			
//			cell = sheet.getRow(i).getCell(7);
//	        cell.setCellType(Cell.CELL_TYPE_STRING);
//			driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //remarks
//			Thread.sleep(2000);
        	
        	driver.findElement(By.xpath("//*[@id=\"validate_user\"]")).click(); //click verify button
        	Thread.sleep(10000);
        	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(10000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
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
        sheet= workbook.getSheetAt(2); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ AddBy +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000); 
        	
        	driver.findElement(By.xpath("//*[contains(text(), 'Two')]")).click(); //click rating option
        	Thread.sleep(3000);
        	
			driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).click(); //click by date
			Thread.sleep(3000);
			
			cell = sheet.getRow(i).getCell(0);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_name1\"]")).sendKeys(cell.getStringCellValue()); //username
			Thread.sleep(2000);
			
			cell = sheet.getRow(i).getCell(8);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_password1\"]")).sendKeys(cell.getStringCellValue()); //password
			Thread.sleep(2000);
			
			cell = sheet.getRow(i).getCell(7);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_cmt1\"]")).sendKeys(cell.getStringCellValue()); //remarks
			Thread.sleep(2000);
        	driver.findElement(By.xpath("//*[@id=\"checked_by\"]")).click(); //click submit button
        	Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"btn btn-info btn-xs bydate cmplt question1QBD\"]")).getText();
	       	String expect1 = "Checked"; 
			AssertJUnit.assertEquals(expect1, msg1); 
			assertion9.assertAll();
        	
        	
        	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg2 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect2 = "Batch Records"; 
			//assertion9.assertEquals(expect2, msg2); 
			//assertion9.assertAll();
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
        driver.findElement(By.cssSelector("#_58_login")).sendKeys("meenachi");
        driver.findElement(By.cssSelector("#_58_password")).sendKeys("test@123");
        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
        Thread.sleep(5000);
        
//        driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//        driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(1));
	}

	public static void Close() throws InterruptedException, IOException{	
		driver.quit();
	}
}
