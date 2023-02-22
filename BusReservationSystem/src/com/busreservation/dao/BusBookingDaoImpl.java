package com.busreservation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.busreservation.dto.BusInfo;
import com.busreservation.exception.BookingFail;

public class BusBookingDaoImpl implements BusBookingDao {

	@Override
	public boolean bookTicket(int customerid, BusInfo busInfo , int tickets) throws BookingFail {
		Connection connection = null;
		
		try { 
			
			connection = DBUtils.createConnection();
			
			String check_query = "Select * from BusInfo where BusNo=? and avaliable_seats >= ? and Dep > ?";
			
			PreparedStatement checkStatement =  connection.prepareStatement(check_query);
			
			checkStatement.setInt(1, busInfo.getBusNo());
			checkStatement.setInt(2, tickets);
			checkStatement.setDate(3, Date.valueOf(LocalDateTime.now().toString()));
			
			int check_result = checkStatement.executeUpdate();
			
			if(check_result <= 0) {
				
				throw new BookingFail("No Seats Available");
			}
			
			String insert_query = "Insert into busbooking (customerId,busNumber,DateOfBooking,Total_tickets,Total_fare) values (? , ? , ? , ? , ?)";
			
			PreparedStatement statement = connection.prepareStatement(insert_query);
			
			statement.setInt(1,customerid);
			statement.setInt(2,busInfo.getBusNo());
			statement.setDate(3,Date.valueOf(LocalDateTime.now().toString()));
			statement.setInt(4,tickets);
			statement.setInt(5,tickets*busInfo.getFare());

			int result = statement.executeUpdate();
			
			if(result > 0) {
				
			   	throw new BookingFail("Booking Fail");
			}
			
			String update_query = "Update BusInfo set avaliable_seats = ? , booked_seats = ? where BusNo = ? ";
			
			PreparedStatement update_statement = connection.prepareStatement(update_query);
			
			update_statement.setInt(1, busInfo.getAvaliable_seats() - tickets );
			update_statement.setInt(2, busInfo.getBooked_seats() + tickets);
			update_statement.setInt(3, busInfo.getBusNo());
			
			int update_result = statement.executeUpdate();
			
			if(update_result <= 0) {
				
			   	throw new BookingFail("Booking Fail");
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

}