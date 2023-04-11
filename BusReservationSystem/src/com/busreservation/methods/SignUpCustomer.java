package com.busreservation.methods;

import java.util.Scanner;

import com.busreservation.colors.Colors;
import com.busreservation.dao.CustomerDao;
import com.busreservation.dao.CustomerDaoImpl;
import com.busreservation.dto.Customer;
import com.busreservation.dto.CustomerImpl;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.ui.Main;

public class SignUpCustomer {

	public static void signUpCustomer(Scanner sc) {

		 CustomerDao customer = new CustomerDaoImpl();
		 System.out.println("");
		 System.out.println(Colors.BLACK_BOLD+"Please Enter your First Name"+Colors.RESET);
		 String fName = sc.next();
		 System.out.println(Colors.BLACK_BOLD+"Please Enter your Last Name"+Colors.RESET);
		 String lName = sc.next();
		 System.out.println(Colors.BLACK_BOLD+"Please Enter your Mobile Number"+Colors.RESET);
		 String mobile = sc.next();
		 System.out.println(Colors.BLACK_BOLD+"Please Enter your Email"+Colors.RESET);
		 String email = sc.next();
		 System.out.println(Colors.BLACK_BOLD+"Please Enter your Password"+Colors.RESET);
		 String password = sc.next();
		 
		 
		 Customer cus = new CustomerImpl(fName , lName , mobile ,email ,password);
		 
		 try {
			
			 if(customer.customerSignUp(cus)){
				 
				 System.out.println("");
				 System.out.println(Colors.GREEN_BACKGROUND + " Sign-Up Successful " + Colors.RESET);
				 System.out.println("");
				 
				try {
					 
					Thread.sleep(2000);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
				 Main.customerMethods();
			 }
			
		}catch(IllegalArgumentException e) {
			
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + e.getMessage() + Colors.RESET);
			System.out.println("");
			Main.customerMethods();
			
		}catch (SomeThingWentWrong e) {
               
			System.out.println(Colors.RED_BACKGROUND + " Something Went Wrong " + Colors.RESET);
			
		}
		 
	}

}
