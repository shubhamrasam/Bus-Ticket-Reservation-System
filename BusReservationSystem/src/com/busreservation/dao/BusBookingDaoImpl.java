package com.busreservation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.busreservation.colors.Colors;
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
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		List<Ticket> tickets = new ArrayList<>();
		
		while(result.next()) {
		
			Ticket ticket = new TicketImpl();
			
			ticket.setTicketNo(result.getInt("ticketNo"));
			ticket.setCustomerId(result.getInt("customerId"));
			ticket.setBusNumber(result.getInt("busNumber"));
			ticket.setDateOfBooking(LocalDateTime.parse(result.getString("DateOfBooking"), formatter));
			ticket.setDeparture(LocalDateTime.parse(result.getString("Departure"), formatter));
			ticket.setTotal_tickets(result.getInt("Total_tickets"));
			ticket.setTotal_fare(result.getInt("Total_fare"));
			ticket.setStatus(result.getBoolean("status"));
			
			tickets.add(ticket);
		}
		
		
		return tickets;
		
	}

	@Override
	public List<Ticket> getTicket(int customerId , int busNumber) throws NoTicketFound {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Connection connection = null;
		List<Ticket> tickets = null;
		try { 
			
			connection = DBUtils.createConnection();
			
			String get_query = "select * from busbooking where customerId = ? and busNumber = ?";
			
			PreparedStatement statement = connection.prepareStatement(get_query);
			
			statement.setInt(1,customerId);
			statement.setInt(2, busNumber);
			
			ResultSet result = statement.executeQuery();
			
			if(isEmptyResult(result)) {
				
				 throw new NoTicketFound();
				 
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
	public boolean confirmTicket(int ticketNo) throws NoTicketFound {
		
		Connection connection = null;
		boolean val = true;
		try { 
			
			connection = DBUtils.createConnection();
			
			String update_query = "Update BusBooking set status = ? where ticketNo = ? "; 
			
			PreparedStatement statement = connection.prepareStatement(update_query);
			
			statement.setBoolean(1, true);
			statement.setInt(2, ticketNo);
			
			int result = statement.executeUpdate();
			
			if(result <= 0) {
				val = false;
			   	throw new NoTicketFound();
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
		
		return val;
	}


	@Override
	public boolean confirmAllPendingReq() throws SomeThingWentWrong {
		
		Connection connection = null;
		
		try { 
			
			connection = DBUtils.createConnection();
			
			String update_query = "Update BusBooking set status = true where status = ? "; 
			
			PreparedStatement statement = connection.prepareStatement(update_query);
			
			statement.setBoolean(1, false);
			
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
			
			String get_query = "select * from BusBooking where status = false";
			
			PreparedStatement statement = connection.prepareStatement(get_query);
	
			
			ResultSet result = statement.executeQuery();
			
			if(isEmptyResult(result)) {
				 throw new NoTicketFound("No Record Found");
			}

		    tickets = getAllTickets(result);
			
			
		} catch (SQLException e) {
			
			e.getMessage();
			
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
	public boolean cancelBooking(int ticketNo , int customerID ,int BusNumber , int tickets) throws SomeThingWentWrong {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		BusInfo busInfo = new BusInfoImpl();
		Connection connection = null;
		boolean val = false;
		try { 
			
			connection = DBUtils.createConnection();
			
			
			
			String get_bus = "select * from busInfo where BusNo = ? ";
			
			PreparedStatement getStatement = connection.prepareStatement(get_bus);
			
			getStatement.setInt(1, BusNumber);
			
			ResultSet get_result = getStatement.executeQuery();
			
			if(isEmptyResult(get_result)) {
				
				throw new SomeThingWentWrong();
			}
			
			if(get_result.next()) {
				
				busInfo.setBusNo(get_result.getInt("BusNo"));
				busInfo.setBusType(get_result.getString("BusType"));
				busInfo.setDepfrom(get_result.getString("Depfrom"));
				busInfo.setArrto(get_result.getString("Arrto"));
				busInfo.setTotal_seats(get_result.getInt("Total_Seats"));
				busInfo.setBooked_seats(get_result.getInt("booked_seats"));
				busInfo.setAvaliable_seats(get_result.getInt("avaliable_seats"));
				busInfo.setDeparture(LocalDateTime.parse(get_result.getString("Dep") , formatter));
				busInfo.setArrival(LocalDateTime.parse(get_result.getString("ARR") , formatter));
				busInfo.setFare(get_result.getInt("Fare"));
				
			}
			
			
			String delete_query = "Delete from busBooking where ticketNo = ? and Departure > ? and customerId = ? ";
			
			PreparedStatement statement = connection.prepareStatement(delete_query);
			
			statement.setInt( 1, ticketNo );
			statement.setString(2, LocalDateTime.now().toString());
			statement.setInt( 3, customerID );
			
			int result = statement.executeUpdate();
			
			if(result > 0) {
				
				val = true;
				
			}else {
				
				throw new SomeThingWentWrong();
				
			}
			
			String update_query = "Update BusInfo set avaliable_seats = ? , booked_seats = ? where BusNo = ? ";
			
			PreparedStatement update_statement = connection.prepareStatement(update_query);
			
			update_statement.setInt(1, busInfo.getAvaliable_seats() + tickets );
			update_statement.setInt(2, busInfo.getBooked_seats() - tickets);
			update_statement.setInt(3, busInfo.getBusNo());
			
			int update_result = update_statement.executeUpdate();
			
			if(update_result > 0) {
				
				val = true;
				
			}else {
				throw new SomeThingWentWrong();				
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
		
		return val;
	}
	
	@Override
	public boolean bookTicket(int customerid, int busId, int tickets) throws BookingFail {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Connection connection = null;
		BusInfo busInfo = new BusInfoImpl();
		boolean val = false;
		
		try { 
			connection = DBUtils.createConnection();

			String get_bus = "select * from busInfo where BusNo = ? ";
			
			PreparedStatement getStatement = connection.prepareStatement(get_bus);
			
			getStatement.setInt(1, busId);
			
			ResultSet get_result = getStatement.executeQuery();
			
			if(isEmptyResult(get_result)) {
				
				throw new BookingFail("No Bus Found");
			}
			
			if(get_result.next()) {
				
				busInfo.setBusNo(get_result.getInt("BusNo"));
				busInfo.setBusType(get_result.getString("BusType"));
				busInfo.setDepfrom(get_result.getString("Depfrom"));
				busInfo.setArrto(get_result.getString("Arrto"));
				busInfo.setTotal_seats(get_result.getInt("Total_Seats"));
				busInfo.setBooked_seats(get_result.getInt("booked_seats"));
				busInfo.setAvaliable_seats(get_result.getInt("avaliable_seats"));
				busInfo.setDeparture(LocalDateTime.parse(get_result.getString("Dep") , formatter));
				busInfo.setArrival(LocalDateTime.parse(get_result.getString("ARR") , formatter));
				busInfo.setFare(get_result.getInt("Fare"));
				
			}
			
			
			String check_query = "Select * from BusInfo where BusNo=? and avaliable_seats >= ? and Dep > ?";
			
			PreparedStatement checkStatement =  connection.prepareStatement(check_query);
			
			checkStatement.setInt(1, busId);
			checkStatement.setInt(2, tickets);
			checkStatement.setString(3, LocalDateTime.now().toString());
			
			ResultSet check_result = checkStatement.executeQuery();
			
			if(isEmptyResult(check_result)) {
				
				throw new BookingFail("No Seats Available");
			}
			
			String insert_query = "Insert into busbooking (customerId,busNumber,DateOfBooking,Departure,Total_tickets,Total_fare,status) values (? , ? , ?, ? , ? , ? , ?)";
			
			PreparedStatement statement = connection.prepareStatement(insert_query);
			
			statement.setInt(1,customerid);
			statement.setInt(2,busInfo.getBusNo());
			statement.setString(3,LocalDateTime.now().toString());
			statement.setString(4,busInfo.getDeparture().toString());
			statement.setInt(5,tickets);
			statement.setInt(6,tickets*busInfo.getFare());
			statement.setBoolean(7,false);
			
			int result = statement.executeUpdate();
			
			if(result > 0) {
				
			}else {
				
				throw new BookingFail("Booking Fail");
				
			}
			
			String update_query = "Update BusInfo set avaliable_seats = ? , booked_seats = ? where BusNo = ? ";
			
			PreparedStatement update_statement = connection.prepareStatement(update_query);
			
			if(busInfo.getAvaliable_seats() + busInfo.getBooked_seats() > busInfo.getTotal_seats() ) {
				
				throw new BookingFail(); 
				
			}
			
			update_statement.setInt(1, busInfo.getAvaliable_seats() - tickets );
			update_statement.setInt(2, busInfo.getBooked_seats() + tickets);
			update_statement.setInt(3, busInfo.getBusNo());
			
			int update_result = update_statement.executeUpdate();
			
			if(update_result > 0) {
				
				val = true;
				
			}else {
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
		
		return val;
	}

	@Override
	public List<Ticket> getAllTicketList() throws NoTicketFound {
		Connection connection = null;
		
		List<Ticket> tickets = new ArrayList<>();
		
		try { 
			
			connection = DBUtils.createConnection();
			
			String get_query = "select * from busbooking";
			
			PreparedStatement statement = connection.prepareStatement(get_query);
	
			
			ResultSet result = statement.executeQuery();
			
			if(isEmptyResult(result)) {
				
				 throw new NoTicketFound();
				 
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


}
