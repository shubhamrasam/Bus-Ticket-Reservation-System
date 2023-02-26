package com.busreservation.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class TicketImpl implements Ticket {

	private int ticketNo;
	private int customerId;
	private int busNumber;
	private LocalDateTime DateOfBooking;
	private LocalDateTime Departure;
	private int Total_tickets;
	private int Total_fare;
	private boolean status;
	
	public TicketImpl() {};
	
	
	public TicketImpl(int ticketNo, int customerId, int busNumber, LocalDateTime dateOfBooking, LocalDateTime departure,
			int total_tickets, int total_fare, boolean status) {
		this.ticketNo = ticketNo;
		this.customerId = customerId;
		this.busNumber = busNumber;
		DateOfBooking = dateOfBooking;
		Departure = departure;
		Total_tickets = total_tickets;
		Total_fare = total_fare;
		this.status = status;
	}

 
	@Override
	public int getTicketNo() {
		return ticketNo;
	}
	@Override
	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}
	@Override
	public int getCustomerId() {
		return customerId;
	}
	@Override
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public int getBusNumber() {
		return busNumber;
	}
	@Override
	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}
	@Override
	public LocalDateTime getDateOfBooking() {
		return DateOfBooking;
	}
	@Override
	public void setDateOfBooking(LocalDateTime dateOfBooking) {
		DateOfBooking = dateOfBooking;
	}
	@Override
	public LocalDateTime getDeparture() {
		return Departure;
	}
	@Override
	public void setDeparture(LocalDateTime departure) {
		Departure = departure;
	}
	@Override
	public int getTotal_tickets() {
		return Total_tickets;
	}
	@Override
	public void setTotal_tickets(int total_tickets) {
		Total_tickets = total_tickets;
	}
	@Override
	public int getTotal_fare() {
		return Total_fare;
	}
	@Override
	public void setTotal_fare(int total_fare) {
		Total_fare = total_fare;
	}
	@Override
	public boolean isStatus() {
		return status;
	}
	@Override
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		
		return "TicketNo: " + ticketNo +"\n"
	           +"Customer Id: " + customerId +"\n"
	           +"Bus Number: " + busNumber +"\n"
			   +"Date Of Booking: " + DateOfBooking +"\n"
			   +"Departure: " + Departure + "\n"
			   +"Total_tickets: " + Total_tickets+ "\n"
			   +"Total_fare: " + Total_fare + "\n";
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(DateOfBooking, Departure, Total_fare, Total_tickets, busNumber, customerId, ticketNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketImpl other = (TicketImpl) obj;
		return Objects.equals(DateOfBooking, other.DateOfBooking) && Objects.equals(Departure, other.Departure)
				&& Total_fare == other.Total_fare && Total_tickets == other.Total_tickets
				&& busNumber == other.busNumber && customerId == other.customerId && ticketNo == other.ticketNo;
	}
	
	
	
	
}
