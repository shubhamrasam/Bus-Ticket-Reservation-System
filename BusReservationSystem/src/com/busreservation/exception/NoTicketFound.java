package com.busreservation.exception;

public class NoTicketFound extends Exception {

	public NoTicketFound() {
		
	}
	
	public NoTicketFound(String message) {
		super(message);
	}
	
}
