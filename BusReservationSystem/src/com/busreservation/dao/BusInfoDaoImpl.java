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
import com.busreservation.exception.SomeThingWentWrong;
import com.busreservation.exception.WrongCredentials;

public class BusInfoDaoImpl implements BusInfoDao {
	
	static boolean isEmptyResult(ResultSet result) throws SQLException {
		
		return !result.isBeforeFirst() && result.getRow()==0? true : false;
	}
	
	static List<BusInfo> getBusInfo(ResultSet result) throws SQLException{
		
		List<BusInfo> buses = new ArrayList<>();
		
		while(result.next()) {
			
		    BusInfo bus = new BusInfoImpl();
		    
		    bus.setBusNo(result.getInt("BusNo"));
		    bus.setDepfrom(result.getString("Depfrom"));
		    bus.setArrto(result.getString("Arrto"));
		    bus.setTotal_seats(result.getInt("Total_Seats"));
		    bus.setBooked_seats(result.getInt("booked_seats"));
		    bus.setAvaliable_seats(result.getInt("avaliable_seats"));
		    bus.setDeparture(LocalDateTime.parse(result.getString("Dep")));
		    bus.setArrival(LocalDateTime.parse(result.getString("ARR")));
		    bus.setFare(result.getInt("Fare"));
		    
            buses.add(bus);
            
		}
		
		return buses;
	}
	
	@Override
	public boolean loginAdmin(String username, String password) throws WrongCredentials {
		
		if( !username.equals("admin") || !password.equals("admin")) {
			throw new WrongCredentials("Please Enter Correct Username and Password");
		}
		return true;
	}

	@Override
	public String addBus(BusInfo busInfo) throws SomeThingWentWrong {
		Connection connection = null;
		String val = "";
		try {
			
			connection = DBUtils.createConnection();
			String insert_query = "INSERT into BusInfo (BusNo,Depfrom,Arrto,Total_Seats,booked_seats,avaliable_seats,Dep ,ARR ,Fare) values (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(insert_query);
			
			statement.setInt(1,busInfo.getBusNo());
			statement.setString(2,busInfo.getDepfrom());
			statement.setString(3,busInfo.getArrto());
			statement.setInt(4,busInfo.getTotal_seats());
			statement.setInt(5,busInfo.getBooked_seats());
			statement.setInt(6,busInfo.getAvaliable_seats());
			statement.setDate(7, Date.valueOf(busInfo.getDeparture().toString()));
			statement.setDate(8,Date.valueOf(busInfo.getArrival().toString()) );
			statement.setInt(9,busInfo.getFare());

			int result = statement.executeUpdate();
			
			if(result > 0) {
			   	throw new SomeThingWentWrong("Some Thing Went Wrong Please Try Again");
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
	public boolean updateBusDate(int busNumber, LocalDateTime dep , LocalDateTime arr) throws SomeThingWentWrong {

		Connection connection = null;

		try {
			connection = DBUtils.createConnection();
			
			String update_query = "UPDATE BusInfo set Dep= ? , set ARR = ? where BusNo = ?";
			
			PreparedStatement statement = connection.prepareStatement(update_query);
			
			statement.setDate(1,Date.valueOf(dep.toString()));
			statement.setDate(2,Date.valueOf(arr.toString()));
			statement.setInt(3,busNumber);
			
            int result	= statement.executeUpdate();
            
            if(result > 0) {
            	
                 System.out.println("Bus Date Updated Successfully");
            	
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
	public boolean removeBus(int busNumber) throws SomeThingWentWrong {
		
		Connection connection = null;

		try {
			connection = DBUtils.createConnection();
			
			String delete_query = "Delete from BusInfo where BusNo = ?";
			
			PreparedStatement statement = connection.prepareStatement(delete_query);
			
			statement.setInt(1,busNumber);
			
            int result	= statement.executeUpdate();
            
            if(result > 0) {
            	
                 System.out.println("Bus Deleted Successfully");
            	
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
	public List<BusInfo> getBusInfo() throws SomeThingWentWrong {
		Connection connection = null;
        List<BusInfo> Buses = new ArrayList<>();
        
		try {
			connection = DBUtils.createConnection();
			
			String get_query = "SELECT * from Businfo";
			
			PreparedStatement statement = connection.prepareStatement(get_query);
		
            ResultSet result = statement.executeQuery();
            
            if(isEmptyResult(result)) {
            	
            	throw new SomeThingWentWrong("Bus Not Found");
            }
            
            Buses = getBusInfo(result);
              
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		    try {
				DBUtils.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
		return Buses;
	}

	@Override
	public List<BusInfo> searchBusByDestination(String departure, String arrival) throws SomeThingWentWrong {
		Connection connection = null;
        List<BusInfo> Buses = new ArrayList<>();
        
		try {
			connection = DBUtils.createConnection();
			
			String get_query = "SELECT * from Businfo where Depfrom = ? and Arrto = ? and Dep > ?";
			
			PreparedStatement statement = connection.prepareStatement(get_query);
		    
			statement.setString(1, departure);
			statement.setString(2, arrival);
			statement.setDate(3, Date.valueOf(LocalDateTime.now().toString()));
			
            ResultSet result = statement.executeQuery();
            
            if(isEmptyResult(result)) {
            	
            	throw new SomeThingWentWrong("Bus Not Found");
            }
            
            Buses = getBusInfo(result);
              
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		    try {
				DBUtils.closeConnection(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	
		return Buses;
	}

	
	
	
}
