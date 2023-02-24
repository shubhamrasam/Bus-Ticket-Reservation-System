package com.busreservation.methods;

import java.util.Scanner;

import com.busreservation.colors.Colors;
import com.busreservation.dao.BusBookingDao;
import com.busreservation.dao.BusBookingDaoImpl;
import com.busreservation.dto.Ticket;
import com.busreservation.dto.TicketImpl;
import com.busreservation.exception.NoTicketFound;
import com.busreservation.exception.SomeThingWentWrong;

public class TicketInfo {

	public static void checkTicket(Scanner sc){
		
		BusBookingDao busBooking = new BusBookingDaoImpl(); 
		
		Ticket ticket = new TicketImpl();
		
		System.out.println("Please Enter Ticket Number");
		int ticketNumber = sc.nextInt();
		try {
			ticket = busBooking.getTicket(ticketNumber);
			
		    System.out.println(Colors.LIGHT_BLUE_BACKGROUND  +"------------------------------------"+"\n"
														     +" |" + ticket.getTicketNo()      +"| "+"\n"
														     +" |" + ticket.getCustomerId()    +"| "+"\n"
														     +" |" + ticket.getBusNumber()     +"| "+"\n"
														     +" |" + ticket.getDateOfBooking() +"| "+"\n"
														     +" |" + ticket.getDeparture()     +"| "+"\n"
														     +" |" + ticket.getTotal_tickets() +"| "+"\n"
														     +" |" + ticket.getTotal_fare()    +"| "+"\n"
														     +" |" + ticket.isStatus()         +"| "+"\n"
														     +"------------------------------------"+"\n"+Colors.RESET);
		    
		    System.out.print(Colors.BLACK_BOLD+" Your Choice: "+Colors.RESET);
			
			int choice = sc.nextInt(); 
		    
			System.out.println(Colors.LIGHT_BLUE_BACKGROUND  +"--------------------------------"+"\n"
															 +" | 1.Cancel Ticket            | "+"\n"
										                     +" | 2.Back                     | "+"\n"
										                     +"--------------------------------"+"\n"+Colors.RESET);
			
			if(choice == 1) {
				
				cancelTicket(sc);
				
			}else if(choice == 2) {

				CustomerChoice.customerChoice(sc);;
				
			}else {
				
				System.out.println(Colors.BLACK_BOLD_BRIGHT+"Please select correct"+Colors.RESET);
				CustomerChoice.customerChoice(sc);
			}
		    
			
		} catch (NoTicketFound e) {
			e.printStackTrace();
		}
	}
	
	public static void cancelTicket(Scanner sc){
		
		BusBookingDao busBooking = new BusBookingDaoImpl(); 

		System.out.println("Please Enter Ticket Number");
		int ticketNumber = sc.nextInt();
		
		try {
			
			if(busBooking.cancelBooking(ticketNumber)) {
				System.out.println("Ticket Cancel Success");
			}
			
		} catch (SomeThingWentWrong e) {
			e.printStackTrace();
			CustomerChoice.customerChoice(sc);
		}
		
	}
	
	
	public static void getAllPendingReq(Scanner sc) {
		
		BusBookingDao busBooking = new BusBookingDaoImpl(); 
		
		
		
			
			try {
				busBooking.getPedingTicketReq().forEach(System.out::println);
			} catch (NoTicketFound e) {
			
				e.printStackTrace();	
				AdminDashboard.adminDashboard(sc);
			}
			
		
	}
	
	public static void confirmAllPendingReq(Scanner sc) {
		
		BusBookingDao busBooking = new BusBookingDaoImpl(); 
		
		System.out.println("Please enter Ticket Number");
		int ticketNumber = sc.nextInt();

				try {
					
					if(busBooking.confirmAllPendingReq()) {
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						System.out.println("All Tickets are Confirm");
						AdminDashboard.adminDashboard(sc);
						
					}
					
					
				} catch (SomeThingWentWrong e) {
				
					e.printStackTrace();
					AdminDashboard.adminDashboard(sc);
				}
		
	}
	
	public static void confirmByTicketNo(Scanner sc) {
		
		BusBookingDao busBooking = new BusBookingDaoImpl(); 
		
		
		try {
			
			if(busBooking.confirmAllPendingReq()) {
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("All Tickets are Confirm");
				AdminDashboard.adminDashboard(sc);
			}
			
		} catch (SomeThingWentWrong e) {
			e.printStackTrace();
			AdminDashboard.adminDashboard(sc);
		}
		
		
	}
	
}
