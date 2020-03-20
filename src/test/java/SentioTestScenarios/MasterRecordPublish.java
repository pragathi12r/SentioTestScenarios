package SentioTestScenarios;

import org.testng.annotations.Test;
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

public class MasterRecordPublish {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/Master Record - Preview.xls"); 
    static String filename = "TestValue2";
	   
	   @Test (priority=1)
		public static void QuestionType1 () throws InterruptedException, IOException {	
		   SoftAssert assertion1 = new SoftAssert();
		    FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1); //publish sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	//temporary code to view the form
//	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//	driver.findElement(By.xpath("//*[contains(text(),'TestData28')]")).click();  //Click temporary file
//	Thread.sleep(5000);
//	driver.findElements(By.className("item")).get(4).click();  //click view form
//	Thread.sleep(10000);
//	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
	    	
		        	
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
			   		
				//Form Builder - single input
			    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon   
			    Thread.sleep(6000);
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
	            cell = sheet.getRow(i).getCell(7);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
			    
			  //Form Builder - dropdown
			    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//span[contains(text(),'Dropdown')]")).click(); //Dropdown
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon   
			    Thread.sleep(6000);
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
	            cell = sheet.getRow(i).getCell(8);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
			    Thread.sleep(5000);

			 //Form Builder - dynamic matrix
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//span[contains(text(),'Matrix (dynamic rows)')]")).click(); //Matrix (dynamic rows)
			    Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
	    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
	            cell = sheet.getRow(i).getCell(9);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
			    Thread.sleep(5000);

			 //Add second page
			    driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click(); 
				
			//Form Builder - rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Rating')]")).click(); //Rating		
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
	            cell = sheet.getRow(i).getCell(10);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
			    Thread.sleep(5000);

			//Form Builder - matrix single choice
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-matrix\"]/span[2]")).click(); //single choice
			    Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
	            cell = sheet.getRow(i).getCell(11);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
			    Thread.sleep(5000);

			    driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	        	Thread.sleep(5000);  
	        	
		        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Complete')]")));     
			    driver.findElement(By.xpath("//*[contains(text(), 'Complete')]")).click(); //complete
			    Thread.sleep(5000);
		
			    String thirdques = driver.findElement(By.xpath("//*[@class=\"sv_p_root\"]/div[4]/div/div/h5/span[3]")).getText();
		        String expectthirdques = "Dynamic Matrix Question";
		        //assertion1.assertEquals(expectthirdques, thirdques);  
		
		        Thread.sleep(6000);
		        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id=\"surveyElement\"]/div/form/div[2]/div/div[4]/input[2]")));
	    
		        driver.findElement(By.xpath("//*[@id=\"surveyElement\"]/div/form/div[2]/div/div[4]/input[2]")).click(); //click next
		        Thread.sleep(5000);
		        String fifthques = driver.findElement(By.xpath("//*[@class=\"sv_p_root\"]/div[3]/div/div/h5/span[3]")).getText();
		        String expectfifthques = "Single Matrix Question";
		        //assertion1.assertEquals(expectfifthques, fifthques);  
		        //assertion1.assertAll();
		   		
		        driver.findElement(By.xpath("//*[@id=\"surveyElement\"]/div/form/div[2]/div/div[4]/input")).click(); //click previous
		    	Thread.sleep(2000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
		    	Thread.sleep(5000);
		    	
		    	String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
				System.out.println(msg);
		       	String expect = "Batch Records"; 
				//assertion1.assertEquals(expect, msg); 
		    	Thread.sleep(5000);
		    	
	        //Recheck the publish status	
	        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	         	driver.findElement(By.xpath("//*[contains(text(),'" + filename + "')]")).click();  //Click temporary file
	    		Thread.sleep(5000);
	    		driver.findElements(By.className("item")).get(4).click();  //click view form
	    		Thread.sleep(25000);
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
				//assertion1.assertAll();
		    	
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
