package com.busreservation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.busreservation.dto.BusInfo;
import com.busreservation.dto.BusInfoImpl;
import com.busreservation.dto.Ticket;
import com.busreservation.dto.TicketImpl;
import com.busreservation.exception.BookingFail;
import com.busreservation.exception.NoTicketFound;
import com.busreservation.exception.SomeThingWentWrong;

public class BusBookingDaoImpl implements BusBookingDao {
	
	static boolean isEmptyResult(ResultSet result) throws SQLException {
		
		return !result.isBeforeFirst() && result.getRow()==0? true : false;
	}
	
	static List<Ticket> getAllTickets(ResultSet result) throws SQLException{
		
		List<Ticket> tickets = new ArrayList<>();
		
		if(result.next()) {
			
			Ticket ticket = new TicketImpl();
			
			ticket.setTicketNo(result.getInt("ticketNo"));
			ticket.setCustomerId(result.getInt("customerId"));
			ticket.setBusNumber(result.getInt("busNumber"));
			ticket.setDateOfBooking(LocalDateTime.parse(result.getString("DateOfBooking")));
			ticket.setDeparture(LocalDateTime.parse(result.getString("Departure")));
			ticket.setTotal_tickets(result.getInt("Total_tickets"));
			ticket.setTotal_fare(result.getInt("Total_fare"));
			ticket.setStatus(result.getBoolean("status"));
			
			tickets.add(ticket);
		}
		
		
		return tickets;
		
	}
	
//	@Override
//	public boolean bookTicket(int customerid, BusInfo busInfo , int tickets) throws BookingFail {
//		Connection connection = null;
//		
//		try { 
//			
//			connection = DBUtils.createConnection();
//			
//			String check_query = "Select * from BusInfo where BusNo=? and avaliable_seats >= ? and Dep > ?";
//			
//			PreparedStatement checkStatement =  connection.prepareStatement(check_query);
//			
//			checkStatement.setInt(1, busInfo.getBusNo());
//			checkStatement.setInt(2, tickets);
//			checkStatement.setDate(3, Date.valueOf(LocalDateTime.now().toString()));
//			
//			int check_result = checkStatement.executeUpdate();
//			
//			if(check_result <= 0) {
//				
//				throw new BookingFail("No Seats Available");
//			}
//			
//			String insert_query = "Insert into busbooking (customerId,busNumber,DateOfBooking,Departure,Total_tickets,Total_fare,status) values (? , ? ,?, ? , ? , ? , ?)";
//			
//			PreparedStatement statement = connection.prepareStatement(insert_query);
//			
//			statement.setInt(1,customerid);
//			statement.setInt(2,busInfo.getBusNo());
//			statement.setDate(3,Date.valueOf(LocalDateTime.now().toString()));
//			statement.setDate(4,Date.valueOf(busInfo.getDeparture().toString()));
//			statement.setInt(5,tickets);
//			statement.setInt(6,tickets*busInfo.getFare());
//			statement.setBoolean(7,false);
//			
//			int result = statement.executeUpdate();
//			
//			if(result > 0) {
//				
//			   	throw new BookingFail("Booking Fail");
//			}
//			
//			String update_query = "Update BusInfo set avaliable_seats = ? , booked_seats = ? where BusNo = ? ";
//			
//			PreparedStatement update_statement = connection.prepareStatement(update_query);
//			
//			update_statement.setInt(1, busInfo.getAvaliable_seats() - tickets );
//			update_statement.setInt(2, busInfo.getBooked_seats() + tickets);
//			update_statement.setInt(3, busInfo.getBusNo());
//			
//			int update_result = statement.executeUpdate();
//			
//			if(update_result <= 0) {
//				
//			   	throw new BookingFail("Booking Fail");
//			}
//			
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			
//		}finally {
//			
//			try {
//				DBUtils.closeConnection(connection);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		
//		return true;
//	}

	@Override
	public boolean cancelBooking(int ticketNo) throws SomeThingWentWrong {
		
		Connection connection = null;
		
		try { 
			
			connection = DBUtils.createConnection();
			
			String delete_query = "Delete from busBooking where ticketNo = ? and dep > ? ";
			
			PreparedStatement statement = connection.prepareStatement(delete_query);
			
			statement.setInt(1,ticketNo );
			statement.setDate(2, Date.valueOf(LocalDateTime.now().toString()));
			
			int result = statement.executeUpdate();
			
			if(result <= 0) {
				
			   	throw new SomeThingWentWrong("You Cannot Cancel Ticket Now");
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
	public Ticket getTicket(int ticketNo) throws NoTicketFound {
		
		Connection connection = null;
		Ticket ticket = new TicketImpl();
		try { 
			
			connection = DBUtils.createConnection();
			
			String get_query = "select * from busbooking where ticketNo = ?";
			
			PreparedStatement statement = connection.prepareStatement(get_query);
			
			statement.setInt(1,ticketNo);
			
			ResultSet result = statement.executeQuery();
			
			if(isEmptyResult(result)) {
				 throw new NoTicketFound("Wrong Ticket Number");
			}
			
			if(result.next()) {
				
				ticket.setTicketNo(result.getInt("ticketNo"));
				ticket.setCustomerId(result.getInt("customerId"));
				ticket.setBusNumber(result.getInt("busNumber"));
				ticket.setDateOfBooking(LocalDateTime.parse(result.getString("DateOfBooking")));
				ticket.setDeparture(LocalDateTime.parse(result.getString("Departure")));
				ticket.setTotal_tickets(result.getInt("Total_tickets"));
				ticket.setTotal_fare(result.getInt("Total_fare"));
				ticket.setStatus(result.getBoolean("status"));
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
		
		return ticket;
	}

	@Override
	public boolean confirmTicket(int ticketNo) throws NoTicketFound {
		
		Connection connection = null;
		
		try { 
			
			connection = DBUtils.createConnection();
			
			String update_query = "Update BusBooking set status = ? where BusNo = ? "; 
			
			PreparedStatement statement = connection.prepareStatement(update_query);
			
			statement.setBoolean(1, true);
			statement.setInt(2, ticketNo);
			
			int result = statement.executeUpdate();
			
			if(result <= 0) {
				
			   	throw new NoTicketFound("No Ticket found");
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
	public boolean confirmAllPendingReq() throws SomeThingWentWrong {
		
		Connection connection = null;
		
		try { 
			
			connection = DBUtils.createConnection();
			
			String update_query = "Update BusBooking set status = true where status = ? "; 
			
			PreparedStatement statement = connection.prepareStatement(update_query);
			
			statement.setBoolean(1, true);
			
			int result = statement.executeUpdate();
			
			if(result <= 0) {
				
			   	throw new SomeThingWentWrong("No Record found");
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
	public List<Ticket> getPedingTicketReq() throws NoTicketFound {
		Connection connection = null;
		
		List<Ticket> tickets = new ArrayList<>();
		
		try { 
			
			connection = DBUtils.createConnection();
			
			String get_query = "select * from BusBooking where status = ? ";
			
			PreparedStatement statement = connection.prepareStatement(get_query);
			
			statement.setBoolean(1, false);
			
			ResultSet result = statement.executeQuery();
			
			if(isEmptyResult(result)) {
				 throw new NoTicketFound("No Record Found");
			}

		    tickets = getAllTickets(result);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				DBUtils.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
        
	   return tickets;
	}

	@Override
	public boolean bookTicket(int customerid, int busId, int tickets) throws BookingFail {
		
		Connection connection = null;
		BusInfo busInfo = new BusInfoImpl();
		try { 
			connection = DBUtils.createConnection();

			String get_bus = "select * from busInfo where BusNo = ? ";
			
			PreparedStatement getStatement = connection.prepareStatement(get_bus);
			
			ResultSet get_result = getStatement.executeQuery();
			
			if(isEmptyResult(get_result)) {
				
				throw new BookingFail("No Bus Found");
			}
			
			if(get_result.next()) {
				
				busInfo.setBusNo(get_result.getInt("BusNo"));
				busInfo.setDepfrom(get_result.getString("Depfrom"));
				busInfo.setArrto(get_result.getString("Arrto"));
				busInfo.setTotal_seats(get_result.getInt("Total_Seats"));
				busInfo.setBooked_seats(get_result.getInt("booked_seats"));
				busInfo.setAvaliable_seats(get_result.getInt("avaliable_seats"));
				busInfo.setDeparture(LocalDateTime.parse(get_result.getString("Dep")));
				busInfo.setArrival(LocalDateTime.parse(get_result.getString("ARR")));
				busInfo.setFare(get_result.getInt("Fare"));
				
			}
			
			
			String check_query = "Select * from BusInfo where BusNo=? and avaliable_seats >= ? and Dep > ?";
			
			PreparedStatement checkStatement =  connection.prepareStatement(check_query);
			
			checkStatement.setInt(1, busInfo.getBusNo());
			checkStatement.setInt(2, tickets);
			checkStatement.setDate(3, Date.valueOf(LocalDateTime.now().toString()));
			
			int check_result = checkStatement.executeUpdate();
			
			if(check_result <= 0) {
				
				throw new BookingFail("No Seats Available");
			}
			
			String insert_query = "Insert into busbooking (customerId,busNumber,DateOfBooking,Departure,Total_tickets,Total_fare,status) values (? , ? ,?, ? , ? , ? , ?)";
			
			PreparedStatement statement = connection.prepareStatement(insert_query);
			
			statement.setInt(1,customerid);
			statement.setInt(2,busInfo.getBusNo());
			statement.setDate(3,Date.valueOf(LocalDateTime.now().toString()));
			statement.setDate(4,Date.valueOf(busInfo.getDeparture().toString()));
			statement.setInt(5,tickets);
			statement.setInt(6,tickets*busInfo.getFare());
			statement.setBoolean(7,false);
			
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
