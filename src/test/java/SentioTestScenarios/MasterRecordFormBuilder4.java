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
import org.openqa.selenium.JavascriptExecutor;

public class MasterRecordFormBuilder4 {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/BatchIssuance.xls"); 
	static String LoginAsDifferentUserPublish = "TestCloseData609";
	static String LoginAsDifferentUserStartBatch = "TestCloseBatch609"; //3rd page assigned for qa, completed only 1&2 pages, close action still incomplete
	static String LoginAsDifferentUserPublishWithoutCompleteLastStage = "TestCloseData704";
	static String LoginAsDifferentUserStartBatchWithouCompleteLastStage = "TestCloseBatch704"; //3rd page assigned for admin, completed 3 pages, close action still incomplete
	static String PublishValidatorsWithDeviation = "TestDeviationData803";
	static String StartBatchValidatorsWithDeviation = "TestDeviationBatch803";
	 

	@Test (priority=1)
	public static void WithCompleteQAStage1 () throws InterruptedException, IOException {	
		SoftAssert assertion1 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4); //close batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
        	//temporary code to view the form
//        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//        	driver.findElement(By.xpath("//*[contains(text(),'TestCloseData1')]")).click();  //Click temporary file
//        	Thread.sleep(5000);
//        	driver.findElements(By.className("item")).get(4).click();  //click view form
//        	Thread.sleep(10000);
//        	driver.findElement(By.xpath("//*[@id=\"two-circle\"]")).click(); //No need its a issue
        	
        	
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
			//assertion1.assertEquals(expectuploadedfile1text, uploadedfile1text);  
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
    		driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected1 = "[\"qa\",\"test\"]";
    		//assertion1.assertEquals(expected1, role1);  
    
//    		driver.findElement(By.xpath("//*[@class=\"select2-selection__arrow\"]")).click(); // click question type dropdown
//    		driver.findElement(By.xpath("//li[contains(text(), '..question1')]")).click(); //click ..question1
    		
//    		Thread.sleep(6000);
//        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
    		
//    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
//    		cell = sheet.getRow(i).getCell(7);
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//    		Select verifyrole1 =new Select(driver.findElement(By.xpath("//*[contains(text(),'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/select")));  //select verify role
//    		verifyrole1.selectByVisibleText(cell.getStringCellValue());
    		
//    		String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/select")).getText();
//    		String expected2 = "qa";
//    		assertion.assertEquals(expected2, verifyrole2); 
    		Thread.sleep(3000);
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
        	Thread.sleep(3000);
    		
    		//Form Builder
    		driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click(); //add new page
    		Thread.sleep(3000);
    		  		
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"toolBoxDiv\"]/div/div[3]")).click(); //single input
    		
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')]")).click(); //click properties icon
    		Thread.sleep(5000);
    			
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '.page2')]")).click(); //click .page2

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
    		//driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected2 = "test";
    		//assertion1.assertEquals(expected2, role2);  

    		//Form Builder
    		driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click(); //add new page
    		Thread.sleep(3000);
    		  		
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"toolBoxDiv\"]/div/div[3]")).click(); //single input
    		
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')]")).click(); //click properties icon
    		Thread.sleep(5000);
    			
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '.page3')]")).click(); //click .page3

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		//driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role3 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected3 = "qa";
    		//AssertJUnit.assertEquals(expected3, role3); 
    		//assertion1.assertAll();
    		Thread.sleep(3000);
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
        	Thread.sleep(3000);
			
			driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
    		Close();	
    		
        }
	}
	
	@Test (priority=2)
	public static void CreateBatchWithCompleteQAStage2 () throws InterruptedException, IOException {
		SoftAssert assertion2 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4); //close batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
       	
	    //create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ LoginAsDifferentUserPublish +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(8);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//cell = sheet.getRow(i).getCell(9);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
			driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
			
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign admin
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			//driver.findElement(By.xpath("//*[contains(text(), 'adminuser')]/parent::tr/td/input")).click(); //assign admin
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(8000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
			//AssertJUnit.assertEquals(expect1, msg1); 
			//assertion2.assertAll();
	    	Thread.sleep(5000);
        	Close();      	
        }
	}

	 @Test (priority=3) 
		public static void StartBatchWithCompleteQAStage3 () throws InterruptedException, IOException {	
		 SoftAssert assertion3 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4); //start batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++){	
	        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
				driver = new ChromeDriver();
				action = new Actions(driver);
//				assertion= new SoftAssert();
				driver.manage().window().maximize();
				wait = new WebDriverWait(driver,30);
		        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		        readProperty a = new readProperty();     
		        driver.get(a.getApplicationUrl());
		        driver.findElement(By.cssSelector("#_58_login")).sendKeys("meenachi");
		        driver.findElement(By.cssSelector("#_58_password")).sendKeys("test@123");
		        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
		        Thread.sleep(5000);
		        
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));
		        
			
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ LoginAsDifferentUserStartBatch +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
	        	Thread.sleep(5000);
	        	
//	        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
//		       	String expect = "TestCloseBatch1"; 
				//assertion3.assertEquals(expect, msg); 
		    	Thread.sleep(5000);
		    	
		    	cell = sheet.getRow(i).getCell(10);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue());  //question1
			   		    	    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
		    	Thread.sleep(5000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
	            Thread.sleep(5000);
	            
	            cell = sheet.getRow(i).getCell(11);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//*[@aria-label=\"question2\"]")).sendKeys(cell.getStringCellValue());  //question2
			   		    	    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
		    	Thread.sleep(10000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
	            Thread.sleep(5000);
	            
	        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
		       	String expect1 = "Batch Records"; 
				//AssertJUnit.assertEquals(expect1, msg1); 
				//assertion3.assertAll();
		    	Thread.sleep(5000);
	        	Close();           
	        }
	   }
	 
	 @Test (priority=4)
		public static void WithoutCompleteQAStage4 () throws InterruptedException, IOException {	
		 SoftAssert assertion4 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4); //close batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	//temporary code to view the form
//	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//	        	driver.findElement(By.xpath("//*[contains(text(),'TestCloseData2')]")).click();  //Click temporary file
//	        	Thread.sleep(5000);
//	        	driver.findElements(By.className("item")).get(4).click();  //click view form
//	        	Thread.sleep(10000);
//	        	driver.findElement(By.xpath("//*[@id=\"two-circle\"]")).click(); //No need its a issue
	        	
			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
				
			//Fill basic details
				cell = sheet.getRow(i).getCell(13);
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
				//assertion4.assertEquals(expectuploadedfile1text, uploadedfile1text);  
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
	    		driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //check supervisor

	    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
	    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
	    		
	    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
	    		String expected1 = "[\"qa\",\"test\"]";
	    		//assertion4.assertEquals(expected1, role1);  
	    
//	    		driver.findElement(By.xpath("//*[@class=\"select2-selection__arrow\"]")).click(); // click question type dropdown
//	    		driver.findElement(By.xpath("//li[contains(text(), '..question1')]")).click(); //click ..question1
	    		
//	    		Thread.sleep(6000);
//	        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
	    		
//	    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
//	    		cell = sheet.getRow(i).getCell(7);
//	            cell.setCellType(Cell.CELL_TYPE_STRING);
//	    		Select verifyrole1 =new Select(driver.findElement(By.xpath("//*[contains(text(),'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/select")));  //select verify role
//	    		verifyrole1.selectByVisibleText(cell.getStringCellValue());
	    		
//	    		String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/select")).getText();
//	    		String expected2 = "qa";
//	    		assertion.assertEquals(expected2, verifyrole2); 
	    		Thread.sleep(3000);
	    		
	    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	        	Thread.sleep(3000);
	    		
	    		//Form Builder
	    		driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click(); //add new page
	    		Thread.sleep(3000);
	    		  		
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@class=\"toolBoxDiv\"]/div/div[3]")).click(); //single input
	    		
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')]")).click(); //click properties icon
	    		Thread.sleep(5000);
	    			
	    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
	    		driver.findElement(By.xpath("//option[contains(text(), '.page2')]")).click(); //click .page2

	    		Thread.sleep(6000);
	        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

	    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
	    		Thread.sleep(2000);
	    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
	    		//driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
	    		driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //check supervisor

	    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
	    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
	    		
	    		String role2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
	    		String expected2 = "test";
	    		//assertion4.assertEquals(expected2, role2);  

	    		//Form Builder
	    		driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click(); //add new page
	    		Thread.sleep(3000);
	    		  		
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@class=\"toolBoxDiv\"]/div/div[3]")).click(); //single input
	    		
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')]")).click(); //click properties icon
	    		Thread.sleep(5000);
	    			
	    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
	    		driver.findElement(By.xpath("//option[contains(text(), '.page3')]")).click(); //click .page3

	    		Thread.sleep(6000);
	        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

	    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
	    		Thread.sleep(2000);
	    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
	    		//driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
	    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check supervisor

	    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
	    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
	    		
	    		String role3 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
	    		String expected3 = "supervisor";
	    		//AssertJUnit.assertEquals(expected3, role3); 
	    		//assertion4.assertAll();
	    		Thread.sleep(3000);
	    		
	    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	        	Thread.sleep(3000);
	    		
				driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
				Thread.sleep(5000);
	    		
	    		Close();	
	    		
	        }
		}
	
		@Test (priority=5)
		public static void CreateBatchWithoutCompleteQAStage5 () throws InterruptedException, IOException {	
			SoftAssert assertion5 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4); //close batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	
		    //create batch	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ LoginAsDifferentUserPublishWithoutCompleteLastStage +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(14);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(9);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign qa
				//driver.findElement(By.xpath("//*[contains(text(), 'adminuser')]/parent::tr/td/input")).click(); //assign admin
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
				String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
				String expect1 = "Batch Records "; 
				//AssertJUnit.assertEquals(expect1, msg1); 
				//assertion5.assertAll();
		    	Thread.sleep(5000);
	        	//Close();      	
	        }
		}

		 @Test (priority=6) 
			public static void StartBatchWithoutCompleteQAStage6 () throws InterruptedException, IOException {	
			 SoftAssert assertion6 = new SoftAssert();
			    FileInputStream finput = new FileInputStream(src);
		        workbook = new HSSFWorkbook(finput);
		        sheet= workbook.getSheetAt(4); //start batch sheet
		        for(int i=2; i<=sheet.getLastRowNum(); i++){	
		        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
					driver = new ChromeDriver();
					action = new Actions(driver);
//					assertion= new SoftAssert();
					driver.manage().window().maximize();
					wait = new WebDriverWait(driver,30);
			        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			        readProperty a = new readProperty();     
			        driver.get(a.getApplicationUrl());
			        driver.findElement(By.cssSelector("#_58_login")).sendKeys("meenachi");
			        driver.findElement(By.cssSelector("#_58_password")).sendKeys("test@123");
			        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
			        Thread.sleep(5000);
			        			        
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));
			        
			        
		        	driver.findElement(By.xpath("//*[contains(text(),'"+ LoginAsDifferentUserStartBatchWithouCompleteLastStage +"')]")).click();  //Click temporary file
		        	Thread.sleep(5000);
		        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
		        	Thread.sleep(5000);
		        	
//		        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
//			       	String expect = "TestCloseBatch2"; 
//					assertion6.assertEquals(expect, msg); 
//			    	Thread.sleep(5000);
			    	
			    	cell = sheet.getRow(i).getCell(10);
				    cell.setCellType(Cell.CELL_TYPE_STRING);
				    driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue());  //question1
				   		    	    	
			    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
			    	Thread.sleep(5000);
			    	
			    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
		            Thread.sleep(5000);
		            
		            cell = sheet.getRow(i).getCell(11);
				    cell.setCellType(Cell.CELL_TYPE_STRING);
				    driver.findElement(By.xpath("//*[@aria-label=\"question2\"]")).sendKeys(cell.getStringCellValue());  //question2
				   		    	    	
			    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
			    	Thread.sleep(10000);
			    	
			    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
		            Thread.sleep(5000);
		            
//		        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
//			       	String expect1 = "Batch Records"; 
//					assertion6.assertEquals(expect1, msg1); 
//					assertion6.assertAll();
//			    	Thread.sleep(5000);
		        	Close();           
		        	
		        	
		        	Login();
		        	driver.findElement(By.xpath("//*[contains(text(),'"+ LoginAsDifferentUserStartBatchWithouCompleteLastStage +"')]")).click();  //Click temporary file
		        	Thread.sleep(5000);
		        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
		        	Thread.sleep(5000);
		        	
		        	String msg2 = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
			       	//String expect2 = "TestCloseBatch2"; 
					//assertion6.assertEquals(expect2, msg2); 
			    	Thread.sleep(5000);

		            cell = sheet.getRow(i).getCell(12);
				    cell.setCellType(Cell.CELL_TYPE_STRING);
				    driver.findElement(By.xpath("//*[@aria-label=\"question3\"]")).sendKeys(cell.getStringCellValue());  //question3
				   		    	    	
			    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
			    	Thread.sleep(10000);
			    	
			    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
		            Thread.sleep(5000);
//		            String msg11 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
//			       	String expect11 = "Batch Records"; 
//					AssertJUnit.assertEquals(expect11, msg11); 
//					assertion6.assertAll();
//			    	Thread.sleep(5000);
		        	Close();           
		        	
		        }
		   }

	 @Test (priority=7)
		public static void ValidatorsWithDeviation7 () throws InterruptedException, IOException {	
		 SoftAssert assertion7 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4); //close batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	//temporary code to view the form
//	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//	        	driver.findElement(By.xpath("//*[contains(text(),'TestDeviationData6')]")).click();  //Click temporary file
//	        	Thread.sleep(5000);
//	        	driver.findElements(By.className("item")).get(4).click();  //click view form
//	        	Thread.sleep(10000);
//	        	driver.findElement(By.xpath("//*[@id=\"two-circle\"]")).click(); //No need its a issue
	        	
	        	
			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
			//Fill basic details
				cell = sheet.getRow(i).getCell(15);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(16);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(17);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(18);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(19);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				//upload assert
				cell = sheet.getRow(i).getCell(20);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download1.jpeg";
				//AssertJUnit.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				//assertion7.assertAll(); 
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
		   	//Form Builder
			    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon   
			    Thread.sleep(6000);
			    WebElement checkbox1 = driver.findElement(By.xpath("//*[@title=\"Complete\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//click complete checkbox
	    		if(!checkbox1.isSelected())
	    			checkbox1.click();
			    
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
	            cell = sheet.getRow(i).getCell(21);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
			    
			    driver.findElement(By.xpath("//*[contains(text(), 'Validators')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click validators
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/span/span")).click(); //click validators add icon
			    Thread.sleep(2000);
			    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/ul/li[6]/a/span")).click(); //click expression option
			    Thread.sleep(2000);
			    
			    cell = sheet.getRow(i).getCell(22);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//label[contains(text(), 'Text')]/parent::div/parent::div/div/input")).sendKeys(cell.getStringCellValue()); //text
			    
			    cell = sheet.getRow(i).getCell(23);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select validationType =new Select(driver.findElement(By.xpath("//*[contains(text(), 'Validation   Type')]/parent::div/div[2]/select"))); //validation type
				validationType.selectByVisibleText(cell.getStringCellValue());
			    
				cell = sheet.getRow(i).getCell(24);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select expressionSelectQuestion =new Select(driver.findElement(By.xpath("//label[contains(text(), 'Expression')]/parent::div/div[3]/div/div/select"))); //expression select question
				expressionSelectQuestion.selectByVisibleText(cell.getStringCellValue());
				
				cell = sheet.getRow(i).getCell(25);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//label[contains(text(), 'Expression')]/parent::div/div[3]/div[2]/textarea")).sendKeys(cell.getStringCellValue()); //Expression textarea

			    driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[2]")).click(); //ok
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	    		Thread.sleep(5000);          
	    		
				driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
				Thread.sleep(5000);
			    Close();
	        }
		}

	 @Test (priority=8)
		public static void CreateBatchValidatorsWithDeviation8 () throws InterruptedException, IOException {	
		 SoftAssert assertion8 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4); //close batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();	        	
	       
		    //create batch	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	       		driver.findElement(By.xpath("//*[contains(text(),'"+ PublishValidatorsWithDeviation +"')]")).click();  //Click temporary file
	       		Thread.sleep(5000);
	       		
	       		driver.findElements(By.className("item")).get(5).click();  //click create batch
	       		Thread.sleep(5000);    	
	       	
	       		cell = sheet.getRow(i).getCell(26);
	       		cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
				//cell = sheet.getRow(i).getCell(9);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(5000);			
				
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				//driver.findElement(By.xpath("//*[contains(text(), 'adminuser')]/parent::tr/td/input")).click(); //assign admin
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign ////*[@id="masterSave"]
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
		public static void StartBatchValidatorsWithDeviation9 () throws InterruptedException, IOException {	
		 SoftAssert assertion9 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4); //start batch sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++){	
	        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
				driver = new ChromeDriver();
				action = new Actions(driver);
//				assertion= new SoftAssert();
				driver.manage().window().maximize();
				wait = new WebDriverWait(driver,30);
		        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		        readProperty a = new readProperty();     
		        driver.get(a.getApplicationUrl());
		        driver.findElement(By.cssSelector("#_58_login")).sendKeys("meenachi");
		        driver.findElement(By.cssSelector("#_58_password")).sendKeys("test@123");
		        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
		        Thread.sleep(5000);
		        
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));
		        
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ StartBatchValidatorsWithDeviation +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
	        	Thread.sleep(5000);
	        	
	        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
		       	//String expect = "TestDeviationBatch6"; 
				//assertion9.assertEquals(expect, msg); 
		    	Thread.sleep(5000);
		    	
		    	cell = sheet.getRow(i).getCell(27);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@class=\"sv_q_text_root\"]")).sendKeys(cell.getStringCellValue()); //new question 1
				Thread.sleep(5000);
				
				driver.findElement(By.xpath("//*[contains(text(), 'question1')]")).click(); //click question label
				driver.findElement(By.xpath("//*[@id=\"0\"]")).click(); //click complete

				
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
		    	Thread.sleep(5000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
	            Thread.sleep(5000);
	        	
	        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
		       	String expect1 = "Batch Records"; 
				//AssertJUnit.assertEquals(expect1, msg1); 
				//assertion9.assertAll();
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
	        //Thread.sleep(25000);
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
