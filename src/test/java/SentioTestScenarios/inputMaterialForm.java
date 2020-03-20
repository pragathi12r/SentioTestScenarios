package SentioTestScenarios;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.DateFormatConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class inputMaterialForm {

	public WebDriver driver =  null;
	static Actions action = null;
	static ExtentTest test;
	static ExtentReports report;
	
	public  void waitTime(long time) {
		try {
				Thread.sleep(time);
									
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	@BeforeClass
	public static void startTest()
	{
		Date d=new Date();
        String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
	//report = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReportResults.html");
	report=new ExtentReports(System.getProperty("user.dir")+"/ExtReport/"+fileName,true, DisplayOrder.NEWEST_FIRST);
	test = report.startTest("Equipment Validator");
	}
	@Test(priority=1)
	public void Login() throws Exception{
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
			
			//login
		driver.get("http://sentio.pagemajik.info/");
		test.log(LogStatus.PASS, "Navigated to the specified URL");
		
		//loginpage
        driver.findElement(By.cssSelector("#_58_login")).sendKeys("aarthi");
        driver.findElement(By.cssSelector("#_58_password")).sendKeys("sample@123");
        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
        Thread.sleep(5000);
        System.out.println("Login Successfully");
        test.log(LogStatus.PASS, "Login Successfully");
			
	}
	
	@Test(priority=2)
	public void OpenInputMaterial() throws Exception{
		 //batchrecord
        driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
        
      //verifyElement
        if(driver.findElements(By.xpath("(//a[text()='Master Records '])[1]")).size()>0){
        	System.out.println("Verify Element(Master Record) is Presrent");
        	test.log(LogStatus.INFO, "Verify Element(Master Record) is present");
        	
        }
        else{
        	System.out.println("Verify Element(Master Record) is Absent");
        	test.log(LogStatus.INFO, "Verify Element(Master Record) is Not present");
        }
		driver.findElement(By.xpath("(//img[@class='cat_sort'])[1]")).click();
        driver.findElement(By.xpath("(//select[@class='client-list'])[1]")).click();
		 driver.findElement(By.xpath("(//option[@label='Control Data'])[1]")).click();
	       
	       	//Double click the button to launch an alertbox
	       		Actions action = new Actions(driver);
	       		WebElement link =driver.findElement(By.xpath("(//div[contains(text(),'Input Material')])"));
	       		action.doubleClick(link).perform();
	       		Thread.sleep(2000);
		
	}
	
	@Test(priority=3)
	public void GiveExistingFormidDaterecievedLesserThanTodaysDate() throws Exception{
		SoftAssert assertion1 = new SoftAssert();
		driver.findElement(By.xpath("(//span[text()='New'])[1]")).click();
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).click();
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).sendKeys("val9");;
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).click();
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).sendKeys("30/12/2019");
		
		waitTime(2000);	
		driver.findElement(By.xpath("//*[@id=\"controlSave\"]")).click(); 
		waitTime(1000);
		assertion1.assertEquals("Form ID is already exists.",(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]"))).getText(),"Bookshelf items its an error");
		assertion1.assertAll();	
		
	}
	
	@Test(priority=4)
	public void GiveExistingFormidDaterecievedgreaterThanTodaysDate() throws Exception{
		SoftAssert assertion2 = new SoftAssert();
		driver.findElement(By.id("batch_Id")).clear();
		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).click();
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).sendKeys("val9");;
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).click();
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).sendKeys("30/12/2020");
		
		waitTime(2000);	
		driver.findElement(By.xpath("//*[@id=\"controlSave\"]")).click(); 
		waitTime(1000);
		assertion2.assertEquals("Form ID is already exists.",(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]"))).getText(),"Bookshelf items its an error");
		assertion2.assertAll();	
	}
	
	@Test(priority=5)
	public void GiveExistingFormidDaterecievedSameAsTodaysDate() throws Exception{
		SoftAssert assertion3 = new SoftAssert();
		driver.findElement(By.id("batch_Id")).clear();
		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).click();
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).sendKeys("val9");;
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).click();
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).sendKeys("17/03/2020");
		
		waitTime(2000);	
		driver.findElement(By.xpath("//*[@id=\"controlSave\"]")).click(); 
		waitTime(1000);
		assertion3.assertEquals("Form ID is already exists.",(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]"))).getText(),"Bookshelf items its an error");
		assertion3.assertAll();	
	}
	
	@Test(priority=6)
	public void GiveNewFormidDaterecievedLesserThanTodaysDate() throws Exception{
		SoftAssert assertion4 = new SoftAssert();
		driver.findElement(By.id("batch_Id")).clear();
		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).click();
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).sendKeys("try72");;
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).click();
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).sendKeys("12/03/2018");
   		driver.findElement(By.xpath("//*[@id=\"controlSave\"]")).click(); 
		assertion4.assertEquals("Given date is greater than the current date.",(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]"))).getText(),"Bookshelf items its an error");
		assertion4.assertAll();	
	}
	////span[contains(text(),'Please answer the question.')]
	@Test(priority=7)
	public void GiveNewFormidDaterecievedGreaterThanTodaysDate() throws Exception{
		//SoftAssert assertion4 = new SoftAssert();
		driver.findElement(By.id("batch_Id")).clear();
		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).click();
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).sendKeys("try73");;
   		Thread.sleep(2000);
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).click();
   		driver.findElement(By.xpath("//input[@placeholder='enter date']")).sendKeys("17/03/2020");
   		driver.findElement(By.xpath("//*[@id=\"controlSave\"]")).click(); 
   		Thread.sleep(5000);
   		
		//assertion4.assertEquals("Given date is greater than the current date.",(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div[1]"))).getText(),"Bookshelf items its an error");
		//assertion4.assertAll();	
	}
	
	@Test(priority=8)
	public void CheckForCorrectDateOfReceipt() throws Exception{
		SoftAssert assertion5 = new SoftAssert();
		
		WebElement datetext=driver.findElement(By.xpath("//input[@aria-label='Date of Receipt *']"));
		String datetext1=datetext.getText();
		System.out.println(datetext1);
				
		/*assertion5.assertEquals("2020-03-17",(driver.findElements(By.className("sv_q_text_root")).get(2)).getAttribute("value"),"Bookshelf items its an error");
		assertion5.assertAll();*/
		
	}
	
	@Test(priority=9)
	public void CheckForCorrectRecievedBy() throws Exception{
		SoftAssert assertion6 = new SoftAssert();
		WebElement Receivedtext=driver.findElement(By.xpath("//input[@aria-label='Received By *']"));
		String Receivedtext1=Receivedtext.getText();
		System.out.println(Receivedtext1);
		/*assertion6.assertEquals("qa",(driver.findElements(By.className("sv_q_text_root")).get(7)).getAttribute("value"),"Bookshelf items its an error");
		assertion6.assertAll();
		*/
	}
	
	@Test(priority=10)
	public void GiveCompleteWithoutFillingInternalLotNumber() throws Exception{
		SoftAssert assertion7 = new SoftAssert();
		//Fillin description
		Thread.sleep(2000);
       	driver.findElement(By.xpath("//input[@aria-label='Description *']")).click();
       	driver.findElement(By.xpath("//input[@aria-label='Description *']")).sendKeys("Input Material");
       	WebElement getvalue =driver.findElement(By.xpath("//input[@aria-label='Description *']"));
       	String getvalue1=getvalue.getAttribute("value");
       	test.log(LogStatus.INFO, "Description Value-->"+getvalue1);
    	driver.findElement(By.xpath("//input[@aria-label='Internal Lot Number *']")).click();
    	driver.findElement(By.xpath("(//button[text()='Complete'])[2]")).click();
		assertion7.assertEquals("Please answer the question.",(driver.findElements(By.xpath("//span[contains(text(),'Please answer the question.')]")).get(0)).getText(),"Bookshelf items its an error");
		assertion7.assertAll();
		
	}
	
	@Test(priority=11)
	public void GiveCompleteWithoutFillingManufacturer() throws Exception{
		SoftAssert assertion8 = new SoftAssert();
		//Fillin lotnumber
		String s1 = RandomStringUtils.randomNumeric(5);
    	driver.findElement(By.xpath("//input[@aria-label='Internal Lot Number *']")).click();
       	driver.findElement(By.xpath("//input[@aria-label='Internal Lot Number *']")).sendKeys(s1);
       	driver.findElement(By.xpath("(//button[text()='Complete'])[2]")).click();
       	driver.findElement(By.xpath("//input[@aria-label='Manufacturer *']")).click();
		assertion8.assertEquals("Please answer the question.",(driver.findElements(By.xpath("//span[contains(text(),'Please answer the question.')]")).get(1)).getText(),"Bookshelf items its an error");
		assertion8.assertAll();		
	}
	
	@Test(priority=12)
	public void GiveCompleteWithoutFillingPartNumber() throws Exception{
		SoftAssert assertion9 = new SoftAssert();
		//Fillin description
		driver.findElement(By.xpath("//input[@aria-label='Manufacturer *']")).click();
       	driver.findElement(By.xpath("//input[@aria-label='Manufacturer *']")).sendKeys("priya");
       
       	driver.findElement(By.xpath("//input[@aria-label='Part Number *']")).click();
    	driver.findElement(By.xpath("(//button[text()='Complete'])[2]")).click();
		assertion9.assertEquals("Please answer the question.",(driver.findElements(By.xpath("//span[contains(text(),'Please answer the question.')]")).get(2)).getText(),"Bookshelf items its an error");
		assertion9.assertAll();
		
	}
	
	@Test(priority=13)
	public void GiveCompleteWithoutFillingQuantity() throws Exception{
		SoftAssert assertion10 = new SoftAssert();
		//Fillin description
		String s2 = RandomStringUtils.randomNumeric(5);
    	driver.findElement(By.xpath("//input[@aria-label='Part Number *']")).click();
       	driver.findElement(By.xpath("//input[@aria-label='Part Number *']")).sendKeys(s2);
       //	driver.findElement(By.xpath("//input[@aria-label='Quantity *']")).click();
    	driver.findElement(By.xpath("(//button[text()='Complete'])[2]")).click();
		assertion10.assertEquals("Please answer the question.",(driver.findElements(By.xpath("//span[contains(text(),'Please answer the question.')]")).get(3)).getText(),"Bookshelf items its an error");
		assertion10.assertAll();
		
	}
	
	@Test(priority=14)
	public void GiveCompleteWithoutFillingUnit() throws Exception{
		SoftAssert assertion11 = new SoftAssert();
		//Fillin description
		String s3 = RandomStringUtils.randomNumeric(2);
    	driver.findElement(By.xpath("//input[@aria-label='Quantity *']")).click();
       	driver.findElement(By.xpath("//input[@aria-label='Quantity *']")).sendKeys(s3);
       	
    	driver.findElement(By.xpath("(//button[text()='Complete'])[2]")).click();
		assertion11.assertEquals("Please answer the question.",(driver.findElements(By.xpath("//span[contains(text(),'Please answer the question.')]")).get(4)).getText(),"Bookshelf items its an error");
		assertion11.assertAll();
		
	}
	
	@Test(priority=15)
	public void GiveAllCorrectDetailsComplete() throws Exception{
		SoftAssert assertion11 = new SoftAssert();
		//Fillin description
		driver.findElement(By.xpath("//input[@aria-label='Unit *']")).click();
       	driver.findElement(By.xpath("//input[@aria-label='Unit *']")).sendKeys("l");
       	Thread.sleep(2000);
       	driver.findElement(By.xpath("(//button[text()='Complete'])[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]")).click();		
	}
	
	@Test(priority=16)
	public void VerifyingValuesFromPage1inPage2() throws Exception{
		waitTime(21000);	

		SoftAssert assertion11 = new SoftAssert();
		//Fillin description
		SoftAssert assertion12 = new SoftAssert();
		WebElement desccription=driver.findElement(By.xpath("//input[@aria-label='Description']"));
		String description1=desccription.getText();
		System.out.println(description1);
		//Internal Lot Number
		WebElement LotNumber=driver.findElement(By.xpath("//input[@aria-label='Internal Lot Number *']"));
		String LotNumber1=LotNumber.getText();
		System.out.println(LotNumber1);
       	//Date of Receipt 
       	WebElement DateofReceipt=driver.findElement(By.xpath("//input[@aria-label='Date of Receipt *']"));
   		String DateofReceipt1=DateofReceipt.getText();
   		System.out.println("Date of Receipt-->"+DateofReceipt1);
   		Thread.sleep(2000);
   		//Manufacturer
   		WebElement Manufacturer=driver.findElement(By.xpath("//input[@aria-label='Manufacturer *']"));
   		String Manufacturer1=Manufacturer.getText();
		System.out.println(Manufacturer1);
       	Thread.sleep(2000);
   		//Part Number
       	WebElement PartNumber=driver.findElement(By.xpath("//input[@aria-label='Part Number *']"));
       	String PartNumber1=PartNumber.getText();
		System.out.println(PartNumber1);
       	Thread.sleep(2000);
       	//quantity
       	WebElement quantity=driver.findElement(By.xpath("//input[@aria-label='Quantity *']"));
       	String quantity1=quantity.getText();
		System.out.println(quantity1);
       	//unit
		WebElement unit=driver.findElement(By.xpath("//input[@aria-label='Unit *']"));
		String unit1=unit.getText();
		System.out.println(unit1);
       	Thread.sleep(2000);
		
		/*assertion12.assertEquals("try22",(driver.findElements(By.className("sv_q_text_root")).get(0)).getAttribute("value"),"Bookshelf items its an error");
		assertion12.assertEquals("1234",(driver.findElements(By.className("sv_q_text_root")).get(1)).getAttribute("value"),"Bookshelf items its an error");
		assertion12.assertEquals("2020-03-14",(driver.findElements(By.className("sv_q_text_root")).get(2)).getAttribute("value"),"Bookshelf items its an error");
		assertion12.assertEquals("Vallab",(driver.findElements(By.className("sv_q_text_root")).get(3)).getAttribute("value"),"Bookshelf items its an error");
		assertion12.assertEquals("345",(driver.findElements(By.className("sv_q_text_root")).get(4)).getAttribute("value"),"Bookshelf items its an error");
		assertion12.assertEquals("90000",(driver.findElements(By.className("sv_q_text_root")).get(5)).getAttribute("value"),"Bookshelf items its an error");
		assertion12.assertEquals("l",(driver.findElements(By.className("sv_q_text_root")).get(6)).getAttribute("value"),"Bookshelf items its an error");
		assertion12.assertEquals("aarthi",(driver.findElements(By.className("sv_q_text_root")).get(7)).getAttribute("value"),"Bookshelf items its an error");*/

		assertion12.assertAll();

	}
	
	/*@Test(priority=17)
	public void ClickCompleteAfterSelectingRadioButtonOptionPage2() throws Exception{
		
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Packaging Intact']//following::input[@value='Yes']"))).click().perform();
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Packaging Slip']//following::input[@value='No']"))).click().perform();
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Partial Shipment']//following::input[@value='No']"))).click().perform();
		action.moveToElement(driver.findElement(By.xpath("//span[text()='QTY Match']//following::input[@value='Yes']"))).click().perform();
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Certificate']//following::input[@value='Yes']"))).click().perform();
		action.moveToElement(driver.findElement(By.xpath("//span[text()='MSDS on File']//following::input[@value='N/A']"))).click().perform();

	}
	
	@Test(priority=18)
	public void GiveExpirationDateComplete() throws Exception{
		SoftAssert assertion13 = new SoftAssert();
		//Fillin description
		action.moveToElement(driver.findElements(By.className("sv_q_text_root")).get(8)).click().sendKeys("01052021").sendKeys(Keys.ENTER).perform(); 
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"pl0\"]"))).click().perform();
		waitTime(4000);	
	}
	
	@Test(priority=19)
	public void GiveSpecComplete() throws Exception{
		SoftAssert assertion14 = new SoftAssert();
		//Fillin description
		action.moveToElement(driver.findElements(By.className("sv_q_text_root")).get(9)).click().sendKeys("abc").sendKeys(Keys.ENTER).perform(); 
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"pl0\"]"))).click().perform();
		waitTime(4000);	
	}
	
	@Test(priority=20)
	public void GiveInspectedByComplete() throws Exception{
		SoftAssert assertion14 = new SoftAssert();
		//Fillin description
		action.moveToElement(driver.findElements(By.className("sv_q_text_root")).get(10)).click().sendKeys("abc").sendKeys(Keys.ENTER).perform(); 
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"pl0\"]"))).click().perform();
		waitTime(4000);	
	}
	
	@Test(priority=21)
	public void GiveInspectedDate() throws Exception{
		SoftAssert assertion14 = new SoftAssert();
		//Fillin description
		action.moveToElement(driver.findElements(By.className("sv_q_text_root")).get(11)).click().sendKeys("10102021").sendKeys(Keys.ENTER).perform(); 
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"pl0\"]"))).click().perform();
		waitTime(4000);	
	}
	
	@Test(priority=22)
	public void GiveSample() throws Exception{
		SoftAssert assertion14 = new SoftAssert();
		//Fillin description
		action.moveToElement(driver.findElements(By.className("sv_q_radiogroup_control_item")).get(1)).click().perform(); 
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"pl0\"]"))).click().perform();
		waitTime(4000);	
	}
	
	@Test(priority=22)
	public void GiveReleased() throws Exception{
		SoftAssert assertion14 = new SoftAssert();
		//Fillin description
		action.moveToElement(driver.findElements(By.className("sv_q_radiogroup_control_item")).get(1)).click().perform(); 
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"pl0\"]"))).click().perform();
		waitTime(4000);	
	}
	
	@Test(priority=22)
	public void GiveReject() throws Exception{
		SoftAssert assertion14 = new SoftAssert();
		//Fillin description
		action.moveToElement(driver.findElements(By.className("sv_q_radiogroup_control_item")).get(2)).click().perform(); 
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"pl0\"]"))).click().perform();
		waitTime(4000);	
	}
	@Test(priority=22)
	public void ContinueAfterSample() throws Exception{
		SoftAssert assertion14 = new SoftAssert();
		//Fillin description
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"tempBatchSvae1\"]"))).click().perform(); 
		//action.moveToElement(driver.findElement(By.xpath("//*[@id=\"pl0\"]"))).click().perform();
		waitTime(4000);	
	}*/
	 @AfterClass
	   	public static void endTest()
	   	{
	   	report.endTest(test);
	   	report.flush();
	   	
	   	
	   	}
	
}
