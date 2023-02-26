package com.busreservation.exception;

public class WrongCredentials extends Exception {

	public WrongCredentials() {
		
	}
	
	public WrongCredentials(String message){
		super(message);
	}
	
}
