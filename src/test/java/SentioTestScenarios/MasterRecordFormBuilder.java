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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MasterRecordFormBuilder {

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
	public static void AddDifferentPages1 () throws InterruptedException, IOException {	
		SoftAssert assertion1 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3);
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
			assertion1.assertAll(); 
			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
	   		Thread.sleep(3000);
	   		
			for(int j=2; j<=7; j++){
	    		driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click();
	    		String select = driver.findElement(By.xpath("//*[@class=\"row svd_survey_designer\"]/div/pages-editor/div[3]")).getText();
		       	boolean expect1 = select.contains("PAGE"+j); 
				//assertion1.assertTrue(expect1, select);  
				driver.findElement(By.xpath("//*[contains(text(), 'page"+j+"')]")).click(); //click pages
				
				String pagemsg = driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div[3]/div/div")).getText();
				String expectpagemsg = "Please select a question from the ADD ICON at the bottom.";
		    	//assertion1.assertEquals(expectpagemsg , pagemsg);  
				//assertion1.assertAll();
	    	}
	    	
	    	driver.findElement(By.xpath("//*[@class=\"svd-page-name\"][contains(text(), 'page2')]")).click(); //click pages
	    	String pagemsg = driver.findElement(By.xpath("//span[contains(text(), 'question')]")).getText();
			String expectpagemsg = "Please select a question from the ADD ICON at the bottom.";
	    	//assertion1.assertNotEquals(expectpagemsg , pagemsg);  
	    	//assertion1.assertAll();		
    		
	    	Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			
    		Close();
        }
	}
	
	@Test (priority=2)
	public static void SetRolesToPage2 () throws InterruptedException, IOException {	
		SoftAssert assertion2 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();        	
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
    		driver.findElement(By.xpath("//*[@class=\"toolBoxDiv\"]/div/div[3]")).click(); //single input
    		
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')]")).click(); //click properties icon
    		Thread.sleep(5000);
    		
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
    			
    		cell = sheet.getRow(i).getCell(13);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //title

    		//driver.findElement(By.xpath("//*[@class=\"closeProp\"]/parent::div/img[2]")).click();  //click advanced icon
    		driver.findElements(By.className("closeProp")).get(2).click();  //click advanced icon
    		WebElement checkbox1 = driver.findElement(By.xpath("//*[@title=\"Is Required\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//Is required
    		if(!checkbox1.isSelected())
    			checkbox1.click();
    		
    		WebElement checkbox2 = driver.findElement(By.xpath("//*[@title=\"Start With New Line\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//Is start with new line
    		if(checkbox2.isSelected())
    			checkbox2.click();
    		
    		cell = sheet.getRow(i).getCell(14);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select inputType =new Select(driver.findElement(By.xpath("//*[@title=\"Input Type\"]/parent::td/parent::tr/td[2]/div/div/select"))); //select input type
    		inputType.selectByVisibleText(cell.getStringCellValue());
    		
    		Thread.sleep(5000);
    		cell = sheet.getRow(i).getCell(15);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@title=\"Place Holder\"]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue()); //type input placeholder		
        	Thread.sleep(2000);
    		
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '.page1')]")).click(); //click .page1

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected1 = "[\"qa\",\"supervisor\"]";
    		//assertion2.assertEquals(expected1, role1);  
    
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '..question1')]")).click(); //click ..New Question
    		
    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
    		
    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
    		
    		driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
    		Thread.sleep(2000);
    		
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //click qa
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    		
//    		String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr")).getText();
//    		String expected2 = "qa";
//    		assertion2.assertEquals(expected2, verifyrole2); 
//    		assertion2.assertAll(); 
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
	

	@Test (priority=3)
	public static void AddQuestionTypes3 () throws InterruptedException, IOException {	
		SoftAssert assertion3 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
   		 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
			//Fill basic details
			cell = sheet.getRow(i).getCell(17);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(18);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
			templateCategory.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(19);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
			templateType.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(20);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(21);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
			//save
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
			//upload assert
			cell = sheet.getRow(i).getCell(22);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(5000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(3000);
			WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
			String uploadedfile1text = uploadedfile1.getText();
			String expectuploadedfile1text = "download1.jpeg";
			//assertion.assertEquals(expectuploadedfile1text, uploadedfile1text);   
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
    		
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
    			
    		cell = sheet.getRow(i).getCell(23);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //title
  		
    		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
    		
    		driver.findElement(By.xpath("(//*[@class='closeProp'])[3]")).click();  //click advanced icon
    		WebElement checkbox1 = driver.findElement(By.xpath("//*[@title=\"Is Required\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//Is required
    		if(!checkbox1.isSelected())
    			checkbox1.click();
       

    		
    		WebElement checkbox2 = driver.findElement(By.xpath("//*[@title=\"Start With New Line\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//Is start with new line
    		if(checkbox2.isSelected())
    			checkbox2.click();
    		
    		cell = sheet.getRow(i).getCell(24);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select inputType =new Select(driver.findElement(By.xpath("//*[@title=\"Input Type\"]/parent::td/parent::tr/td[2]/div/div/select"))); //select input type
    		inputType.selectByVisibleText(cell.getStringCellValue());
    		
    		Thread.sleep(5000);
    		cell = sheet.getRow(i).getCell(25);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@title=\"Place Holder\"]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue()); //type input placeholder
    		Thread.sleep(5000);
        	
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/lesso.svg')]")).click(); //advanced icon
    		cell = sheet.getRow(i).getCell(26);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[contains(text(), 'Description')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //property description
    		driver.findElement(By.xpath("//*[contains(text(), 'Indent')]/parent::td/parent::tr/td[2]/div/input")).clear();
    		cell = sheet.getRow(i).getCell(27);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[contains(text(), 'Indent')]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue()); //indent
    		
    		Thread.sleep(5000);
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Max Length')]/parent::td/parent::tr/td[2]/div/input")));
    		
    		driver.findElement(By.xpath("//*[contains(text(), 'Max Length')]/parent::td/parent::tr/td[2]/div/input")).clear();
    		cell = sheet.getRow(i).getCell(28);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[contains(text(), 'Max Length')]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue()); //maxlength
    		
    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Title Location')]/parent::td/parent::tr/td[2]/div/div/select")));
    		
    		cell = sheet.getRow(i).getCell(29);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select titlelocation =new Select(driver.findElement(By.xpath("//*[contains(text(), 'Title Location')]/parent::td/parent::tr/td[2]/div/div/select"))); 
    		titlelocation.selectByVisibleText(cell.getStringCellValue());
    		WebElement update=driver.findElement(By.xpath("//span[contains(text(),'Verify')]/parent::td/following-sibling::td/div/div/label/div/span")); //update
    		if(!update.isSelected())
    			update.click();
    		
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '.page1')]")).click(); //click .page1

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected1 = "[\"qa\",\"supervisor\"]";
    		AssertJUnit.assertEquals(expected1, role1);  
    
    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '..question1')]")).click(); //click ..New Question
    		
    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
    		
    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
    		

    		driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
    		Thread.sleep(2000);
    			
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //click qa
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    			
//    		String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();
//    		String expected2 = "supervisor";
//    		assertion3.assertEquals(expected2, verifyrole2); 
    		Thread.sleep(3000);
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
        	Thread.sleep(3000);
    		
    //Form Builder - checkbox
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//span[contains(text(),'Checkbox')]")).click(); //checkbox
    		
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
     		
     		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
		
    		cell = sheet.getRow(i).getCell(31);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //title

    		WebElement checkbox4 = driver.findElement(By.xpath("//*[@title=\"Is Required\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//Is required
    		if(!checkbox4.isSelected())
    			checkbox4.click();
    		
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@title=\"Choices Order\"]/parent::td/parent::tr/td[2]/div/div/select")));
    		
    		cell = sheet.getRow(i).getCell(32);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select choicesorder =new Select(driver.findElement(By.xpath("//*[@title=\"Choices Order\"]/parent::td/parent::tr/td[2]/div/div/select"))); //choices order
			choicesorder.selectByVisibleText(cell.getStringCellValue());
	
    		//Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@title=\"Col Count\"]/parent::td/parent::tr/td[2]/div/input")).clear();
    		Thread.sleep(3000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
     		
    		cell = sheet.getRow(i).getCell(33);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@title=\"Col Count\"]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue()); //type column count
    		Thread.sleep(5000);
    		
    	//choices
    		//form entry
    		driver.findElement(By.xpath("//*[@title=\"Choices\"]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click choices
    		
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
    		driver.findElement(By.xpath("//*[@title=\"Choices\"]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click choices
    		
    		Thread.sleep(3000);
    		//driver.findElement(By.xpath("//input[@class='form-control svd_editor_control'])[1]")).clear();
    		//driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[1]/td[3]/input")).clear();
    		cell = sheet.getRow(i).getCell(34);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[1]/td[3]/input")).sendKeys(cell.getStringCellValue()); //text1
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[2]/td[3]/input")).clear();
    		cell = sheet.getRow(i).getCell(35);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[2]/td[3]/input")).sendKeys(cell.getStringCellValue());
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[3]/td[3]/input")).clear();
    		cell = sheet.getRow(i).getCell(36);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[3]/td[3]/input")).sendKeys(cell.getStringCellValue());
   	
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[3]/td[4]/button")).click(); //delete
    		
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/div/input[2]")).click(); //remove all
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/div/input[1]")).click(); //add
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/div/input[1]")).click(); //add
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/div/input[1]")).click(); //add
    		
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[1]/td[3]/input")).clear();
    		cell = sheet.getRow(i).getCell(34);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[1]/td[3]/input")).sendKeys(cell.getStringCellValue()); //text1
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[2]/td[3]/input")).clear();
    		cell = sheet.getRow(i).getCell(35);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[2]/td[3]/input")).sendKeys(cell.getStringCellValue());
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[3]/td[3]/input")).clear();
    		cell = sheet.getRow(i).getCell(36);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[2]/div[4]/div/table/tbody/tr[3]/td[3]/input")).sendKeys(cell.getStringCellValue());
    	
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    		Thread.sleep(3000);
   	
    		driver.findElement(By.xpath("//*[@class=\"sv_technical svda-add-new-item svd-primary-icon svda-add-custom-item\"]")).click(); //click option add icon
    		driver.findElement(By.xpath("//span[contains(text(),'Other')]")).click(); //click other
    		driver.findElement(By.xpath("//span[contains(text(),'Select All')]")).click(); //click select all
    		driver.findElement(By.xpath("//span[contains(text(),'None')]")).click(); //click none
    		Thread.sleep(2000);
    		
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click(); //click properties icon
     		
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Description')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
    		
    		cell = sheet.getRow(i).getCell(38);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Description')]/parent::td/parent::tr/td[2]/div/div[2]/input")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Description')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //Description
            cell = sheet.getRow(i).getCell(39);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Required Error Text')]/parent::td/parent::tr/td[2]/div/div[2]/input")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Required Error Text')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //required error text
            cell = sheet.getRow(i).getCell(40);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Width')]/parent::td/parent::tr/td[2]/div/input")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Width')]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue()); //width	
        
            driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
    		
     	    driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
     		Thread.sleep(2000);
     		
     		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //click qa
     		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
     		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
     		
//     		String verifyrole3 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();    
//            String expected3 = "qa";
//    		assertion3.assertEquals(expected3, verifyrole3); 
    		Thread.sleep(3000);
    		
         	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
         	Thread.sleep(5000);
    		//assertion3.assertAll(); 		
    		//Close();	
        }
	}

	@Test (priority=4)
	public static void AddQuestionTypesPartTwo4 () throws InterruptedException, IOException {
		SoftAssert assertion4 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
	    workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(4);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
	    {	
          	//Login();
	 //Form Builder - Radio Group
			driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//span[contains(text(),'Radiogroup')]")).click(); //Radiogroup
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
    		
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
    		cell = sheet.getRow(i).getCell(1);
            cell.setCellType(Cell.CELL_TYPE_STRING);
	    	driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue());  //title
	 
    		WebElement checkbox1 = driver.findElement(By.xpath("//*[@title=\"Is Required\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//Is required
    		if(!checkbox1.isSelected())
    			checkbox1.click();
    		WebElement checkbox2 = driver.findElement(By.xpath("//*[@title=\"Start With New Line\"]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//Is start with new line
    		if(checkbox2.isSelected())
    			checkbox2.click();
    			
    		cell = sheet.getRow(i).getCell(2);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            Select choicesorder =new Select(driver.findElement(By.xpath("//*[@title=\"Choices Order\"]/parent::td/parent::tr/td[2]/div/div/select")));
    		choicesorder.selectByVisibleText(cell.getStringCellValue());
	    	driver.findElement(By.xpath("//*[@title=\"Col Count\"]/parent::td/parent::tr/td[2]/div/input")).click();
	    	action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
	    	action.sendKeys(Keys.DELETE).perform();
	    	cell = sheet.getRow(i).getCell(3);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[@title=\"Col Count\"]/parent::td/parent::tr/td[2]/div/input")).sendKeys(cell.getStringCellValue()); //type column count	    		
    		
    	//choices	
    		driver.findElement(By.xpath("//*[@title=\"Choices\"]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click choices
    		
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
    		driver.findElement(By.xpath("//*[@title=\"Choices\"]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click choices
    		Thread.sleep(2000);
    		  		
    		//fast entry
    		driver.findElement(By.xpath("//button[contains(text(), 'Fast Entry')]")).click();
    		Thread.sleep(3000);
	    	driver.findElement(By.xpath("//button[contains(text(), 'Fast Entry')]/parent::div/parent::Div/div[5]/textarea")).clear();
			
	    	Thread.sleep(2000);
	    	cell = sheet.getRow(i).getCell(4);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
	        driver.findElement(By.xpath("//button[contains(text(), 'Fast Entry')]/parent::div/parent::Div/div[5]/textarea")).sendKeys(cell.getStringCellValue()); //item 1
	    	action.sendKeys(Keys.ENTER).perform();
	    	cell = sheet.getRow(i).getCell(5);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
	        driver.findElement(By.xpath("//button[contains(text(), 'Fast Entry')]/parent::div/parent::Div/div[5]/textarea")).sendKeys(cell.getStringCellValue()); //item 2
	    	action.sendKeys(Keys.ENTER).perform();
	   		cell = sheet.getRow(i).getCell(6);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//button[contains(text(), 'Fast Entry')]/parent::div/parent::Div/div[5]/textarea")).sendKeys(cell.getStringCellValue()); //item 3
	    			   	
            driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Choices')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[2]/fieldset/div/div[3]")).click(); //click option add icon
     		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[2]/fieldset/div[1]/div[4]/span")).click(); //click other
     		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
     		Thread.sleep(5000);

		//Add new page
 			driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click();
 			String select1 = driver.findElement(By.xpath("//*[@class=\"col-lg-2 col-md-2 col-sm-1 col-xs-1 svd_toolbox svd-dark-bg-color\"]/pages-editor/div[3]/div[2]/span")).getText();
 			String expect1 = "PAGE2"; 
 			AssertJUnit.assertEquals(expect1, select1);  
	
// 			String select2 = driver.findElement(By.xpath("//*[@class=\"empty-message\"]")).getText();
// 			String expect2 = "Please select a question from the ADD ICON at the bottom."; 
// 			AssertJUnit.assertEquals(expect2, select2);  
 			
 		//Form Builder - Dropdown
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//span[contains(text(),'Dropdown')]")).click(); //Dropdown
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
	    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
	    		
    	//general
	    	cell = sheet.getRow(i).getCell(9);
            cell.setCellType(Cell.CELL_TYPE_STRING);
	    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
	    	//driver.findElement(By.xpath("(//*[@class='closeProp'])[3]")).click();  //click advanced icon
	    	WebElement checkbox3 = driver.findElement(By.xpath("//span[contains(text(),'Is visible?')]"));	//Is visible
	    	if(checkbox3.isSelected())
	    		checkbox3.click();
	    	WebElement checkbox4 = driver.findElement(By.xpath("//span[contains(text(),'Is required?')]"));	//Is required
	    	if(checkbox4.isSelected())
	    		checkbox4.click();
	    	WebElement checkbox5 = driver.findElement(By.xpath("//span[contains(text(),'Is start with new line?')]"));	//Is start with new line
	   		if(checkbox5.isSelected())
	   			checkbox5.click();
	   			
    		cell = sheet.getRow(i).getCell(10);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            Select choicesorder1 =new Select(driver.findElement(By.xpath("//label[contains(text(), 'Select choices order')]/parent::div/div[2]/select")));
    		choicesorder1.selectByVisibleText(cell.getStringCellValue());
	    	cell = sheet.getRow(i).getCell(11);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[contains(text(), 'Options caption')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //type options caption    		
    		driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
    	
    	//choices
    		driver.findElement(By.xpath("//*[@class=\"svd-accordion-tab-header\"]/parent::div/div[3]")).click(); //click choices
    		//fast entry
    		driver.findElement(By.xpath("//*[@class=\"svd-accordion-tab-content\"]/div/div/div/div/div[3]/button[2]")).click();
	    	driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[5]/textarea")).clear();
			cell = sheet.getRow(i).getCell(12);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
	        driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[5]/textarea")).sendKeys(cell.getStringCellValue()); //item 1
	    	action.sendKeys(Keys.ENTER).perform();
	    	cell = sheet.getRow(i).getCell(13);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
	        driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[5]/textarea")).sendKeys(cell.getStringCellValue()); //item 2
	    	action.sendKeys(Keys.ENTER).perform();
	   		cell = sheet.getRow(i).getCell(14);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[@data-bind=\"visible: objectProperty.koVisible, event: { keydown: objectProperty.editor.keyDownHandler }\"]/div[5]/textarea")).sendKeys(cell.getStringCellValue()); //item 3
            Thread.sleep(2000);
            WebElement checkbox11 = driver.findElement(By.xpath("//input[@type='checkbox']//following::span[contains(text(),'Has other item')]")); //click has other item
	    	if(checkbox11.isSelected())
	    		checkbox11.click();
             		
    		driver.findElement(By.xpath("//label[contains(text(),'Other item text')]/parent::div/input")).click();
	    	action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
	    	action.sendKeys(Keys.DELETE).perform();
	    	driver.findElement(By.xpath("//label[contains(text(),'Other item text')]/parent::div/input")).click();
    		action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
    		action.sendKeys(Keys.DELETE).perform();
    		cell = sheet.getRow(i).getCell(15);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//label[contains(text(),'Other item text')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //other describe
    		
    		cell = sheet.getRow(i).getCell(16);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Minimum value for auto generated items')]/parent::div/input")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Minimum value for auto generated items')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //minimum value for auto generated items            
            cell = sheet.getRow(i).getCell(17);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Maximum value for auto generated items')]/parent::div/input")).clear();
            driver.findElement(By.xpath("//*[contains(text(), 'Maximum value for auto generated items')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //Maximum value for auto generated items
            cell = sheet.getRow(i).getCell(18);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            
            driver.findElement(By.xpath("//*[contains(text(), 'The difference between auto generated items')]/parent::div/input")).click();
            action.sendKeys(Keys.CONTROL + "a" + Keys.CONTROL).perform();
    		action.sendKeys(Keys.DELETE).perform();
            driver.findElement(By.xpath("//*[contains(text(), 'The difference between auto generated items')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //difference between auto generated items
            Thread.sleep(6000);
            
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("(//span[contains(text(),'Choices')]/parent::div)[2]")));
            driver.findElement(By.xpath("(//span[contains(text(),'Choices')]/parent::div)[2]")).click(); //hide choices
            
            driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
    			
     		//driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[2]/div[2]/div/select-items-editor/div/div[2]/div[3]/svg-icon")).click(); //click option add icon
    		Thread.sleep(5000);
    		//driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[2]/div[2]/div/select-items-editor/div/div[2]/div[4]/span")).click(); //click other
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
    		Thread.sleep(2000);
    		
    		driver.findElement(By.xpath("(//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')])[1]")).click(); //click properties icon
     		Thread.sleep(2000);
     		
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Description')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
            
    		cell = sheet.getRow(i).getCell(19);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(),'Description')]/parent::td/parent::tr/td[2]/div/div[2]/input")).clear();
            driver.findElement(By.xpath("//*[contains(text(),'Description')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //Description
            
            Thread.sleep(2000);
            

    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '.page2')]")).click(); //click .page2
    		Thread.sleep(6000);
    		
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check supervisor

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
//    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
//    		String expected1 = "[\"qa\",\"supervisor\"]";
//    		assertion4.assertEquals(expected1, role1);  
    

    		driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//option[contains(text(), '..question4')]")).click();  //click ..Please select your native
    		
    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
    		
    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
    		
    		driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
    		Thread.sleep(2000);
    		
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //click qa
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    		
//    		String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();
//    		String expected2 = "qa";
//    		assertion4.assertEquals(expected2, verifyrole2); 
    		Thread.sleep(3000);
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
        	Thread.sleep(5000);
    			

		//Click Second Page
			//driver.findElement(By.xpath("(//*[@class=svd-secondary-icon'])[1]")).click(); //click page 2
		//Form Builder - Comment
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("(//span[contains(text(),'Comment')])")));
    		
    		driver.findElement(By.xpath("(//span[contains(text(),'Comment')])")).click(); //Comment
    		
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("(//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span)[1]")).click(); //click properties icon
    		
	    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
	    		
    	//general
	    	cell = sheet.getRow(i).getCell(21);
            cell.setCellType(Cell.CELL_TYPE_STRING);
	    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
	    	 
	    	
	    	WebElement checkbox7 = driver.findElement(By.xpath("//span[contains(text(),'Is visible?')]"));	//Is visible
	    	if(checkbox7.isSelected())
	    		checkbox7.click();
	    		
	    	WebElement checkbox8 = driver.findElement(By.xpath("//span[contains(text(),'Is required?')]"));	//Is required
	    	if(checkbox8.isSelected())
	    		checkbox8.click();
	    		
	    	WebElement checkbox9 = driver.findElement(By.xpath("//span[contains(text(),'Is start with new line?')]"));	//Is start with new line
	   		if(checkbox9.isSelected())
	   			checkbox9.click();
	    		
	   		cell = sheet.getRow(i).getCell(22);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            driver.findElement(By.xpath("//*[contains(text(), 'Row count')]/parent::div/input")).clear();
           	driver.findElement(By.xpath("//*[contains(text(), 'Row count')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //row count
    	
	    	cell = sheet.getRow(i).getCell(23);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//*[contains(text(), 'Input place holder')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //input placeholder	    		
    		driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
            Thread.sleep(5000);
			
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("(//*[contains(text(),'Complete')])[1]")).click(); //click complete
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
			Thread.sleep(5000);
			//assertion4.assertAll();
    		Close();      
	        }
	   }

	   @Test (priority=5)
		public static void AddVerify5 () throws InterruptedException, IOException {	
		   SoftAssert assertion5 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
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
				AssertJUnit.assertEquals(expectuploadedfile1text, uploadedfile1text); 
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
	    		
	    	//general	
	    		cell = sheet.getRow(i).getCell(30);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
	    		
	    		cell = sheet.getRow(i).getCell(31);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		Select inputType =new Select(driver.findElement(By.xpath("//*[contains(text(), 'Input type')]/parent::div/div[2]/select"))); //select input type
	    		inputType.selectByVisibleText(cell.getStringCellValue());
	    		
	    		Thread.sleep(5000);
	    		cell = sheet.getRow(i).getCell(32);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//label[contains(text(),'Input place holder')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //type input placeholder
	    		driver.findElement(By.xpath("//*[@class=\"svd-accordion-tab-header\"]")).click();

	    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
	        
	        	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	        	Thread.sleep(3000);
	        	
	        	WebElement msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
	    		String texts = msg.getText();
	    		String expected = "Master record details saved successfully.";//
	    		//assertion5.assertEquals(expected, texts);  
	    		Thread.sleep(5000); 

	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Question properties icon.svg')]")).click(); //click properties icon
	    		Thread.sleep(3000);
	    	
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
		    		
//		    	String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
//		    	String expected1 = "[\"qa\",\"test\"]";
//		    	assertion5.assertEquals(expected1, role1);  
		    
		    	driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
		    	driver.findElement(By.xpath("//option[contains(text(), '..question1')]")).click(); //click ..question1
		    	
		    	Thread.sleep(6000);
	        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
	    		
	    		WebElement verify = driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//verify
				   	if(!verify.isSelected())
				   		verify.click();
				   	
				driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //click qa
				driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //click user
				driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
				driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
				
//				String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();	
//		    	String expected2 = "[\"qa\",\"test\"]";
//		    	assertion5.assertEquals(expected2, verifyrole2); 
		    	Thread.sleep(3000);
		    	
	    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	    		
	    		driver.findElement(By.xpath("//*[@id=\"four-circle\"]")).click(); //click preview to verify the verify button
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@id=\"three-circle\"]")).click(); //click three circle 
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
				Thread.sleep(5000);
		    	
		    	WebElement verifyverify = driver.findElement(By.xpath("//*[@class=\"btn btn-info btn-xs verify_stage cmplt\"]"));	//verify
				if(verifyverify.isDisplayed()){
					   String verifyy = verifyverify.getText();
					   System.out.println(verifyy);
					   //assertion.assertAll();  	
				   }
	    		
	    		
				driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
				Thread.sleep(5000);
				//assertion5.assertAll();
	        	Close();
	        }
	   }

	   @Test (priority=6)
		public static void AddBy6 () throws InterruptedException, IOException {	
		   SoftAssert assertion6 = new SoftAssert();
	        FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(4);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
				//Fill basic details
				cell = sheet.getRow(i).getCell(33);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(34);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(35);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(36);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(37);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				//upload assert
				cell = sheet.getRow(i).getCell(38);
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
	   		
		   	//Form Builder -Rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@title=\"Rating\"]/parent::div/span[2]")).click(); //Rating
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon	
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    		
		    	//general
		    	cell = sheet.getRow(i).getCell(39);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
		    	
		    	//rate values
		    	 //form entry
		    	 driver.findElement(By.xpath("//span[contains(text(),'Rate Values')]/parent::div")).click(); //click rate values
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/div/input[1]")).click(); // add new
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/div/input[1]")).click(); // add new
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/div/input[1]")).click(); // add new
		    	 
		    	 cell = sheet.getRow(i).getCell(40);
		         cell.setCellType(Cell.CELL_TYPE_STRING);
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[1]/td[2]/input")).clear(); //item1
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[1]/td[2]/input")).sendKeys(cell.getStringCellValue());
		    	 cell = sheet.getRow(i).getCell(41);
		         cell.setCellType(Cell.CELL_TYPE_STRING);
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[2]/td[2]/input")).clear(); //item2
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[2]/td[2]/input")).sendKeys(cell.getStringCellValue());
		    	 cell = sheet.getRow(i).getCell(42);
		         cell.setCellType(Cell.CELL_TYPE_STRING);
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[3]/td[2]/input")).clear(); //item3
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[3]/td[2]/input")).sendKeys(cell.getStringCellValue());
		    	 action.sendKeys(Keys.TAB).perform();
		    	 Thread.sleep(5000);
		    	 
		    	 driver.findElement(By.xpath("//span[contains(text(),'Rate Values')]/parent::div")).click(); //hide rate values
		    	 driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	 driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	 Thread.sleep(5000);
		    		
		    	 driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
		    	 WebElement msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"));
		    	 String texts = msg.getText();
		    	 String expected = "Master record details saved successfully.";
		    	 //assertion6.assertEquals(expected, texts);  
		    	 Thread.sleep(5000);

		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon

			  	//properties		
			    WebElement bydate = driver.findElement(By.xpath("//span[contains(text(),'By / Date')]/parent::td/parent::tr/td[2]/div/div/label/div/span"));	//by date
				   if(!bydate.isSelected())
				  		bydate.click();
				   		
				driver.findElement(By.xpath("//span[contains(text(),'Correct Answer')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click correct answer
			    Thread.sleep(5000);
			   	driver.findElement(By.xpath("//*[@id=\"question1\"]/parent::label")).click(); //click correct answer
			   	driver.findElement(By.xpath("//*[@value=\"Apply\"]")).click(); //click apply button
			   	driver.findElement(By.xpath("//*[@value=\"OK\"]")).click(); //click ok button  
			    		
			 	String correctAnswer1 = driver.findElement(By.xpath("//*[contains(text(),'Correct Answer')]/parent::td/parent::tr/td[2]/div/div/a")).getText();
			    String expected1 = "\"Two\"";
			    AssertJUnit.assertEquals(expected1, correctAnswer1);  	
			        
			    driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click();// click question type dropdown
		    	driver.findElement(By.xpath("//option[contains(text(), '.page1')]")).click(); //click .page1

		    	Thread.sleep(6000);
		        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

		    	driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
		    	Thread.sleep(2000);
		    	//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
		    	driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
		    	driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //check test

		    	driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
		    	driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
		    		
//		    	String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
//		    	String expected11 = "[\"qa\",\"test\"]";
//		    	assertion6.assertEquals(expected11, role1);  
		    
		    	driver.findElement(By.xpath("//*[@class='selectBox select2-hidden-accessible']")).click(); // click question type dropdown
		    	driver.findElement(By.xpath("//option[contains(text(), '..question1')]")).click(); //click ..question1
		    	
		    	Thread.sleep(6000);
	        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")));
	    		
				driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //click qa
				driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //click test
				driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
				driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
				
				String verifyrole2 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();	
		    	String expected2 = "[\"qa\",\"test\"]";
		    	System.out.println(expected2);
		    	Thread.sleep(3000);
			    
			   	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	        	Thread.sleep(5000);
	  
	    		driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/span/button")).click(); //click complete
	        	Thread.sleep(5000);
	        	
		    	WebElement bydateverify = driver.findElement(By.xpath("//*[@id=\"by_date\"]"));	//by date
				   if(bydateverify.isDisplayed()){
					   String by = bydateverify.getText();
					   System.out.println(by); 
					   //assertion.assertAll();  	
				   }
				   
				   
					Thread.sleep(5000);
					driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //click publish
					Thread.sleep(5000);
	        	Close();
	        }
	   }

	   @Test (priority=7)
		public static void CopyQuestionBeforeSave7 () throws InterruptedException, IOException {
		   SoftAssert assertion7 = new SoftAssert();
	        FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(5);
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
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download.jpeg";
				//assertion7.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
		    	//Form Builder - Rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@title=\"Rating\"]/parent::div/span[2]")).click(); //Rating
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon	
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    		
		    	//general
		    	cell = sheet.getRow(i).getCell(7);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
		    	
		    	//rate values
		    	 //form entry
		    	 driver.findElement(By.xpath("//span[contains(text(),'Rate Values')]/parent::div")).click(); //click rate values
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/div/input[1]")).click(); // add new
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/div/input[1]")).click(); // add new
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/div/input[1]")).click(); // add new
		    	 
		    	 cell = sheet.getRow(i).getCell(8);
		         cell.setCellType(Cell.CELL_TYPE_STRING);
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[1]/td[2]/input")).clear(); //item1
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[1]/td[2]/input")).sendKeys(cell.getStringCellValue());
		    	 cell = sheet.getRow(i).getCell(9);
		         cell.setCellType(Cell.CELL_TYPE_STRING);
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[2]/td[2]/input")).clear(); //item2
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[2]/td[2]/input")).sendKeys(cell.getStringCellValue());
		    	 cell = sheet.getRow(i).getCell(10);
		         cell.setCellType(Cell.CELL_TYPE_STRING);
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[3]/td[2]/input")).clear(); //item3
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[3]/td[2]/input")).sendKeys(cell.getStringCellValue());
		    	 action.sendKeys(Keys.TAB).perform();
		    	 Thread.sleep(5000);
		    	 
		    	 driver.findElement(By.xpath("//span[contains(text(),'Rate Values')]/parent::div")).click(); //hide rate values
		    	 driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	 driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	 Thread.sleep(5000);
		    		
		    	 driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[9]/span[1]/svg-icon")).click(); //copy
			   	 Thread.sleep(3000);
			   	 
			   	String copyverify = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/div[1]/h5/span[3]")).getText();
	 		    boolean expectcopyverify1 = copyverify.contains("QUESTION1"); 
				System.out.println(expectcopyverify1);	
		    	Close();
	        }
	   }
	   
	   @Test (priority=8)
		public static void CopyQuestionAfterSave8 () throws InterruptedException, IOException {	
		   SoftAssert assertion8 = new SoftAssert();
	        FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(5);
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
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download.jpeg";
				//assertion8.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
		    	//Form Builder - Rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Comment')]")).click(); //Comment
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon	
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    		
		    	//general
		    	cell = sheet.getRow(i).getCell(17);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	
		    	cell = sheet.getRow(i).getCell(18);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            driver.findElement(By.xpath("//*[contains(text(), 'Row count')]/parent::div/input")).clear();
	           	driver.findElement(By.xpath("//*[contains(text(), 'Row count')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //row count
	    	
		    	cell = sheet.getRow(i).getCell(19);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[contains(text(), 'Input place holder')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //input placeholder	    		
	    		driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
    	
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	Thread.sleep(5000);
		    	 
		    	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[9]/span[1]/svg-icon")).click(); //copy
			   	Thread.sleep(3000);
			   	 
			   	String copyverify = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/div[1]/h5/span[3]")).getText();
	 		    boolean expectcopyverify1 = copyverify.contains("QUESTION2"); 
				System.out.println(expectcopyverify1);		    	
				Close();	
	        }
	   }

	   @Test (priority=9)
		public static void CopyQuestionBeforeSaveWhenAddMultipleQuestions9 () throws InterruptedException, IOException {
		   SoftAssert assertion9 = new SoftAssert();
	        FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(5);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
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
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download.jpeg";
				//assertion9.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
		    	//Form Builder - Rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@title=\"Rating\"]/parent::div/span[2]")).click(); //Rating
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon	
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    	//general
		    	cell = sheet.getRow(i).getCell(26);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general		    	
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	Thread.sleep(3000);
		    	
		    	//Form Builder - Comment
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Comment')]")).click(); //Comment
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon		  		
		    	//general
		    	cell = sheet.getRow(i).getCell(27);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
	            Thread.sleep(3000);
		    	
		    	//Form Builder - Single Input
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(2000);
	    		driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
	    		Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon	
	    		//general	
	    		cell = sheet.getRow(i).getCell(28);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
	            driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	Thread.sleep(3000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div/div/form/div/div/div[2]/div/div[3]/div")).click(); //click second question
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[9]/span[1]/svg-icon")).click(); //copy
			   	Thread.sleep(3000);
			   	 
			   	String copyverify = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/parent::Div/parent::Div/div[4]/div/div/h5/span[3]")).getText();
	 		    boolean expectcopyverify1 = copyverify.contains("COMMENT TITLE"); 
				//AssertJUnit.assertTrue(expectcopyverify1, copyverify);		
				
				driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div/div/form/div/div/div[2]/div/div[5]/div")).click(); //click 4th question
				Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[9]/span[1]/svg-icon")).click(); //copy
			   	Thread.sleep(3000);
			   	 
			   	String copyverify2 = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/parent::Div/parent::Div/div[5]/div/div/h5/span[3]")).getText();
	 		    boolean expectcopyverify2 = copyverify2.contains("SINGLE INPUT TITLE"); 
//				assertion9.assertTrue(expectcopyverify2, copyverify2);
//		    	assertion9.assertAll(); 	
		    	Thread.sleep(1000);
		    	Close();
	        }
	   }
	   
	   @Test (priority=10)
		public static void CopyQuestionAfterSaveWhenAddMultipleQuestions10 () throws InterruptedException, IOException {
		   SoftAssert assertion10 = new SoftAssert();
	        FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(5);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
				//Fill basic details
				cell = sheet.getRow(i).getCell(29);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(30);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(31);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(32);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(33);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				//upload assert
				cell = sheet.getRow(i).getCell(34);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download.jpeg";
				//assertion10.assertEquals(expectuploadedfile1text, uploadedfile1text);   
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
		    	//Form Builder - Rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@title=\"Rating\"]/parent::div/span[2]")).click(); //Rating
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon	
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    	//general
		    	cell = sheet.getRow(i).getCell(35);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general		    	
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	Thread.sleep(5000);
		    	
		    	//Form Builder - Comment
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Comment')]")).click(); //Comment
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon		  		
		    	//general
		    	cell = sheet.getRow(i).getCell(36);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
	            Thread.sleep(3000);
		    	
		    	//Form Builder - Single Input
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(2000);
	    		driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
	    		Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon	
	    		//general	
	    		cell = sheet.getRow(i).getCell(37);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
	            driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	Thread.sleep(3000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div/div/form/div/div/div[2]/div/div[3]/div")).click(); //click second question
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[9]/span[1]/svg-icon")).click(); //copy
			   	Thread.sleep(3000);
			   	 
			   	String copyverify = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/parent::Div/parent::Div/div[4]/div/div/h5/span[3]")).getText();
	 		    boolean expectcopyverify1 = copyverify.contains("COMMENT TITLE"); 
				//AssertJUnit.assertTrue(expectcopyverify1, copyverify);
				
				driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
				Thread.sleep(500);
		    	String msg10 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		    	String expected10 = "Master record details saved successfully.";
		    	//assertion10.assertEquals(expected10, msg10);  	
		    	Thread.sleep(5000);
				
				driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div/div/form/div/div/div[2]/div/div[5]/div")).click(); // click 4th question
				Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[9]/span[1]/svg-icon")).click(); //copy
			   	Thread.sleep(3000);
			   	 
			   	String copyverify2 = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/parent::Div/parent::Div/div[5]/div/div/h5/span[3]")).getText();
	 		    boolean expectcopyverify2 = copyverify2.contains("SINGLE INPUT TITLE"); 
//				assertion10.assertTrue(expectcopyverify2, copyverify2);
//		    	assertion10.assertAll(); 	
		    	Close();
	        }
	   }

	   @Test (priority=11)
		public static void DeleteQuestionBeforeSave11 () throws InterruptedException, IOException {	
		  // SoftAssert assertion11 = new SoftAssert();
	        FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(6);
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
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download.jpeg";
				//assertion11.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
		    	//Form Builder - Rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@title=\"Rating\"]/parent::div/span[2]")).click(); //Rating
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon	
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    		
		    	//general
		    	cell = sheet.getRow(i).getCell(7);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
		    	
		    	//rate values
		    	 //form entry
		    	 driver.findElement(By.xpath("//span[contains(text(),'Rate Values')]/parent::div")).click(); //click rate values
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/div/input[1]")).click(); // add new
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/div/input[1]")).click(); // add new
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/div/input[1]")).click(); // add new
		    	 
		    	 cell = sheet.getRow(i).getCell(8);
		         cell.setCellType(Cell.CELL_TYPE_STRING);
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[1]/td[2]/input")).clear(); //item1
		    	 driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[1]/td[2]/input")).sendKeys(cell.getStringCellValue());
		    	 cell = sheet.getRow(i).getCell(9);
		         cell.setCellType(Cell.CELL_TYPE_STRING);
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[2]/td[2]/input")).clear(); //item2
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[2]/td[2]/input")).sendKeys(cell.getStringCellValue());
		    	 cell = sheet.getRow(i).getCell(10);
		         cell.setCellType(Cell.CELL_TYPE_STRING);
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[3]/td[2]/input")).clear(); //item3
		         driver.findElement(By.xpath("//*[@id=\"editor_tab_id_rateValues\"]/div/div/div/div[4]/div/table/tbody/tr[3]/td[2]/input")).sendKeys(cell.getStringCellValue());
		    	 action.sendKeys(Keys.TAB).perform();
		    	 Thread.sleep(5000);
		    	 
		    	 driver.findElement(By.xpath("//span[contains(text(),'Rate Values')]/parent::div")).click(); //hide rate values
		    	 driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	 driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	 Thread.sleep(5000);
		    		
			   	 driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[5]/span[1]/svg-icon")).click(); //delete
			   	 Thread.sleep(3000);
			   	 
			   	String pagemsg = driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div[3]/div/div")).getText();
				String expectpagemsg = "Please select a question from the ADD ICON at the bottom.";
//		    	assertion11.assertEquals(expectpagemsg , pagemsg);  
//				assertion11.assertAll();	
		    	Close();
	        }
	   }
	   
	   @Test (priority=12)
		public static void DeleteQuestionAfterSave12 () throws InterruptedException, IOException {	
		   SoftAssert assertion12 = new SoftAssert();
	        FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(6);
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
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download.jpeg";
				//assertion12.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
		    	//Form Builder - Rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Comment')]")).click(); //Comment
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon	
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    		
		    	//general
		    	cell = sheet.getRow(i).getCell(17);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	
		    	cell = sheet.getRow(i).getCell(18);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            driver.findElement(By.xpath("//*[contains(text(), 'Row count')]/parent::div/input")).clear();
	           	driver.findElement(By.xpath("//*[contains(text(), 'Row count')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //row count
	    	
		    	cell = sheet.getRow(i).getCell(19);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	    		driver.findElement(By.xpath("//*[contains(text(), 'Input place holder')]/parent::div/input")).sendKeys(cell.getStringCellValue()); //input placeholder	    		
	    		driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general
   	
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	Thread.sleep(5000);
		    	 
		    	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
		    		
		    	String msg10 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		    	String expected10 = "Master record details saved successfully.";
		    	//assertion12.assertEquals(expected10, msg10);  
		    	Thread.sleep(5000);
		    		
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[5]/span[1]/svg-icon")).click(); //delete
			   	Thread.sleep(3000);
			   	 
			   	String pagemsg = driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div[3]/div/div")).getText();
				String expectpagemsg = "Please select a question from the ADD ICON at the bottom.";
		    	System.out.println(expectpagemsg);
		    	Close();	
	        }
	   }

	   @Test (priority=13)
		public static void DeleteQuestionBeforeSaveWhenAddMultipleQuestions13 () throws InterruptedException, IOException {	
		   SoftAssert assertion13 = new SoftAssert(); 
		   FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(6);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();
			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
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
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download.jpeg";
				//assertion13.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
		    	//Form Builder - Rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@title=\"Rating\"]/parent::div/span[2]")).click(); //Rating
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon	
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    	//general
		    	cell = sheet.getRow(i).getCell(26);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general		    	
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	Thread.sleep(3000);
		    	
		    	//Form Builder - Comment
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Comment')]")).click(); //Comment
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon		  		
		    	//general
		    	cell = sheet.getRow(i).getCell(27);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
	            Thread.sleep(3000);
		    	
		    	//Form Builder - Single Input
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(2000);
	    		driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
	    		Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon	
	    		//general	
	    		cell = sheet.getRow(i).getCell(28);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
	            driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
	            Thread.sleep(3000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div/div/form/div/div/div[2]/div/div[3]/div")).click(); //click second question
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[5]/span[1]/svg-icon")).click(); //delete
			   	Thread.sleep(3000);
			   	 
			   	String copyverify = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/parent::Div/parent::Div/div[2]/div/div/h5/span[3]")).getText();
	 		    boolean expectcopyverify1 = copyverify.contains("RATING TITLE"); 
				//AssertJUnit.assertTrue(expectcopyverify1, copyverify);		
				
				driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div/div/form/div/div/div[2]/div/div[2]/div")).click(); //click 1st question
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[5]/span[1]/svg-icon")).click(); //delete
			   	Thread.sleep(3000);
			   	 
			   	String copyverify2 = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/parent::Div/parent::Div/div[2]/div/div/h5/span[3]")).getText();
	 		    boolean expectcopyverify2 = copyverify2.contains("SINGLE INPUT TITLE"); 
				System.out.println(expectcopyverify2); 	
		    	Close();
	        }
	   }
	   
	   @Test (priority=14)
		public static void DeleteQuestionAfterSaveWhenAddMultipleQuestions14 () throws InterruptedException, IOException {	
		   SoftAssert assertion14 = new SoftAssert(); 
		   FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(6);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
	        	Login();		    	
			 	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
				driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
				//Fill basic details
				cell = sheet.getRow(i).getCell(29);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(30);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
				templateCategory.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(31);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
				templateType.selectByVisibleText(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(32);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
				cell = sheet.getRow(i).getCell(33);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
				//save
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
				//upload assert
				cell = sheet.getRow(i).getCell(34);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
				Thread.sleep(5000);
				
				String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
				driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
				Thread.sleep(3000);
				WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
				String uploadedfile1text = uploadedfile1.getText();
				String expectuploadedfile1text = "download.jpeg";
				//assertion14.assertEquals(expectuploadedfile1text, uploadedfile1text);  
				Thread.sleep(3000);
		   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		   		Thread.sleep(3000);
		   		
		    	//Form Builder - Rating
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@title=\"Rating\"]/parent::div/span[2]")).click(); //Rating
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon	
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon
		    	//general
		    	cell = sheet.getRow(i).getCell(35);
		        cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//span[contains(text(),'General')]/parent::div")).click(); //hide general		    	
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	Thread.sleep(5000);
		    	
		    	//Form Builder - Comment
		    	driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//span[contains(text(),'Comment')]")).click(); //Comment
		    	Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon		  		
		    	//general
		    	cell = sheet.getRow(i).getCell(36);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
		    	driver.findElement(By.xpath("//label[contains(text(), 'Title')]/parent::div/textarea")).sendKeys(cell.getStringCellValue());
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
		    	driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
		    	Thread.sleep(3000);
		    	
		    	//Form Builder - Single Input
	    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
	    		Thread.sleep(2000);
	    		driver.findElement(By.xpath("//*[@class=\"svd_toolbox_item svd-light-border-color svdToolboxItem svd_toolbox_item_icon-text\"]")).click(); //single input
	    		Thread.sleep(2000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span")).click(); //click properties icon
		    	driver.findElement(By.xpath("//label[contains(text(), 'Question Type')]/parent::div/span")).click();  //click properties edit icon	
	    		//general	
	    		cell = sheet.getRow(i).getCell(37);
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	            driver.findElement(By.xpath("//*[@id=\"editor_tab_id_general\"]/div/div[2]/div/textarea")).sendKeys(cell.getStringCellValue());
	            driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[1]")).click(); //Apply
	    		driver.findElement(By.xpath("//*[@id=\"surveyquestioneditorwindow\"]/div/div/div[3]/input[2]")).click(); //ok
	            Thread.sleep(3000);
		    	
		    	driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div/div/form/div/div/div[2]/div/div[3]/div")).click(); //click second question
		    	Thread.sleep(5000);
		    	driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[5]/span[1]/svg-icon")).click(); //delete
			   	Thread.sleep(3000);
			   	 
			   	String copyverify = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/parent::Div/parent::Div/div[2]/div/div/h5/span[3]")).getText();
	 		    boolean expectcopyverify1 = copyverify.contains("RATING TITLE"); 
				//AssertJUnit.assertTrue(expectcopyverify1, copyverify);	
				
				driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
		    	String msg10 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		    	String expected10 = "Master record details saved successfully.";
		    	//assertion14.assertEquals(expected10, msg10);  
		    	Thread.sleep(5000);
				
				driver.findElement(By.xpath("//*[@id=\"scrollableDiv\"]/div/div/form/div/div/div[2]/div/div[2]/div")).click(); //click 1st question
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[5]/span[1]/svg-icon")).click(); //delete
			   	Thread.sleep(3000);
			   	 
			   	String copyverify2 = driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border\"]/parent::Div/parent::Div/div[2]/div/div/h5/span[3]")).getText();
	 		    boolean expectcopyverify2 = copyverify2.contains("SINGLE INPUT TITLE"); 
				//AssertJUnit.assertTrue(expectcopyverify2, copyverify2);
		    	//assertion14.assertAll(); 	
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
