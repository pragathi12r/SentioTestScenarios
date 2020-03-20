package SentioTestScenarios;



import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SentioEquipment {
	
  
  
  //String dayofuseequip;
  
static   String	EquipmentValidatorname="Equipmatrixdetail06";
  static   String	EquipmentBatchname="Equipment";
	
  
		static WebDriver driver = null;
		static Actions action = null;
		static WebDriverWait wait;
		static HSSFWorkbook workbook;
		static HSSFSheet sheet;
		static HSSFCell cell;
		private static String dateused;
	
		
		//login page
		
		 @Test (priority=1)
		 public static void Login() throws InterruptedException, IOException, ParseException{
				
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
	       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("WGT-17");
	       		
	       	//(tempoutofservice)1
	       		if(driver.findElements(By.xpath("(//div[@tabulator-field='temporarilyOutOfService'])[2]"))!= null){
	       			System.out.println("Element is Present");
	       			}
	       		
	       		else{
	       			System.out.println("Element is Absent");
	       			}
	       		
	       	//today Date
	        	   DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	        	   LocalDate now1 = LocalDate.now();  
	        	   System.out.println(dtf1.format(now1));  
	        	   
	       	//calibirationdate compare today date 2
	       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).click();
	       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).clear();
	       		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("CHL-08");
	       		
	       		WebElement calibirationdueon=driver.findElement(By.xpath(" (//div[@tabulator-field='calibirationDueOn'])[2]"));
	       		String calibirationdueon1=calibirationdueon.getText();
	       		String[] calibirationdueon2=calibirationdueon1.split("T");
	       		System.out.println(calibirationdueon2[0]);
	       		
	       		try 
	       	    {  
	       	      String datestr=calibirationdueon2[0];
	       	      DateFormat formatter; 
	       	      Date date1; 
	       	      formatter = new SimpleDateFormat("yyyy-MM-dd");
	       	      date1 = (Date)formatter.parse(datestr); 
	       	      System.out.println(date1);
	       	  
	       	  
	       	    } 
	       		catch (Exception e)
	       	    {
	       	    	
	       	    }
	       	
		 
	       //compare Date
	        	
	        	   
	        	   
		 //Dayofuse 
	       	driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).click();
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).clear();
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("PHC-02");
    		driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
    		
    		WebElement dayofuse=driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
        	String dayofuse1=dayofuse.getText();
        	System.out.println(dayofuse1);
    		
    	//switch
    		
    		if(dayofuse1 != null){
        	//status
        	WebElement Status=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedStatus'])[2]"));
        	String Status1=Status.getText();
        	System.out.println(Status1);
        	
        	//cleaned date
        	WebElement cleanedDate=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedDate'])[2]"));
       		String cleanedDate1=cleanedDate.getText();
       		String[] cleanedDate2=cleanedDate1.split("T");
       		System.out.println(cleanedDate2[0]);
       		
       		try 
       	    {  
       	      String datestr=cleanedDate2[0];
       	      DateFormat formatter; 
       	      Date date; 
       	      formatter = new SimpleDateFormat("yyyy-MM-dd");
       	      date = (Date)formatter.parse(datestr); 
       	      System.out.println(date);
       	    
       	   
       	    } 
       	    catch (Exception e)
       	    {
       	    	
       	    }
        	
    		}
    		
    		else {
    			
    			System.out.println("Next");
			
    		}
    		
    		//Dayofuse 
	       	driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).click();
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).clear();
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("SCL-02");
    		driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
    		
    		WebElement dayofuse2=driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
        	String dayofuse3=dayofuse2.getText();
        	System.out.println(dayofuse3);
    		

    		switch(dayofuse3){
    		
    		case "Yes":
    		
        	//status
        	WebElement Status=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedStatus'])[2]"));
        	String Status1=Status.getText();
        	System.out.println(Status1);
        	
        	//Used date and dirty hold 
        	
        	WebElement UsedDate=driver.findElement(By.xpath("(//div[@tabulator-field='usedDate'])[2]"));
        	String UsedDate1=UsedDate.getText();
       		String[] UsedDate2=UsedDate1.split("T");
       		System.out.println(UsedDate2[0]);
       		
       		try 
       	    {  
       	      String datestr=UsedDate2[0];
       	      DateFormat formatter; 
       	      Date dateused; 
       	      formatter = new SimpleDateFormat("yyyy-MM-dd");
       	      dateused = (Date)formatter.parse(datestr); 
       	      System.out.println(dateused);
       	    
       	   
       	    } 
       	    catch (Exception e)
       	    {
       	    	
       	    }
       		
       		WebElement dirtyHold=driver.findElement(By.xpath("(//div[@tabulator-field='daysforDirtyHold'])[2]"));
       		String dirtyHold1=dirtyHold.getText();
       		String[] dirtyHold2=dirtyHold1.split("T");
       		System.out.println(dirtyHold2[0]);
       		
       		
				
				
    		
    		//Add Date
       		
       		//compare date
       		
       		
       		
		 }
    		
    		/*//dayofuse
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).click();
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).clear();
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("AHU-04");
    		driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
		 
		 
    		WebElement dayofuse4=driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
        	String dayofuse5=dayofuse4.getText();
        	System.out.println(dayofuse5);	 
        	
        	switch(dayofuse5){
    		
    		case " ":
    		
    		
       		
        	//status
        	WebElement Status=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedStatus'])[2]"));
        	String Status1=Status.getText();
        	System.out.println(Status1);
        	
        	//cleaned date
        	WebElement cleanedDate4=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedDate'])[2]"));
       		String cleanedDate5=cleanedDate4.getText();
       		String[] cleanedDate6=cleanedDate5.split("T");
       		System.out.println(cleanedDate6[0]);
       		
       		try 
       	    {  
       	      String datestr=cleanedDate6[0];
       	      DateFormat formatter; 
       	      Date date; 
       	      formatter = new SimpleDateFormat("yyyy-MM-dd");
       	      date = (Date)formatter.parse(datestr); 
       	      System.out.println(date);
       	      
       	     
       	   
       	    } 
       	    catch (Exception e)
       	    {
       	    	
       	    	
       	    }
        	
       		
       	   
           break;
        	
    		case "No":
    			
    			System.out.println("Day of use is NO");
    			
    			break;
    			
    	 
		}
        	
        	//dayofuse
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).click();
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).clear();
    		driver.findElement(By.xpath("(//input[@id='searchText_sentio'])[1]")).sendKeys("OVN-01");
    		driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[2]"));
		 
		 
    		WebElement dayofuse6=driver.findElement(By.xpath("(//div[@tabulator-field='checkForDayOfUse'])[4]"));
        	String dayofuse7=dayofuse6.getText();
        	System.out.println(dayofuse7);	 
        	
        	switch(dayofuse5){
    		
    		case " ":
    		
    		
       		
        	//status
        	WebElement Status=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedStatus'])[4]"));
        	String Status1=Status.getText();
        	System.out.println(Status1);
        	
        	//cleaned date
        	WebElement cleanedDate6=driver.findElement(By.xpath("(//div[@tabulator-field='cleanedDate'])[4]"));
       		String cleanedDate7=cleanedDate6.getText();
       		String[] cleanedDate8=cleanedDate7.split("T");
       		System.out.println(cleanedDate8[0]);
       		
       		try 
       	    {  
       	      String datestr=cleanedDate8[0];
       	      DateFormat formatter; 
       	      Date date; 
       	      formatter = new SimpleDateFormat("yyyy-MM-dd");
       	      date = (Date)formatter.parse(datestr); 
       	      System.out.println(date);
       	      
       	     
       	   
       	    } 
       	    catch (Exception e)
       	    {
       	    	
       	    	
       	    }
        	
       	   
       	   
           break;
        	
    		case "No":
    			
    			System.out.println("Day of use is NO");
    			
    			break;
    			
    	 
		}*/
        	
}
}
	       		