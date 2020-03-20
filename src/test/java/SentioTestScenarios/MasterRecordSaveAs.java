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

public class MasterRecordSaveAs {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/Master Record - Preview.xls"); 
    static String filename = "TestValue2"; //CopyThePublishedMasterRecord1
    static String filename1 = "TestValue1"; //CopyTheDraftMasterRecord2
	   
	   @Test (priority=1)
		public static void CopyThePublishedMasterRecord1 () throws InterruptedException, IOException {	
		   SoftAssert assertion1 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2); //save as sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(7).click();  //click save as
	        	Thread.sleep(5000);
	        	
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
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download2.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download2.jpeg";
				//assertion1.assertEquals(expectuploadedfile1text, uploadedfile1text);
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(5000);
			
		   		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save  	
		   		WebElement msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts = msg.getText();
	    		String expected = "Master record details saved successfully.";
	    		//AssertJUnit.assertEquals(expected, texts);
	    		//assertion1.assertAll();  
	    		Thread.sleep(5000);
				Close();    	
	        }
	   }
	   
	   @Test (priority=2)
		public static void CopyTheDraftMasterRecord2 () throws InterruptedException, IOException {
		   SoftAssert assertion2 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2); //save as sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(7).click();  //click save as
	        	Thread.sleep(5000);
	        	
	        	//Fill basic details
				cell = sheet.getRow(i).getCell(7);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(8);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(9);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(10);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(11);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				//upload assert
				cell = sheet.getRow(i).getCell(12);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys("TestAss1"); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download2.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download2.jpeg";
				//assertion2.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(5000);
			
		   		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save  	
		   		WebElement msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts = msg.getText();
	    		String expected = "Master record details saved successfully.";
	    		//AssertJUnit.assertEquals(expected, texts);  
	    		//assertion2.assertAll();
		   		Thread.sleep(3000);
		   		Close();    	
	        }
	   }
	   
	   @Test (priority=3)
		public static void CopyThePublishedMasterRecordAndAddQuestion3 () throws InterruptedException, IOException {	
		   SoftAssert assertion3 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2); //save as sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(7).click();  //click save as
	        	Thread.sleep(5000);
	        	
	        	//Fill basic details
				cell = sheet.getRow(i).getCell(13);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(14);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(15);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(16);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(17);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				Thread.sleep(3000);
				//upload assert
				cell = sheet.getRow(i).getCell(18);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys("TestAss2"); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download2.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download2.jpeg";
				//assertion3.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(5000);
			
		   		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save  	
		   		WebElement msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts = msg.getText();
	    		String expected = "Request submitted save copy.";
	    		//assertion3.assertEquals(expected, texts);  
	    		Thread.sleep(5000);
	    		
	    		//Form Builder
			    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon   
			    Thread.sleep(6000);
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
	            cell = sheet.getRow(i).getCell(19);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
			    driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save 
			    WebElement msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts1 = msg1.getText();
	    		String expected1 = "Master record details saved successfully.";
	    		//AssertJUnit.assertEquals(expected1, texts1);  
	    		//assertion3.assertAll();
	    		Thread.sleep(3000);	
		   		Close();    	
	        }
	   }
	   
	   @Test (priority=4)
		public static void CopyTheDraftMasterRecordAndAddQuestion4 () throws InterruptedException, IOException {	
		   SoftAssert assertion4 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2); //save as sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	        	driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(7).click();  //click save as
	        	Thread.sleep(5000);
	        	
	        	//Fill basic details
				cell = sheet.getRow(i).getCell(20);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(21);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(22);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(23);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(24);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				//upload assert
				cell = sheet.getRow(i).getCell(25);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys("TestAss3"); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download2.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download2.jpeg";
				//assertion4.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(5000);
			
		   		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save  	
		   		WebElement msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts = msg.getText();
	    		String expected = "Request submitted save copy.";
	    		//assertion4.assertEquals(expected, texts);  
	    		Thread.sleep(5000);
	    		
	    		//Form Builder
			    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon   
			    Thread.sleep(6000);
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
	            cell = sheet.getRow(i).getCell(26);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
			    driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save 
			    WebElement msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts1 = msg1.getText();
	    		String expected1 = "Master record details saved successfully.";
	    		//AssertJUnit.assertEquals(expected1, texts1); 
	    		//assertion4.assertAll();
	    		Thread.sleep(3000);
		   		Close();    	
	        }
	   }
	  
	   @Test (priority=5)
		public static void RecheckTheMainFileStatusForCopyThePublishedMasterRecord5 () throws InterruptedException, IOException {	
		   SoftAssert assertion5 = new SoftAssert(); 
		   FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2); //save as sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	//Recheck the publish status	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	         	driver.findElement(By.xpath("//*[contains(text(),'" + filename + "')]")).click();  //Click temporary file
	    		Thread.sleep(5000);
	    		driver.findElements(By.className("item")).get(4).click();  //click view form
	    		Thread.sleep(15000);
	    		driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
	    		String savecontent = driver.findElement(By.xpath("//*[@class=\"left panel asset_left_panel\"]")).getText();
	     		boolean expectsavecontent = savecontent.contains("save"); 
				//AssertJUnit.assertFalse(expectsavecontent, savecontent);
				String completecontent = driver.findElement(By.xpath("//*[@id=\"creatorElement\"]/div/div[2]/div[1]/div")).getText();
				boolean expectcompletecontent = completecontent.contains("complete"); 
				//AssertJUnit.assertFalse(expectcompletecontent, completecontent);
				driver.findElement(By.xpath("//*[@id=\"four-circle\"]")).click(); //No need its a issue
				Thread.sleep(5000);
				String publishcontent = driver.findElement(By.xpath("//*[@id=\"surveyElement\"]")).getText();
				boolean expectpublishcontent = publishcontent.contains("Publish"); 
				//AssertJUnit.assertFalse(expectpublishcontent, publishcontent);
				//assertion5.assertAll();
		    	Close();
	        }
	   }
	   
	   @Test (priority=6)
		public static void RecheckTheMainFileStatusForCopyTheDraftMasterRecord6 () throws InterruptedException, IOException {	
		   SoftAssert assertion6 = new SoftAssert();
		   FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2); //save as sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	//Recheck the publish status	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	         	driver.findElement(By.xpath("//*[contains(text(),'" + filename1 + "')]")).click();  //Click temporary file
	    		Thread.sleep(5000);
	    		driver.findElements(By.className("item")).get(4).click();  //click view form
	    		Thread.sleep(15000);
	    		driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
	    		String savecontent = driver.findElement(By.xpath("//*[@class=\"left panel asset_left_panel\"]")).getText();
	     		boolean expectsavecontent = savecontent.contains("save"); 
				//AssertJUnit.assertTrue(expectsavecontent, savecontent);
				String completecontent = driver.findElement(By.xpath("//*[@id=\"creatorElement\"]/div/div[2]/div[1]/div")).getText();
				boolean expectcompletecontent = completecontent.contains("complete"); 
				//AssertJUnit.assertTrue(expectcompletecontent, completecontent);
				driver.findElement(By.xpath("//*[@id=\"four-circle\"]")).click(); //No need its a issue
				Thread.sleep(5000);
				String publishcontent = driver.findElement(By.xpath("//*[@id=\"surveyElement\"]")).getText();
				boolean expectpublishcontent = publishcontent.contains("Publish"); 
				//AssertJUnit.assertTrue(expectpublishcontent, publishcontent);
				//assertion6.assertAll();
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
