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

public class StageRole {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;

	static File src=new File(System.getProperty("user.dir") + "/ExcelData/BatchIssuance.xls"); 
    static String StageRoleAndLoginRoleDifferent = "TestBatch12";
    static String StageRoleAndLoginRoleSameAndStageCheckedOut = "TestBatch13";
    
    @Test (priority=1) 
    public static void StageRoleAndLoginRoleDifferent1 () throws InterruptedException, IOException {	
    	SoftAssert assertion1 = new SoftAssert();
    	FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3); //start batch sheet
        //for(int i=2; i<=sheet.getLastRowNum(); i++){	
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
		driver = new ChromeDriver();
		action = new Actions(driver);
//		assertion= new SoftAssert();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        readProperty a = new readProperty();     
        driver.get(a.getApplicationUrl());
        driver.findElement(By.cssSelector("#_58_login")).sendKeys("qa");
        driver.findElement(By.cssSelector("#_58_password")).sendKeys("quality@123");
        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
  

        
        	Thread.sleep(10000);
        	driver.findElement(By.xpath("//*[contains(text(),'"+ StageRoleAndLoginRoleDifferent +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElement(By.xpath("(//div[contains(text(),'Execute Batch')])[2]")).click();  //click start batch
       	//driver.findElements(By.className("item")).get(1).click();  //click start batch
//        	Thread.sleep(10000); 
        	driver.findElement(By.xpath("//button[contains(text(),'Complete')]")).click();
        	driver.findElement(By.xpath("//button[contains(text(),'Exit')]")).click();
        	Thread.sleep(5000);
        	
//        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
//	       	String expect1 = "Batch Records"; 
//			assertion1.assertEquals(expect1, msg1); 
			//assertion1.assertAll();
	    	Thread.sleep(5000);
        	Close();
        //}
    }
   
    @Test (priority=2) 
    public static void StageRoleAndLoginRoleSame2 () throws InterruptedException, IOException {	
    	SoftAssert assertion2 = new SoftAssert();
        FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        Login();    
        Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));
        	
        	driver.findElement(By.xpath("//*[contains(text(),'vbatach')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElement(By.xpath("(//div[contains(text(),'Execute Batch')])[2]")).click();  //Click tstart batch
        	Thread.sleep(5000);
        	cell = sheet.getRow(i).getCell(1);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question 1
			Thread.sleep(2000);
//			driver.findElement(By.xpath("//button[contains(text(),'Complete')]")).click();
//        	driver.findElement(By.xpath("//button[contains(text(),'Exit')]")).click();
        	
			driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).click(); //click verify
			Thread.sleep(3000);
			
			
			
		
			cell = sheet.getRow(i).getCell(2);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys(cell.getStringCellValue()); //username
			Thread.sleep(2000);
			
			cell = sheet.getRow(i).getCell(3);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_password\"]")).sendKeys(cell.getStringCellValue()); //password
			Thread.sleep(2000);
			
//			cell = sheet.getRow(i).getCell(4);
//	        cell.setCellType(Cell.CELL_TYPE_STRING);
//			driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //remarks
//			Thread.sleep(2000);
        	
        	driver.findElement(By.xpath("//*[@id=\"validate_user\"]")).click(); //click verify button
        	Thread.sleep(15000);
		
        	String msg3 = driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).getText();
	       	String expect3 = "Verified"; 
			AssertJUnit.assertEquals(expect3, msg3);
        	
			driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).click(); //click verify
			Thread.sleep(3000);
        	
			String msg2 = driver.findElement(By.xpath("//*[@id=\"dialog1\"]/div/span/div[3]/span[1]")).getText();
	       	String expect2 = "Checked By: "; 
			AssertJUnit.assertEquals(expect2, msg2);
			//assertion2.assertAll();
			
			driver.findElement(By.xpath("//*[@class=\"yui3-skin-sam controls-visible page-maximized signed-in public-page site dockbar-ready\"]/div[7]/div[1]/button/span[1]")).click(); //close
			Thread.sleep(5000);
			
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
        }}

    
    
    
    @Test (priority=3) 
    public static void StageRoleAndLoginRoleSameAndStageCheckedOut3 () throws InterruptedException, IOException {	
    	SoftAssert assertion3 = new SoftAssert();
    	FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(3); //start batch sheet
        for(int i=2; i<=sheet.getLastRowNum(); i++){	
        	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
    		driver = new ChromeDriver();
    		action = new Actions(driver);
//    		assertion= new SoftAssert();
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
            
        	
        	driver.findElement(By.xpath("//*[contains(text(),'"+ StageRoleAndLoginRoleSameAndStageCheckedOut +"')]")).click();  //Click temporary file
        	Thread.sleep(5000);
        	driver.findElement(By.xpath("(//div[contains(text(),'Execute Batch')])[2]")).click();  //click start batch
        	Thread.sleep(10000);  
	   
        	cell = sheet.getRow(i).getCell(1);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question 1
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).click(); //click verify
			Thread.sleep(3000);
			
		
			cell = sheet.getRow(i).getCell(2);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys(cell.getStringCellValue()); //username
			Thread.sleep(2000);
			
			cell = sheet.getRow(i).getCell(3);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"user_password\"]")).sendKeys(cell.getStringCellValue()); //password
			Thread.sleep(2000);
			
//			cell = sheet.getRow(i).getCell(4);
//	        cell.setCellType(Cell.CELL_TYPE_STRING);
//			driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //remarks
//			Thread.sleep(2000);
        /*	
        	driver.findElement(By.xpath("//*[@id=\"validate_user\"]")).click(); //click verify button
        	Thread.sleep(5000);
        	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
	    	Thread.sleep(5000);
	    	
	    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
            Thread.sleep(5000);
        	
        	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
	       	String expect1 = "Batch Records"; 
			assertion.assertEquals(expect1, msg1); 
			assertion.assertAll();
	    */	Thread.sleep(5000);
        	Close();
        	
        	
        	//login as user1
    	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
		driver = new ChromeDriver();
		action = new Actions(driver);
//		assertion= new SoftAssert();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //readProperty aa = new readProperty();     
        driver.get(a.getApplicationUrl());
        driver.findElement(By.cssSelector("#_58_login")).sendKeys("shiftengineer");
        driver.findElement(By.cssSelector("#_58_password")).sendKeys("shift@321");
        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
        Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabss = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabss.get(1));
        
        Thread.sleep(10000);
    	driver.findElement(By.xpath("//*[contains(text(),'"+ StageRoleAndLoginRoleSameAndStageCheckedOut +"')]")).click();  //Click temporary file
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//div[contains(text(),'Execute Batch')])[2]")).click();  //click start batch
    	Thread.sleep(10000);  
    	
    	String msg1 = driver.findElement(By.xpath("//*[@class=\"project-item\"]/a")).getText();
       	String expect1 = "Batch Records"; 
		//assertion3.assertEquals(expect1, msg1); 
		//assertion3.assertAll();
    	Thread.sleep(5000);
    	Close();
    	
    	
    //save & complete the file then try to open	
    	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
		driver = new ChromeDriver();
		action = new Actions(driver);
//		assertion= new SoftAssert();
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
//ArrayList<String> tabsss = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabsss.get(1));
        
    	
    	driver.findElement(By.xpath("//*[contains(text(),'"+ StageRoleAndLoginRoleSameAndStageCheckedOut +"')]")).click();  //Click temporary file
    	Thread.sleep(5000);
    	driver.findElements(By.className("item")).get(1).click();  //click start batch
    	Thread.sleep(10000);  
   
    	cell = sheet.getRow(i).getCell(5);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@aria-label=\"question1\"]")).sendKeys(cell.getStringCellValue()); //question 1
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@class=\"sv_q sv_qstn\"]/div[1]/h5/button")).click(); //click verify
		Thread.sleep(3000);
		
		cell = sheet.getRow(i).getCell(6);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"user_name\"]")).sendKeys(cell.getStringCellValue()); //username
		Thread.sleep(2000);
		
		cell = sheet.getRow(i).getCell(7);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"user_password\"]")).sendKeys(cell.getStringCellValue()); //password
		Thread.sleep(2000);
		
//		cell = sheet.getRow(i).getCell(8);
//        cell.setCellType(Cell.CELL_TYPE_STRING);
//		driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys(cell.getStringCellValue()); //remarks
//		Thread.sleep(2000);
    	
    	driver.findElement(By.xpath("//*[@id=\"validate_user\"]")).click(); //click verify button
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae\"]")).click(); //save
    	Thread.sleep(5000);
    	
    	driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click(); //complete
        Thread.sleep(5000);
        
        driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).click(); //click close
    	Thread.sleep(5000);
    	
    //fill close popup details
	    driver.findElement(By.xpath("//*[@id=\"user_cmt\"]")).sendKeys("test"); //batch name
	    //driver.findElement(By.xpath("//*[@id=\"type_name2\"]")).sendKeys("test1"); //deviation name
	    //driver.findElement(By.xpath("//*[@id=\"type_name3\"]")).sendKeys("test2"); //title
	    Thread.sleep(5000);
	    
	    driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); //click ok
	    Thread.sleep(8000);
	    String msg = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
       	String expect = "Closed"; 
		AssertJUnit.assertEquals(expect, msg);
 
		Thread.sleep(5000);
		//assertion3.assertAll();
		Close();	 	
    	
    	//login as user1
	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
	driver = new ChromeDriver();
	action = new Actions(driver);
//	assertion= new SoftAssert();
	driver.manage().window().maximize();
	wait = new WebDriverWait(driver,30);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    //readProperty aa = new readProperty();     
    driver.get(a.getApplicationUrl());
    driver.findElement(By.cssSelector("#_58_login")).sendKeys("shiftengineer");
    driver.findElement(By.cssSelector("#_58_password")).sendKeys("shift@321");
    driver.findElement(By.cssSelector("#_58_fm > button")).click();    
    Thread.sleep(5000);
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[1]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabssss = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabssss.get(1));
    
    Thread.sleep(10000);
	driver.findElement(By.xpath("//*[contains(text(),'"+ StageRoleAndLoginRoleSameAndStageCheckedOut +"')]")).click();  //Click temporary file
	Thread.sleep(5000);
	driver.findElements(By.className("item")).get(1).click();  //click start batch
	Thread.sleep(10000);
	
	
	String msg123 = driver.findElement(By.xpath("//*[@id=\"surveycls\"]")).getText();
   	String expect123 = "Closed"; 
	//assertion3.assertEquals(expect123, msg123);

	Thread.sleep(5000);
	//assertion3.assertAll();
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
//driver.findElement(By.xpath("//*[@class=\"nav nav-account-controls\"]/li[3]")).click(); //click my sites
//driver.findElement(By.xpath("//*[contains(text(), 'BATCH RECORD')]")).click(); //click batch records
//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//driver.switchTo().window(tabs.get(1));
	}

	public static void Close() throws InterruptedException, IOException{	
		driver.quit();
	} 
    
}
