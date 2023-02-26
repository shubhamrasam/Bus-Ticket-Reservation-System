package com.busreservation.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class BusInfoImpl implements BusInfo {
   
	private int busNo;
	private String busType;
	private String depfrom;
	private String arrto;
	private int total_seats;
	private int booked_seats;
	private int avaliable_seats;
	private LocalDateTime departure;
	private LocalDateTime arrival;
	private int fare;
	
	public BusInfoImpl() {};
	
	
	
	public BusInfoImpl(int busNo, String busType, String depfrom, String arrto, int total_seats, int booked_seats,
			int avaliable_seats, LocalDateTime departure, LocalDateTime arrival, int fare) {

		this.busNo = busNo;
		this.busType = busType;
		this.depfrom = depfrom;
		this.arrto = arrto;
		this.total_seats = total_seats;
		this.booked_seats = booked_seats;
		this.avaliable_seats = avaliable_seats;
		this.departure = departure;
		this.arrival = arrival;
		this.fare = fare;
	}



	public BusInfoImpl(int busNo, int booked_seats, int avaliable_seats, int fare) {

		this.busNo = busNo;
		this.booked_seats = booked_seats;
		this.avaliable_seats = avaliable_seats;
		this.fare = fare;
	}
	
	@Override
	public int getBusNo() {
		return busNo;
	}
	@Override
	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}
	@Override
	public String getDepfrom() {
		return depfrom;
	}
	@Override
	public void setDepfrom(String depfrom) {
		this.depfrom = depfrom;
	}
	@Override
	public String getArrto() {
		return arrto;
	}
	@Override
	public void setArrto(String arrto) {
		this.arrto = arrto;
	}
	@Override
	public int getTotal_seats() {
		return total_seats;
	}
	@Override
	public void setTotal_seats(int total_seats) {
		this.total_seats = total_seats;
	}
	@Override
	public int getBooked_seats() {
		return booked_seats;
	}
	@Override
	public void setBooked_seats(int booked_seats) {
		this.booked_seats = booked_seats;
	}
	@Override
	public int getAvaliable_seats() {
		return avaliable_seats;
	}
	@Override
	public void setAvaliable_seats(int avaliable_seats) {
		this.avaliable_seats = avaliable_seats;
	}
	@Override
	public LocalDateTime getDeparture() {
		return departure;
	}
	@Override
	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}
	@Override
	public LocalDateTime getArrival() {
		return arrival;
	}
	@Override
	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}
	@Override
	public int getFare() {
		return fare;
	}
	@Override
	public void setFare(int fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		 String val = "Bus Number: " + busNo + "\n" 
	                 +"Depfrom: " + depfrom +  "\n" 
				     +"Arrivalto: " + arrto +  "\n" 
	                 +"Total_seats: "+ total_seats + "\n" 
				     +"Booked_seats: " + booked_seats + "\n" 
	                 +"Avaliable_seats: " + avaliable_seats + "\n" 
				     +"Departure: " + departure + "\n" 
				     +"Arrival: " + arrival + "\n" 
				     +"Fare: " + fare + "\n";
		 
		 
		 return val;
	}

	@Override
	public int hashCode() {
		return Objects.hash(arrival, arrto, avaliable_seats, booked_seats, busNo, departure, depfrom, fare,
				total_seats);
	}
	@Override
	public String getBusType() {
		return busType;
	}

	@Override
	public void setBusType(String busType) {
		this.busType = busType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusInfoImpl other = (BusInfoImpl) obj;
		return Objects.equals(arrival, other.arrival) && Objects.equals(arrto, other.arrto)
				&& avaliable_seats == other.avaliable_seats && booked_seats == other.booked_seats
				&& busNo == other.busNo && Objects.equals(departure, other.departure)
				&& Objects.equals(depfrom, other.depfrom) && fare == other.fare && total_seats == other.total_seats;
	}
	
	
	
	
}
