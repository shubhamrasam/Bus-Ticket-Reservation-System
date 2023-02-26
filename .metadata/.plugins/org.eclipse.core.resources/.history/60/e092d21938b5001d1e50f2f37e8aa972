package com.busreservation.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.busreservation.dao.BusInfoDao;
import com.busreservation.dao.BusInfoDaoImpl;
import com.busreservation.dto.BusInfo;
import com.busreservation.exception.SomeThingWentWrong;


public class SearchBus {

	public static void searchBusByDestination(Scanner sc) {
		
		BusInfoDao buses = new BusInfoDaoImpl();
		List<BusInfo> busList = new ArrayList<>();
		
		System.out.println("Bus Pickup From");
		String dep = sc.next();
		System.out.println("Bus Drop location");
		String arr = sc.next();
		
		try {
			
			busList = buses.searchBusByDestination(dep, arr);
			
			busList.forEach(System.out::println);
			
			CustomerChoice.customerChoice(sc);
		
			
		} catch (SomeThingWentWrong e) {
			e.printStackTrace();
			CustomerChoice.customerChoice(sc);
		}
		
	}
	
	
	
}
