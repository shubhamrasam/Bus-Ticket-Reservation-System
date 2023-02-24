package com.busreservation.methods;

import java.util.Scanner;

import com.busreservation.colors.Colors;
import com.busreservation.ui.Main;

public class CustomerChoice {

	public static void customerChoice(Scanner sc) {
		
	    System.out.println(Colors.LIGHT_BLUE_BACKGROUND  +"--------------------------------"+"\n"
            										     +" | 1.Change Password          | "+"\n"
            										     +" | 2.Get Profile Details      | "+"\n"
            										     +" | 3.Search Bus By Destinaton | "+"\n"
            										     +" | 4.Book a Ticket            | "+"\n"
            										     +" | 5.Check a Ticket           | "+"\n"
								                         +" | 6.Cancel a Ticket          | "+"\n"
								                         +" | 7.Logout                   | "+"\n"
								                         +"--------------------------------"+"\n"+Colors.RESET);
	    
	    System.out.print(Colors.BLACK_BOLD+"Your Choice: "+Colors.RESET);
		int choice = sc.nextInt(); 
		

		if(choice == 1) {
			
			 ChangePassword.changePassword(sc);
			
		}else if(choice == 2) {

			 LoginCustomer.customerProfile(sc);
			
		}else if(choice == 3) {

			 SearchBus.searchBusByDestination(sc);
			
		}else if(choice == 4) {
			
			 BookTicket.bookTicket(sc);
			
		}else if(choice == 5) {
			
			TicketInfo.checkTicket(sc);
			
		}else if(choice == 6) {
			
			TicketInfo.cancelTicket(sc);
			
		}else if(choice == 7) {
			
			Main.choiceCustomerOrAdmin(sc);
			
		}else {
			
			System.out.println(Colors.BLACK_BOLD_BRIGHT+"Please select correct"+Colors.RESET);
			customerChoice(sc);
		}
		
		
	}
	
}
