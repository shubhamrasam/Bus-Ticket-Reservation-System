package com.busreservation.ui;

import java.util.Scanner;
import com.busreservation.colors.Colors;
import com.busreservation.methods.AdminLogin;
import com.busreservation.methods.LoginCustomer;
import com.busreservation.methods.SignUpCustomer;

public class Main {

	public static void choiceCustomerOrAdmin(Scanner sc) {
		
		System.out.println(Colors.LIGHT_BLUE_BACKGROUND    +"-------------------------"+"\n"
						                                   +" | 1.ENTER AS CUSTOMER | "+"\n"
				                                           +" | 2.ENTER AS ADMIN    | "+"\n"
						                                   +" | 3.EXIT              | "+"\n"
						                                   +"-------------------------"+"\n"+Colors.RESET);
		
		System.out.print(Colors.BLACK_BOLD+"Your Choice: "+Colors.RESET);
		
		int choice = sc.nextInt(); 
		
		if(choice == 1) {

			customerMethods(sc);
			
		}else if(choice == 2) {

			adminMethods(sc);
			
		}else if(choice == 3) {
			System.out.println();
			System.out.println(Colors.BLACK_ITALIC+"Thank You! Have a Great Day"+Colors.RESET);
			sc.close();
			
		}else {
			
			System.out.println(Colors.BLACK_BOLD_BRIGHT+"Please select correct"+Colors.RESET);
			choiceCustomerOrAdmin(sc);
		}
		
	}
	
	static void customerMethods(Scanner sc) {
		
		System.out.println(Colors.GREEN_BACKGROUND+" Welcome to Bus Reservation System "+Colors.RESET);
		System.out.println();
		System.out.println(Colors.LIGHT_BLUE_BACKGROUND    +"-------------------------"+"\n"
						                                   +" | 1.SignUp            | "+"\n"
				                                           +" | 2.Login             | "+"\n"
						                                   +" | 3.Back              | "+"\n"
						                                   +"-------------------------"+"\n"+Colors.RESET);
		
		System.out.print(Colors.BLACK_BOLD+" Your Choice: "+Colors.RESET);
		
		int choice = sc.nextInt(); 
		System.out.println("");
		
		if(choice == 1) {
			
			 SignUpCustomer.signUpCustomer(sc);
			
		}else if(choice == 2) {

			 LoginCustomer.loginCustomer(sc);
			
		}else if(choice == 3) {
			
			choiceCustomerOrAdmin(sc);
			
		}else {
			
			System.out.println(Colors.BLACK_BOLD_BRIGHT+"Please select correct"+Colors.RESET);
			choiceCustomerOrAdmin(sc);
		}
	}
	
	static void adminMethods(Scanner sc) {
		
		AdminLogin.adminLogin(sc);
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		choiceCustomerOrAdmin(sc);
		
		sc.close();
	}
	
}
