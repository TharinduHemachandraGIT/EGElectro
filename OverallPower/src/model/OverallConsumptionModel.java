package model;

import java.sql.*;

public class OverallConsumptionModel {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/overallpower", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
	public String insertData(String District, int OverallUnits, String ConsumptionStatus)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
				{
					return "Error while connecting to the database for inserting."; 
				}
			// create a prepared statement
			String query = " insert into overallconsumption (`RecID`,`District`,`OverallUnits`,`ConsumptionStatus`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, District);
			preparedStmt.setInt(3, OverallUnits);
			preparedStmt.setString(4, ConsumptionStatus);
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			output = "Record Created Successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the record.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String retrieveData()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>RecID</th><th>District</th>" +
					"<th>OverallUnits</th>" +
					"<th>ConsumptionStatus</th>";
			
			String query = "select * from overallconsumption";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{	
				String RecID = Integer.toString(rs.getInt("RecID"));
				String District = rs.getString("District");
				int OverallUnits = rs.getInt("OverallUnits");
				String ConsumptionStatus = rs.getString("ConsumptionStatus");
				
				// Add into the html table
				output += "<td>" + RecID + "</td>";
				output += "<td>" + District + "</td>";
				output += "<td>" + OverallUnits + "</td>";
				output += "<tr><td>" + ConsumptionStatus + "</td>";
			}
			
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	public String deleteUnits(int RecID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			// create a prepared statement
			String query = "delete from overallconsumption where RecID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, RecID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the units.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		
		
	public String updateUnits(int RecID, String District, int OverallUnits, String ConsumptionStatus)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for updating."; 
			 }
			 // create a prepared statement
			 String query = "UPDATE overallconsumption SET District=?,OverallUnits=?,ConsumptionStatus=?"
			 		+ "WHERE RecID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, District);
			 preparedStmt.setInt(2, OverallUnits);
			 preparedStmt.setString(3, ConsumptionStatus);
			 preparedStmt.setInt(4, RecID);
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the limit.";
			 System.err.println(e.getMessage());
			 }
			 return output;
		}
}

