package com.busreservation.methods;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.busreservation.colors.Colors;
import com.busreservation.ui.Main;

public class CustomerDashboard {

	public static void customerChoice() {
		
		Scanner sc = new Scanner(System.in);
		
	    System.out.println(Colors.BLACK_BOLD             +"-+----------------------------+-"+"\n"
            										     +" | 1.Change Password          | "+"\n"
            										     +" | 2.Get Profile Details      | "+"\n"
            										     +" | 3.Search Bus By Destinaton | "+"\n"
            										     +" | 4.Book a Ticket            | "+"\n"
            										     +" | 5.Check a Ticket           | "+"\n"
								                         +" | 6.Cancel a Ticket          | "+"\n"
								                         +" | 7.Logout                   | "+"\n"
								                         +"-+----------------------------+-"+"\n"+Colors.RESET);
	    
	    System.out.print(Colors.BLACK_BOLD+"Your Choice: "+Colors.RESET);
		int choice = 0; 
		
		 try {
			 choice = sc.nextInt();
		 }catch (InputMismatchException e) {
			 
			 System.out.println("");
			 System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			 System.out.println("");
			 customerChoice();
		 }
		
		if(choice == 1) {
			
			 CustomerMethods.changePassword();
			
		}else if(choice == 2) {

			 CustomerMethods.customerProfile();
			
		}else if(choice == 3) {

			CustomerMethods.searchBusByDestination(sc);
			
		}else if(choice == 4) {
			
			CustomerMethods.bookBusTicket();
			
		}else if(choice == 5) {
			
			CustomerMethods.checkTicket();
			
		}else if(choice == 6) {
			
			CustomerMethods.cancelTicket();
			
		}else if(choice == 7) {
			
			System.out.println(Colors.BLACK_ITALIC +"----Logout Successful----"+ Colors.RESET);
			
			Main.choiceCustomerOrAdmin();
			
		}else {
			
			System.out.println(Colors.BLACK_BOLD_BRIGHT+" Please select correct Number "+Colors.RESET);
			customerChoice();
		}
		
		
	}
	
}
