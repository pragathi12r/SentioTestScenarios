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

public class MultiStage {
	static WebDriver driver = null;
	static Actions action = null;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/CreateControlData.xls"); 
    static String filename = "Testing2"; 
    static String filename1 = "Testing Batch2"; //PublishedMasterRecord2
	   
    @Test (priority=1)
	public static void CreateMasterForMultiStage1 () throws InterruptedException, IOException {
    	SoftAssert assertion1 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
        	Login();
        	//temporary code to view the form
//        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
//        	driver.findElement(By.xpath("//*[contains(text(),'Testing2')]")).click();  //Click temporary file
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
    		
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")));
    			
    		cell = sheet.getRow(i).getCell(7);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		driver.findElement(By.xpath("//span[contains(text(), 'Title')]/parent::td/parent::tr/td[2]/div/div[2]/input")).sendKeys(cell.getStringCellValue()); //title
  		
    		    		
    		driver.findElement(By.xpath("//*[@class=\"select2-selection__arrow\"]")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//li[contains(text(), '.page1')]")).click(); //click .page1

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
    		//driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //check meena

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role1 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected1 = "[\"test\"]";
    		//assertion1.assertEquals(expected1, role1);  
    
    		driver.findElement(By.xpath("//*[@class=\"select2-selection__arrow\"]")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//li[contains(text(), '..question1')]")).click(); //click ..New Question
    		
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
    		//assertion1.assertEquals(expected2, verifyrole2); 
    		Thread.sleep(3000);
    		   		
         	driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
         	Thread.sleep(5000);

        	//Form Builder
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"toolBoxDiv\"]/div/div[3]")).click(); //single input
    		Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click(); //click add pages icon
        	
    		//Form Builder
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[contains(text(), 'Data Field')]")).click(); //single input
    		Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click();
         	
    		driver.findElement(By.xpath("//*[@class=\"select2-selection__arrow\"]")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//li[contains(text(), '.page2')]")).click(); //click .page1

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		//driver.findElement(By.xpath("//span[contains(text(), 'Administrator')]/parent::span/parent::label/input")).click(); //check Administrator
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		//driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //check meena

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role3 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected3 = "[\"qa\"]";
    		//assertion1.assertEquals(expected3, role3);  
    
    		driver.findElement(By.xpath("//*[@class=\"select2-selection__arrow\"]")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//li[contains(text(), '..question3')]")).click(); //click ..New Question
    		
    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
    		
    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
    		

    		driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
    		Thread.sleep(2000);
    			
    		driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //click qa
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    			
    		String verifyrole4 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();
    		String expected4 = "qa";
    		//assertion1.assertEquals(expected4, verifyrole4); 
    		Thread.sleep(3000);
    		
            cell = sheet.getRow(i).getCell(8);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select master  =new Select(driver.findElement(By.xpath("//*[contains(text(), 'Master')]/parent::td/parent::tr/td[2]/div/div/select"))); //master
    		master.selectByVisibleText(cell.getStringCellValue());
    		
    		Thread.sleep(6000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'M P R   Field')]/parent::td/parent::tr/td[2]/div/div/select")));
    		
            cell = sheet.getRow(i).getCell(9);
            cell.setCellType(Cell.CELL_TYPE_STRING);
    		Select mprfield =new Select(driver.findElement(By.xpath("//*[contains(text(), 'M P R   Field')]/parent::td/parent::tr/td[2]/div/div/select"))); //mpr field
    		mprfield.selectByVisibleText(cell.getStringCellValue());
    		
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click();
         	Thread.sleep(3000);
         	
    		WebElement mprprimary=driver.findElement(By.xpath("//*[contains(text(), 'M P R   Primary')]/parent::td/parent::tr/td[2]/div/div/label/div/span")); //mpr primary
        	if(!mprprimary.isSelected())
        		mprprimary.click();
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
         	Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@title=\"Add New Page\"]")).click(); //click add pages icon
        	
    		//Form Builder
    		driver.findElement(By.xpath("//img[contains(@src,'/Survey-portlet/images/Add question icon.svg')]")).click();
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[@class=\"toolBoxDiv\"]/div/div[3]")).click(); //single input
    		Thread.sleep(5000);
    		
    		driver.findElement(By.xpath("//*[@class=\"sv_qstn question_actions svd_question svd-dark-bg-color svd_q_design_border svd_q_selected svd-main-border-color\"]/div[3]/question-actions/div/span[1]/img")).click();
         	
    		driver.findElement(By.xpath("//*[@class=\"select2-selection__arrow\"]")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//li[contains(text(), '.page3')]")).click(); //click .page1

    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")));

    		driver.findElement(By.xpath("//*[contains(text(), 'Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); //click role
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //check Administrator
    		//driver.findElement(By.xpath("//span[contains(text(), 'qa')]/parent::span/parent::label/input")).click(); //check qa
    		//driver.findElement(By.xpath("//span[contains(text(), 'test')]/parent::span/parent::label/input")).click(); //check meena

    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[1]")).click(); //click apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Role')]/parent::Div/parent::div/div[3]/input[2]")).click(); //click ok
    		
    		String role11 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div")).getText();
    		String expected11 = "[\"supervisor\"]";
    		//assertion1.assertEquals(expected11, role11);  
    
    		driver.findElement(By.xpath("//*[@class=\"select2-selection__arrow\"]")).click(); // click question type dropdown
    		driver.findElement(By.xpath("//li[contains(text(), '.page3')]/following-sibling::li")).click(); //click ..New Question
    		
    		Thread.sleep(6000);
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")));
    		Thread.sleep(5000);
    		driver.findElement(By.xpath("//*[contains(text(),'Verify')]/parent::td/parent::tr/td[2]/div/div/label/div/span")).click(); // click verify
    		Thread.sleep(3000);

    		driver.findElement(By.xpath("//*[contains(text(), 'Verify   Role')]/parent::td/parent::tr/td[2]/div/div/a")).click(); // click verify role
    		Thread.sleep(5000);
    			
    		driver.findElement(By.xpath("//span[contains(text(), 'supervisor')]/parent::span/parent::label/input")).click(); //click qa
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[1]")).click(); //apply
    		driver.findElement(By.xpath("//h4[contains(text(), 'Verify   Role')]/parent::div/parent::div/div[3]/input[2]")).click(); //ok
    			
    		String verifyrole22 = driver.findElement(By.xpath("//*[contains(text(),'Role')]/parent::td/parent::tr/td[2]/div/div/a")).getText();
    		String expected22 = "supervisor";
    		AssertJUnit.assertEquals(expected22, verifyrole22);
    		//assertion1.assertAll();
    		Thread.sleep(3000);
    		
    		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
         	Thread.sleep(5000);
         	
         	driver.findElement(By.xpath("//*[@class=\"cmplt\"]")).click(); //complete
         	Thread.sleep(5000);
         	
         	driver.findElement(By.xpath("//*[@id=\"publishSurvey\"]")).click(); //publish
         	Thread.sleep(5000);       	
    		Close();	
        }
	}

    @Test (priority=2)
   	public static void CreateBatchForMultiStage2 () throws InterruptedException, IOException {	
    	SoftAssert assertion2 = new SoftAssert();
   		FileInputStream finput = new FileInputStream(src);
           workbook = new HSSFWorkbook(finput);
           sheet= workbook.getSheetAt(1);
           for(int i=2; i<=sheet.getLastRowNum(); i++)
           {	
           	Login();
          //create batch	
        	driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
       		driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
       		Thread.sleep(5000);
       		
       		driver.findElements(By.className("item")).get(5).click();  //click create batch
       		Thread.sleep(5000);    	
       	
       		cell = sheet.getRow(i).getCell(10);
       		cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"batch_Id\"]")).sendKeys(cell.getStringCellValue());
			//cell = sheet.getRow(i).getCell(11);
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@class=\"type_category1 category_type_1\"]")).sendKeys("23-09-2019"); //effective date
			
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
			Thread.sleep(3000);			
			
			driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
			driver.findElement(By.xpath("//*[contains(text(), 'adminuser')]/parent::tr/td/input")).click(); //assign admin
			driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
			driver.findElement(By.xpath("//*[contains(text(), 'meenachi')]/parent::tr/td/input")).click(); //assign meena
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
			Thread.sleep(8000);
			
			String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
			String expect1 = "Batch Records "; 
			AssertJUnit.assertEquals(expect1, msg1); 
			//assertion2.assertAll();
	    	Thread.sleep(5000);
            Close();
           }
    }
   
    @Test (priority=3)
   	public static void EditorFirstPage3 () throws InterruptedException, IOException {	
    	SoftAssert assertion3 = new SoftAssert();
   		FileInputStream finput = new FileInputStream(src);
           workbook = new HSSFWorkbook(finput);
           sheet= workbook.getSheetAt(1);
           for(int i=2; i<=sheet.getLastRowNum(); i++)
           {
       //login as admin 	   
           	Login();
           	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect1 = "Unauthorized User"; 
			AssertJUnit.assertEquals(expect1, msg1);
			//assertion3.assertAll();
        	Thread.sleep(3000);
        	
        //login as qa
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	        readProperty a = new readProperty();     
	        driver.get(a.getApplicationUrl());
	        driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
	        driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
	        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	        Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));
	        
	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg11 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect11 = "Unauthorized User"; 
			AssertJUnit.assertEquals(expect11, msg11);
			assertion3.assertAll();
        	Thread.sleep(3000);
        	
       //login as user
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	        //readProperty a = new readProperty();     
	        driver.get(a.getApplicationUrl());
	        driver.findElement(By.cssSelector("#_58_login")).sendKeys("meenachi");
	        driver.findElement(By.cssSelector("#_58_password")).sendKeys("test@123");
	        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	        Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabss = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabss.get(1));

	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "Testing Batch2"; 
			AssertJUnit.assertEquals(expect, msg); 
			//assertion3.assertAll();
	    	Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(11);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_text_root\"]")).sendKeys(cell.getStringCellValue()); //question1
		    
		    driver.findElement(By.xpath("//*[@class=\"btn btn-info btn-xs verify_stage btnCls cmplt question1QVfy\"]")).click(); //click verify
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(12);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys(cell.getStringCellValue()); //username
			Thread.sleep(2000);
			
			cell = sheet.getRow(i).getCell(13);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_password\"]")).sendKeys(cell.getStringCellValue()); //password
			Thread.sleep(2000);
			
//			cell = sheet.getRow(i).getCell(14);
//	        cell.setCellType(Cell.CELL_TYPE_STRING);
//			driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //remarks
//			Thread.sleep(2000);
        	
        	driver.findElement(By.xpath("//*[@id=\"validate_user\"]")).click(); //click verify button
        	Thread.sleep(10000);
		    
        	String msgg = driver.findElement(By.xpath("//*[@class=\"btn btn-info btn-xs verify_stage btnCls cmplt question1QVfy\"]")).getText();
	       	String expectt = "Verified"; 
			AssertJUnit.assertEquals(expectt, msgg); 
			//assertion3.assertAll();
	    	Thread.sleep(5000);
        	
        	cell = sheet.getRow(i).getCell(15);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
        	driver.findElement(By.xpath("//*[contains(text(), 'question2')]/parent::h5/parent::div/following-sibling::div/input")).sendKeys(cell.getStringCellValue()); //question2 
		        	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg5 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect5 = "Batch Records"; 
			AssertJUnit.assertEquals(expect5, msg5); 
			//assertion3.assertAll();
	    	Thread.sleep(5000);
        	Close();           
           }
    }

    @Test (priority=4)
   	public static void EditorSecondPage4 () throws InterruptedException, IOException {	
    	SoftAssert assertion4 = new SoftAssert();
   		FileInputStream finput = new FileInputStream(src);
           workbook = new HSSFWorkbook(finput);
           sheet= workbook.getSheetAt(1);
           for(int i=2; i<=sheet.getLastRowNum(); i++)
           {
       //login as admin 	   
           	Login();
           	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect1 = "Unauthorized User"; 
			AssertJUnit.assertEquals(expect1, msg1);
			//assertion4.assertAll();
        	Thread.sleep(3000);
        	
        //login as user
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
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

	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg11 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect11 = "Unauthorized User"; 
			AssertJUnit.assertEquals(expect11, msg11);
			//assertion4.assertAll();
        	Thread.sleep(3000);
        	
       //login as qa
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	        //readProperty a = new readProperty();     
	        driver.get(a.getApplicationUrl());
	        driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
	        driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
	        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	        Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabss = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabss.get(1));

	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "Testing Batch2"; 
			AssertJUnit.assertEquals(expect, msg); 
			//assertion4.assertAll();
	    	Thread.sleep(5000);
	    	 	
	    	cell = sheet.getRow(i).getCell(16);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    Select materialName2 =new Select(driver.findElement(By.xpath("//*[@aria-label=\"Raw Material\"]"))); //raw material
		    materialName2.selectByVisibleText(cell.getStringCellValue());
		    Thread.sleep(3000);
		    
		    driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).click(); //click verify
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(12);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys("qa"); //username
			Thread.sleep(2000);
			
			cell = sheet.getRow(i).getCell(13);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_password\"]")).sendKeys("quality@123"); //password
			Thread.sleep(2000);
			
//			cell = sheet.getRow(i).getCell(14);
//	        cell.setCellType(Cell.CELL_TYPE_STRING);
//			driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //remarks
//			Thread.sleep(2000);
        	
        	driver.findElement(By.xpath("//*[@id=\"validate_user\"]")).click(); //click verify button
        	Thread.sleep(10000);
		    
        	String msgg = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).getText();
	       	String expectt = "Verified"; 
			AssertJUnit.assertEquals(expectt, msgg); 
			//assertion4.assertAll();
	    	Thread.sleep(5000);
		    
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(10000);
        	
//        	String msg6 = driver.findElement(By.xpath("//*[@id=\"bookshelf\"]")).getText();
//	       	String expect6 = "Batch Records "; 
//			assertion.assertEquals(expect6, msg6); 
//			assertion.assertAll();
	    	Thread.sleep(5000);
        	Close();           
           }
    }
	 
    @Test (priority=5)
   	public static void EditorThirdPage5 () throws InterruptedException, IOException {	
    	SoftAssert assertion5 = new SoftAssert();
   		FileInputStream finput = new FileInputStream(src);
           workbook = new HSSFWorkbook(finput);
           sheet= workbook.getSheetAt(1);
           for(int i=2; i<=sheet.getLastRowNum(); i++)
           {
       //login as qa 	   
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
   			driver = new ChromeDriver();
   			action = new Actions(driver);
   			driver.manage().window().maximize();
   			wait = new WebDriverWait(driver,30);
   	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

   	        readProperty a = new readProperty();     
   	        driver.get(a.getApplicationUrl());
   	        driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
   	        driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
   	        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	        Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));

   	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
           	Thread.sleep(5000);
           	driver.findElements(By.className("item")).get(1).click();  //click execute batch
           	Thread.sleep(5000);
           	
           	String msg11 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect11 = "Unauthorized User"; 
			AssertJUnit.assertEquals(expect11, msg11);
			//assertion5.assertAll();
        	Thread.sleep(3000);
           	
        //login as user
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	        //readProperty a = new readProperty();     
	        driver.get(a.getApplicationUrl());
	        driver.findElement(By.cssSelector("#_58_login")).sendKeys("meenachi");
	        driver.findElement(By.cssSelector("#_58_password")).sendKeys("test@123");
	        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	        Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabss = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabss.get(1));

	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg7 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect7 = "Unauthorized User"; 
			AssertJUnit.assertEquals(expect7, msg7);
			//assertion5.assertAll();
        	Thread.sleep(3000);
       	
       //login as admin
        	Login();
	        Thread.sleep(5000);
	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "Testing Batch2"; 
			AssertJUnit.assertEquals(expect, msg); 
			//assertion5.assertAll();
	    	Thread.sleep(5000);
	    	
	    	cell = sheet.getRow(i).getCell(17);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@class=\"sv_q_text_root\"]")).sendKeys(cell.getStringCellValue()); //question4
		    Thread.sleep(5000);
		    
		    driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).click(); //click verify
		    Thread.sleep(2000);
		    
		    cell = sheet.getRow(i).getCell(12);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys("pagemajikadmin"); //username
			Thread.sleep(2000);
			
			cell = sheet.getRow(i).getCell(13);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_password\"]")).sendKeys("sentio@123"); //password
			Thread.sleep(2000);
			
//			cell = sheet.getRow(i).getCell(14);
//	        cell.setCellType(Cell.CELL_TYPE_STRING);
//			driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //remarks
//			Thread.sleep(2000);
        	
        	driver.findElement(By.xpath("//*[@id=\"validate_user\"]")).click(); //click verify button
        	Thread.sleep(10000);
		    
        	String msgg = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).getText();
	       	String expectt = "Verified"; 
			AssertJUnit.assertEquals(expectt, msgg); 
			//assertion5.assertAll();
	    	Thread.sleep(5000);
		    
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg6 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect6 = "Batch Records"; 
			AssertJUnit.assertEquals(expect6, msg6); 
			//assertion5.assertAll();
	    	Thread.sleep(5000);
        	Close();           
           }
    }
    
    @Test (priority=6)
   	public static void EditorFinalPage6 () throws InterruptedException, IOException {	
    	SoftAssert assertion6 = new SoftAssert();
   		FileInputStream finput = new FileInputStream(src);
           workbook = new HSSFWorkbook(finput);
           sheet= workbook.getSheetAt(1);
           for(int i=2; i<=sheet.getLastRowNum(); i++)
           {
       //login as admin 	   
           	Login();
           	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect1 = "Unauthorized User"; 
			AssertJUnit.assertEquals(expect1, msg1);
			//assertion6.assertAll();
        	Thread.sleep(3000);
        	
        //login as user
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
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

	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg11 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]")).getText();
	       	String expect11 = "Unauthorized User"; 
			AssertJUnit.assertEquals(expect11, msg11);
			//assertion6.assertAll();
        	Thread.sleep(3000);
        	
       //login as qa
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	        //readProperty a = new readProperty();     
	        driver.get(a.getApplicationUrl());
	        driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
	        driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
	        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	        Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabss = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabss.get(1));

	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "Testing Batch2"; 
			AssertJUnit.assertEquals(expect, msg); 
			//assertion6.assertAll();
	    	Thread.sleep(5000);
	    	
	    	String msgclose = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expectclose = "Close"; 
			AssertJUnit.assertEquals(expectclose, msgclose); 
			//assertion6.assertAll();
	    	
			driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).click(); //click close
			Thread.sleep(5000);
			
			//fill close popup details
            cell = sheet.getRow(i).getCell(18);
		    cell.setCellType(Cell.CELL_TYPE_STRING);
		    driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //batch name
		    
//		    cell = sheet.getRow(i).getCell(19);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    driver.findElement(By.xpath("//*[@id=\"type_name2\"]")).sendKeys(cell.getStringCellValue()); //deviation name
//		    
//		    cell = sheet.getRow(i).getCell(20);
//		    cell.setCellType(Cell.CELL_TYPE_STRING);
//		    driver.findElement(By.xpath("//*[@id=\"type_name3\"]")).sendKeys(cell.getStringCellValue()); //title
//		    Thread.sleep(5000);
		    
		    driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); //click ok
		    Thread.sleep(8000);
		    String msgclosed = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expectclosed = "Closed"; 
			AssertJUnit.assertEquals(expectclosed, msgclosed); 
			
        	Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyComplete\"]")).click(); //click exit
			Thread.sleep(5000);
        		        	
        	String msg10 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect10 = "Batch Records"; 
			AssertJUnit.assertEquals(expect10, msg10); 
			//assertion6.assertAll();
			Thread.sleep(5000);	 	
        	Close();           
           }
    }
    
    @Test (priority=7)
   	public static void ViewEditor7 () throws InterruptedException, IOException {
    	SoftAssert assertion7 = new SoftAssert();
   		FileInputStream finput = new FileInputStream(src);
           workbook = new HSSFWorkbook(finput);
           sheet= workbook.getSheetAt(1);
           for(int i=2; i<=sheet.getLastRowNum(); i++)
           {
       //login as admin 	   
           	Login();
           	driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect = "Testing Batch2"; 
			AssertJUnit.assertEquals(expect, msg); 
			//assertion7.assertAll();
	    	Thread.sleep(5000);
	    	
	    	String msgclose = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expectclose = "Closed"; 
			AssertJUnit.assertEquals(expectclose, msgclose); 
			//assertion7.assertAll();
			
			String msgexit = driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).getText();
	       	String expectexit = "Next"; 
			AssertJUnit.assertEquals(expectexit, msgexit); 
			assertion7.assertAll();
	    	
        	Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyComplete\"]")).click(); //click exit
			Thread.sleep(5000);
        		        	
        	String msg10 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect10 = "Batch Records"; 
			AssertJUnit.assertEquals(expect10, msg10); 
			//assertion7.assertAll();
			Thread.sleep(5000);	 	
        	Close();  
        	
        //login as user
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
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

	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect1 = "Testing Batch2"; 
			AssertJUnit.assertEquals(expect1, msg1); 
			//assertion7.assertAll();
	    	Thread.sleep(5000);
	    	
	    	String msgclose1 = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expectclose1 = "Closed"; 
			AssertJUnit.assertEquals(expectclose1, msgclose1); 
			//assertion7.assertAll();
			
			String msgexit1 = driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).getText();
	       	String expectexit1 = "Next"; 
			AssertJUnit.assertEquals(expectexit1, msgexit1); 
			//assertion7.assertAll();
	    	
        	Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyComplete\"]")).click(); //click exit
			Thread.sleep(5000);
        		        	
        	String msg2 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect2 = "Batch Records"; 
			AssertJUnit.assertEquals(expect2, msg2); 
			//assertion7.assertAll();
			Thread.sleep(5000);	 	
        	Close();  
        	
       //login as qa
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver,30);
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	        //readProperty a = new readProperty();     
	        driver.get(a.getApplicationUrl());
	        driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
	        driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
	        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
	        Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabss = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabss.get(1));

	        driver.findElement(By.xpath("//*[contains(text(),'"+ filename1 +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
        	Thread.sleep(5000);
        	        	
        	String msg3 = driver.findElement(By.xpath("//*[@id=\"wrappersi\"]/div[1]/div/div/div[3]")).getText();
	       	String expect3 = "Testing Batch2"; 
			AssertJUnit.assertEquals(expect3, msg3); 
			//assertion7.assertAll();
	    	Thread.sleep(5000);
	    	
	    	String msgclose3 = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
	       	String expectclose3 = "Closed"; 
			AssertJUnit.assertEquals(expectclose3, msgclose3); 
			//assertion7.assertAll();
			
			String msgexit3 = driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).getText();
	       	String expectexit3 = "Next"; 
			AssertJUnit.assertEquals(expectexit3, msgexit3); 
			//assertion7.assertAll();
	    	
        	Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyNext\"]")).click(); //click next
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"surveyComplete\"]")).click(); //click exit
			Thread.sleep(5000);
        		        	
        	String msg4 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect4 = "Batch Records"; 
			AssertJUnit.assertEquals(expect4, msg4); 
			//assertion7.assertAll();
			Thread.sleep(5000);	 	
        	Close();           
           }
    }

	   
	   
	   
	   public static void Login() throws InterruptedException, IOException{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			driver = new ChromeDriver();
			action = new Actions(driver);
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
//	        
//	        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//	        driver.switchTo().window(tabs.get(1));
		}

		public static void Close() throws InterruptedException, IOException{	
			driver.quit();
		}
}
