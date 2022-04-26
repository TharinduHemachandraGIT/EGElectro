package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PaymentModel {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/billingservicedb", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
	public String insertData(int Year,int Month,int PaidDate,Float Amount, String CustomerID, int BillID)
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
			String query = " insert into payment (PaymentID, Year,Month,PaidDate,Amount, "
					+ "CustomerID, BillID)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Year);
			preparedStmt.setInt(3, Month);
			preparedStmt.setInt(4, PaidDate);
			preparedStmt.setFloat(5, Amount);
			preparedStmt.setString(6, CustomerID);
			preparedStmt.setInt(7, BillID);
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			output = "Paid";
		}
		catch (Exception e)
		{
			output = "Error while creating the bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String retrieveData(int BillID)
	{
		String output = "";
		String query = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr>"
					+ "<th>BillID</th>"
					+ "<th>No Of Units</th>" 
					+ "<th>No of Exceeded Units</th>" 
					+ "<th>Connection Status</th>"
					+ "<th>CustomerID</th>"
					+ "<th>UnitPrice</th>"
					+ "<th>Month</th>"
					+ "<th>Year</th>"
					+ "<th>Input Date</th>"
					+ "<th>District</th>"
					+ "<th>Total Bill</th>"
					+ "<th>Payment Status</th>"
					+ "</tr>"
					+ "<tr>";
			
			if(BillID == -1) {
				query = "select * from billservice";
			}
			else {
				query = "select * from billservice where BillID = " + BillID;
			}
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				int rBillID = rs.getInt("BillID");
				int rNoOfUnits = rs.getInt("NoOfUnits");
				int rExceed = rs.getInt("NoOfExceededUnits");
				String rStatus = rs.getNString("ConnectionStatus");
				String rCustomerID = rs.getNString("CustomerID");
				Float rUnitPrice = rs.getFloat("UnitPrice");
				int rMonth = rs.getInt("Month");
				int rYear = rs.getInt("Year");
				int rDate = rs.getInt("InputDate");
				String rDistrict = rs.getString("District");
				float rTotalBill = rs.getFloat("TotalBill");
				String rPaymentStatus = rs.getString("PaymentStatus");
				// Add into the html table
				output += "<td>" + rBillID + "</td>";
				output += "<td>" + rNoOfUnits + "</td>";
				output += "<td>" + rExceed + "</td>";
				output += "<tr><td>" + rStatus + "</td>";
				output += "<td>" + rCustomerID + "</td>";
				output += "<td>" + rUnitPrice + "</td>";
				output += "<td>" + rMonth + "</td>";
				output += "<tr><td>" + rYear + "</td>";
				output += "<td>" + rDate + "</td>";
				output += "<td>" + rDistrict + "</td>";
				output += "<td>" + rTotalBill + "</td>";
				output += "<td>" + rPaymentStatus + "</td>";
			}
			
			con.close();
			// Complete the html table
			output += "</tr></table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteItem(int BillID, int Year)
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
			String query = "delete from billservice where BillID = ? and Year = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, BillID);
			preparedStmt.setInt(2, Year);
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
	
	public String updateData(int BillID, String PaymentStatus)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE billservice SET PaymentStatus=?"
			 		+ "WHERE BillID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, PaymentStatus);
			 preparedStmt.setInt(2, BillID);
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
	
	
	
	public String retrievePaidBills(String CustomerID, int Year, int Month)
	{
		String output = "";
		String query = "";
		
		int y = Year;
		int m1 = Month - 1;
		int m2 = Month - 2;
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
			query = "select PaymentStatus from billservice where CustomerID = '" + CustomerID + "' and Year = " + y + "and Month = " + m2;
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				//int rBillID = rs.getInt("BillID");
				//int rNoOfUnits = rs.getInt("NoOfUnits");
				//int rExceed = rs.getInt("NoOfExceededUnits");
				//String rStatus = rs.getNString("ConnectionStatus");
				//String rCustomerID = rs.getNString("CustomerID");
				//Float rUnitPrice = rs.getFloat("UnitPrice");
				//int rMonth = rs.getInt("Month");
				//int rYear = rs.getInt("Year");
				//int rDate = rs.getInt("InputDate");
				//String rDistrict = rs.getString("District");
				//float rTotalBill = rs.getFloat("TotalBill");
				String rPaymentStatus = rs.getString("PaymentStatus");
				// Add into the html table
				
				if(rPaymentStatus == "Paid") {
					output = "Connected";
				}
				else {
					output = "Disconnected";
				}
			}
			
			con.close();
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}