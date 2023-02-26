package com.busreservation.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.busreservation.dto.BusInfo;
import com.busreservation.dto.Customer;
import com.busreservation.exception.CustomerNotFound;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;

public interface BusInfoDao {
    
	/**
	 * Below Method is to login as Admin
	 * @param username
	 * @param password
	 * @return boolean
	 * @throws WrongCredentials
	 */
	boolean loginAdmin(String username , String password) throws WrongCredentials;
	
	/**
	 * Below Method is to Add Bus
	 * @param busInfo
	 * @return String
	 * @throws SomeThingWentWrong
	 */
	String addBus(BusInfo busInfo) throws SomeThingWentWrong;
	
	/**
	 *  Below Method is to Update Bus Details
	 * @param busNumber
	 * @param dep
	 * @param arr
	 * @return boolean
	 * @throws SomeThingWentWrong
	 */
	boolean updateBusDate(int busNumber, LocalDateTime dep , LocalDateTime arr) throws SomeThingWentWrong;
	
	/**
	 *  Below Method is to Remove Bus Details
	 * @param busNumber
	 * @return boolean
	 * @throws SomeThingWentWrong
	 */
	boolean removeBus(int busNumber) throws SomeThingWentWrong;
	
	/**
	 * Below Method is to get All Bus Details
	 * @return List<BusInfo>
	 * @throws SomeThingWentWrong
	 */
	List<BusInfo> getBusInfo() throws SomeThingWentWrong;

    /**
     * 	Below Method is to search bus by Destination
     * @param departure
     * @param arrival
     * @return List<BusInfo>
     * @throws SomeThingWentWrong
     */
	List<BusInfo> searchBusByDestination(String departure , String arrival) throws SomeThingWentWrong;
	
}
