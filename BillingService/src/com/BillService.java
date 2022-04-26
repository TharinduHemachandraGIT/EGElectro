package com;
import model.BillModel;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/billing") 
public class BillService {
	BillModel model = new BillModel();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBill() {
		String result = model.retrieveData();
		return result;
	}
	
	@POST
	@Path("/fetchbillstatus")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String readBill2(@FormParam("customerID") String CustomerID, @FormParam("Year") int Year, @FormParam("Month") int Month) {
		String result = model.retrievePaidBills(CustomerID, Year, Month);
		return result;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String inputBill(@FormParam("NoOfUnits") int NoOfUnits, @FormParam("NoOfExceededUnits") int NoOfExceededUnits, @FormParam("ConnectionStatus") String ConnectionStatus, @FormParam("CustomerID") String CustomerID,
			@FormParam("UnitPrice") float UnitPrice, @FormParam("Month") int Month, @FormParam("Year") int Year, @FormParam("InputDate") int InputDate, @FormParam("District") String District, @FormParam("TotalBill")float TotalBill) {
		String result = model.insertData(NoOfUnits, NoOfExceededUnits, ConnectionStatus, CustomerID, UnitPrice, Month, Year, InputDate, District, TotalBill, "NotPaid");
		return result;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(@FormParam("BillID") int BillID, @FormParam("PaymentStatus") String PaymentStatus)
	{
		String output = model.updateData(BillID, PaymentStatus);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(@FormParam("BillID") int BillID)
	{
		String output = model.deleteItem(BillID);
		return output;
	}
}