package com.busreservation.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.busreservation.dto.BusInfo;
import com.busreservation.dto.Customer;
import com.busreservation.exception.CustomerNotFound;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;

public interface BusInfoDao {
    
	boolean loginAdmin(String username , String password) throws WrongCredentials;
	
	String addBus(BusInfo busInfo) throws SomeThingWentWrong;
	
	boolean updateBusDate(int busNumber, LocalDateTime dep , LocalDateTime arr) throws SomeThingWentWrong;
	
	boolean removeBus(int busNumber) throws SomeThingWentWrong;
	
	List<BusInfo> getBusInfo() throws SomeThingWentWrong;
	
	List<BusInfo> searchBusByDestination(String departure , String arrival) throws SomeThingWentWrong;
	
}
