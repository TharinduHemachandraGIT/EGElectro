package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UnitLimitModel {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powerconsumptiondb", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
	public String insertData(String connectionType, int unitLimit, String IssuedDate)
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
			String query = " insert into unitlimits (limitID,connectionType,unitLimit,IssuedDate)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, connectionType);
			preparedStmt.setInt(3, unitLimit);
			preparedStmt.setString(4, IssuedDate);
			
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
				return "Connected to database for Retrieving Data"; 
			}
			
			// Prepare the html table to be displayed
			output = "<table><tr><th>RecID</th><th>Item Name</th><th>Item Price</th><th>Item Description</th></tr><tr>";
			
			String query = "select * from unitlimits";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{	
				String limitID = Integer.toString(rs.getInt("limitID"));
				String connectionType1 = rs.getString("connectionType");
				int unitLimit = rs.getInt("unitLimit");
				String IssuedDate = rs.getString("IssuedDate");
				
				// Add into the html table
				output += "<td>" + limitID + "</td>";
				output += "<td>" + connectionType1 + "</td>";
				output += "<td>" + unitLimit + "</td>";
				output += "<td>" + IssuedDate + "</td>";
			}
			
			con.close();
			// Complete the html table
			//output += "</tr></table>";
		}
		catch (Exception e)
		{
			output = "Error while retrieving data";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteItem(int limitID)
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
			String query = "delete from unitlimits where limitID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, limitID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateItem(int limitID, String connectionType, int unitLimit)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE unitlimits SET connectionType=?,unitLimit=?"
			 		+ "WHERE limitID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, connectionType);
			 preparedStmt.setInt(2, unitLimit);
			 preparedStmt.setInt(3, limitID);
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	} 
}
