package com.busreservation.methods;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.busreservation.colors.Colors;
import com.busreservation.dao.BusInfoDao;
import com.busreservation.dao.BusInfoDaoImpl;
import com.busreservation.dto.BusInfo;
import com.busreservation.dto.BusInfoImpl;
import com.busreservation.exception.SomeThingWentWrong;

public class BusMethods {

	public static void addBus() {
		
		Scanner sc = new Scanner(System.in);
		
		BusInfoDao busInfo = new BusInfoDaoImpl();
		System.out.println(Colors.BLACK_BOLD+"Enter Bus Number"+Colors.RESET);
		int busNo = 0;
		
		try {
			busNo = sc.nextInt();
		 }catch (InputMismatchException e) {
		 	System.out.println("");
		 	System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
		 	System.out.println("");
		 	addBus();
		 }
		
		System.out.println(Colors.BLACK_BOLD+"Enter Bus Type (AC/Non-AC)"+Colors.RESET);
		String busType = sc.next();
		
		System.out.println(Colors.BLACK_BOLD +"Enter Departure from"+ Colors.RESET);
		String departure = sc.next();
		
		System.out.println(Colors.BLACK_BOLD +"Enter Destination"+ Colors.RESET);
		String destination = sc.next();
		
		System.out.println(Colors.BLACK_BOLD + "Enter Bus Total Seats"+ Colors.RESET);
		int total_seats = 0;
		try {
			total_seats = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			addBus();
		}
		
		System.out.println(Colors.BLACK_BOLD + "Enter Bus Booked Seats"+ Colors.RESET);
		int booked_seats = 0;
		try {
			booked_seats = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			addBus();
		}
		
		System.out.println(Colors.BLACK_BOLD +"Enter Bus Avaliable Seats"+ Colors.RESET);
		int avaliable_seats = 0;
		try {
			avaliable_seats = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			addBus();
		}
		
		System.out.println(Colors.BLACK_BOLD + "Enter Depature Date and Time"+ Colors.RESET);
		LocalDateTime depDate = null;
		try {
			depDate = LocalDateTime.parse(sc.next());
		}catch (InputMismatchException | DateTimeParseException  e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Date " + Colors.RESET);
			System.out.println("");
			addBus();
		}
		
		System.out.println(Colors.BLACK_BOLD + "Enter Destination Arrival Date and Time"+ Colors.RESET);
		LocalDateTime arrDate = null;
		try {
			arrDate = LocalDateTime.parse(sc.next());
		}catch (InputMismatchException | DateTimeParseException  e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Date " + Colors.RESET);
			System.out.println("");
			addBus();
		}
		
		System.out.println(Colors.BLACK_BOLD + "Enter Bus Fare" + Colors.RESET);
		int fare = 0;
		try {
			fare = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			addBus();
		}
		
		BusInfo bus = new BusInfoImpl(busNo,busType, departure , destination ,total_seats ,booked_seats,avaliable_seats,depDate,arrDate,fare);
		try {
			
			System.out.println("");
			System.out.println(Colors.GREEN_BACKGROUND+ busInfo.addBus(bus)+Colors.RESET);
			System.out.println("");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			AdminDashboard.adminDashboard();
			
		} catch (SomeThingWentWrong e) {
            
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Something Went Wrong " + Colors.RESET);
			System.out.println("");
			
			AdminDashboard.adminDashboard();
		}
		
	}
	
	
	public static void removeBus() {
		
		Scanner sc = new Scanner(System.in);
		BusInfoDao busInfo = new BusInfoDaoImpl();
		
		System.out.println( Colors.BLACK_BOLD +"Enter Bus Number" + Colors.RESET);
		int BusNumber = 0;
		
		try {
			BusNumber = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			removeBus();
		}
		
		try {
			if(busInfo.removeBus(BusNumber)) {
				System.out.println("");
				System.out.println(Colors.GREEN_BACKGROUND+" Bus Removed "+Colors.RESET);
				System.out.println("");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				AdminDashboard.adminDashboard();
			}
			
		} catch (SomeThingWentWrong e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " No Bus Found " + Colors.RESET);
			System.out.println("");
			AdminDashboard.adminDashboard();
		}
		
	}
	
	public static void changeBusDep() {
		
		Scanner sc = new Scanner(System.in);
		BusInfoDao busInfo = new BusInfoDaoImpl();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(Colors.BLACK_BOLD +"Enter Bus Number"+ Colors.RESET);
		int busNo = 0;
		try {
			busNo = sc.nextInt();
		}catch (InputMismatchException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + "Input type should be Number" + Colors.RESET);
			System.out.println("");
			changeBusDep();
		}
		
		System.out.println(Colors.BLACK_BOLD + "Enter Depature new Date and Time" + Colors.RESET);
		LocalDateTime depDate = null;
		try {
			
			depDate = LocalDateTime.parse(sc.next());
			
		}catch (InputMismatchException | DateTimeParseException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Date and Time " + Colors.RESET);
			System.out.println("");
			changeBusDep();
		}
		
		System.out.println(Colors.BLACK_BOLD+"Enter Destination new Arrival Date and Time"+Colors.RESET);
		LocalDateTime arrDate = null;
		try {
			arrDate = LocalDateTime.parse(sc.next());
		}catch (InputMismatchException | DateTimeParseException e) {
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Input type should be Number " + Colors.RESET);
			System.out.println("");
			changeBusDep();
		}
		
		try {
			
			if(busInfo.updateBusDate(busNo, depDate, arrDate)) {
				System.out.println("");
				System.out.println(Colors.GREEN_BACKGROUND+" Bus DateTime Changed "+Colors.RESET);
				System.out.println("");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				AdminDashboard.adminDashboard();
				
			}
			
		} catch (SomeThingWentWrong e) {
			
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " Something Went Wrong " + Colors.RESET);
			System.out.println("");
			
			AdminDashboard.adminDashboard();
		}
		
	}
	
	public static void getBusList() {
		
		Scanner sc = new Scanner(System.in);
		BusInfoDao busInfo = new BusInfoDaoImpl();
		
		try {
			busInfo.getBusInfo().forEach(i->{
				System.out.println(Colors.PURPLE_BOLD+i+Colors.RESET);
			});
			

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AdminDashboard.adminDashboard();
			
			
		} catch (SomeThingWentWrong e) {
			
			System.out.println("");
			System.out.println(Colors.RED_BACKGROUND + " No Bus Found " + Colors.RESET);
			System.out.println("");
			AdminDashboard.adminDashboard();
		}
	}
	
}
