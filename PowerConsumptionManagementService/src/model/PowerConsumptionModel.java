package model;
import java.sql.*;

public class PowerConsumptionModel {
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
		
		public String insertData(String customerID, int noOfUnits, int year, int month, int inputDate, 
				String inputBy, String conStatus, String powerConsumptionLevel)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
					{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				String query = " insert into powerconsumption (recID,customerID,noOfUnits,year,month, inputDate, inputBy,conStatus, powerConsumptionLevel)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, customerID);
				preparedStmt.setInt(3, noOfUnits);
				preparedStmt.setInt(4, year);
				preparedStmt.setInt(5, month);
				preparedStmt.setInt(6, inputDate);
				preparedStmt.setString(7, inputBy);
				preparedStmt.setString(8, conStatus);
				preparedStmt.setString(9, powerConsumptionLevel);
				
				// execute the statement
				preparedStmt.execute();
				
				con.close();
				output = "Inserted successfully";
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
			String query = "";
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				
				// Prepare the html table to be displayed
				output = "<table><tr>"
						+ "<th>RecID</th>"
						+ "<th>CustomerID</th>"
						+ "<th>NoOfUnits</th>"
						+ "<th>Year</th>"
						+ "<th>Month</th>"
						+ "<th>InputDate</th>"
						+ "<th>InputBy</th>"
						+ "<th>ConStatus</th>"
						+ "<th>PowerConsumptionLevel</th>"
						+ "</tr>"
						+ "<tr>";
				
				query = "select * from powerconsumption";

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{	
					int recID = rs.getInt("recID");
					String customerID1 = rs.getString("customerID");
					int noOfUnits = rs.getInt("noOfUnits");
					int year1 = rs.getInt("year");
					int month1 = rs.getInt("month");
					int inputDate = rs.getInt("inputDate");
					String inputBy = rs.getString("inputBy");
					String conStatus = rs.getString("conStatus");
					String powerConsumptionLevel = rs.getString("powerConsumptionLevel");
					
					// Add into the html table
					output += "<td>" + recID + "</td>";
					output += "<td>" + customerID1 + "</td>";
					output += "<td>" + noOfUnits + "</td>";
					output += "<td>" + year1 + "</td>";
					output += "<tr><td>" + month1 + "</td>";
					output += "<td>" + inputDate + "</td>";
					output += "<td>" + inputBy + "</td>";
					output += "<td>" + conStatus + "</td>";
					output += "<td>" + powerConsumptionLevel + "</td>";
				}
				
				con.close();
				// Complete the html table
				output += "</tr>"
						+ "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String updateItem(int RecID, String conStatus)
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
				String query = "update powerconsumption set conStatus = ? where RecID = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, conStatus);
				preparedStmt.setInt(2, RecID);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated Successfully";
			}
			catch (Exception e)
			{
				output = "Error while updating the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}
}
