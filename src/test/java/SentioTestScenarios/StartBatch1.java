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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StartBatch1 {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/DeviationForm.xls"); 
    static String Complete = "TestDeviation CreateBatch1";
    static String CompleteWithRequiredField = "TestDeviation CreateBatch2";
    static String CompleteWithRequiredFieldsAndDeviation = "TestDeviation CreateBatch3";
    static String MasterFieldQuestionType = "TestDeviation CreateBatch4";
    static String MasterFieldDynamicMatrix = "TestDeviation CreateBatch5";
    static String Validation = "TestDeviation CreateBatch7";
    static String CompleteBydateVerifyFlow = "TestDeviation CreateBatch8";
	   
    @Test (priority=1) 
	public static void Complete1 () throws InterruptedException, IOException {
    	SoftAssert assertion1 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4); //start batch1 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ Complete +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestDeviation CreateBatch1"; 
			//assertion1.assertEquals(expect, msg); 
			Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(1);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question1
		    driver.findElement(By.xpath("//*[@class=\"sv_q_title\"]/span[3]")).click(); //click question label
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete
		    String msg2 = driver.findElement(By.xpath("//*[@id=\"0\"]")).getText();
	       	String expect2 = "Completed"; 
			AssertJUnit.assertEquals(expect2, msg2); 
			assertion1.assertAll();
	    	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//assertion1.assertEquals(expect1, msg1); 
	    	Thread.sleep(5000);
        	Close();           
        }
   }

    @Test (priority=2) 
	public static void CompleteWithRequiredField2 () throws InterruptedException, IOException {	
    	SoftAssert assertion2 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4); //start batch1 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ CompleteWithRequiredField +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestDeviation CreateBatch2"; 
			//assertion2.assertEquals(expect, msg); 
			Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(2);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question1
		    driver.findElement(By.xpath("//*[@class=\"sv_q_title\"]/span[3]")).click(); //click question label
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete
		    String msg2 = driver.findElement(By.xpath("//*[@id=\"0\"]")).getText();
	       	String expect2 = "Completed"; 
			AssertJUnit.assertEquals(expect2, msg2); 
			assertion2.assertAll();
	    	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//assertion2.assertEquals(expect1, msg1); 
			//assertion2.assertAll();
	    	Thread.sleep(5000);
        	Close();           
        }
   }

    @Test (priority=3) 
	public static void CompleteWithRequiredFieldsAndDeviation3 () throws InterruptedException, IOException {
    	SoftAssert assertion3 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4); //start batch1 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ CompleteWithRequiredFieldsAndDeviation +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestDeviation CreateBatch3"; 
			//assertion3.assertEquals(expect, msg); 
			Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(3);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question1
		    driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).click(); //click label
		    driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete
//		    String msg2 = driver.findElement(By.xpath("//*[@id=\"0\"]")).getText();
//	       	String expect2 = "Completed"; 
//			assertion.assertEquals(expect2, msg2); 
			
			String deviation = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[2]/div/div/div")).getText();
		    String expectdeviation = "Deviation Occured Please Check It"; 
			AssertJUnit.assertEquals(expectdeviation, deviation); 
			assertion3.assertAll();
			
	    	    	
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
	public static void MasterFieldQuestionType4 () throws InterruptedException, IOException {	
		SoftAssert assertion4 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4); //start batch1 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ MasterFieldQuestionType +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestDeviation CreateBatch4"; 
			//assertion4.assertEquals(expect, msg); 
			Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(4);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[contains(text(), 'Raw Material')]/parent::h5/parent::div/parent::div/div[2]/input")).sendKeys(cell.getStringCellValue()); //question1
		    
		    cell = sheet.getRow(i).getCell(5);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[contains(text(), 'Batch Number')]/parent::h5/parent::div/parent::div/div[2]/input")).sendKeys(cell.getStringCellValue()); //question1
		    
		    cell = sheet.getRow(i).getCell(6);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[contains(text(), 'Release Date')]/parent::h5/parent::div/parent::div/div[2]/input")).sendKeys(cell.getStringCellValue()); //question1
		    
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			AssertJUnit.assertEquals(expect1, msg1); 
			assertion4.assertAll();
	    	Thread.sleep(5000);
        	Close();            
        }
   }

   @Test (priority=5) 
	public static void MasterFieldDynamicMatrix5 () throws InterruptedException, IOException {
	   SoftAssert assertion5 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4); //start batch1 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();
       	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ MasterFieldDynamicMatrix +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestDeviation CreateBatch5"; 
			//assertion5.assertEquals(expect, msg); 
			Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(4);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[1]/div/input")).sendKeys(cell.getStringCellValue()); //question1
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[2]/div/input")).click();
		    cell = sheet.getRow(i).getCell(5);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[1]/div/input")).sendKeys(cell.getStringCellValue()); //question1
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[2]/div/input")).click();
  
		    cell = sheet.getRow(i).getCell(6);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[1]/td[6]/div/input")).sendKeys(cell.getStringCellValue()); //sum 1
		    cell = sheet.getRow(i).getCell(7);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_matrix_dynamic\"]/tbody/tr[2]/td[6]/div/input")).sendKeys(cell.getStringCellValue()); //sum 2
		    
		       	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			AssertJUnit.assertEquals(expect1, msg1); 
			assertion5.assertAll();
	    	Thread.sleep(5000);
        	Close();           
        }
   }

 
    @Test (priority=6) 
	public static void Validation6 () throws InterruptedException, IOException {	
    	SoftAssert assertion6 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4); //start batch1 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
            Login();       	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ Validation +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestDeviation CreateBatch7"; 
			//assertion6.assertEquals(expect, msg); 
			Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(8);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question1
		    driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]")).click(); //click question1 label
		    
		    driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete
		    Thread.sleep(3000);
		    
		    String valerr = driver.findElement(By.xpath("//*[@class=\"sv_q_erbox\"]/div/span[2]/span")).getText();
		    String expectvalerr = "Your answer should be in email format";
		    AssertJUnit.assertEquals(expectvalerr, valerr); 
			assertion6.assertAll();
	    	Thread.sleep(5000);
		       	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
            
            cell = sheet.getRow(i).getCell(9);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).click();
		    action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
	    	action.sendKeys(Keys.DELETE).perform();
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question1
		    driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]")).click(); //click question1 label
		    
		    driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete
		    Thread.sleep(5000);
		    
		    String val = driver.findElement(By.xpath("//*[@id=\"0\"]")).getText();
		    String expectval = "Completed";
		    //assertion6.assertEquals(expectval, val); 
			//assertion6.assertAll();
	    	Thread.sleep(5000);
            
		    driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//assertion6.assertEquals(expect1, msg1); 
			//assertion6.assertAll();
	    	Thread.sleep(5000);
        	Close();           
        }
   }    	
  
   @Test (priority=7) 
	public static void CompleteBydateVerifyFlow7 () throws InterruptedException, IOException {	
	   SoftAssert assertion7 = new SoftAssert();
	    FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4); //start batch1 sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	Login();       	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ CompleteBydateVerifyFlow +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "TestDeviation CreateBatch8"; 
			//assertion7.assertEquals(expect, msg); 
			Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(10);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question1
		    driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]")).click(); //click question1 label
		    
		    driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete
		    Thread.sleep(3000);
		    
		    String valerr = driver.findElement(By.xpath("//*[@class=\"sv_q_erbox\"]/div/span[2]/span")).getText();
		    String expectvalerr = "Your answer should be in email format";
		    AssertJUnit.assertEquals(expectvalerr, valerr); 
			assertion7.assertAll();
	    	Thread.sleep(5000);
		       	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
            
            cell = sheet.getRow(i).getCell(11);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).click();
		    action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
	    	action.sendKeys(Keys.DELETE).perform();
		    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question1
		    driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]")).click(); //click question1 label
		    
		    driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete
		    Thread.sleep(5000);
		    
		    String val = driver.findElement(By.xpath("//*[@id=\"0\"]")).getText();
		    String expectval = "Completed";
		    //assertion7.assertEquals(expectval, val); 
			//assertion7.assertAll();
	    	Thread.sleep(5000);

	    	driver.findElement(By.xpath("//*[@class=\"sv_q_title\"]/button[2]")).click(); //click by date
	    	
	    	cell = sheet.getRow(i).getCell(12);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@id=\"user_name1\"]")).sendKeys(cell.getStringCellValue()); //username
	    	
		    cell = sheet.getRow(i).getCell(13);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@id=\"user_password1\"]")).sendKeys(cell.getStringCellValue()); //password
	    	
		    cell = sheet.getRow(i).getCell(14);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@id=\"user_cmt1\"]")).sendKeys(cell.getStringCellValue()); //remarks
	    	Thread.sleep(3000);
		    driver.findElement(By.xpath("//*[@id=\"checked_by\"]")).click(); //submit
		    Thread.sleep(3000);
		    
		    String check = driver.findElement(By.xpath("//*[@class=\"btn btn-info btn-xs bydate cmplt question1QBD\"]")).getText();
		    String expectcheck = "Checked";
		    //assertion7.assertEquals(expectcheck, check); 
			//assertion7.assertAll();
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@class=\"sv_q_title\"]/button[3]")).click(); //click verify
			Thread.sleep(3000);
			
			cell = sheet.getRow(i).getCell(15);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys(cell.getStringCellValue()); //username
			Thread.sleep(2000);
			
			cell = sheet.getRow(i).getCell(16);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_password\"]")).sendKeys(cell.getStringCellValue()); //password
			Thread.sleep(2000);
			
//			cell = sheet.getRow(i).getCell(17);
//	        cell.setCellType(Cell.CELL_TYPE_STRING);
//			driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //remarks
//			Thread.sleep(2000);
        	
        	driver.findElement(By.xpath("//*[@id=\"validate_user\"]")).click(); //click verify button
	    	Thread.sleep(5000);
        	String veri = driver.findElement(By.xpath("//*[@class=\"sv_q_title\"]/button[3]")).getText();
		    String expectveri = "Verified";
		    //assertion7.assertEquals(expectveri, veri); 
			//assertion7.assertAll();
	    	Thread.sleep(5000);
	    	
	    	
		    driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			//assertion7.assertEquals(expect1, msg1); 
			//assertion7.assertAll();
	    	Thread.sleep(5000);
        	Close();           
        }
   }    	
      	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	public static void Login() throws InterruptedException, IOException{
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
                
//                driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//                driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//                ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//                driver.switchTo().window(tabs.get(1));
        	}

        	public static void Close() throws InterruptedException, IOException{	
        		driver.quit();
        	}
}
