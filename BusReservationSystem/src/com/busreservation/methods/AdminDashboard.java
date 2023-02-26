package com.busreservation.methods;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.busreservation.colors.Colors;
import com.busreservation.ui.Main;

public class AdminDashboard {

	public static void adminDashboard() {
		
		 Scanner sc = new Scanner(System.in);
		 System.out.println(Colors.BLACK_BOLD 
				                                          +"-+-----------------------------------------------+-"+"\n"
													      +" | 1.Add Bus                                     | "+"\n"
													      +" | 2.Remove Bus                                  | "+"\n"
													      +" | 3.Change Bus Departure Date                   | "+"\n"
													      +" | 4.Get Buses List                              | "+"\n"
													      +" | 5.Get Customer Details                        | "+"\n"
										                  +" | 6.Confirm All Pending Request                 | "+"\n"
										                  +" | 7.Get All Pending Request                     | "+"\n"
										                  +" | 8.Confirm Customer Ticket Using Ticket No     | "+"\n"
										                  +" | 9.Get All Ticket Info                         | "+"\n"
										                  +" | 10.Logout                                     | "+"\n"
										                  +"-+-----------------------------------------------+-"+"\n"+Colors.RESET);
    
		 System.out.print(Colors.BLACK_BOLD+"Your Choice: "+Colors.RESET);
		 int choice = 0; 
		 
		 try {
			 choice = sc.nextInt();
	    }catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be Number" + Colors.RESET);
			System.out.println("");
			adminDashboard();
		}
		 System.out.println("");
		 
			if(choice == 1) {
				
				 BusMethods.addBus();
				
			}else if(choice == 2) {

				BusMethods.removeBus();
				
			}else if(choice == 3) {

				BusMethods.changeBusDep();
				
			}else if(choice == 4) {
				
				BusMethods.getBusList();
				
			}else if(choice == 5) {
				
				AdminMethods.customerInfo();
				
			}else if(choice == 6) {
				
				AdminMethods.confirmAllPendingReq(sc);
				
			}else if(choice == 7) {
				
				AdminMethods.getAllPendingReq();
				
			}else if(choice == 8) {
				
				AdminMethods.confirmByTicketNo();
				
			}else if(choice == 9) {
				
				AdminMethods.getAllTicketList();
				
			}else if(choice == 10) {
				
				System.out.println(Colors.BLACK_ITALIC +"----Logout Successful----"+ Colors.RESET);
				
				Main.choiceCustomerOrAdmin();
				
			}else {
				
				System.out.println(Colors.BLACK_BOLD_BRIGHT+" Please select correct Number "+Colors.RESET);
				adminDashboard();
			}
		 
	}
	
}
