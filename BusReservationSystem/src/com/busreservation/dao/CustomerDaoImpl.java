package com.busreservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.busreservation.colors.Colors;
import com.busreservation.dto.Customer;
import com.busreservation.dto.CustomerImpl;
import com.busreservation.exception.CustomerNotFound;
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;

public class CustomerDaoImpl implements CustomerDao {
    
	static List<Customer> getCustomerData(ResultSet result) throws SQLException{
		
		List<Customer> customers = new ArrayList<>();
		
		while(result.next()) {
			
		    Customer customer = new CustomerImpl();
		    
		    customer.setCustomerId(result.getInt("customerId"));
		    customer.setfName(result.getString("fName"));
		    customer.setlName(result.getString("lName"));
            customer.setMobile(result.getString("mobile"));
            customer.setEmail(result.getString("email"));
            customer.setPassword(result.getString("password"));
            
            customers.add(customer);
            
		}
		
		return customers;
	}
	
	static boolean isEmptyResult(ResultSet result) throws SQLException {
		
		return !result.isBeforeFirst() && result.getRow()==0? true : false;
	}
	
	@Override
	public boolean customerSignUp(Customer customer) throws SomeThingWentWrong , IllegalArgumentException {
		
		Connection connection = null;
		
		try {
			
			connection = DBUtils.createConnection();
			String insert_query = "INSERT into Customer(fName,lName,mobile,email,password) values (?,?,?,?,md5(?))";
			
			PreparedStatement statement = connection.prepareStatement(insert_query);
			
			statement.setString(1,customer.getfName());
			
			if(customer.getfName() == null) {
				
				throw new IllegalArgumentException(" Value of first name is not Valid ");
			}
			
			statement.setString(2,customer.getlName());
			
			if(customer.getlName() == null) {
				
				throw new IllegalArgumentException(" Value of last name is not Valid ");
			}
			
			statement.setString(3,customer.getMobile());
			
			if(customer.getMobile() == null) {
				
				throw new IllegalArgumentException(" Value of mobile is not Valid ");
			}
			
			
			statement.setString(4,customer.getEmail());
			
			if(customer.getEmail() == null) {
				
				throw new IllegalArgumentException(" Value of email is not Valid ");
			}
			
			
			statement.setString(5,customer.getPassword());
			
			if(customer.getPassword() == null) {
				
				throw new IllegalArgumentException(" Value of password is not Valid ");
			}
			

			int result = statement.executeUpdate();
			
			if(result <= 0) {
			   	throw new SomeThingWentWrong("Signup Fail! Try Again");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				DBUtils.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return true;
	}

	@Override
	public Customer customerLogin(String email, String password) throws SomeThingWentWrong, WrongCredentials {
		Connection connection = null;
		Customer cus = new CustomerImpl();
		try {
			
			connection = DBUtils.createConnection();
			String insert_query = "Select * from Customer where email = ? and password = md5(?)";
			
			PreparedStatement statement = connection.prepareStatement(insert_query);
			
			statement.setString(1,email);
			statement.setString(2,password);

			ResultSet result = statement.executeQuery();
			
			if(isEmptyResult(result)) {
			   	throw new WrongCredentials("Login Fail! Please Enter Valid Credentials");
			}
			
			if(result.next()) {
				
				cus.setCustomerId(result.getInt("customerId"));
			    cus.setfName(result.getString("fName"));
	            cus.setlName(result.getString("lName"));
	            cus.setMobile(result.getString("mobile"));
	            cus.setEmail(result.getString("email"));
	            cus.setPassword(result.getString("password"));
			
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				DBUtils.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return cus;
	}


	@Override
	public boolean changeCustomerPassword(String email, String newPassword) throws CustomerNotFound, SomeThingWentWrong {
		Connection connection = null;

		try {
			connection = DBUtils.createConnection();
			
			String update_query = "UPDATE customer set password = ? where email = ?";
			
			PreparedStatement statement = connection.prepareStatement(update_query);
			
			statement.setString(1,newPassword);
			statement.setString(2,email);
			
            int result	= statement.executeUpdate();
            
            if(result > 0) {
            	
                 
            	
            }else {
            	
            	throw new SomeThingWentWrong("Bus Not Found");
            	
            }   
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		    try {
				DBUtils.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
		return true;
	
	}

	@Override
	public List<Customer> getCustomersInfo() throws CustomerNotFound {
		
		Connection connection = null;
        List<Customer> customers = new ArrayList<>();
        
		try {
			connection = DBUtils.createConnection();
			
			String get_query = "SELECT * from Customer";
			
			PreparedStatement statement = connection.prepareStatement(get_query);
		
            ResultSet result = statement.executeQuery();
            
            if(isEmptyResult(result)) {
            	
            	throw new CustomerNotFound("Customers Not Found");
            }
            
            customers = getCustomerData(result);
              
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		    try {
				DBUtils.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
		return customers;
		
		
	}

	@Override
	public Customer getMyDetails(String email,int CustomerID) throws CustomerNotFound {
		
		Connection connection = null;
        Customer customer = new CustomerImpl();
        
		try {
			connection = DBUtils.createConnection();
			
			String get_query = "SELECT * from Customer where email = ? and customerId = ?";
			
			PreparedStatement statement = connection.prepareStatement(get_query);
		
			statement.setString(1, email);
			statement.setInt(2, CustomerID);
			
            ResultSet result = statement.executeQuery();
            
            if(isEmptyResult(result)) {
            	
            	throw new CustomerNotFound();
            }
            
            if(result.next()) {
            	
            	customer.setCustomerId(result.getInt("customerId"));
     		    customer.setfName(result.getString("fName"));
     		    customer.setlName(result.getString("lName"));
                customer.setMobile(result.getString("mobile"));
                customer.setEmail(result.getString("email"));
                customer.setPassword(result.getString("password"));
            	
            }
              
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		    try {
				DBUtils.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
		return customer;
	}
     
}
