package com.busreservation.methods;

import java.util.Scanner;

import com.busreservation.colors.Colors;
import com.busreservation.ui.Main;

public class AdminDashboard {

	public static void adminDashboard(Scanner sc) {
		
		 System.out.println(Colors.LIGHT_BLUE_BACKGROUND  +"---------------------------------------------------"+"\n"
													      +" | 1.Add Bus                                     | "+"\n"
													      +" | 2.Remove Bus                                  | "+"\n"
													      +" | 3.Change Bus Departure Date                   | "+"\n"
													      +" | 4.Get Buses List                              | "+"\n"
													      +" | 5.Get Customer Details                        | "+"\n"
										                  +" | 6.Confirm All Pending Request                 | "+"\n"
										                  +" | 7.Get Pending Request                         | "+"\n"
										                  +" | 8.Confirm Customer Ticket Using Ticket No     | "+"\n"
										                  +" | 9.Logout                                      | "+"\n"
										                  +"---------------------------------------------------"+"\n"+Colors.RESET);

		 System.out.print(Colors.BLACK_BOLD+"Your Choice: "+Colors.RESET);
		 int choice = sc.nextInt(); 
		 
			if(choice == 1) {
				
				 BusMethods.addBus(sc);
				
			}else if(choice == 2) {

				BusMethods.removeBus(sc);
				
			}else if(choice == 3) {

				BusMethods.changeBusDep(sc);
				
			}else if(choice == 4) {
				
				BusMethods.getBusList(sc);
				
			}else if(choice == 5) {
				
				CustomerInfo.customerInfo(sc);
				
			}else if(choice == 6) {
				
				TicketInfo.confirmAllPendingReq(sc);
				
			}else if(choice == 7) {
				
				TicketInfo.getAllPendingReq(sc);
				
			}else if(choice == 8) {
				
				TicketInfo.confirmByTicketNo(sc);
				
			}else if(choice == 9) {
				
				Main.choiceCustomerOrAdmin(sc);
				
			}else {
				
				System.out.println(Colors.BLACK_BOLD_BRIGHT+"Please select correct"+Colors.RESET);
				adminDashboard(sc);
			}
		 
	}
	
}
