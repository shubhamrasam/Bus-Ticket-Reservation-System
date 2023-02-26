package com.busreservation.dto;

public interface Customer {
	
	/**
	 * Below Method is to Get CustomerId
	 * @return CustomerId
	 */
	public int getCustomerId();
	
    /**
     * Below Method is to Set CustomerId
     * @param customerId
     */
	public void setCustomerId(int customerId);
	
	/**
	 * Below Method is to Get First Name
	 * @return FirstName
	 */
	public String getfName();
	
    /**
     * Below Method is to Set First Name
     * @param fName
     */
	public void setfName(String fName);
	
	/**
	 * Below Method is to Get Last Name
	 * @return LastName
	 */
	public String getlName();
	
    /**
     *  Below Method is to Set Last Name
     * @param lName
     */
	public void setlName(String lName);
	
	/**
	 * Below Method is to Get Mobile
	 * @return Mobile
	 */
	public String getMobile();
	
    /**
     * Below Method is to Set Mobile
     * @param mobile
     */
	public void setMobile(String mobile);
	
	/**
	 * Below Method is to Get Email
	 * @return Email
	 */
	public String getEmail();
	
    /**
     * Below Method is to Set Email
     * @param email
     */
	public void setEmail(String email);
	
	/**
	 * Below Method is to Get Password
	 * @return Password
	 */
	public String getPassword();
	
    /**
     * Below Method is to Set Password
     * @param password
     */
	public void setPassword(String password);
	
}
