package com.busreservation.methods;

import java.util.Scanner;

import com.busreservation.dao.BusBookingDao;
import com.busreservation.dao.BusBookingDaoImpl;
import com.busreservation.exception.BookingFail;

public class BookTicket {

	public static void bookTicket(Scanner sc) {
		
		BusBookingDao busBook = new BusBookingDaoImpl();
		
		System.out.println("Please Enter your Customer Id");
		int cusId = sc.nextInt();
		System.out.println("Please Enter your Bus Number");
		int busNo = sc.nextInt();
		System.out.println("Please Enter Ticket Quantity");
		int ticketAmount = sc.nextInt();
		
		try {
			
			if(busBook.bookTicket(cusId, busNo, ticketAmount)) {
				
			    System.out.println("Ticket Booked Successfully wait for Confirmation from Admin");
				
			}
			
		} catch (BookingFail e) {
			e.printStackTrace();
			CustomerChoice.customerChoice(sc);
		}
		
	}
	
}
