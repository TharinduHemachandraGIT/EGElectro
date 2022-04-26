package model;

import java.sql.*;

public class Limits {
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
	
	public String insertData(String district, int unit_limit, String issued_date)
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
			String query = "insert into limits (limitID,district,unit_limit,issued_date)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, district);
			preparedStmt.setInt(3, unit_limit);
			preparedStmt.setString(4, issued_date);
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			output = "Limit Created Successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the item.";
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
			output = "<table border='1'><tr><th>limitId</th><th>district</th>" +
					"<th>unit_limit</th>" +
					"<th>issued_date</th>";
			
			String query = "select * from limits";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{	
				String limitId = Integer.toString(rs.getInt("limitId"));
				String district = rs.getString("district");
				int unit_limit = rs.getInt("unit_limit");
				String issued_date = rs.getString("issued_date");
				
				// Add into the html table
				output += "<td>" + limitId + "</td>";
				output += "<td>" + district + "</td>";
				output += "<td>" + unit_limit + "</td>";
				output += "<tr><td>" + issued_date + "</td>";
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
	
	
	public String deleteLimit(int limitId)
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
			String query = "delete from limits where limitId = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, limitId);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the limit.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		
		
	public String updateLimit(int limitId, String district, int unit_limit, String issued_date)
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
			 String query = "UPDATE limits SET district=?,unit_limit=?,issued_date=?"
			 		+ "WHERE limitId=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, district);
			 preparedStmt.setInt(2, unit_limit);
			 preparedStmt.setString(3, issued_date);
			 preparedStmt.setInt(4, limitId);
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
