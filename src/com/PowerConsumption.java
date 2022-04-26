package com;
import model.PowerConsumptionModel;
import com.UnitLimit;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/consumption") 
public class PowerConsumption {
	PowerConsumptionModel model = new PowerConsumptionModel();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readConsumption1() {
		String result = model.retrieveData();
		return result;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String inputUnits(@FormParam("customerID") String customerID, @FormParam("noOfUnits") int noOfUnits, @FormParam("year") int year, @FormParam("month") int month, @FormParam("inputDate") int inputDate, 
			@FormParam("inputBy") String inputBy, @FormParam("conStatus") String conStatus, @FormParam("powerConsumptionLevel") String powerConsumptionLevel) {
		String result = "";
		
		result = model.insertData(customerID, noOfUnits, year, month, inputDate, inputBy, conStatus, powerConsumptionLevel);
		
		return result;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String update(@PathParam("BillID") int BillID, @PathParam("conStatus") String conStatus) {
		String result = model.updateItem(BillID, conStatus);
		return result;
	}
	
	
}
