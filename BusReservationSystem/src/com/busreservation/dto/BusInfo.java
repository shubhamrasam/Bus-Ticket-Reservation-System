package com.busreservation.dto;

import java.time.LocalDateTime;

public interface BusInfo {
	
	/**
	 * Below Method is to Get Bus Number
	 * @return BusNumber
	 */
	public int getBusNo();
	
	/**
	 * Below Method is to Set Bus Number
	 * @param busNo
	 */
	public void setBusNo(int busNo);
	
    /**
     * Below Method is to Get Depfrom
     * @return Depfrom
     */
	public String getDepfrom();
	
	/**
	 * Below Method is to Set Depfrom
	 * @param Depfrom
	 */
	public void setDepfrom(String depfrom);
	
    /**
     * Below Method is to Get Arrto
     * @return Arrto
     */
	public String getArrto();
    
	/**
	 * Below Method is to Set Arrto
	 * @param Arrto
	 */
	public void setArrto(String arrto);
	
    /**
     * Below Method is to Get Total_seats
     * @return Total_seats
     */
	public int getTotal_seats();

	/**
	 * Below Method is to Set Total_seats
	 * @param Total_seats
	 */
	public void setTotal_seats(int total_seats);
	
    /**
     * Below Method is to Get Booked_seats
     * @return Booked_seats
     */
	public int getBooked_seats();
	
	/**
	 * Below Method is to Set Booked_seats
	 * @param Booked_seats
	 */
	public void setBooked_seats(int booked_seats);
	
	/**
	 * Below Method is to Get Avaliable_seats
	 * @return Avaliable_seats
	 */
	public int getAvaliable_seats();

	/**
	 * Below Method is to Set Avaliable_seats
	 * @param Avaliable_seats
	 */
	public void setAvaliable_seats(int avaliable_seats);
	
    /**
     * Below Method is to Get Departure
     * @return Departure
     */
	public LocalDateTime getDeparture();

	/**
	 * Below Method is to Set Departure
	 * @param Departure
	 */
	public void setDeparture(LocalDateTime departure);
	
    /**
     * Below Method is to Get Arrival
     * @return Arrival
     */
	public LocalDateTime getArrival();

	/**
	 * Below Method is to Set Arrival
	 * @param Arrival
	 */
	public void setArrival(LocalDateTime arrival);

	 /**
     * Below Method is to Get Fare
     * @return Fare
     */
	public int getFare();

	/**
	 * Below Method is to Set Fare
	 * @param Fare
	 */
	public void setFare(int fare);
	
	 /**
     * Below Method is to Get BusType
     * @return BusType
     */
	public String getBusType();

	/**
	 * Below Method is to Set BusTypes
	 * @param BusTypes
	 */
	public void setBusType(String busType);
}
