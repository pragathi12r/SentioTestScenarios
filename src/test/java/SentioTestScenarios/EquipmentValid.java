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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.DateFormatConverter;
import org.openqa.selenium.By;
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

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class EquipmentValid {
	
	
	static   String	EquipmentValidatorname="Equipmatrixdetail06";
	  static   String	EquipmentBatchname="Equipment";
		
	  static ExtentTest test;
		static ExtentReports report;
		@BeforeClass
		public static void startTest()
		{
			Date d=new Date();
	        String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
		//report = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReportResults.html");
		report=new ExtentReports(System.getProperty("user.dir")+"/ExtReport/"+fileName,true, DisplayOrder.NEWEST_FIRST);
		test = report.startTest("Equipment Validator");
		}
			static WebDriver driver = null;
			static Actions action = null;
			static WebDriverWait wait;
			static HSSFWorkbook workbook;
			static HSSFSheet sheet;
			static HSSFCell cell;
			
			
			//login page
		
			@Test (priority=1)
			 public static void Login_TempOutOfService() throws InterruptedException, IOException, ParseException{
					
				 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
				
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					driver.get("http://sentio.pagemajik.info/");
					test.log(LogStatus.PASS, "Navigated to the specified URL");
					
					//loginpage
			        driver.findElement(By.cssSelector("#_58_login")).sendKeys("aarthi");
			        driver.findElement(By.cssSelector("#_58_password")).sendKeys("sample@123");
			        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
			        Thread.sleep(5000);
			        System.out.println("Login Successfully");
			        test.log(LogStatus.PASS, "Login Successfully");
			        
			        //batchrecord
			        driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon

		       	//Double click the button to launch an alertbox
		       		Actions action = new Actions(driver);
		       		WebElement link =driver.findElement(By.xpath("(//div[contains(text(),'Equipment')])[4]"));
		       		action.doubleClick(link).perform();
		       		Thread.sleep(2000);
		       		
		       	//searchbutton
		       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).click();
		       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("WGT-17");
		       		WebElement equpno1=driver.findElement(By.xpath("(//div[@tabulator-field='EquipmentNo'])[2]"));
		       		String equpno=equpno1.getText();
		       		System.out.println(equpno);
		       		test.log(LogStatus.INFO, "The Equipment No is "+equpno);
		       		
		       		
		       	//1 tempoutofservice
		       		if(driver.findElements(By.xpath("(//div[@tabulator-field='temporarilyOutOfService'])[2]")).size()>0){
		       		WebElement Tempoutofservice=driver.findElement(By.xpath("(//div[@tabulator-field='temporarilyOutOfService'])[2]"));
		       	    String	Tempoutofservice1=Tempoutofservice.getText();
		       	    System.out.println(Tempoutofservice1);
		       	    //test.log(LogStatus.PASS, "The value of Tempoutofservice is"+Tempoutofservice1);
		       		System.out.println("The value is Empty");	
		       		
		       		
		       		switch(Tempoutofservice1){
		       		case " ":
		       			
		       			test.log(LogStatus.PASS, "The tempoutofservice value is Empty");
		       			break;
		       		
		       		case "No":
		       			
		       			test.log(LogStatus.FAIL, "The Equipment is Not consider");
		       			break;
		       		
		       		}
		       		
		       			}
		       		
		       		else{
		       			System.out.println("Element is Absent");
		       			test.log(LogStatus.FAIL, "The value of Tempoutofservice is Not Available");
		       			}	
		       		
		       		
		       		driver.close();

			 }
			 
			 @Test (priority=2)
			 public static void Calibirationdue() throws InterruptedException, IOException, ParseException{
					
				 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
				
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					driver.get("http://sentio.pagemajik.info/");
					test.log(LogStatus.PASS, "Navigated to the specified URL");
					Thread.sleep(60000);
					
					//loginpage
			        driver.findElement(By.cssSelector("#_58_login")).sendKeys("aarthi");
			        driver.findElement(By.cssSelector("#_58_password")).sendKeys("sample@123");
			        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
			        Thread.sleep(5000);
			        System.out.println("Login Successfully");
			        
			        //batchrecord
			        driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon

		       	//Double click the button to launch an alertbox
		       		Actions action = new Actions(driver);
		       		WebElement link =driver.findElement(By.xpath("(//div[contains(text(),'Equipment')])[4]"));
		       		action.doubleClick(link).perform();
		       		
		      
		       	
		       	//searchbutton
		       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).click();
		       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("CHL-08");
		       		WebElement equpno3=driver.findElement(By.xpath("(//div[@tabulator-field='EquipmentNo'])[2]"));
		       		String equpno4=equpno3.getText();
		       		System.out.println(equpno4);
		       		test.log(LogStatus.INFO, "The Equipment No is "+equpno4);
		       		
		       	//day of use
	       			   
		       		WebElement dayofuse=driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
		       		String dayofuse1=dayofuse.getText();
		       		System.out.println(dayofuse1);
		       		
		       		switch(dayofuse1){
		       		case " ":
		       			test.log(LogStatus.INFO, "The day of use is Empty"+dayofuse1);
		       			break;
		       			
		       		case "Yes":
		       			test.log(LogStatus.INFO, "The day of use is  "+dayofuse1);
		       			break;
		       			
		       		case "No" :
		       			test.log(LogStatus.INFO, "The day of use is  "+dayofuse1);
		       			break;
		       		}
		       	
		       	
		       	//to get calibiration date
		       		
		       		WebElement calibirationdueon=driver.findElement(By.xpath(" (//div[@tabulator-field='calibirationDueOn'])[2]"));
		       		String calibirationdueon1=calibirationdueon.getText();
		       		String[] calibirationdueon2=calibirationdueon1.split("T");
		       		System.out.println("Splitted date-->"+calibirationdueon2[0]);
		       		
		        		//comparedate
		       		//to get the system current date
		       		String currentDate=java.time.LocalDate.now().toString();
		       		if(calibirationdueon2[0].equals(currentDate))
		    		{
		    			System.out.println("Calibration date is equal to current system date");
		    			test.log(LogStatus.PASS, "Calibration date is equal to current system date");
		    		}else if(calibirationdueon2[0].compareTo(currentDate)<0){
		    			System.out.println("Calibration date-->"+calibirationdueon2[0]+" less than current date"+currentDate);
		    			test.log(LogStatus.FAIL, "Calibration date-->"+calibirationdueon2[0]+" less than current date-->"+currentDate);
		    		}else{
		    			System.out.println("Calibration date-->"+calibirationdueon2[0]+" is greater than current date"+currentDate);
		    			test.log(LogStatus.PASS, "Calibration date-->"+calibirationdueon2[0]+" is greater than current date-->"+currentDate);
		    		}
		    		driver.close();
			 
			 }
			 
			 @Test (priority=3)
			 public static void Status_and_Cleaneddate() throws InterruptedException, IOException, ParseException{
					
				 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
				
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					driver.get("http://sentio.pagemajik.info/");
					test.log(LogStatus.PASS, "Navigated to the specified URL");
					
					//loginpage
			        driver.findElement(By.cssSelector("#_58_login")).sendKeys("aarthi");
			        driver.findElement(By.cssSelector("#_58_password")).sendKeys("sample@123");
			        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
			        Thread.sleep(5000);
			        System.out.println("Login Successfully");
			        
			        //batchrecord
			        driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon

		       	//Double click the button to launch an alertbox
		       		Actions action = new Actions(driver);
		       		WebElement link =driver.findElement(By.xpath("(//div[contains(text(),'Equipment')])[4]"));
		       		action.doubleClick(link).perform();
		       		
		       		
		       	//searchbutton
		       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).click();
		       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("SCL-02");
		       		WebElement equpno5=driver.findElement(By.xpath("(//div[@tabulator-field='EquipmentNo'])[2]"));
		       		String equpno6=equpno5.getText();
		       		System.out.println(equpno6);
		       		test.log(LogStatus.INFO, "The Equipment No is "+equpno6);
		       			
		       	//day of use
		       			   
				       	WebElement dayofuse2=driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
				       	String dayofuse3=dayofuse2.getText();
				       	System.out.println(dayofuse3);	
				       	switch(dayofuse3){
			       		case " ":
			       			test.log(LogStatus.INFO, "The day of use is Empty"+dayofuse3);
			       			break;
			       			
			       		case "Yes":
			       			test.log(LogStatus.INFO, "The day of use is  "+dayofuse3);
			       			break;
			       			
			       		case "No" :
			       			test.log(LogStatus.INFO, "The day of use is  "+dayofuse3);
			       			break;
			       		
			       		}
		       	
		       		
		       	//to get status 
		      
		       	WebElement status1=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedStatus'])[2]"));
		       	String status2=status1.getText();
		       	System.out.println(status2);
		       	test.log(LogStatus.INFO, "Status Value = "+status2);
		       
		       	//to get cleaned date
		       String a ="Cleaned";
		       String b ="In Use";
		       
		       switch(status2)	{
		       
		       case "Cleaned":
		       	WebElement cleaneddate1=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedDate'])[2]"));
		       	String cleaneddate2=cleaneddate1.getText();
		       	String[] cleaneddate3=cleaneddate2.split("T");
		       	System.out.println(cleaneddate3[0]);
		       	
		    	//comparedate
	       		//to get the system current date
	       		String currentDate=java.time.LocalDate.now().toString();
	       		if(cleaneddate3[0].equals(currentDate))
	    		{
	    			System.out.println("Cleaned date is equal to current system date");
	    			test.log(LogStatus.PASS, "Cleaned date is equal to current system date");
	    		}else if(cleaneddate3[0].compareTo(currentDate)<0){
	    			System.out.println("Cleaned date-->"+cleaneddate3[0]+" less than current date"+currentDate);
	    			test.log(LogStatus.FAIL, "Cleaned date-->"+cleaneddate3[0]+" less than current date-->"+currentDate);
	    		}else{
	    			System.out.println("Cleaned date-->"+cleaneddate3[0]+" is greater than current date"+currentDate);
	    			test.log(LogStatus.PASS, "Cleaned date-->"+cleaneddate3[0]+" greater than current date-->"+currentDate);
	    		}
	    		
		       	break;
		       	
		       case "In Use":
		    	   
		    	 //to get used date	
		       		WebElement useddate=driver.findElement(By.xpath("(//div[@tabulator-field='usedDate'])[2]"));
		       		String useddate1=useddate.getText();
		       		String[] useddate3=useddate1.split("T");
		       		System.out.println(useddate3[0]);
		       		
		      //to get dirty hold
		       		
		       		WebElement dirtyhold=driver.findElement(By.xpath("(//div[@tabulator-field='daysforDirtyHold'])[2]"));
		       		String dirtyhold1=dirtyhold.getText();
		       		System.out.println(dirtyhold1);
		       
		       //add two date
		       		final String DATE_FORMAT = "yyyy-MM-dd";

		   	 	    final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

		   			// Get current date from system 

		   	        Date systemDate = new Date(); 

		   	        LocalDateTime currentDateConvert = systemDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		   	        Date currentDate1 = Date.from(currentDateConvert.atZone(ZoneId.systemDefault()).toInstant());

		   	       System.out.println("Current Date-->"+dateFormat.format(currentDate1));
		   	       test.log(LogStatus.INFO, "Current Date -->"+currentDate1);

		   	      

		   	       // Pass the Input Date

		   	       String inputDateFromUI=useddate3[0];

		   			//Convert the string to date format

		   			Date inputDate=dateFormat.parse(inputDateFromUI);

		   	        LocalDateTime localDateTime = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		   	        long  inputValue=0;

		   	        localDateTime = localDateTime.plusYears(0).plusMonths(0).plusDays(inputValue);

		   	        Date inputDatePlueValue = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		   	        System.out.println("InputDate-->"+dateFormat.format(inputDatePlueValue));
		   	        test.log(LogStatus.INFO, "InputDate--> "+inputDatePlueValue);
		   	        

		   	        // compare InputDate with current date 

		   	        if(dateFormat.format(inputDatePlueValue).equals(dateFormat.format(currentDate1)))

		   	        {

		   	        	System.out.println("Input Date-->"+dateFormat.format(inputDatePlueValue)+" is equal with current date");
		   	        	test.log(LogStatus.PASS, "Input date-->"+inputDatePlueValue+" is equal to current system date-->"+currentDate1);

		   	        }else if(dateFormat.format(inputDatePlueValue).compareTo(dateFormat.format(currentDate1))>0){

		   	         	System.out.println("Input Date-->"+dateFormat.format(inputDatePlueValue)+" is greater than current date");
		   	         	test.log(LogStatus.PASS, "Input date-->"+inputDatePlueValue+" greater than current date-->"+currentDate1);

		   	        }else {

		   	        	System.out.println("Input Date-->"+dateFormat.format(inputDatePlueValue)+" is less than current date");
		   	        	test.log(LogStatus.FAIL, "Input date-->"+inputDatePlueValue+" less than current date-->"+currentDate1);

		   	        }

		      
	    		
		       }
		       driver.close();
			 }
			 
		       @Test (priority=4)
				 public static void Status1_and_Cleaneddate1() throws InterruptedException, IOException, ParseException{
						
					 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
					
						driver = new ChromeDriver();
						driver.manage().window().maximize();
						driver.get("http://sentio.pagemajik.info/");
						test.log(LogStatus.PASS, "Navigated to the specified URL");
						
						//loginpage
				        driver.findElement(By.cssSelector("#_58_login")).sendKeys("aarthi");
				        driver.findElement(By.cssSelector("#_58_password")).sendKeys("sample@123");
				        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
				        Thread.sleep(5000);
				        System.out.println("Login Successfully");
				        
				        //batchrecord
				        driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon

			       	//Double click the button to launch an alertbox
			       		Actions action = new Actions(driver);
			       		WebElement link =driver.findElement(By.xpath("(//div[contains(text(),'Equipment')])[4]"));
			       		action.doubleClick(link).perform();
			       		       			
			       	
			       	
			       	//searchbutton
			       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).click();
			       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("AHU-04");
			       		WebElement equpno7=driver.findElement(By.xpath("(//div[@tabulator-field='EquipmentNo'])[2]"));
			       		String equpno8=equpno7.getText();
			       		System.out.println(equpno8);
			       		test.log(LogStatus.INFO, "The Equipment No is "+equpno8);
			       		
			       	//day of use
		       			   
				       	WebElement dayofuse4=driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
				       	String dayofuse5=dayofuse4.getText();
				       	System.out.println(dayofuse5);	
				       	switch(dayofuse5){
			       		case " ":
			       			test.log(LogStatus.INFO, "The day of use is Empty"+dayofuse5);
			       			break;
			       			
			       		case "Yes":
			       			test.log(LogStatus.INFO, "The day of use is  "+dayofuse5);
			       			break;
			       		
			       		case "No" :
			       			test.log(LogStatus.INFO, "The day of use is  "+dayofuse5);
			       			break;
			       		
			       		}	
			       		
			       	//to get status 
			      
			       	WebElement status3=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedStatus'])[2]"));
			       	String status4=status3.getText();
			       	System.out.println(status4);
			       	test.log(LogStatus.INFO,"Status Value = "+status4);
			       
			       	//to get cleaned date
			       String a ="Cleaned";
			       String b ="In Use";
			       
			       switch(status4)	{
			       
			       case "Cleaned":
			       	WebElement cleaneddate4=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedDate'])[2]"));
			       	String cleaneddate5=cleaneddate4.getText();
			       	String[] cleaneddate6=cleaneddate5.split("T");
			       	System.out.println(cleaneddate6[0]);
			       	
			       	//cleanhold
			       	 WebElement cleanhold=driver.findElement(By.xpath("(//div[@tabulator-field='daysforCleanHold'])[2]"));
			       	 String cleanhold1=cleanhold.getText();
			       	 System.out.println(cleanhold1);
			       	
			    	//add two date
		       		final String DATE_FORMAT = "yyyy-MM-dd";

		   	 	    final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

		   			// Get current date from system 

		   	        Date systemDate = new Date(); 

		   	        LocalDateTime currentDateConvert = systemDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		   	        Date currentDate2 = Date.from(currentDateConvert.atZone(ZoneId.systemDefault()).toInstant());

		   	       System.out.println("Current Date-->"+dateFormat.format(currentDate2));
				   test.log(LogStatus.INFO, "Current Date--> "+currentDate2);
		   	      

		   	       // Pass the Input Date

		   	       String inputDateFromUI=cleaneddate6[0];

		   			//Convert the string to date format

		   			Date inputDate=dateFormat.parse(inputDateFromUI);

		   	        LocalDateTime localDateTime = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		   	        long  inputValue=0;

		   	        localDateTime = localDateTime.plusYears(0).plusMonths(0).plusDays(inputValue);

		   	        Date inputDatePlueValue1 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		   	        System.out.println("InputDate-->"+dateFormat.format(inputDatePlueValue1));
		   	        test.log(LogStatus.INFO, "InputDate--> "+inputDatePlueValue1);

		   	        

		   	        // compare InputDate with current date 

		   	        if(dateFormat.format(inputDatePlueValue1).equals(dateFormat.format(currentDate2)))

		   	        {

		   	        	System.out.println("Input Date-->"+dateFormat.format(inputDatePlueValue1)+" is equal with current date");
		   	        	test.log(LogStatus.PASS, "Input date-->"+inputDatePlueValue1+"is equal to current system date->"+currentDate2);


		   	        }else if(dateFormat.format(inputDatePlueValue1).compareTo(dateFormat.format(currentDate2))>0){

		   	         	System.out.println("Input Date-->"+dateFormat.format(inputDatePlueValue1)+" is greater than current date");
		   	         	test.log(LogStatus.PASS, "Input date-->"+inputDatePlueValue1+" greater than current date-->"+currentDate2);

		   	        }else {

		   	        	System.out.println("Input Date-->"+dateFormat.format(inputDatePlueValue1)+" is less than current date");
		   	        	test.log(LogStatus.FAIL, "Input date-->"+inputDatePlueValue1+" less than current date-->"+currentDate2);

		   	        }

		    		
			       	break;
			       	
			       case "In Use":
			    	   
			    	 //to get used date	
			       		WebElement useddate4=driver.findElement(By.xpath("(//div[@tabulator-field='usedDate'])[2]"));
			       		String useddate5=useddate4.getText();
			       		String[] useddate6=useddate5.split("T");
			       		System.out.println(useddate6[0]);
			       		
			      //to get dirty hold
			       		
			       		WebElement dirtyhold=driver.findElement(By.xpath("(//div[@tabulator-field='daysforDirtyHold'])[2]"));
			       		String dirtyhold1=dirtyhold.getText();
			       		System.out.println(dirtyhold1);
			       
			       //add two date
		       		final String DATE_FORMAT1 = "yyyy-MM-dd";

		   	 	    final DateFormat dateFormat1 = new SimpleDateFormat(DATE_FORMAT1);

		   			// Get current date from system 

		   	        Date systemDate1 = new Date(); 

		   	        LocalDateTime currentDateConvert1 = systemDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		   	        Date currentDate3 = Date.from(currentDateConvert1.atZone(ZoneId.systemDefault()).toInstant());

		   	       System.out.println("Current Date-->"+dateFormat1.format(currentDate3));
		   	    test.log(LogStatus.INFO, "Current Date "+currentDate3);
		   	      

		   	       // Pass the Input Date

		   	       String inputDateFromUI1=useddate6[0];

		   			//Convert the string to date format

		   			Date inputDate1=dateFormat1.parse(inputDateFromUI1);

		   	        LocalDateTime localDateTime1 = inputDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		   	        long  inputValue1=0;

		   	        localDateTime = localDateTime1.plusYears(0).plusMonths(0).plusDays(inputValue1);

		   	        Date inputDatePlueValue2 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		   	        System.out.println("InputDate-->"+dateFormat1.format(inputDatePlueValue2));
		   	     test.log(LogStatus.INFO, "InputDate "+inputDatePlueValue2);
		   	        

		   	        // compare InputDate with current date 

		   	        if(dateFormat1.format(inputDatePlueValue2).equals(dateFormat1.format(currentDate3)))

		   	        {

		   	        	System.out.println("Input Date-->"+dateFormat1.format(inputDatePlueValue2)+" is equal with current date");
		   	        	test.log(LogStatus.PASS, "Input date-->"+inputDatePlueValue2+" is equal to current system date-->"+currentDate3);

		   	        }else if(dateFormat1.format(inputDatePlueValue2).compareTo(dateFormat1.format(currentDate3))>0){

		   	         	System.out.println("Input Date-->"+dateFormat1.format(inputDatePlueValue2)+" is greater than current date");
		   	         	test.log(LogStatus.PASS, "Input date-->"+inputDatePlueValue2+" greater than current date-->"+currentDate3);

		   	        }else {

		   	        	System.out.println("Input Date-->"+dateFormat1.format(inputDatePlueValue2)+" is less than current date");
		   	        	test.log(LogStatus.FAIL, "Input date-->"+inputDatePlueValue2+" less than current date-->"+currentDate3);

		   	        }

			       	
				 }
			       driver.close();
		       		
			 }
		       
		    /*  public static String getScreenshot(WebDriver driver)
		   	{
		   		TakesScreenshot ts=(TakesScreenshot) driver;
		   		
		   		File src=ts.getScreenshotAs(OutputType.FILE);
		   		
		   		String path=System.getProperty("user.dir")+"/Screenshot/march11"+System.currentTimeMillis()+".png";
		   		
		   		File destination=new File(path);
		   		
		   		try 
		   		{
		   			FileUtils.copyFile(src, destination);
		   		} catch (IOException e) 
		   		{
		   			System.out.println("Capture Failed "+e.getMessage());
		   		}
		   		
		   		return path;
		   	}*/
		       
		       @AfterClass
		   	public static void endTest()
		   	{
		   	report.endTest(test);
		   	report.flush();
		   	
		   	
		   	}
		   	}

			 
 


