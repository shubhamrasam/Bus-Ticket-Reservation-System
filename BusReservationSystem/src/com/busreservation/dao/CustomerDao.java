package com.busreservation.dao;

import java.util.List;

import com.busreservation.dto.BusInfo;
import com.busreservation.dto.Customer;
import com.busreservation.exception.CustomerNotFound;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;

public interface CustomerDao {

	boolean customerSignUp(Customer customer) throws SomeThingWentWrong;
	
	boolean customerLogin(String email , String password) throws SomeThingWentWrong , WrongCredentials;
	
	boolean changeCustomerPassword(int customerID , String newPassword) throws CustomerNotFound ,SomeThingWentWrong;
	
	List<Customer> getCustomersInfo() throws CustomerNotFound;
	
}
