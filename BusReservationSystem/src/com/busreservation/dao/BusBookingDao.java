package com.busreservation.dao;

import java.util.List;

import com.busreservation.dto.BusInfo;
import com.busreservation.dto.Ticket;
import com.busreservation.exception.BookingFail;
import com.busreservation.exception.NoTicketFound;
import com.busreservation.exception.SomeThingWentWrong;

public interface BusBookingDao {

	/**
	 *  Below Method is to Book Ticket
	 * @param customerid
	 * @param busId
	 * @param tickets
	 * @return boolean
	 * @throws BookingFail
	 */
	boolean bookTicket(int customerid , int busId , int tickets) throws BookingFail;
	
	/**
	 * Below Method is to get Book Ticket
	 * @param customerid
	 * @return Ticket
	 * @throws NoTicketFound
	 */
	Ticket getTicket(int customerid) throws NoTicketFound;
	
	/**
	 * Below Method is to get cancel Book Ticket
	 * @param ticketNo
	 * @param customerID
	 * @param BusNumber
	 * @param tickets
	 * @return boolean
	 * @throws SomeThingWentWrong
	 */
	boolean cancelBooking(int ticketNo , int customerID , int BusNumber, int tickets) throws SomeThingWentWrong;
	
	/**
	 * Below Method is to Confirm Book Ticket
	 * @param ticketNo
	 * @return boolean
	 * @throws NoTicketFound
	 */
	boolean confirmTicket(int ticketNo) throws NoTicketFound;
	
	/**
	 * Below Method is to get Pending Ticket Request
	 * @return List<Ticket>
	 * @throws NoTicketFound
	 */
	List<Ticket> getPedingTicketReq() throws NoTicketFound;
	
	/**
	 * Below Method is to get All Ticket
	 * @return List<Ticket>
	 * @throws NoTicketFound
	 */
	List<Ticket> getAllTicketList() throws NoTicketFound;
	
	/**
	 * Below Method is to Confirm all pending Ticket request
	 * @return boolean
	 * @throws SomeThingWentWrong
	 */
	boolean confirmAllPendingReq() throws SomeThingWentWrong;
	
}
