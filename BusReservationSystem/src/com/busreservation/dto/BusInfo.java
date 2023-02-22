package com.busreservation.dto;

import java.time.LocalDateTime;

public interface BusInfo {
	public int getBusNo();
	
	public void setBusNo(int busNo);

	public String getDepfrom();

	public void setDepfrom(String depfrom);

	public String getArrto();

	public void setArrto(String arrto);

	public int getTotal_seats();

	public void setTotal_seats(int total_seats);

	public int getBooked_seats();
	
	public void setBooked_seats(int booked_seats);

	public int getAvaliable_seats();

	public void setAvaliable_seats(int avaliable_seats);

	public LocalDateTime getDeparture();

	public void setDeparture(LocalDateTime departure);

	public LocalDateTime getArrival();

	public void setArrival(LocalDateTime arrival);

	public int getFare();

	public void setFare(int fare);
}
