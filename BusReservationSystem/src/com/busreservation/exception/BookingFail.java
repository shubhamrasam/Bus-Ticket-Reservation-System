package com.busreservation.exception;

public class BookingFail extends Exception {

	public BookingFail() {
		
	}
	
	public BookingFail(String message){
		super(message);
	}
}
