package com.busreservation.dao;

import java.util.List;

import com.busreservation.dto.BusInfo;
import com.busreservation.dto.Ticket;
import com.busreservation.exception.BookingFail;
import com.busreservation.exception.NoTicketFound;
import com.busreservation.exception.SomeThingWentWrong;

public interface BusBookingDao {

	boolean bookTicket(int customerid , BusInfo busInfo , int tickets) throws BookingFail;
	
	Ticket getTicket(int ticketNo) throws NoTicketFound;
	
	boolean cancelBooking(int ticketNo) throws SomeThingWentWrong;
	
	boolean confirmTicket(int ticketNo) throws NoTicketFound;
	
	List<Ticket> getPedingTicketReq() throws NoTicketFound;
	
	boolean confirmAllPendingReq() throws SomeThingWentWrong;
	
}
