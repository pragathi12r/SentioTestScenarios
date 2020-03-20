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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MasterRecordBasicDetails {

	static WebDriver driver = null;
	static Actions action = null;
	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;
	
	   static File src=new File(System.getProperty("user.dir") + "/ExcelData/Master Record - Form Builder.xls"); 
       //static String filename4 = "TestData1";
		
	@Test (priority=1)
	public static void ExitWithoutFillingUpBasicDetails1 () throws InterruptedException, IOException {	
		 	Login();
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
    		driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@id=\"basic_content\"]/div[1]/button")).click(); //close
    		Thread.sleep(2000);
    		Close();
        }
	
	@Test (priority=2)
	public static void ExitBasicDetailsPopUpFormAfterFillingItUpPartially2 () throws InterruptedException, IOException {	
            FileInputStream finput = new FileInputStream(src);
            workbook = new HSSFWorkbook(finput);
            sheet= workbook.getSheetAt(0);
            for(int i=2; i<=sheet.getLastRowNum(); i++) {
            
            Login();
    		driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
    		driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
    		Thread.sleep(3000);
    		
    		//Directly click continue
    		driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
    		WebElement successmsg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
    		String text1 = successmsg1.getText();
    		String expect1 = "Please enter the master record name.";
    		AssertJUnit.assertEquals(expect1, text1);  
    		Thread.sleep(5000);
    		cell = sheet.getRow(i).getCell(1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
          
    		//select template category and click
    		cell = sheet.getRow(i).getCell(2);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
    		templateCategory.selectByVisibleText(cell.getStringCellValue()); 
    		driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
    		WebElement successmsg2 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
    		String text2 = successmsg2.getText();
    		String expect2 = "Fill all the mandatory field(s).";
    		AssertJUnit.assertEquals(expect2, text2);  
    		Thread.sleep(5000);
          
    		//select template type and click
    		cell = sheet.getRow(i).getCell(3);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
    		templateType.selectByVisibleText(cell.getStringCellValue());
    		driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
    		AssertJUnit.assertEquals(expect2, text2);   
    		Thread.sleep(5000);
    		
    		//type template purpose & click
    		cell = sheet.getRow(i).getCell(4);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
    		driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
    		AssertJUnit.assertEquals(expect2, text2);  
    		Thread.sleep(5000);
    		
    		//type type document & click
    		cell = sheet.getRow(i).getCell(5);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
    		driver.findElement(By.xpath("//*[@id=\"type_name\"]")).clear(); //clear mpr name
    		driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
    		AssertJUnit.assertEquals(expect1, text1);  
    		//assertion.assertAll(); 
    		Thread.sleep(5000);
    		
    		//close popup
    		driver.findElement(By.xpath("//*[@id=\"basic_content\"]/div[1]/button")).click();
    		Thread.sleep(2000);
    		Close();
        }
	} 
	
	@Test (priority=3)
	public static void ExitBasicDetailsPopUpFormAfterFillingItUpFully3 () throws InterruptedException, IOException {	
		    
            FileInputStream finput = new FileInputStream(src);
            workbook = new HSSFWorkbook(finput);
            sheet= workbook.getSheetAt(0);
            for(int i=2; i<=sheet.getLastRowNum(); i++)
            {	
    		Login();
    		driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
    		driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
    		
    	//Fill basic details
    		cell = sheet.getRow(i).getCell(6);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
    		cell = sheet.getRow(i).getCell(7);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
    		templateCategory.selectByVisibleText(cell.getStringCellValue());
    		cell = sheet.getRow(i).getCell(8);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
    		templateType.selectByVisibleText(cell.getStringCellValue());
    		cell = sheet.getRow(i).getCell(9);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
    		driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
    		cell = sheet.getRow(i).getCell(10);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
    		driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
    		
    		//close popup
    		driver.findElement(By.xpath("//*[@id=\"basic_content\"]/div[1]/button")).click();
    		Thread.sleep(2000);
    		Close();
            }
            
	}

	@Test (priority=4)  
	public static void FillUpAndContinue4 () throws InterruptedException, IOException {	
	     
        FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(0);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
		Login();
		driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
		driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
		
	//Fill basic details
		cell = sheet.getRow(i).getCell(11);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(12);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
		templateCategory.selectByVisibleText(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(13);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
		templateType.selectByVisibleText(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(14);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(15);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
		//save
		driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
		
		Thread.sleep(2000);
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

        
//        driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[3]")).click(); //click my sites
//        driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records        
//        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(1));
	}
	
	public static void Close() throws InterruptedException, IOException{	
		driver.quit();
	}

	
	
	
}


