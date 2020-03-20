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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.JavascriptExecutor;

public class DeviationForm {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/DeviationForm.xls"); 
    static String filename = "TestCloseBatch1";
	   
    @Test (priority=1)
	public static void Complete1 () throws InterruptedException, IOException {	
    	SoftAssert assertion1 = new SoftAssert();
    	FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(0);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
        	//temporary code to view the form
//        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//        	driver.findElement(By.xpath("//*[contains(text(),'TestDeviationData1')]")).click();  //Click temporary file
//        	Thread.sleep(5000);
//        	driver.findElements(By.className("item")).get(4).click();  //click view form
//        	Thread.sleep(10000);
//        	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
       	    	       	
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
			//assertion1.assertAll(); 
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
    		
    		WebElement complete = driver.findElement(By.xpath("//*[@title =\"Complete\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//complete
	    	if(!complete.isSelected())
	    		complete.click();
	    	Thread.sleep(3000);
			 	
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
    		Thread.sleep(5000);
			
			driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
			String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
			//AssertJUnit.assertEquals(expect, msg); 
			//assertion1.assertAll();
			Thread.sleep(5000);
    		
		 	Close();
        }
   }
    
    @Test (priority=2)
	public static void CompleteWithRequiredField2 () throws InterruptedException, IOException {	
    	SoftAssert assertion2 = new SoftAssert();
    	FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(0);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
        	//temporary code to view the form
//        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//        	driver.findElement(By.xpath("//*[contains(text(),'TestDeviationData1')]")).click();  //Click temporary file
//        	Thread.sleep(5000);
//        	driver.findElements(By.className("item")).get(4).click();  //click view form
//        	Thread.sleep(10000);
//        	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
        	    	       	
		 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
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
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(5000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(6000);
			WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
			String uploadedfile1text = uploadedfile1.getText();
			String expectuploadedfile1text = "download1.jpeg";
			//assertion2.assertEquals(expectuploadedfile1text, uploadedfile1text);  
			//assertion2.assertAll(); 
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
    		
    		WebElement complete = driver.findElement(By.xpath("//*[@title =\"Complete\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//complete
	    	if(!complete.isSelected())
	    		complete.click();
	    	Thread.sleep(3000);
	    	
	    	driver.findElement(By.xpath("(//*[@class='closeProp']/parent::div/img)[4]")).click();  //click advanced icon
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(),'Is Required')]")));
	    	
	    	WebElement isrequired = driver.findElement(By.xpath("//span[contains(text(),'Is Required')]"));	//Is required
	    	if(!isrequired.isSelected())
	    		isrequired.click();
	    	
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
        	Thread.sleep(3000);

        	driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
//			String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
//	       	String expect = "Batch Records"; 
//			assertion2.assertEquals(expect, msg); 
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
        sheet= workbook.getSheetAt(0);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
        	
		 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
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
			//upload assert
			cell = sheet.getRow(i).getCell(18);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(5000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(3000);
			WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
			String uploadedfile1text = uploadedfile1.getText();
			String expectuploadedfile1text = "download1.jpeg";
			//assertion3.assertEquals(expectuploadedfile1text, uploadedfile1text);  
			//assertion3.assertAll(); 
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
    		
    		WebElement complete = driver.findElement(By.xpath("//*[@title =\"Complete\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//complete
	    	if(!complete.isSelected())
	    		complete.click();
	    	Thread.sleep(3000);
	    	
	    	driver.findElement(By.xpath("(//*[@class='closeProp']/parent::div/img)[4]")).click();  //click advanced icon
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(),'Is Required')]")));
	    	
	    	WebElement isrequired = driver.findElement(By.xpath("//span[contains(text(),'Is Required')]"));	//Is required
	    	if(!isrequired.isSelected())
	    		isrequired.click();
	    	Thread.sleep(5000);
    		
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@title=\"Validators\"]/parent::td/parent::tr/td[2]/div/div/a")));
    		
    		driver.findElement(By.xpath("//*[@title=\"Validators\"]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click validators
    		
    		Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/span/span")).click(); //click validators add icon
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/ul/li[6]/a/span")).click(); //click expression option
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(19);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//label[contains(text(), 'Text')]/parent::div/parent::div/div/input")).sendKeys(cell.getStringCellValue()); //text
		    
		    cell = sheet.getRow(i).getCell(20);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select validationType =new Select(driver.findElement(By.xpath("//*[contains(text(), 'Validation   Type')]/parent::div/div[2]/select"))); //validation type
			validationType.selectByVisibleText(cell.getStringCellValue());
		    
			cell = sheet.getRow(i).getCell(21);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select expressionSelectQuestion =new Select(driver.findElement(By.xpath("//label[contains(text(), 'Expression')]/parent::div/div[3]/div/div/select"))); //expression select question
			expressionSelectQuestion.selectByVisibleText(cell.getStringCellValue());
			
			cell = sheet.getRow(i).getCell(22);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//label[contains(text(), 'Expression')]/parent::div/div[3]/div/textarea")).sendKeys(cell.getStringCellValue()); //Expression textarea

		    driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[2]")).click(); //ok
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
        	Thread.sleep(3000);
        	
        	driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
			String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
			//AssertJUnit.assertEquals(expect, msg); 
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
        sheet= workbook.getSheetAt(1); //masterfield sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();    
        	//temporary code to view the form
//        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//        	driver.findElement(By.xpath("//*[contains(text(),'TestDeviationData4')]")).click();  //Click temporary file
//        	Thread.sleep(5000);
//        	driver.findElements(By.className("item")).get(4).click();  //click view form
//        	Thread.sleep(10000);
//        	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
        	
                	
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
			//assertion4.assertEquals(expectuploadedfile1text, uploadedfile1text);  
			//assertion4.assertAll(); 
			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
	   		Thread.sleep(3000);
	   		
			//Form Builder
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//span[contains(text(), 'INV MasterField')]")).click(); //INVMaster Field
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@src=\"/Survey-portlet/images/Question properties icon.svg\"]")).click(); //click properties icon
    		
    		driver.findElement(By.xpath("(//*[@class='closeProp']/parent::div/img)[4]")).click();  //click advanced icon		
			Thread.sleep(6000);
            //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Master')]/parent::td/parent::tr/td[2]/div/div/select")));
            
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Master')]")));
            
            WebElement testDropDown =  driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[2]"));//master 
            testDropDown.click();
            WebElement masterdropdown=driver.findElement(By.xpath("//option[contains(text(),'InputMaterial')]"));
            masterdropdown.click();
    		
    		Thread.sleep(6000);
    		 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]")));
	    		
	            
	           WebElement invfield= driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[1]")); //inv field
	           invfield.click();
	           WebElement batchdropdown=driver.findElement(By.xpath("(//option[contains(text(),'BatchNumber')])"));//dropdown field
	           batchdropdown.click();
	            
	    		
	           
    		Thread.sleep(6000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Primary')]")));
            
    		WebElement mprprimary=driver.findElement(By.xpath("//*[contains(text(), 'I N V   Primary')]")); //mpr primary
        	if(!mprprimary.isSelected())
        		mprprimary.click();
        	
        	driver.findElement(By.xpath("//*[contains(text(), 'Max Length')]/parent::td/parent::tr/td[2]/div/input")).clear();
        	cell = sheet.getRow(i).getCell(10);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Max Length')]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue());
    		Thread.sleep(3000);
        	
       //second question
    		//Form Builder
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//span[contains(text(), 'INV MasterField')]")).click(); //Inv Master Field
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
    		
    		driver.findElement(By.xpath("(//*[@class='closeProp']/parent::div/img)[4]")).click();  //click advanced icon		
			Thread.sleep(6000);
            //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Master')]/parent::td/parent::tr/td[2]/div/div/select")));
            
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Master')]")));
            
            WebElement testDropDown1 =  driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[2]"));//master 
            testDropDown1.click();
            WebElement masterdropdown1=driver.findElement(By.xpath("//option[contains(text(),'InputMaterial')]"));
            masterdropdown1.click();
    		
    		Thread.sleep(6000);
    		 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]")));
	    		
	            
	           WebElement invfield1= driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[1]")); //inv field
	           invfield1.click();
	           WebElement batchdropdown1=driver.findElement(By.xpath("(//option[contains(text(),'BatchNumber')])"));//dropdown field
	           batchdropdown1.click();
            
    		driver.findElement(By.xpath("//*[contains(text(), 'Max Length')]/parent::td/parent::tr/td[2]/div/input")).clear();
    		cell = sheet.getRow(i).getCell(14);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Max Length')]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue());
    		Thread.sleep(3000);
        	
       //third question
    		//Form Builder
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//span[contains(text(), 'INV MasterField')]")).click(); //Master Field
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
    		
    		Thread.sleep(6000);
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@title=\"Name\"]/parent::td/parent::tr/td[2]/div/input")));
	    	driver.findElement(By.xpath("//*[@title=\"Name\"]/parent::td/parent::tr/td[2]/div/input")).click();
	    	action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
		    action.sendKeys(Keys.DELETE).perform();
	    	
	    	driver.findElement(By.xpath("//*[@title=\"Name\"]/parent::td/parent::tr/td[2]/div/input")).sendKeys("New Question"); //name
	    	
    		Thread.sleep(6000);
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
    		driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys("New Question"); //title
    		Thread.sleep(6000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
    		driver.findElement(By.xpath("(//*[@class='closeProp']/parent::div/img)[4]")).click();  //click advanced icon		
			Thread.sleep(6000);
            //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Master')]/parent::td/parent::tr/td[2]/div/div/select")));
            
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Master')]")));
            
            WebElement testDropDown2 =  driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[2]"));//master 
            testDropDown2.click();
            WebElement masterdropdown2=driver.findElement(By.xpath("//option[contains(text(),'InputMaterial')]"));
            masterdropdown2.click();
    		
    		Thread.sleep(6000);
    		 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]")));
	    		
	            
	           WebElement invfield2= driver.findElement(By.xpath("(//select[@class='propertyeditorDropdown'])[1]")); //inv field
	           invfield2.click();
	           WebElement batchdropdown2=driver.findElement(By.xpath("(//option[contains(text(),'ReleaseDate')])"));//dropdown field
	           batchdropdown2.click();
            
    		driver.findElement(By.xpath("//*[contains(text(), 'Max Length')]/parent::td/parent::tr/td[2]/div/input")).clear();
    		cell = sheet.getRow(i).getCell(18);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Max Length')]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue());
    		Thread.sleep(3000);
        	      	
        	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
    		Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
			String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
			//AssertJUnit.assertEquals(expect, msg); 
			//assertion4.assertAll();
			Thread.sleep(5000);
        	Close();	
        }
   }
  /*
   @Test (priority=5)
	public static void MasterFieldDynamicMatrix5 () throws InterruptedException, IOException {	
	   SoftAssert assertion5 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(2); //master field dynamic matrix sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
        	
        	//temporary code to view the form
//        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//         	driver.findElement(By.xpath("//*[contains(text(),'TestDeviationData5')]")).click();  //Click temporary file
//    		Thread.sleep(5000);
//    		driver.findElements(By.className("item")).get(4).click();  //click view form
//    		Thread.sleep(10000);
//    		driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
        	
        	
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
			//assertion5.assertEquals(expectuploadedfile1text, uploadedfile1text);  
			//assertion5.assertAll(); 
			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
	   		Thread.sleep(3000);

    		
			//Form Builder
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//span[contains(text(),'Matrix (dynamic rows)')]")).click(); //Matrix (dynamic rows)
		    Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@src=\"/Survey-portlet/images/Question properties icon.svg\"]")).click(); //click properties icon

    		Thread.sleep(6000);
//	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@title=\"Name\"]/parent::td/parent::tr/td[2]/div/input")));
//	    	driver.findElement(By.xpath("//*[@title=\"Name\"]/parent::td/parent::tr/td[2]/div/input")).click();
//	    	action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
//		    action.sendKeys(Keys.DELETE).perform();
//	    	
//	    	driver.findElement(By.xpath("//*[@title=\"Name\"]/parent::td/parent::tr/td[2]/div/input")).sendKeys("New Question"); //name
	    	
    		Thread.sleep(6000);
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
    		driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys("New Question"); //title
    		
    		driver.findElement(By.xpath("//*[@src=\"/Survey-portlet/images/Question properties icon.svg\"]")).click(); //click properties icon
    		driver.findElement(By.xpath("//*[@class=\"closeProp\"]/parent::div/img[2]")).click();  //click advanced icon
    		
    		Thread.sleep(6000);
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(),'Columns')]/parent::td/parent::tr/td[2]/div/div/a")));
	    
       //columns 
		    driver.findElement(By.xpath("//span[contains(text(),'Columns')]/parent::td/parent::tr/td[2]/div/div/a")).click();
		    	    
		    cell = sheet.getRow(i).getCell(8);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select celltype1 =new Select(driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[1]/td[3]/div[2]/select"))); //cell type 1
		    celltype1.selectByVisibleText(cell.getStringCellValue());
		    
		    
		    driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[1]/td[1]/button")).click(); //click edit 1
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(9);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select master1 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
		    master1.selectByVisibleText(cell.getStringCellValue());
		    
		    cell = sheet.getRow(i).getCell(10);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select mprfield1 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]/parent::div/div[2]/select"))); // Select mpr field
		    mprfield1.selectByVisibleText(cell.getStringCellValue());
		    
		    WebElement mprprimary=driver.findElement(By.xpath("//*[contains(text(), 'I N V   Primary')]/parent::div/div[2]/label/div/span")); //mpr primary
        	if(!mprprimary.isSelected())
        		mprprimary.click();
        	
		    driver.findElement(By.xpath("//*[@class=\"modal-body svd_notopbottompaddings\"]/div[4]/button")).click(); //click edit back
		    Thread.sleep(2000);
		    
		    driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[1]/td[4]/input")).clear();
    		cell = sheet.getRow(i).getCell(11);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[1]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 1
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[1]/td[5]/input")).click();
    		cell = sheet.getRow(i).getCell(11);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[1]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 1

    		
    		cell = sheet.getRow(i).getCell(12);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select celltype2 =new Select(driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[2]/td[3]/div[2]/select"))); //cell type 2
		    celltype2.selectByVisibleText(cell.getStringCellValue());
		    
		    driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[2]/td[1]/button")).click(); //click edit 2
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(13);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select master2 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
		    master2.selectByVisibleText(cell.getStringCellValue());
		    
		    cell = sheet.getRow(i).getCell(14);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select mprfield2 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]/parent::div/div[2]/select"))); // Select mpr field
		    mprfield2.selectByVisibleText(cell.getStringCellValue());
		    
    		driver.findElement(By.xpath("//*[@class=\"modal-body svd_notopbottompaddings\"]/div[4]/button")).click(); //click edit back 
		    Thread.sleep(2000);
		    
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[2]/td[4]/input")).clear();
    		cell = sheet.getRow(i).getCell(15);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[2]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 2
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[2]/td[5]/input")).click();
    		cell = sheet.getRow(i).getCell(15);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[2]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 2
    		
		    cell = sheet.getRow(i).getCell(16);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select celltype3 =new Select(driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[3]/td[3]/div[2]/select"))); //cell type 3
		    celltype3.selectByVisibleText(cell.getStringCellValue());

		    driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[3]/td[1]/button")).click(); //click edit 3
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(17);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select master3 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
		    master3.selectByVisibleText(cell.getStringCellValue());
		    
		    cell = sheet.getRow(i).getCell(18);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select mprfield3 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]/parent::div/div[2]/select"))); // Select mpr field
		    mprfield3.selectByVisibleText(cell.getStringCellValue());
		   
		    driver.findElement(By.xpath("//*[@class=\"modal-body svd_notopbottompaddings\"]/div[4]/button")).click(); //click edit back 
		    Thread.sleep(2000);
		    	 
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[3]/td[4]/input")).clear();
    		cell = sheet.getRow(i).getCell(19);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[3]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 3
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[3]/td[5]/input")).click();
    		cell = sheet.getRow(i).getCell(19);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[3]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 3

    		driver.findElement(By.xpath("//*[@class=\"modal-dialog animated fadeInDown\"]/div/div[2]/div[3]/div/input[1]")).click(); //add new
    		Thread.sleep(2000);
    		
    		cell = sheet.getRow(i).getCell(20);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select celltype4 =new Select(driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[4]/td[3]/div[2]/select"))); //cell type 4
		    celltype4.selectByVisibleText(cell.getStringCellValue());
		    
		    driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[4]/td[1]/button")).click(); //click edit 4
		    Thread.sleep(2000);
    		
    		cell = sheet.getRow(i).getCell(21);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select master4 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
		    master4.selectByVisibleText(cell.getStringCellValue());
		    
		    cell = sheet.getRow(i).getCell(22);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select mprfield4 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]/parent::div/div[2]/select"))); // Select mpr field
		    mprfield4.selectByVisibleText(cell.getStringCellValue());
		   
		    driver.findElement(By.xpath("//*[@class=\"modal-body svd_notopbottompaddings\"]/div[4]/button")).click(); //click edit back 
		    Thread.sleep(2000);
		    	 
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[4]/td[4]/input")).clear();
    		cell = sheet.getRow(i).getCell(23);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[4]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 4
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[4]/td[5]/input")).click();
    		cell = sheet.getRow(i).getCell(23);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[4]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 4

    		
    		driver.findElement(By.xpath("//*[@class=\"modal-dialog animated fadeInDown\"]/div/div[2]/div[3]/div/input[1]")).click(); //add new
    		Thread.sleep(2000);
    		
    		cell = sheet.getRow(i).getCell(24);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select celltype5 =new Select(driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[5]/td[3]/div[2]/select"))); //cell type 5
		    celltype5.selectByVisibleText(cell.getStringCellValue());
		    
		    driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[5]/td[1]/button")).click(); //click edit 5
		    Thread.sleep(2000);
    		
    		cell = sheet.getRow(i).getCell(25);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select master5 =new Select(driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[1]/div/div[2]/select"))); // Select master
		    master5.selectByVisibleText(cell.getStringCellValue());
		    
		    cell = sheet.getRow(i).getCell(26);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select mprfield5 =new Select(driver.findElement(By.xpath("//*[contains(text(), 'I N V   Field')]/parent::div/div[2]/select"))); // Select mpr field
		    mprfield5.selectByVisibleText(cell.getStringCellValue());
		    		   
		    driver.findElement(By.xpath("//*[@class=\"modal-body svd_notopbottompaddings\"]/div[4]/button")).click(); //click edit back 
		    Thread.sleep(2000);
		    	 
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[5]/td[4]/input")).clear();
    		cell = sheet.getRow(i).getCell(27);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[5]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 5
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[5]/td[5]/input")).click();
    		cell = sheet.getRow(i).getCell(27);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[5]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 5
    				
    		driver.findElement(By.xpath("//*[@class=\"modal-dialog animated fadeInDown\"]/div/div[2]/div[3]/div/input[1]")).click(); //add new
    		Thread.sleep(2000);

    		cell = sheet.getRow(i).getCell(28);
    		cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select celltype6 =new Select(driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[6]/td[3]/div[2]/select"))); //cell type 6
    		celltype6.selectByVisibleText(cell.getStringCellValue());
    		
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[6]/td[4]/input")).clear();
    		cell = sheet.getRow(i).getCell(29);
    		cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[6]/td[4]/input")).sendKeys(cell.getStringCellValue()); //name 6
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[6]/td[5]/input")).click();
    		cell = sheet.getRow(i).getCell(29);
    		cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[6]/td[5]/input")).sendKeys(cell.getStringCellValue()); //title 6
    		
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/tbody/tr[5]/td[5]/input")).click(); //click name5

    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/parent::div/parent::div/parent::div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//*[@class=\"modal\"]/div/div/div[2]/div[3]/table/parent::div/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
    		Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
			String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
			assertion5.assertEquals(expect, msg); 
			//assertion5.assertAll();
			Thread.sleep(5000);
    		
    		Close();        
        }
   }*/

    @Test (priority=6)
	public static void Validation6 () throws InterruptedException, IOException {	
    	SoftAssert assertion6 = new SoftAssert();
    	FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(6); //validation
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
			//assertion6.assertEquals(expectuploadedfile1text, uploadedfile1text);  
			//assertion6.assertAll(); 
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
            cell = sheet.getRow(i).getCell(7);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
		    
		    driver.findElement(By.xpath("//*[contains(text(), 'Validators')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click validators
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/span/span")).click(); //click validators add icon
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/ul/li[5]/a/span")).click(); //click e-mail option
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(8);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//label[contains(text(), 'Text')]/parent::div/parent::div/div/input")).sendKeys(cell.getStringCellValue()); //text
		    
		    cell = sheet.getRow(i).getCell(9);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select validationType =new Select(driver.findElement(By.xpath("//*[contains(text(), 'Validation   Type')]/parent::div/div[2]/select"))); //validation type
			validationType.selectByVisibleText(cell.getStringCellValue());
		   
		    driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[2]")).click(); //ok
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save 
    		Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
			String msg = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect = "Batch Records"; 
//			AssertJUnit.assertEquals(expect, msg); 
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
        sheet= workbook.getSheetAt(6); //validation
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
        	//temporary code to view the form
//        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//        	driver.findElement(By.xpath("//*[contains(text(),'TestDeviationData8')]")).click();  //Click temporary file
//        	Thread.sleep(5000);
//        	driver.findElements(By.className("item")).get(4).click();  //click view form
//        	Thread.sleep(10000);
//        	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //No need its a issue
       	    	       	
		 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
			//Fill basic details
			cell = sheet.getRow(i).getCell(10);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(11);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
			templateCategory.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(12);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
			templateType.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(13);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(14);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
			//save
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
			//upload assert
			cell = sheet.getRow(i).getCell(15);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(5000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(3000);
			WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
			String uploadedfile1text = uploadedfile1.getText();
			String expectuploadedfile1text = "download1.jpeg";
			//assertion7.assertEquals(expectuploadedfile1text, uploadedfile1text);  
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
		    Thread.sleep(3000);
		    
		    WebElement bydate=driver.findElement(By.xpath("//span[contains(text(), 'By / Date')]/parent::td/parent::tr/td[2]/div/div/label/div/span")); //by date
        	if(!bydate.isSelected())
        		bydate.click();
        	WebElement comp=driver.findElement(By.xpath("//span[contains(text(), 'Complete')]/parent::td/parent::tr/td[2]/div/div/label/div/span")); //complete
        	if(!comp.isSelected())
        		comp.click();
        		    
		    Thread.sleep(6000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
            cell = sheet.getRow(i).getCell(16);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());
		    
		    driver.findElement(By.xpath("//*[contains(text(), 'Validators')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click validators
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/span/span")).click(); //click validators add icon
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@class=\"ddmenu-container\"]/div/ul/li[5]/a/span")).click(); //click e-mail option
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(17);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//label[contains(text(), 'Text')]/parent::div/parent::div/div/input")).sendKeys(cell.getStringCellValue()); //text
		    
		    cell = sheet.getRow(i).getCell(18);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select validationType =new Select(driver.findElement(By.xpath("//*[contains(text(), 'Validation   Type')]/parent::div/div[2]/select"))); //validation type
			validationType.selectByVisibleText(cell.getStringCellValue());
		   
		    driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Validators')]/parent::Div/parent::div/div[3]/input[2]")).click(); //ok
    		Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '.page1')]")).click(); //click .page1

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //check Administrator
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected1 = "[\"test\",\"qa\",\"supervisor\"]";
    		//assertion7.assertEquals(expected1, role1);  
    
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '..question1')]")).click(); //click ..New Question
    		
    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
    		
    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
    		
    		driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
    		Thread.sleep(2000);
    		
    		driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //click qa
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    		
    		String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();
    		String expected2 = "test";
    		AssertJUnit.assertEquals(expected2, verifyrole2); 
    		//assertion7.assertAll();
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
