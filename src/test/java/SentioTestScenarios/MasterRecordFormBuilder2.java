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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.gargoylesoftware.htmlunit.javascript.host.media.webkitAudioContext;

public class MasterRecordFormBuilder2 {
	//MasterRecordFormBuilder3 is used for MasterRecordFormBuilder2
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/Master Record - Form Builder.xls"); 
    //static String filename4 = "TestData1";
	    
	   @Test (priority=1)
		public static void UploadFileForAQuestion1 () throws InterruptedException, IOException {	
		   SoftAssert assertion1 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(7); //datafield sheet
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
	    		driver.findElement(By.xpath("//*[contains(@title,'Edit selected object')]")).click();  //click properties edit icon
	    		Thread.sleep(5000);
	    		
	    	//general	
	    		cell = sheet.getRow(i).getCell(7);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
	    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
	    		
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')]")).click(); //click properties icon
	    		String file1 = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
	    		driver.findElement(By.xpath("//*[@title=\"File\"]/parent::td/parent::tr/td[2]/div/div/input")).sendKeys(file1);
	    		Thread.sleep(5000);
	    		
	    		WebElement file_tooltip=driver.findElement(By.xpath("//*[@data-bind=\"foreach: koProperties\"]/tr[6]/td[2]/div/div/input"));
	    		action.moveToElement(file_tooltip).perform();
	    		String tooltip_msg=file_tooltip.getText(); 
	    		boolean expect_tooltip_msg = driver.getPageSource().contains("download.jpeg"); 
				//AssertJUnit.assertSame(expect_tooltip_msg, tooltip_msg);
		    	//assertion1.assertAll(); 	
				driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save 
		    	Thread.sleep(5000);
	    		
	    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
				Thread.sleep(5000);
				
		    	Close();
	        }
	   }  		

	   @Test (priority=2)
		public static void DataFieldQuestionType2 () throws InterruptedException, IOException {	
		   SoftAssert assertion2 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(7); //datafield sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();         	
			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
			//Fill basic details
				cell = sheet.getRow(i).getCell(8);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(9);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(10);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(11);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(12);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				//upload assert
				cell = sheet.getRow(i).getCell(13);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download1.jpeg";
				//assertion2.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
				//Form Builder
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//span[contains(text(), 'INV DataField')]")).click(); //Data Field
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@src=\"/Survey-portlet/images/Question properties icon.svg\"]")).click(); //click properties icon
	    		driver.findElement(By.xpath("//*[contains(@title,'Edit selected object')]")).click();  //click properties edit icon
	    		
	    	//general
	    		cell = sheet.getRow(i).getCell(14);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
	    		driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
	   			
	    	//choices
	    		//form entry
	    		driver.findElement(By.xpath("//span[contains(text(),'Choices')]/parent::div/parent::div/parent::svd-accordion/div/div[3]")).click(); //click choices
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_choices\"]/div/div[1]/div/div[4]/div/div/input[1]")).click(); //add
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_choices\"]/div/div[1]/div/div[4]/div/div/input[1]")).click(); //add
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_choices\"]/div/div[1]/div/div[4]/div/div/input[1]")).click(); //add
	    		
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_choices\"]/div/div[1]/div/div[4]/div/table/tbody/tr[1]/td[2]/input")).clear();
	    		cell = sheet.getRow(i).getCell(15);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_choices\"]/div/div[1]/div/div[4]/div/table/tbody/tr[1]/td[2]/input")).sendKeys(cell.getStringCellValue()); //value
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_choices\"]/div/div[1]/div/div[4]/div/table/tbody/tr[2]/td[2]/input")).clear();
	    		cell = sheet.getRow(i).getCell(16);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_choices\"]/div/div[1]/div/div[4]/div/table/tbody/tr[2]/td[2]/input")).sendKeys(cell.getStringCellValue());
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_choices\"]/div/div[1]/div/div[4]/div/table/tbody/tr[3]/td[2]/input")).clear();
	    		cell = sheet.getRow(i).getCell(17);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_choices\"]/div/div[1]/div/div[4]/div/table/tbody/tr[3]/td[2]/input")).sendKeys(cell.getStringCellValue());
	   	
	    		
	            driver.findElement(By.xpath("//*[contains(text(), 'Minimum value for auto generated items')]/parent::div/input")).click();
	            action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
		    	action.sendKeys(Keys.DELETE).perform();
	            cell = sheet.getRow(i).getCell(18);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[contains(text(), 'Minimum value for auto generated items')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //minimum value for auto generated values
	    		
	    		driver.findElement(By.xpath("//*[contains(text(), 'Maximum value for auto generated items')]/parent::div/input")).click();
	    		action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
		    	action.sendKeys(Keys.DELETE).perform();
	    		cell = sheet.getRow(i).getCell(19);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[contains(text(), 'Maximum value for auto generated items')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //minimum value for auto generated values
	    		
	    		driver.findElement(By.xpath("//*[contains(text(), 'The difference between auto generated items')]/parent::div/input")).click();
	    		action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
		    	action.sendKeys(Keys.DELETE).perform();
	    		cell = sheet.getRow(i).getCell(20);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[contains(text(), 'The difference between auto generated items')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //minimum value for auto generated values
	    		    				    		
	    		driver.findElement(By.xpath("//span[contains(text(),'Choices')]/parent::div/parent::div/parent::svd-accordion/div/div[3]")).click(); //hide choices
	    		    		
	    		//driver.findElement(By.xpath("//*[contains(text(), 'Edit: question1')]/parent::div/parent::div/div[3]/input[3]")).click(); //cancel
	    		driver.findElement(By.xpath("//*[contains(text(), 'Edit: question1')]/parent::div/parent::div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//*[contains(text(), 'Edit: question1')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	    		
//	    		WebElement msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
//	    		String texts = msg.getText();
//	    		String expected = "Master record details saved successfully.";
//	    		//assertion2.assertEquals(expected, texts);  
	    		Thread.sleep(15000);
	
//driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]")).click();
				driver.findElement(By.xpath("//*[@src=\"/Survey-portlet/images/Question properties icon.svg\"]")).click(); //click properties icon
	
				Thread.sleep(2000);
				
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Master')]")));
            
	            WebElement testDropDown =  driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[4]"));//master
	            //WebElement testDropDown = driver.findElement(By.xpath("//*[contains(text(), 'I N V   Master')]")); //master  
	            //Select master = new Select(testDropDown);  
	            testDropDown.click();
	            WebElement masterdropdown=driver.findElement(By.xpath("//option[contains(text(),'InputMaterial')]"));
	            masterdropdown.click();
	    		
	    		Thread.sleep(2000);
	    		
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]")));
	    		
	            
	           WebElement invfield= driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[2]")); //inv field
	           invfield.click();
	           WebElement batchdropdown=driver.findElement(By.xpath("(//option[contains(text(),'BatchNumber')])[2]"));//dropdown field
	           batchdropdown.click();
	            
	    		
	           WebElement invLink= driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[3]")); //inv field
	           invLink.click();
	           WebElement linkdrop=driver.findElement(By.xpath("//option[contains(text(),'RawMaterial')]"));//dropdown field
	           linkdrop.click();
	           Thread.sleep(3000);
	           
	         driver.findElement(By.xpath("//*[@src=\"/Survey-portlet/images/Question properties icon.svg\"]")).click(); //click properties icon
	    		
	           ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Operation')]")));
	    		
	    		WebElement invoperation =driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[5]")); //inv operation
	    		invoperation.click();
	    		Thread.sleep(3000);
	    		WebElement invdropdown=driver.findElement(By.xpath("//option[contains(text(),'Add')]"));//dropdown field
	    		invdropdown.click();
	    		Thread.sleep(3000);
	    		
	    		//driver.findElement(By.xpath("//*[@src=\"/Survey-portlet/images/Question properties icon.svg\"]")).click(); //click properties icon
	    		
	    		
	            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Primary')]")));
	            
	    		WebElement invprimary=driver.findElement(By.xpath("(//*[@class='checkmark svd-light-background-color'])[5]")); //inv primary
	        	if(!invprimary.isSelected())
	        		invprimary.click();
	        	
	        	driver.findElement(By.xpath("(//img[@class='closeProp'])[2]"));
				
	        	
	    		
	        	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	    		
	    		WebElement msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts1 = msg1.getText();
	    		String expected1 = "Master record details saved successfully.";
	    		AssertJUnit.assertEquals(expected1, texts1);  
	    		//assertion2.assertAll();
	    		Thread.sleep(5000);
	    		
	    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
				Thread.sleep(5000);Close();	
	        }
	   }
	   
	  /* @Test (priority=3)
		public static void DataFieldDynamicMatrix3 () throws InterruptedException, IOException {	
		   SoftAssert assertion3 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(7); //datafield sheet
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
	        	
	        	//temporary code to view the form

			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
			//Fill basic details
				cell = sheet.getRow(i).getCell(26);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(27);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(28);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(29);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(30);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				//upload assert
				cell = sheet.getRow(i).getCell(31);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download1.jpeg";
				assertion3.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				//assertion3.assertAll(); 
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);

	    		
				//Form Builder
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//span[contains(text(),'Matrix (dynamic rows)')]")).click(); //Matrix (dynamic rows)
			    Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@src=\"/Survey-portlet/images/Question properties icon.svg\"]")).click(); //click properties icon
	    		driver.findElement(By.xpath("//*[contains(@title,'Edit selected object')]")).click();  //click properties edit icon
	    		
	    	//general
	    		cell = sheet.getRow(i).getCell(32);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
	    		driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
	       //columns 
			    //form entry
			    driver.findElement(By.xpath("//span[contains(text(),'Columns')]/parent::div")).click();
			    
			    cell = sheet.getRow(i).getCell(33);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select celltype1 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[1]/td[3]/div[2]/select"))); //cell type 1
			    celltype1.selectByVisibleText(cell.getStringCellValue());
			    
			    
			    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[1]/td")).click(); //click edit 1
			    Thread.sleep(2000);
			    
			    cell = sheet.getRow(i).getCell(34);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select master1 =new Select(driver.findElement(By.xpath("(//input[@type='text'])[38]"))); // Select master
			    master1.selectByVisibleText(cell.getStringCellValue());
			    
			    cell = sheet.getRow(i).getCell(35);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invfield1 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'M P R   Field')]/parent::div/div[2]/select"))); // Select inv field
			    invfield1.selectByVisibleText(cell.getStringCellValue());
			    
			    WebElement invprimary=driver.findElement(By.xpath("//*[contains(text(), 'I N V   Primary'")); //inv primary
	        	if(!invprimary.isSelected())
	        		invprimary.click();
	        	
			    driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/button")).click(); //click edit back
			    Thread.sleep(2000);
			    
			    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[4]/input")).clear();
	    		cell = sheet.getRow(i).getCell(36);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 1
	    		Thread.sleep(2000);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[5]/input")).click();
	    		cell = sheet.getRow(i).getCell(36);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 1
	    		
	    		
	    		cell = sheet.getRow(i).getCell(37);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select celltype2 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[2]/td[3]/div[2]/select"))); //cell type 2
			    celltype2.selectByVisibleText(cell.getStringCellValue());
			    
			    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[2]/td")).click(); //click edit 2
			    Thread.sleep(2000);
			    
			    cell = sheet.getRow(i).getCell(38);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select master2 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
			    master2.selectByVisibleText(cell.getStringCellValue());
			    
			    cell = sheet.getRow(i).getCell(39);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invfield2 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]"))); // Select inv field
			    invfield2.selectByVisibleText(cell.getStringCellValue());
			    
			    cell = sheet.getRow(i).getCell(40);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invlink2 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Link')]"))); // Select inv link
			    invlink2.selectByVisibleText(cell.getStringCellValue());
			    
	    		driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/button")).click(); //click edit back 
			    Thread.sleep(2000);
			    
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[2]/td[4]/input")).clear();
	    		cell = sheet.getRow(i).getCell(41);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[2]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 2
	    		Thread.sleep(2000);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[2]/td[5]/input")).click();
	    		cell = sheet.getRow(i).getCell(41);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[2]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 2
	    		
			    cell = sheet.getRow(i).getCell(42);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select celltype3 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[3]/td[3]/div[2]/select"))); //cell type 3
			    celltype3.selectByVisibleText(cell.getStringCellValue());

			    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[3]/td")).click(); //click edit 2
			    Thread.sleep(2000);
			    
			    cell = sheet.getRow(i).getCell(43);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select master3 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
			    master3.selectByVisibleText(cell.getStringCellValue());
			    
			    cell = sheet.getRow(i).getCell(44);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invfield3 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]"))); // Select inv field
			    invfield3.selectByVisibleText(cell.getStringCellValue());
			    
			    cell = sheet.getRow(i).getCell(45);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invlink3 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Link')]"))); // Select inv link
			    invlink3.selectByVisibleText(cell.getStringCellValue());
			    
			    	    
			    driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/button")).click(); //click edit back 
			    Thread.sleep(2000);
			    	 
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[3]/td[4]/input")).clear();
	    		cell = sheet.getRow(i).getCell(46);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[3]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 3
	    		Thread.sleep(2000);		    	 
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[3]/td[5]/input")).click();
	    		cell = sheet.getRow(i).getCell(46);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[3]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 3

	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/div/input[1]")).click(); //add new
	    		Thread.sleep(2000);
	    		
	    		cell = sheet.getRow(i).getCell(47);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select celltype4 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[4]/td[3]/div[2]/select"))); //cell type 4
			    celltype4.selectByVisibleText(cell.getStringCellValue());
			    
			    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[4]/td")).click(); //click edit 4
			    Thread.sleep(2000);
	    		
	    		cell = sheet.getRow(i).getCell(48);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select master4 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
			    master4.selectByVisibleText(cell.getStringCellValue());
			    
			    cell = sheet.getRow(i).getCell(49);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invfield4 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]/parent::div/div[2]/select"))); // Select inv field
			    invfield4.selectByVisibleText(cell.getStringCellValue());
			    
			    cell = sheet.getRow(i).getCell(50);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invlink4 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'M P R   Link')]/parent::Div/div[2]/select"))); // Select inv link
			    invlink4.selectByVisibleText(cell.getStringCellValue());
			    			    
			    
			    driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/button")).click(); //click edit back 
			    Thread.sleep(2000);
			    	 
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[4]/td[4]/input")).clear();
	    		cell = sheet.getRow(i).getCell(51);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[4]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 4
	    		Thread.sleep(2000);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[4]/td[5]/input")).click();
	    		cell = sheet.getRow(i).getCell(51);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[4]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 4

	    		
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/div/input[1]")).click(); //add new
	    		Thread.sleep(2000);
	    		
	    		cell = sheet.getRow(i).getCell(52);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select celltype5 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[5]/td[3]/div[2]/select"))); //cell type 5
			    celltype5.selectByVisibleText(cell.getStringCellValue());
			    
			    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[5]/td")).click(); //click edit 5
			    Thread.sleep(2000);
	    		
	    		cell = sheet.getRow(i).getCell(53);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select master5 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
			    master5.selectByVisibleText(cell.getStringCellValue());
			    
			    cell = sheet.getRow(i).getCell(54);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invfield5 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]"))); // Select inv field
			    invfield5.selectByVisibleText(cell.getStringCellValue());
			    cell = sheet.getRow(i).getCell(55);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invlink5 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Link')]"))); // Select inv link
			    invlink5.selectByVisibleText(cell.getStringCellValue());

			    cell = sheet.getRow(i).getCell(56);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    Select invoperation =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Operation')]"))); //inv operation
			    invoperation.selectByVisibleText(cell.getStringCellValue());

			    Thread.sleep(6000);
			    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Update')]")));

			    WebElement invupdate=driver.findElement(By.xpath("//*[contains(text(), 'I N V   Update')]")); //inv update
			    if(!invupdate.isSelected())
			    	invupdate.click();

			    cell = sheet.getRow(i).getCell(57);
			    cell.setCellType(Cell.CELL_TYPE_STRING);
			    driver.findElement(By.xpath("//*[contains(text(), 'I N V   Update   Link')]")).sendKeys(cell.getStringCellValue()); //inv update link    
			    
			    driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/button")).click(); //click edit back 
			    Thread.sleep(2000);
			    	 
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[5]/td[4]/input")).clear();
	    		cell = sheet.getRow(i).getCell(58);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[5]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 5
	    		Thread.sleep(2000); 
		    	driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[5]/td[5]/input")).click();
		    	cell = sheet.getRow(i).getCell(58);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[5]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 5
		    					
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/div/input[1]")).click(); //add new
	    		Thread.sleep(2000);

	    		cell = sheet.getRow(i).getCell(59);
	    		cell.setCellType(Cell.CELL_TYPE_STRING);
	    		Select celltype6 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[6]/td[3]/div[2]/select"))); //cell type 6
	    		celltype6.selectByVisibleText(cell.getStringCellValue());
	    		
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[6]/td[4]/input")).clear();
	    		cell = sheet.getRow(i).getCell(60);
	    		cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[6]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 6
	    		Thread.sleep(2000);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[6]/td[5]/input")).click();
	    		cell = sheet.getRow(i).getCell(60);
	    		cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[6]/td[5]/input")).sendKeys(cell.getStringCellValue()); //name 6
	    		
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[5]/td[5]/input")).click(); //click name5

	    		



	    		driver.findElement(By.xpath("//*[contains(text(), 'Edit: question1')]/parent::Div/parent::div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//*[contains(text(), 'Edit: question1')]/parent::Div/parent::div/div[3]/input[2]")).click(); //ok
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	    		
	    		Thread.sleep(5000);
	    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
				Thread.sleep(5000);
	    		Close(); 
	        }
	   }*/
	   
	@Test (priority=4)
	public static void ExpressionQuestionType4 () throws InterruptedException, IOException {	
		SoftAssert assertion4 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(8); //expression sheet
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
			//assertion4.assertAll(); 
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
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
            cell = sheet.getRow(i).getCell(7);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
		    
		  //Form Builder
		    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon   
		    Thread.sleep(6000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
            cell = sheet.getRow(i).getCell(8);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
		    
		  //Form Builder
		    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//span[contains(text(),'Expression (read-only)')]")).click(); //Expression (read-only)
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    
		    driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    
		    cell = sheet.getRow(i).getCell(9);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
	    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue()); //expression title
		    		    
		    driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
	   		
	    	//Expression
		   	driver.findElement(By.xpath("//*[@class=\"svd-accordion-tab-header\"]/parent::div/div[3]")).click(); //click expression
		    	
		   	cell = sheet.getRow(i).getCell(10);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
	    	driver.findElement(By.xpath("//*[@id=\"editor_tab_id_expression\"]/div/div/div/div[3]/div/textarea")).sendKeys(cell.getStringCellValue()); //expression textarea
		    			    	
		   	driver.findElement(By.xpath("//*[@class=\"svd-accordion-tab-header\"]/parent::div/div[3]")).click(); //hide expression
		   	driver.findElement(By.xpath("//*[contains(text(), 'question3')]/parent::Div/parent::div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//*[contains(text(), 'question3')]/parent::Div/parent::div/div[3]/input[2]")).click(); //ok
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

	@Test (priority=5)
	public static void TimerQuestionType5 () throws InterruptedException, IOException {	
		SoftAssert assertion5 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(8); //expression sheet
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
			//upload assert
			cell = sheet.getRow(i).getCell(16);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(5000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(3000);
			WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
			String uploadedfile1text = uploadedfile1.getText();
			String expectuploadedfile1text = "download1.jpeg";
			//assertion5.assertEquals(expectuploadedfile1text, uploadedfile1text);  
			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
	   		Thread.sleep(3000);
		   		
			//Form Builder
		    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//span[contains(text(),'Timer')]")).click(); //Timer
		    		
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    
		    driver.findElement(By.xpath("//*[contains(text(), 'Set   Time')]/parent::td/parent::tr/td[2]/div/input")).clear();
		    cell = sheet.getRow(i).getCell(17);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[contains(text(), 'Set   Time')]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue()); //set time
			Thread.sleep(5000);
		    
		    cell = sheet.getRow(i).getCell(18);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //title
			Thread.sleep(5000);
		    
			driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save	
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"four-circle\"]")).click(); //click preview
			Thread.sleep(8000);
			
			WebElement msg = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]"));
		   	String texts = msg.getText();
		   	String expected = "Start your Timer";
		   	System.out.println(expected);
		   	//assertion5.assertEquals(expected, texts); 
		   	//assertion5.assertAll();
		   	Thread.sleep(5000);
		   	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //click three
    		
    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
		   	Close();
        }
	}

	@Test (priority=6)
	public static void StopWatch6 () throws InterruptedException, IOException {	
		SoftAssert assertion6 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(8); //expression sheet
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
			//assertion6.assertEquals(expectuploadedfile1text, uploadedfile1text);  
			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
	   		Thread.sleep(3000);		   		

			//Form Builder
		    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//span[contains(text(),'Timer')]")).click(); //Timer
		    		
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    
		    cell = sheet.getRow(i).getCell(25);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //title
			Thread.sleep(5000);
		    
			driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save	  
			Thread.sleep(3000);    
			
    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete			
			Thread.sleep(5000);
			
			WebElement msg = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/span[3]"));
		   	String texts = msg.getText();
		   	String expected = "Start your stopwatch";
		   	AssertJUnit.assertEquals(expected, texts);  
		   	Thread.sleep(5000);
		   	
		   	String start = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).getText();
 	        boolean expectstart = start.contains("Start"); 
 			//AssertJUnit.assertTrue(expectstart, start);
 			assertion6.assertAll();
 			//driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).click(); //click start button
 			driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //click three
    		
 			Thread.sleep(5000);
 			driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete			
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
 			
		   	Close();
        }
	}

	
/*	@Test (priority=7)
	public static void AddTotalField7 () throws InterruptedException, IOException {	
		SoftAssert assertion7 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(9); //total field sheet
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
			assertion7.assertEquals(expectuploadedfile1text, uploadedfile1text);  
			//assertion7.assertAll(); 
			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
	   		Thread.sleep(3000);
		   		
		 //Form Builder
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//span[contains(text(),'Matrix (dynamic rows)')]")).click(); //Matrix (dynamic rows)
		    Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
    		driver.findElement(By.xpath("//*[contains(@title,'Edit selected object')]")).click();  //click properties edit icon
    		
    	//general
    		cell = sheet.getRow(i).getCell(9);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
    		
            driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
       //columns 
		    //form entry
		    driver.findElement(By.xpath("//span[contains(text(),'Columns')]/parent::div")).click();
		    
		    cell = sheet.getRow(i).getCell(10);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select celltype1 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[1]/td[3]/div[2]/select"))); //cell type 1
		    celltype1.selectByVisibleText(cell.getStringCellValue());
		    
		    
		    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[1]/td")).click(); //click edit 1
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(11);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select master1 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
		    master1.selectByVisibleText(cell.getStringCellValue());
		    
		    cell = sheet.getRow(i).getCell(12);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select invfield1 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]"))); // Select inv field
		    invfield1.selectByVisibleText(cell.getStringCellValue());
		    
		    WebElement invprimary=driver.findElement(By.xpath("//*[contains(text(), 'I N V   Primary'")); //inv primary
        	if(!invprimary.isSelected())
        		invprimary.click();
        	
		    driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/button")).click(); //click edit back
		    Thread.sleep(2000);
		    
		    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[4]/input")).clear();
    		cell = sheet.getRow(i).getCell(13);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 1
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[5]/input")).click();
     		cell = sheet.getRow(i).getCell(13);
            cell.setCellType(Cell.CELL_TYPE_STRING);
     		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 1
     		
    		cell = sheet.getRow(i).getCell(14);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select celltype2 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[2]/td[3]/div[2]/select"))); //cell type 2
		    celltype2.selectByVisibleText(cell.getStringCellValue());
		    
		    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[2]/td")).click(); //click edit 2
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(15);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select master2 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
		    master2.selectByVisibleText(cell.getStringCellValue());
		    
		    cell = sheet.getRow(i).getCell(16);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select invfield2 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]"))); // Select inv field
		    invfield2.selectByVisibleText(cell.getStringCellValue());
		    
		    cell = sheet.getRow(i).getCell(17);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select invlink2 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Link')]"))); // Select inv link
		    invlink2.selectByVisibleText(cell.getStringCellValue());
		    
    		driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/button")).click(); //click edit back 
		    Thread.sleep(2000);
		    
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[2]/td[4]/input")).clear();
    		cell = sheet.getRow(i).getCell(18);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[2]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 2
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[2]/td[5]/input")).click();
    		cell = sheet.getRow(i).getCell(18);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[2]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 2
    		
    		Thread.sleep(5000);
    		
		    cell = sheet.getRow(i).getCell(19);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select celltype3 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[3]/td[3]/div[2]/select"))); //cell type 3
		    celltype3.selectByVisibleText(cell.getStringCellValue());
		    Thread.sleep(5000);

		    driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div/div/div[3]/table/tbody/tr[3]/td")).click(); //click edit 3
		    Thread.sleep(2000);
		    
		    driver.findElement(By.xpath("//*[contains(text(), 'Maximum length')]/parent::div/input")).clear();
            cell = sheet.getRow(i).getCell(20);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Maximum length')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //maxlength
    			    
		    driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/svd-accordion/div/div")).click(); //hide general
		    driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/svd-accordion/div/div[11]")).click(); //click totals
		        
		    cell = sheet.getRow(i).getCell(21);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select master3 =new Select(driver.findElement(By.xpath("//*[contains(text(),'Total type')]/parent::Div/div[2]/select"))); // Select total type
		    master3.selectByVisibleText(cell.getStringCellValue());
		    
		    cell = sheet.getRow(i).getCell(22);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[contains(text(),'Total format')]/parent::Div/input")).sendKeys(cell.getStringCellValue()); //total format
    		
		    driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[4]/button")).click(); //click edit back 
		    Thread.sleep(2000);
		    	 
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[3]/td[4]/input")).clear();
    		cell = sheet.getRow(i).getCell(23);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[3]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 3
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[3]/td[5]/input")).click(); //click name 3
    		cell = sheet.getRow(i).getCell(23);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_columns\"]/div/div[1]/div/div[3]/table/tbody/tr[3]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 3
    		
    		
    		driver.findElement(By.xpath("//*[contains(text(), 'question1')]/parent::Div/parent::div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//*[contains(text(), 'question1')]/parent::Div/parent::div/div[3]/input[2]")).click(); //ok
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
    		
    		Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
    		Close(); 
        }
	}*/
	   
	@Test (priority=8)
	public static void AddValidators8 () throws InterruptedException, IOException {	
		SoftAssert assertion8 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(9); //add validators sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();  
        	//temporary code to view the form

		 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
		//Fill basic details
			cell = sheet.getRow(i).getCell(24);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(25);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
			templateCategory.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(26);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
			templateType.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(27);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(28);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
			//save
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
			//upload assert
			cell = sheet.getRow(i).getCell(29);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(5000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(3000);
			WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
			String uploadedfile1text = uploadedfile1.getText();
			String expectuploadedfile1text = "download1.jpeg";
			//assertion8.assertEquals(expectuploadedfile1text, uploadedfile1text);  
			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
	   		Thread.sleep(3000);
		   		
			//Form Builder
		    driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon   
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//span[contains(text(), 'Complete')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); //click complete
		    
		    
		    
		    Thread.sleep(6000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
            cell = sheet.getRow(i).getCell(30);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
		    
		    driver.findElement(By.xpath("//*[contains(text(), 'Validators')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click validators
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/span/span")).click(); //click validators add icon
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/ul/li[6]/a/span")).click(); //click expression option
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(31);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//label[contains(text(), 'Text')]/parent::div/parent::div/div/input")).sendKeys(cell.getStringCellValue()); //text
		    
		    cell = sheet.getRow(i).getCell(32);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select validationType =new Select(driver.findElement(By.xpath("//*[contains(text(), 'Validation   Type')]/parent::div/div[2]/select"))); //validation type
			validationType.selectByVisibleText(cell.getStringCellValue());
		    
			cell = sheet.getRow(i).getCell(33);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select expressionSelectQuestion =new Select(driver.findElement(By.xpath("//label[contains(text(), 'Expression')]/parent::div/div[3]/div/div/select"))); //expression select question
			expressionSelectQuestion.selectByVisibleText(cell.getStringCellValue());
			
			cell = sheet.getRow(i).getCell(34);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//label[contains(text(), 'Expression')]/parent::div/div[3]/div[2]/textarea")).sendKeys(cell.getStringCellValue()); //Expression textarea

		    driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[2]")).click(); //ok
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
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected1 = "[\"qa\",\"test\",\"supervisor\"]";
    		AssertJUnit.assertEquals(expected1, role1);  
    
//    		driver.findElement(By.xpath("//*[@class=\"select2-selection__arrow\"]")).click(); // click question type dropdown
//    		driver.findElement(By.xpath("//li[contains(text(), '..question1')]")).click(); //click ..question1
//    		
//    		Thread.sleep(6000);
//        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
//    		
//    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
//    		
//    		driver.findElement(By.xpath("//*[contains(text(),'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
//    		Thread.sleep(2000);
//    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //click qa
//    		driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //click meena
//    		
//    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //click apply
//    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //click ok
//    		Thread.sleep(3000);
//    		
//    		String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();
//    		String expected2 = "[\"qa\",\"test\"]";
//    		assertion.assertEquals(expected2, verifyrole2); 
    		//assertion8.assertAll();
    		Thread.sleep(3000);
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
    		
    		Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
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
