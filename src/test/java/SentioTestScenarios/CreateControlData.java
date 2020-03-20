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

public class CreateControlData {
	static WebDriver driver = null;
	static Actions action = null;
	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/CreateControlData.xls"); 
    static String filename = "TestControlData1";
	
	@Test (priority=1)
	public static void MasterControlData1 () throws InterruptedException, IOException {	
		SoftAssert assertion= new SoftAssert();
        FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1); //controldata sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();	        	
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
    		driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input	
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')]")).click(); //click properties icon
     		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[contains(@title,'Edit selected object')]")).click();  //click properties edit icon	
    		cell = sheet.getRow(i).getCell(7);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
            driver.findElement(By.xpath("//*[@data-bind=\"visible: koShowApplyButton, click: onApplyClick, value: $root.getLocString('pe.apply')\"]")).click(); //Apply
    		driver.findElement(By.xpath("//*[@data-bind=\"click: onOkClick, value: $root.getLocString('pe.ok')\"]")).click(); //ok
    		Thread.sleep(5000);
    		
            driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input	
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
     		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[contains(@title,'Edit selected object')]")).click();  //click properties edit icon
    		cell = sheet.getRow(i).getCell(8);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
            driver.findElement(By.xpath("//*[@data-bind=\"visible: koShowApplyButton, click: onApplyClick, value: $root.getLocString('pe.apply')\"]")).click(); //Apply
    		driver.findElement(By.xpath("//*[@data-bind=\"click: onOkClick, value: $root.getLocString('pe.ok')\"]")).click(); //ok
    		Thread.sleep(5000);
    		
            driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input	
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
     		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[contains(@title,'Edit selected object')]")).click();  //click properties edit icon
    		cell = sheet.getRow(i).getCell(9);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
            driver.findElement(By.xpath("//*[@data-bind=\"visible: koShowApplyButton, click: onApplyClick, value: $root.getLocString('pe.apply')\"]")).click(); //Apply
    		driver.findElement(By.xpath("//*[@data-bind=\"click: onOkClick, value: $root.getLocString('pe.ok')\"]")).click(); //ok
    		Thread.sleep(5000);
    		
            driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input	
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
     		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[contains(@title,'Edit selected object')]")).click();  //click properties edit icon
    		cell = sheet.getRow(i).getCell(10);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
            driver.findElement(By.xpath("//*[@data-bind=\"visible: koShowApplyButton, click: onApplyClick, value: $root.getLocString('pe.apply')\"]")).click(); //Apply
    		driver.findElement(By.xpath("//*[@data-bind=\"click: onOkClick, value: $root.getLocString('pe.ok')\"]")).click(); //ok
    		Thread.sleep(5000);
    		
            driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input	
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
     		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[contains(@title,'Edit selected object')]")).click();  //click properties edit icon
    		cell = sheet.getRow(i).getCell(11);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
            driver.findElement(By.xpath("//*[@data-bind=\"visible: koShowApplyButton, click: onApplyClick, value: $root.getLocString('pe.apply')\"]")).click(); //Apply
    		driver.findElement(By.xpath("//*[@data-bind=\"click: onOkClick, value: $root.getLocString('pe.ok')\"]")).click(); //ok
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save 
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[contains(text(), 'Complete')]")).click(); //complete
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
    		Thread.sleep(10000);
    		String redirect = driver.findElement(By.xpath("//*[@id=\"bookshelf\"]")).getText();
 	        boolean expectredirect1 = redirect.contains("Batch Records "); 
 			AssertJUnit.assertSame(expectredirect1, redirect);
 			//assertion.assertAll();
 			//Close();
        }
	}
	
	@Test (priority=2)
	public static void PublishControlData2 () throws InterruptedException, IOException {	
		SoftAssert assertion= new SoftAssert();
        FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1); //controldata sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();	        	
		 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
		 	driver.findElement(By.xpath("//*[contains(text(),'" + filename + "')]")).click();  //Click tempo template record
	    	Thread.sleep(5000);
	    	driver.findElements(By.className("item")).get(5).click();  //click publish
	    	Thread.sleep(5000);
    		
	    	//driver.findElement(By.xpath("//*[contains(text(), 'qualityengineer')]/parent::tr/td/input")).click(); //assign admin
	    	driver.findElement(By.xpath("//*[contains(text(), 'qualityengineer')]/parent::tr/td/input")).click(); //assign qa
	    	driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			
			String redirect = driver.findElement(By.xpath("//*[@id=\"bookshelf\"]")).getText();
    		String expectredirect = "Batch Records ";
    		AssertJUnit.assertEquals(expectredirect, redirect);  
    		//assertion.assertAll();
    		//Close();
        }
	}
	
	@Test (priority=3)
	public static void NewControlData3 () throws InterruptedException, IOException {	
		SoftAssert assertion= new SoftAssert();
        FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1); //controldata sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();	        	
		 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
		 	driver.findElement(By.xpath("//*[contains(text(),'" + filename + "')]")).click();  //Click tempo template record
	    	Thread.sleep(5000);
	    	driver.findElements(By.className("item")).get(4).click();  //click new
	    	Thread.sleep(5000);
    		
	    	driver.findElement(By.xpath("//*[@class=\"type_category1 category_type_1\"]")).clear();
			cell = sheet.getRow(i).getCell(12);
            cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(0);
			driver.findElement(By.xpath("//*[@class=\"type_category1 category_type_1\"]")).sendKeys("11/07/2019"); //effective date
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			
			driver.findElement(By.xpath("//*[contains(text(), 'qualityengineer')]/parent::tr/td/input")).click(); //assign qa
			//driver.findElement(By.xpath("//*[contains(text(), 'productionengineer')]/parent::tr/td/input")).click(); //assign pe
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			
			String redirect = driver.findElement(By.xpath("//*[@id=\"bookshelf\"]")).getText();
    		String expectredirect = "Batch Records ";
    		AssertJUnit.assertEquals(expectredirect, redirect);  
    		//assertion.assertAll(); 
    		//Close();
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
	        driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[3]")).click(); //click my sites
	        driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
	        
	        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	        driver.switchTo().window(tabs.get(1));
		}

		public static void Close() throws InterruptedException, IOException{	
			driver.quit();
		}
}
