package com.busreservation.methods;

import java.util.Scanner;

import com.busreservation.dao.CustomerDao;
import com.busreservation.dao.CustomerDaoImpl;
import com.busreservation.exception.CustomerNotFound;

public class CustomerInfo {

	public static void customerInfo(Scanner sc) {
		
		CustomerDao customers = new CustomerDaoImpl();
		
		try {
			customers.getCustomersInfo().forEach(System.out::println);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			AdminDashboard.adminDashboard(sc);
			
		} catch (CustomerNotFound e) {
		
			e.printStackTrace();
			AdminDashboard.adminDashboard(sc);
		}
		
	}
	
}
