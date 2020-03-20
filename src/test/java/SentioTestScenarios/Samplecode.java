package SentioTestScenarios;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Samplecode {
	static ExtentTest test;
	static ExtentReports report;
	@BeforeClass
	public static void startTest()
	{
		Date d=new Date();
        String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
	//report = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReportResults.html");
	report=new ExtentReports(System.getProperty("user.dir")+"/ExtReport/"+fileName,true, DisplayOrder.NEWEST_FIRST);
	test = report.startTest("ExtentDemo");
	}
		static WebDriver driver = null;
	
	public static void Status_and_Cleaneddate() throws InterruptedException, IOException, ParseException{
		
		 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
		
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://sentio.pagemajik.info/");
			
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
      			
      	//day of use
      			   
		       	WebElement dayofuse2=driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
		       	String dayofuse3=dayofuse2.getText();
		       	System.out.println(dayofuse3);	
		       	test.log(LogStatus.INFO, "The day of use is "+dayofuse3);
      	
      	
      		
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
			test.log(LogStatus.FAIL, "Cleaned date-->"+cleaneddate3[0]+" less than current date"+currentDate);
		}else{
			System.out.println("Cleaned date-->"+cleaneddate3[0]+" is greater than current date"+currentDate);
			test.log(LogStatus.PASS, "Cleaned date-->"+cleaneddate3[0]+" greater than current date"+currentDate);
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

   	      

   	       // Pass the Input Date

   	       String inputDateFromUI="useddate3[0]";

   			//Convert the string to date format

   			Date inputDate=dateFormat.parse(inputDateFromUI);

   	        LocalDateTime localDateTime = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

   	        long  inputValue=0;

   	        localDateTime = localDateTime.plusYears(0).plusMonths(0).plusDays(inputValue);

   	        Date inputDatePlueValue = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

   	        System.out.println("InputDate-->"+dateFormat.format(inputDatePlueValue));

   	        

   	        // compare InputDate with current date 

   	        if(dateFormat.format(inputDatePlueValue).equals(dateFormat.format(currentDate1)))

   	        {

   	        	System.out.println("Input Date-->"+dateFormat.format(inputDatePlueValue)+" is equal with current date");

   	        }else if(dateFormat.format(inputDatePlueValue).compareTo(dateFormat.format(currentDate1))>0){

   	         	System.out.println("Input Date-->"+dateFormat.format(inputDatePlueValue)+" is greater than current date");

   	        }else {

   	        	System.out.println("Input Date-->"+dateFormat.format(inputDatePlueValue)+" is less than current date");

   	        }

   	     
      }
      driver.close();
	 }

	 @AfterClass
	   	public static void endTest()
	   	{
	   	report.endTest(test);
	   	report.flush();
	   	}
	   	}
