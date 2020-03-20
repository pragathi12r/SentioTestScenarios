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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MasterRecordUploadAssets {
	static WebDriver driver = null;
	static Actions action = null;
//	static SoftAssert assertion;
	static WebDriverWait wait;
	static HSSFWorkbook workbook;
	static HSSFSheet sheet;
	static HSSFCell cell;
	
	   static File src=new File(System.getProperty("user.dir") + "/ExcelData/Master Record - Form Builder.xls"); 	
   
	   static String filename = "TestData632";
	   static String filename1 = "TestData621";
	   static String filename2 = "TestData624";


	@Test (priority=1)
	public static void ContinueWithoutUploading1 () throws InterruptedException, IOException {	
		
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1);
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
		Thread.sleep(3000);
  		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
   		Thread.sleep(2000);
   		Close();
        }
	}
	
	@Test (priority=2)  
	public static void ContinueWithUpload2 () throws InterruptedException, IOException {
		SoftAssert assertion1 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
		Login();
		driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
		driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
		
	//Fill basic details
		cell = sheet.getRow(i).getCell(6);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(7);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
		templateCategory.selectByVisibleText(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(8);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
		templateType.selectByVisibleText(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(9);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(10);
       cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
		//save
		driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
		//upload assert
		cell = sheet.getRow(i).getCell(11);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
		Thread.sleep(5000);
		
		String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
		driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
		Thread.sleep(10000);
		WebElement uploadedfile1 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
		String uploadedfile1text = uploadedfile1.getText();
		String expectuploadedfile1text = "download.jpeg";
		AssertJUnit.assertEquals(expectuploadedfile1text, uploadedfile1text);  
		assertion1.assertAll(); 
		Thread.sleep(2000);
		Close();
		}
	}
	
	@Test (priority=3)  
	public static void UploadTheSameFile3 () throws InterruptedException, IOException {	
		SoftAssert assertion3 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1);
        for(int i=2; i<=sheet.getLastRowNum(); i++)
        {	
		Login();
		driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
		driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
		
	//Fill basic details
		cell = sheet.getRow(i).getCell(12);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(13);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
		templateCategory.selectByVisibleText(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(14);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
		templateType.selectByVisibleText(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(15);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
		cell = sheet.getRow(i).getCell(16);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
		//save
		driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
		//upload assert1
		cell = sheet.getRow(i).getCell(17);
        cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
		Thread.sleep(2000);
		
		String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
		driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
		Thread.sleep(3000);
		
		//upload assert2
		cell = sheet.getRow(i).getCell(18);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
		Thread.sleep(5000);
				
		String file1 = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
		driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file1); //upload file
		Thread.sleep(10000);		
		
		/*String savecontent1 = driver.findElement(By.xpath("//*[@id=\"toast-message\"]/div/div")).getText();
		System.out.println(savecontent1);
		boolean expectsavecontent1 = savecontent1.equals("File already uploaded."); 
		assertion3.assertTrue(expectsavecontent1, savecontent1);
		assertion3.assertAll(); */ 

		Thread.sleep(3000);
   		driver.findElement(By.xpath("//*[@class=\"sentio_continue continue_assets\"]")).click(); // click continue
  		Thread.sleep(2000);
   		Close();
		}
	}
	
	@Test (priority=4)  
	public static void UploadTheSameFileAfterSaveAndOpen4 () throws InterruptedException, IOException {	
		SoftAssert assertion4 = new SoftAssert();
		FileInputStream finput = new FileInputStream(src);
        workbook = new HSSFWorkbook(finput);
        sheet= workbook.getSheetAt(1);
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
		Thread.sleep(2000);
		
		String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
		driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
		Thread.sleep(5000);
   		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
   		Thread.sleep(1000);
   		driver.findElement(By.xpath("//*[@id=\"backbtn\"]")).click(); //back
   		driver.findElement(By.xpath("//*[@id=\"close\"]/img")).click(); //back
   		
   		driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
   		driver.findElement(By.xpath("//*[contains(text(),'" + filename + "')]")).click();  //Click temporary file
		Thread.sleep(5000);
		driver.findElements(By.className("item")).get(4).click();  //click view form
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//*[@id=\"two-cirlce\"]")).click(); //click second tab
		Thread.sleep(5000);
		//upload assert
		cell = sheet.getRow(i).getCell(25);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys("Testing new"); //enter assert master record name
		Thread.sleep(2000);
				
		String file1 = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
		driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file1); //upload file
		
//		String msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
//		System.out.println(msg);
//       	String expect = "File already uploaded."; 
//		assertion4.assertEquals(expect, msg); 
//		assertion4.assertAll(); 
		
		Thread.sleep(3000);
   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
  		Thread.sleep(2000);
   		Close();
		}
	}

	   @Test (priority=5)  
		public static void DeleteTheUploadedFile5 () throws InterruptedException, IOException {	
		   SoftAssert assertion5 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
			Login();
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
			Thread.sleep(2000);
		
			String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
		Thread.sleep(10000);
			
			//delete uploaded file
			driver.findElement(By.xpath("//*[@class=\"removeAsset1\"]")).click(); //click delete
			Thread.sleep(2000);
			    	
			String savecontent1 = driver.findElement(By.xpath("//*[@id=\"basic_content\"]/div[2]/div/following-sibling::table")).getText();
			System.out.println(savecontent1);
	 		boolean expectsavecontent1 = savecontent1.equals("download.jpeg"); 
			//AssertJUnit.assertFalse(expectsavecontent1, savecontent1);
			assertion5.assertAll(); 

			Thread.sleep(3000);
       		driver.findElement(By.xpath("//*[@id=\"uploadAssetsss\"]/div/div/div/button")).click(); //close the popup
    		Thread.sleep(5000);
	   		Close();
			}
		}

	   @Test (priority=6)  
		public static void DeleteTheFileAfterSaveAndOpen6 () throws InterruptedException, IOException {	
		   SoftAssert assertion6 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(1);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
			Login();
			driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
			driver.findElement(By.xpath("//*[contains(text(),'New Master')]")).click();  //Click master batch icon
		//Fill basic details
			cell = sheet.getRow(i).getCell(32);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(33);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateCategory =new Select(driver.findElement(By.xpath("//*[@id=\"type_category\"]")));
			templateCategory.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(34);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			Select templateType =new Select(driver.findElement(By.xpath("//*[@id=\"template_type\"]")));
			templateType.selectByVisibleText(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(35);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_purpose\"]")).sendKeys(cell.getStringCellValue());
			cell = sheet.getRow(i).getCell(36);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"type_document\"]")).sendKeys(cell.getStringCellValue());
			//save
			driver.findElement(By.xpath("//*[@id=\"masterSave\"]")).click();
			//upload assert
			cell = sheet.getRow(i).getCell(37);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(2000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(10000);
			
			driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
			Thread.sleep(10000);
	   		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	   		Thread.sleep(5000);
   		driver.findElement(By.xpath("//*[@id=\"backbtn\"]")).click(); //back
	   		driver.findElement(By.xpath("//*[@id=\"close\"]/img")).click(); //back
	   		
	   		driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	   		driver.findElement(By.xpath("//*[contains(text(),'" + filename + "')]")).click();  //Click temporary file
			Thread.sleep(5000);
			driver.findElements(By.className("item")).get(4).click();  //click view form
			Thread.sleep(10000);
			
			driver.findElement(By.xpath("//*[@id=\"two-cirlce\"]")).click(); //click second tab
			Thread.sleep(5000);
			
			//Delete the uploaded file
			driver.findElement(By.xpath("//*[@class=\"removeAsset\"]")).click(); //click delete
			Thread.sleep(2000);

			String savecontent1 = driver.findElement(By.xpath("//*[@id=\"basic_content\"]/div[2]/div/following-sibling::table")).getText();
			System.out.println(savecontent1);
	 		boolean expectsavecontent1 = savecontent1.equals("download.jpeg"); 
			//AssertJUnit.assertFalse(expectsavecontent1, savecontent1);
			assertion6.assertAll(); 
			
			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
	   		Thread.sleep(2000);
	   		Close();
			}
		}

	   @Test (priority=7)  
		public static void UploadTheDeletedFile7 () throws InterruptedException, IOException {	
		   SoftAssert assertion7 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2);
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
			Thread.sleep(2000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(10000);
			
			driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
			Thread.sleep(5000);
	   		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	   		Thread.sleep(5000);
	   		driver.findElement(By.xpath("//*[@id=\"backbtn\"]")).click(); //back	   		
	   		driver.findElement(By.xpath("//*[@id=\"close\"]/img")).click(); //back
	   		
	   		driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	   		driver.findElement(By.xpath("//*[contains(text(),'" + filename1 + "')]")).click();  //Click temporary file
			Thread.sleep(5000);
			driver.findElements(By.className("item")).get(4).click();  //click view form
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//*[@id=\"two-cirlce\"]")).click(); //click second tab
			Thread.sleep(5000);
			
			//Delete the uploaded file
			driver.findElement(By.xpath("//*[@class=\"removeAsset\"]")).click(); //click delete
			Thread.sleep(2000);
			
			//upload assert
			cell = sheet.getRow(i).getCell(7);
	        cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(2000);
			
			String file1 = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file1); //upload file
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue

			WebElement uploadedfile2 = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label"));
			String uploadedfile2text = uploadedfile2.getText();
	   		String expectuploadedfile2text = "download.jpeg";
			AssertJUnit.assertEquals(expectuploadedfile2text, uploadedfile2text);  
			assertion7.assertAll(); 
	   		Thread.sleep(2000);
	   		Close();
		}
	   }
	   
	   @Test (priority=8)  
		public static void UploadFileWithoutSpecifyingTheRecordName8 () throws InterruptedException, IOException {	
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2);
	        for(int i=2; i<=sheet.getLastRowNum(); i++)
	        {	
			Login();
			System.out.println(filename);
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
			Thread.sleep(2000);
			if(driver.findElements(By.xpath("//*[@id=\"asset_files\"]")).size() != 0){
				System.out.println("Element is Present");
				}else{
				System.out.println("Element is Absent");
				}
			/*driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).click(); //upload file
			Thread.sleep(1000);*/
			
	   		driver.findElement(By.xpath("//*[@class='close skipAssets']")).click(); // click close
	   		Thread.sleep(2000);
	   		driver.quit();
	   		
			}
		}

	   @Test (priority=9)  
		public static void UploadFileWithTheSameRecordName9 () throws InterruptedException, IOException {	
		   SoftAssert assertion9 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2);
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
			Thread.sleep(2000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(10000);
			
			//upload assert
			cell = sheet.getRow(i).getCell(18);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(2000);
					
			String file1 = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file1); //upload file
			
			/*String msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
	       	String expect = "File with the same asset name already exists."; 
			assertion9.assertEquals(expect, msg); 
			assertion9.assertAll();  */

			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
	  		Thread.sleep(2000);
	   		Close();
			}
		}   
	
	   @Test (priority=10)  
		public static void UploadFileWithTheSameRecordNameAfterSaveAndOpen10 () throws InterruptedException, IOException {
		   SoftAssert assertion10 = new SoftAssert();
			FileInputStream finput = new FileInputStream(src);
	        workbook = new HSSFWorkbook(finput);
	        sheet= workbook.getSheetAt(2);
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
			Thread.sleep(3000);
			
			String file = "//home//s4cchinpc105//Desktop//ZImage//download.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file); //upload file
			Thread.sleep(10000);
			
			String savecontent = driver.findElement(By.xpath("//*[@id=\"assetTable\"]/tr[1]/td/label")).getText();
     		boolean expectsavecontent = savecontent.contains("download.jpeg"); 
			//assertion.assertFalse(expectsavecontent, savecontent);

			Thread.sleep(8000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue
			Thread.sleep(4000);
	   		driver.findElement(By.xpath("//*[@id=\"saveSurvey\"]")).click(); //save
	   		Thread.sleep(1000);
	   		driver.findElement(By.xpath("//*[@id=\"backbtn\"]")).click(); //back
	   		driver.findElement(By.xpath("//*[@id=\"close\"]/img")).click(); //back
	   		
	   		driver.findElement(By.xpath("//*[@id=\"batch\"]")).click();  //click new batch icon
	   		driver.findElement(By.xpath("//*[contains(text(),'" + filename2 + "')]")).click();  //Click temporary file
			Thread.sleep(5000);
			driver.findElements(By.className("item")).get(4).click();  //click view form
			Thread.sleep(15000);
			
			driver.findElement(By.xpath("//*[@id=\"two-cirlce\"]")).click(); //click second tab
			Thread.sleep(5000);
			
			//upload assert
			cell = sheet.getRow(i).getCell(24);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//*[@id=\"type_name1\"]")).sendKeys(cell.getStringCellValue()); //enter assert master record name
			Thread.sleep(3000);
					
			String file1 = "//home//s4cchinpc105//Desktop//ZImage//download1.jpeg";
			driver.findElement(By.xpath("//*[@id=\"asset_files\"]")).sendKeys(file1); //upload file
			
			String msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
	       	String expect = "File with the same asset name already exists."; 
			AssertJUnit.assertEquals(expect, msg); 
			assertion10.assertAll(); 
			
			Thread.sleep(3000);
	   		driver.findElement(By.xpath("//*[@id=\"fileSave\"]")).click(); // click continue	
	   		Thread.sleep(2000);
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
