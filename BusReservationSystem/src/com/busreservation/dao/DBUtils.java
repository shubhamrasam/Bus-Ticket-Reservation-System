package com.busreservation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
    
	final static String url;
	final static String username; 
	final static String password;
	
	static {
	    
		try {	
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		
		ResourceBundle bundle = ResourceBundle.getBundle("dbDetails");
		
		url = bundle.getString("url");
		username = bundle.getString("username");
		password = bundle.getString("password");
		
	}
    
	static Connection createConnection() throws SQLException {	
		return DriverManager.getConnection(url, username, password);
	}
	
	static void closeConnection(Connection con) throws SQLException {
		if(con != null) {
            con.close();
		}	
	}
}


