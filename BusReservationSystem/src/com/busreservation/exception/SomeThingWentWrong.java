package com.busreservation.exception;

public class SomeThingWentWrong extends Exception {

	public SomeThingWentWrong() {
		
	}
	
	public SomeThingWentWrong(String message){
		super(message);
	}
	
}
