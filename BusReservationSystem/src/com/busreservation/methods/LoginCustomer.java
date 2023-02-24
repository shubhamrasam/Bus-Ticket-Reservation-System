package com.busreservation.methods;

import java.util.Scanner;

import com.busreservation.colors.Colors;
import com.busreservation.dao.CustomerDao;
import com.busreservation.dao.CustomerDaoImpl;
import com.busreservation.dto.Customer;
import com.busreservation.dto.CustomerImpl;
import com.busreservation.exception.CustomerNotFound;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;
import com.busreservation.ui.Main;

public class LoginCustomer {

	public static int count = 1;
	
	public static void loginCustomer(Scanner sc) {

		 CustomerDao Customer = new CustomerDaoImpl();
		
		 if(count==2) {
			 System.out.println(Colors.YELLOW_BACKGROUND+"This your "+count+"nd Attempt.You have left with "+ (4-count) +" Attempts"+Colors.RESET);			 
		 }else if(count==3) {
			 System.out.println(Colors.YELLOW_BACKGROUND+"This your "+count+"rd Attempt.You have left with "+ (4-count) +" Attempts"+Colors.RESET);			 
		 }
		 
		 System.out.println(Colors.BLACK_BOLD + "Please Enter your Email" + Colors.RESET);
		 String email = sc.next();
		 System.out.println(Colors.BLACK_BOLD +"Please Enter your Password"+ Colors.RESET);
		 String password = sc.next();
		 
	    try {
	    	
			if(Customer.customerLogin(email, password)) {	
				
				System.out.println("");
				System.out.println(Colors.GREEN_BACKGROUND+"Login Success"+Colors.RESET);
				CustomerChoice.customerChoice(sc);
		    }
			
		} catch (SomeThingWentWrong | WrongCredentials e) {
			
			 System.out.println(Colors.RED_BACKGROUND+"Wrong Credentials"+Colors.RESET);		
			
			count++;
			if(count == 4) {
				System.out.println("Enough Attempts");
				Main.choiceCustomerOrAdmin(sc);
			}
			loginCustomer(sc);
		}
		
	}
	
	
	public static void customerProfile(Scanner sc) {
		
		CustomerDao customer = new CustomerDaoImpl();
		Customer cus = null;
		System.out.println("Please Enter your Email");
		String email = sc.next();
		
		try {
			
			cus = customer.getMyDetails(email);
			
		} catch (CustomerNotFound e) {
		
			System.out.println(Colors.RED_BACKGROUND+"Customer Not Found"+Colors.RESET);
			CustomerChoice.customerChoice(sc);
			
		}
		
		System.out.println(Colors.LIGHT_BLUE_BACKGROUND  +"--------------------------------------------------"+"\n"
													     +" | CustomerId: " + cus.getCustomerId()        +"| "+"\n"
													     +" | Name: " + cus.getfName()+" "+cus.getlName()+"| "+"\n"
													     +" | Email: " + cus.getEmail()                  +"| "+"\n"
													     +" | Password: " + cus.getPassword()            +"| "+"\n"
													     +" | Mobile: " + cus.getMobile()                +"| "+"\n"
													     +"--------------------------------------------------"+"\n"+Colors.RESET);
		CustomerChoice.customerChoice(sc);
	}

}
