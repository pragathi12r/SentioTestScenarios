package SentioTestScenarios;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CompareInputMterial {
	
	static ExtentTest test; 
	static ExtentReports report;
	static String  filename="12March3";
	
			@BeforeClass
			public static void startTest()
		{
			Date d=new Date();
	        String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
		//report = new ExtentReports(System.getProperty("user.dir")+"/Reports/ExtentReportResults.html");
		report=new ExtentReports(System.getProperty("user.dir")+"/ExtReport/Compare_Inputmaterial"+fileName,true, DisplayOrder.NEWEST_FIRST);
		test = report.startTest("Compare Input Material");
		}
			
			@Test (priority=1)
			 public static void Login() throws InterruptedException, IOException, ParseException{
						
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/drivers/chromedriver");
			
				ChromeDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				String URL="http://sentio.pagemajik.info/";
				driver.get(URL);
				test.log(LogStatus.PASS, "Navigated to the specified URL");
				
			
			//login
				String username="aarthi";
				String password="sample@123";
				
				
		        driver.findElement(By.cssSelector("#_58_login")).sendKeys(username);
		        driver.findElement(By.cssSelector("#_58_password")).sendKeys(password);
		        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
		        Thread.sleep(5000);
		        System.out.println("Login Successfully "+username);
		        test.log(LogStatus.PASS, "Login Successfully Username is "+username);
		        
		        //verifyElement
		        if(driver.findElements(By.xpath("(//a[text()='Batch Records '])[1]")).size()>0){
		        	System.out.println("Verify Element(Batch Record) is Presrent");
		        	test.log(LogStatus.INFO, "Verify Element(Batch Record) is present");
		        	
		        }
		        else{
		        	System.out.println("Verify Element(Batch Record) is Absent");
		        	test.log(LogStatus.INFO, "Verify Element(Batch Record) is Not present");
		        }
		        
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
		        
		       

		        
		      //threedotline
		        driver.findElement(By.xpath("(//img[@class='cat_sort'])[1]")).click();
		        driver.findElement(By.xpath("(//select[@class='client-list'])[1]")).click();
		        driver.findElement(By.xpath("(//option[@label='Control Data'])[1]")).click();
		        

	       	//Double click the button to launch an alertbox
	       		Actions action = new Actions(driver);
	       		WebElement link =driver.findElement(By.xpath("(//div[contains(text(),'Input Material')])"));
	       		action.doubleClick(link).perform();
	       		Thread.sleep(2000);
	       		

		        List<WebElement> myList=driver.findElements(By.xpath("//div[@class='tabulator-cell'][@tabulator-field='RawMaterial']"));

		        //myList contains all the web elements
		        List<String> all_elements_text=new ArrayList<>();

		        for(int i=0; i<myList.size(); i++){

		            //loading text of each element in to array all_elements_text
		            all_elements_text.add(myList.get(i).getText());

		            //to print directly
		            System.out.println(myList.get(i).getText());
		            
		           // test.log(LogStatus.INFO,""+myList.get(i).getText());
		        }
		        for(String item1:all_elements_text){
		        	System.out.println("from set "+item1);
		        	//test.log(LogStatus.INFO,""+item1);
		        }
		        
	       		//masterrecord
		        driver.findElement(By.xpath("//a[text()='Master Records ']")).click();
		        test.log(LogStatus.INFO,"Back to Master Record");
		        
		        //search masterrecord
		        
		        driver.findElement(By.xpath("//*[contains(text(),'"+ filename +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
	        	driver.findElements(By.className("item")).get(5).click();  //click create batch
	        	Thread.sleep(5000);
	        	
		        //create batch ID
	        	String s = RandomStringUtils.randomAlphanumeric(5);
	        	driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']")).sendKeys(s);
		       	WebElement getID =driver.findElement(By.xpath("//input[@placeholder='give this document a unique ID']"));
		       	String getID1=getID.getAttribute("value");
		       	test.log(LogStatus.INFO, "Unique ID-->"+getID1);
		       	
		       	//date
	        	driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).click();
				driver.findElement(By.xpath("//*[@class='type_category2 category_type_1']")).sendKeys("11/08/2021"); //effective date
				Thread.sleep(2000);
				/*WebElement dateenter =driver.findElement(By.xpath("//*[@class='type_category2 category_type_1"));
		       	String dateenter1=dateenter.getText();
		       	test.log(LogStatus.INFO, "Effective Date-->"+dateenter1);*/
				
				driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click(); //continue
				Thread.sleep(3000);	
				
				//assign role
				driver.findElement(By.xpath("//*[contains(text(), 'pagemajikadmin')]/parent::tr/td/input")).click(); //assign admin
				driver.findElement(By.xpath("//*[contains(text(), 'qa')]/parent::tr/td/input")).click(); //assign qa
				Thread.sleep(3000);
				driver.findElement(By.xpath("//button[contains(text(), 'Assign')]")).click(); //click assign
				Thread.sleep(8000);
				
				//gettext
				
				WebElement getetxtbatch=driver.findElement(By.xpath("//div[text()='Batch has been successfully created! ']"));
				String getetxtbatch1="Batch has been successfully created!";
				System.out.println(getetxtbatch1);
				test.log(LogStatus.INFO, "Batch Message-->"+getetxtbatch1);
				Thread.sleep(5000);
				
				//signout
				driver.findElement(By.xpath("(//span[@class='nav-item-label'])[7]")).click();
				driver.findElement(By.xpath("(//span[text()=' Sign Out '])[1]")).click();
				Thread.sleep(2000);
				
				//url reload
				driver.navigate().refresh();
				
				//login
				String username1="qa";
				String password1="quality@123";
				
				
		        driver.findElement(By.cssSelector("#_58_login")).sendKeys(username1);
		        driver.findElement(By.cssSelector("#_58_password")).sendKeys(password1);
		        driver.findElement(By.cssSelector("#_58_fm > button")).click();    
		        Thread.sleep(5000);
		        System.out.println("Login Successfully "+username1);
		        test.log(LogStatus.PASS, "Login Successfully Username is "+username1);
		        
		        //verifyElement
		        if(driver.findElements(By.xpath("(//a[text()='Batch Records '])[1]")).size()>0){
		        	System.out.println("Verify Element(Batch Record) is Presrent");
		        	test.log(LogStatus.INFO, "Verify Element(Batch Record) is present");
		        	
		        }
		        else{
		        	System.out.println("Verify Element(Batch Record) is Absent");
		        	test.log(LogStatus.INFO, "Verify Element(Batch Record) is Not present");
		        }
		        
		        //serach value
		        
		        driver.findElement(By.xpath("//*[contains(text(),'"+ getID1 +"')]")).click();  //Click temporary file
	        	Thread.sleep(5000);
				
	        	//execute batch
	        	driver.findElements(By.className("item")).get(1).click();  //click execute batch
	        	Thread.sleep(5000);
	        	
	        	//dropdown
	        	driver.findElement(By.xpath("//select[@aria-label='RawMaterial']")).click();
	        	 List<WebElement> myList1=driver.findElements(By.xpath("//select[@aria-label='RawMaterial']//following::option"));

			        
			        Set<String> all_elements_text1=new HashSet<>();

			        for(int i=0; i<myList1.size(); i++){

			        	if(!myList1.get(i).getText().equals("Choose...")){
			            all_elements_text1.add(myList1.get(i).getText());
			        	}
			            
			           
			        }
			        for(String item:all_elements_text1){
			        	System.out.println("from set "+item);
			        	//test.log(LogStatus.INFO,""+item);
			        }
			        
			       
				//compare values
			        
			      
			}
			
			 @AfterClass
			   	public static void endTest()
			   	{
			   	report.endTest(test);
			   	report.flush();
			   	
			   	
			   	}
	

}
