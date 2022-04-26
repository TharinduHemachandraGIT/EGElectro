package model;
import java.sql.*;

public class PowerManagementModel {
	//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powermanagementdb", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			return con;
		}
		
		public String insertData(String District, int ConsumedPower,int Month, int Year, int NoOfHours, int NoOfDays, int PowerSaved)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
					{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				String query = " insert into powermanagement (PlanID,District,ConsumedPower,Month,Year, NoOfHours, NoOfDays,PowerSaved)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, District);
				preparedStmt.setInt(3, ConsumedPower);
				preparedStmt.setInt(4, Month);
				preparedStmt.setInt(5, Year);
				preparedStmt.setInt(6, NoOfHours);
				preparedStmt.setInt(7, NoOfDays);
				preparedStmt.setInt(8, PowerSaved);
				
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
						+ "<th>PlanID</th>"
						+ "<th>District</th>"
						+ "<th>ConsumedPower</th>"
						+ "<th>Month</th>"
						+ "<th>Year</th>"
						+ "<th>NoOfHours</th>"
						+ "<th>NoOfDays</th>"
						+ "<th>PowerSaved</th>"
						+ "</tr>"
						+ "<tr>";
				
				query = "select * from powermanagement";
				

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{	
					int PlanID = rs.getInt("PlanID");
					String District = rs.getString("District");
					int ConsumedPower = rs.getInt("ConsumedPower");
					int Month = rs.getInt("Month");
					int Year = rs.getInt("Year");
					int NoOfHours = rs.getInt("NoOfHours");
					String NoOfDays = rs.getString("NoOfDays");
					String PowerSaved = rs.getString("PowerSaved");
					
					// Add into the html table
					output += "<td>" + PlanID + "</td>";
					output += "<td>" + District + "</td>";
					output += "<td>" + ConsumedPower + "</td>";
					output += "<td>" + Month + "</td>";
					output += "<tr><td>" + Year + "</td>";
					output += "<td>" + NoOfHours + "</td>";
					output += "<td>" + NoOfDays + "</td>";
					output += "<td>" + PowerSaved + "</td>";
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
		
		public String updateData(int PlanID, int ConsumedPower, int NoOfHours, int NoOfDays, int PowerSaved)
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
				String query = "update powermanagement set ConsumedPower = ?, NoOfHours = ?, NoOfDays = ?, PowerSaved = ? where PlanID = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, ConsumedPower);
				preparedStmt.setInt(2, NoOfHours);
				preparedStmt.setInt(1, NoOfDays);
				preparedStmt.setInt(2, PowerSaved);
				preparedStmt.setInt(1, PlanID);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated Successfully";
			}
			catch (Exception e)
			{
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String deletePlan(int PlanID)
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
				String query = "delete from powermanagement where PlanID = ?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, PlanID);
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
}
