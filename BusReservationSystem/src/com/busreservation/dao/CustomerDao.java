package com.busreservation.dao;

import java.util.List;

import com.busreservation.dto.BusInfo;
import com.busreservation.dto.Customer;
import com.busreservation.exception.CustomerNotFound;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;

public interface CustomerDao {

	/**
	 *  Below Method is to create new Customer account 
	 * @param customer
	 * @return boolean
	 * @throws SomeThingWentWrong , IllegalArgumentException
	 */
	boolean customerSignUp(Customer customer) throws SomeThingWentWrong , IllegalArgumentException;
	
	/**
	 *  Below Method is to login Customer account 
	 * @param email
	 * @param password
	 * @return boolean 
	 * @throws SomeThingWentWrong
	 * @throws WrongCredentials
	 */
	Customer customerLogin(String email , String password) throws SomeThingWentWrong , WrongCredentials;
	
	/**
	 *  Below Method is to Change Customers password using email
	 * @param email
	 * @param newPassword
	 * @return boolean Value
	 * @throws CustomerNotFound
	 * @throws SomeThingWentWrong
	 */
	boolean changeCustomerPassword(String email , String newPassword) throws CustomerNotFound ,SomeThingWentWrong;
	
	/**
	 * Below Method is to get List of Customers
	 * @return List<Customer>
	 * @throws CustomerNotFound
	 */
	List<Customer> getCustomersInfo() throws CustomerNotFound;
	
	/**
	 * Below Method is to Get Details of Customer
	 * @param email
	 * @param CustomerID
	 * @return Customer
	 * @throws CustomerNotFound
	 */
	Customer getMyDetails(String email,int CustomerID)throws CustomerNotFound;
	
}
