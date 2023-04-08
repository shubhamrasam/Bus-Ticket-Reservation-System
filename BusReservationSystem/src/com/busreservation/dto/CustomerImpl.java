package com.busreservation.dto;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerImpl implements Customer {

	private int customerId;
	private String fName;
	private String lName;
	private String mobile;
	private String email;
	private String password;
	
	public CustomerImpl() {};
	
	public CustomerImpl(int customerId, String fName, String lName, String mobile, String email, String password) {
		this.customerId = customerId;
		this.fName = fName;
		this.lName = lName;
		
	    String mobileRegex = "[6-9]{1}[0-9]{9}";
		
		Pattern patternMobile = Pattern.compile(mobileRegex); 
		
		Matcher matcherMobile = patternMobile.matcher(mobile);
		
		if(matcherMobile.matches()) {
			
			this.mobile = mobile;
			
		}
		String regex = "^(.+)@(.+)$";  
		
		Pattern pattern = Pattern.compile(regex);  
		
        Matcher matcher = pattern.matcher(email); 
        
        if(matcher.matches()) {
        	
        	this .email = email;
        	
        }
		this.password = password;
	}
	
	public CustomerImpl( String fName, String lName, String mobile, String email, String password) {
		
		this.fName = fName;
		this.lName = lName;
		
	    String mobileRegex = "[6-9]{1}[0-9]{9}";
		
		Pattern patternMobile = Pattern.compile(mobileRegex); 
		
		Matcher matcherMobile = patternMobile.matcher(mobile);
		
		if(matcherMobile.matches()) {
			
			this.mobile = mobile;
			
		}
		
		String regex = "^(.+)@(.+)$";   
		
		Pattern pattern = Pattern.compile(regex);  
		
        Matcher matcher = pattern.matcher(email); 
        
        if(matcher.matches()) {
        	
        	this .email = email;
        	
        }
		
		this.password = password;
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
	public String getfName() {
		return fName;
	}
	@Override
	public void setfName(String fName) {
		this.fName = fName;
	}
	@Override
	public String getlName() {
		return lName;
	}
	@Override
	public void setlName(String lName) {
		this.lName = lName;
	}
	@Override
	public String getMobile() {
		return mobile;
	}
	@Override
	public void setMobile(String mobile) {
		
		String mobileRegex = "[6-9]{1}[0-9]{9}";
		
		Pattern patternMobile = Pattern.compile(mobileRegex); 
		
		Matcher matcherMobile = patternMobile.matcher(mobile);
		
		if(matcherMobile.matches()) {
			
			this.mobile = mobile;
			
		}
		
	}
	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public void setEmail(String email) {
		
		String regex = "^(.+)@(.+)$"; 
		
		Pattern pattern = Pattern.compile(regex);  
		
        Matcher matcher = pattern.matcher(email); 
        
        if(matcher.matches()) {
        	
        	this .email = email;
        	
        }
		
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		
		return "CustomerId: " + customerId +"\n" 
	           +"FirstName: " + fName + "\n"
			   +"LastName: " + lName +"\n"
	           +"Mobile: " + mobile + "\n"
			   +"Email: " + email +"\n"
	           +"Password: " + password + "\n";
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, email, fName, lName, mobile, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerImpl other = (CustomerImpl) obj;
		return customerId == other.customerId && Objects.equals(email, other.email)
				&& Objects.equals(fName, other.fName) && Objects.equals(lName, other.lName) && mobile == other.mobile
				&& Objects.equals(password, other.password);
	}
	
	
	
	
}
