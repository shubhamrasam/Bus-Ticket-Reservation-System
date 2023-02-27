package com.busreservation.methods;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.busreservation.colors.Colors;
import com.busreservation.dao.BusBookingDao;
import com.busreservation.dao.BusBookingDaoImpl;
import com.busreservation.dao.BusInfoDao;
import com.busreservation.dao.BusInfoDaoImpl;
import com.busreservation.dao.CustomerDao;
import com.busreservation.dao.CustomerDaoImpl;
import com.busreservation.dto.BusInfo;
import com.busreservation.dto.Customer;
import com.busreservation.dto.Ticket;
import com.busreservation.dto.TicketImpl;
import com.busreservation.exception.BookingFail;
import com.busreservation.exception.CustomerNotFound;
import com.busreservation.exception.NoTicketFound;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;
import com.busreservation.ui.Main;

public class CustomerMethods {

	public static int count = 1;

	/**
	 * Customer Details will be Saved Below After login
	 */
	public static int cusID;
	public static String fname;
	public static String lname;
	public static String mobile;
	public static String email;
	public static String password;
	
	public static void loginCustomer() {
         
		 Scanner sc = new Scanner(System.in);
		 CustomerDao Customer = new CustomerDaoImpl();
		
		 if(count==2) {
			 System.out.println(Colors.YELLOW_BACKGROUND+" You have "+ (4-count) +" attempts left! "+Colors.RESET);
		 }else if(count==3) {
			 System.out.println(Colors.YELLOW_BACKGROUND+" You have "+ (4-count) +" attempts left! "+Colors.RESET);
		 }
		 
		 System.out.println("");
		 System.out.println(Colors.BLACK_BOLD + "Please Enter your Email" + Colors.RESET);
		 String emailAddress = null;
		 
		 try {
			 emailAddress = sc.next();
		 }catch (InputMismatchException e) {
		 	System.out.println("");
		 	System.out.println(Colors.RED_BACKGROUND + "Input type should be String" + Colors.RESET);
		 	System.out.println("");
		 	loginCustomer();
		 }
		 
		 System.out.println(Colors.BLACK_BOLD +"Please Enter your Password"+ Colors.RESET);
		 String pass = null;
		 
		 try {
			 pass = sc.next();
		 }catch (InputMismatchException e) {
		 	System.out.println("");
		 	System.out.println(Colors.RED_BACKGROUND + " Input type should be String " + Colors.RESET);
		 	System.out.println("");
		 	loginCustomer();
		 }
		 
	    try {
	    	
			if(Customer.customerLogin(emailAddress, pass) != null) {	
				Customer customer = Customer.customerLogin(emailAddress, pass);
				System.out.println("");
				System.out.println(Colors.BOXING+Colors.BLACK_BOLD+Colors.BANANA_YELLOW_BACKGROUND+"  Welcome "+ customer.getfName() + " " +customer.getlName()+"  " + Colors.RESET);
				System.out.println("");
				
				cusID = customer.getCustomerId();
				fname = customer.getfName();
				lname = customer.getlName();
				mobile = customer.getMobile();
				email = customer.getEmail();
				password = customer.getPassword();
				
				CustomerDashboard.customerChoice();
				
		    }
			
		} catch (SomeThingWentWrong | WrongCredentials e) {
			
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND+" Wrong Credentials "+Colors.RESET);		
			System.out.println("");
			count++;
			if(count == 4) {
				System.out.println(Colors.RED_BOLD+"You've reached the maximum amount of login attempts!"+Colors.RESET);
				count = 0;
				Main.choiceCustomerOrAdmin();
			}
			loginCustomer();
		}
		
	}
	
	
	public static void customerProfile() {
		
		Scanner sc = new Scanner(System.in);
		
		CustomerDao customer = new CustomerDaoImpl();
		Customer cus = null;
		System.out.println("");
		System.out.println(Colors.BLACK_BOLD+"Please Enter your Email"+Colors.RESET);
		String email = null;
		
		try {
			email = sc.next();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be String" + Colors.RESET);
			System.out.println("");
			customerProfile();
		}
		
		
		try {
			
			cus = customer.getMyDetails(email,cusID);
			System.out.println("");
			System.out.println(Colors.PURPLE_BOLD  +"  CustomerId: " + cus.getCustomerId()        );
			System.out.println(Colors.PURPLE_BOLD  +"  Name: " + cus.getfName()+" "+cus.getlName());
			System.out.println(Colors.PURPLE_BOLD  +"  Email: " + cus.getEmail()                  );
			System.out.println(Colors.PURPLE_BOLD  +"  Password: " + cus.getPassword()            );
		    System.out.println(Colors.PURPLE_BOLD  +"  Mobile: " + cus.getMobile()                );
		    System.out.println(""); 
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				System.out.println(Colors.RED_BACKGROUND+" Wrong Email Address "+Colors.RESET);
			}
			
			CustomerDashboard.customerChoice();
			
		} catch (CustomerNotFound e) {
		
			System.out.println(Colors.RED_BACKGROUND+" Customer Not Found "+Colors.RESET);
			CustomerDashboard.customerChoice();
			
		}
		
	}
	
	
	public static void changePassword() {
		
		Scanner sc = new Scanner(System.in);
		
		CustomerDao customer = new CustomerDaoImpl();
		
		System.out.println(Colors.BLACK_BOLD + "Enter your Email "+Colors.RESET);
		String emailAdd = sc.next();
		
		System.out.println(Colors.BLACK_BOLD+"Enter your New Password "+Colors.RESET);
		String pass = sc.next();
		
		try {
			customer.changeCustomerPassword(emailAdd, pass);
			password = pass;
			System.out.println("");
			System.out.println(Colors.GREEN_BACKGROUND+"Password Changed Successfully"+Colors.RESET);
            System.out.println("");
			CustomerDashboard.customerChoice();
		} catch (CustomerNotFound | SomeThingWentWrong e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Customer Not Found " + Colors.RESET);
			System.out.println("");
			CustomerDashboard.customerChoice();
		}
		
	}
	
	
	public static void bookBusTicket() {
		
		Scanner sc = new Scanner(System.in);
		BusBookingDao busBook = new BusBookingDaoImpl();
		
		System.out.println("");
		System.out.println(Colors.BLACK_BOLD +"Please Enter your Bus Number"+Colors.RESET);
		int busNo = 0;
		
		try {
			busNo = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			bookBusTicket();
		}
		
		System.out.println(Colors.BLACK_BOLD + "Please Enter Ticket Quantity"+Colors.RESET);
		int ticketAmount = 0;
		
		try {
			ticketAmount = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			bookBusTicket();
		}
		
		try {
			
			if(busBook.bookTicket(cusID, busNo, ticketAmount)) {
				System.out.println("");
			    System.out.println(Colors.YELLOW_BACKGROUND + " Ticket Booked Successfully wait for Confirmation from Admin "+Colors.RESET);
			    System.out.println("");
			    CustomerDashboard.customerChoice();
			}
			
		} catch (BookingFail e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Booking Fail " + Colors.RESET);
			System.out.println("");
			CustomerDashboard.customerChoice();
			
		}
		
	}
	
	
	public static void searchBusByDestination(Scanner sc) {
		
		BusInfoDao buses = new BusInfoDaoImpl();
		List<BusInfo> busList = new ArrayList<>();
		System.out.println("");
		System.out.println(Colors.BLACK_BOLD+"Bus Pickup From"+Colors.RESET);
		String dep = sc.next();
		System.out.println(Colors.BLACK_BOLD +"Bus Drop location"+Colors.RESET);
		String arr = sc.next();
		
		try {
			
			busList = buses.searchBusByDestination(dep, arr);
			
			busList.forEach(i->{
				System.out.println("");
				System.out.println(Colors.PURPLE_BOLD + i + Colors.RESET)  ;
				System.out.println("");
			});
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			
			CustomerDashboard.customerChoice();
		
			
		} catch (SomeThingWentWrong e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND+ " No Bus Found " + Colors.RESET);
			System.out.println("");
			CustomerDashboard.customerChoice();
		}
		
	}
	
	public static void checkTicket(){
		
		Scanner sc = new Scanner(System.in);
		
		BusBookingDao busBooking = new BusBookingDaoImpl(); 
		
		
		System.out.println("");
		System.out.println(Colors.BLACK_BOLD + "Please Enter Bus Number" + Colors.RESET);
		int busNumber = 0;
		
		try {
			busNumber = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			bookBusTicket();
		}
		
		try {
			
			List<Ticket> tickets = busBooking.getTicket(cusID,busNumber);
			
//			Ticket ticket = null;
			
			for(Ticket ticket: tickets) {
				
				System.out.println("");
				System.out.println(Colors.PURPLE_BOLD + " Ticket Number : " + ticket.getTicketNo() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Customer ID : " + ticket.getCustomerId() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Bus Number : " + ticket.getBusNumber() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Date of Booking : " + ticket.getDateOfBooking() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Date of Departure : " + ticket.getDeparture() + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Total tickets : " + ticket.getTotal_tickets()  + Colors.RESET);
				System.out.println(Colors.PURPLE_BOLD + " Total Fare : " + ticket.getTotal_fare() + Colors.RESET);
				if (ticket.isStatus()) {
					System.out.println(Colors.PURPLE_BOLD + " Status : Booked"  + Colors.RESET);
				}else {
					System.out.println(Colors.PURPLE_BOLD + " Status : Pending" + Colors.RESET);
				}

				System.out.println("");
				
			}
			
			
		    
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			CustomerDashboard.customerChoice();
			
		} catch (NoTicketFound e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND+" No Ticket Found "+ Colors.RESET);
			System.out.println("");
			CustomerDashboard.customerChoice();
		}
	}
	
	public static void cancelTicket(){
		
		Scanner sc  = new Scanner(System.in);
		BusBookingDao busBooking = new BusBookingDaoImpl(); 
		System.out.println("");
		System.out.println(Colors.BLACK_BOLD+ "Please Enter Ticket Number"+Colors.RESET);
		
		int ticketNumber = 0;
		
		try {
			ticketNumber = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be Number" + Colors.RESET);
			System.out.println("");
			cancelTicket();
		}
		
		System.out.println(Colors.BLACK_BOLD+ "Please Enter Bus Number"+Colors.RESET);
			
		int busNumber = 0;
		
		try {
			busNumber = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be Number" + Colors.RESET);
			System.out.println("");
			cancelTicket();
		}
		
		System.out.println(Colors.BLACK_BOLD+ "Please Enter Number of Tickets"+Colors.RESET);
		int tickets = 0;
		
		try {
			tickets = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be Number" + Colors.RESET);
			System.out.println("");
			cancelTicket();
		}
		
		try {
			
			if(busBooking.cancelBooking(ticketNumber , cusID , busNumber , tickets)) {
				System.out.println("");
				System.out.println(Colors.GREEN_BACKGROUND+" Ticket Cancel Success " + Colors.RESET);
				System.out.println("");
			}
			
			CustomerDashboard.customerChoice();
		} catch (SomeThingWentWrong e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND+" You cannot cancel Bus Ticket "+ Colors.RESET);
			System.out.println("");
			CustomerDashboard.customerChoice();
		}
		
	}

}






