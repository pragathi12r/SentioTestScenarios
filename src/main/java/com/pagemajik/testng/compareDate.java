package com.pagemajik.testng;

public class compareDate {
	
	public static void main(String[] args) {    
		String inputDate="2020-03-09";
		String currentDate=java.time.LocalDate.now().toString();
		System.out.println("Print Current Date-->"+currentDate);  
		if(inputDate.equals(currentDate))
		{
			System.out.println("Googling");
		}else if(inputDate.compareTo(currentDate)<0) {
			System.out.println("Input date is lesser than current date"+inputDate);
		}else{
			System.out.println("Fail");
		}
		
		
		  }    

}
