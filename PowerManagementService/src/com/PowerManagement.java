package com;
import model.PowerManagementModel;


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

@Path("/powerplan") 
public class PowerManagement {
	PowerManagementModel model = new PowerManagementModel();
	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String readPowerPlan() {
		String result = model.retrieveData();
		return result;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String newPowerPlan(@FormParam("District") String District, @FormParam("ConsumedPower") int ConsumedPower, @FormParam("Month") int Month,
		 @FormParam("Year") int Year, @FormParam("NoOfHours") int NoOfHours, @FormParam("NoOfDays") int NoOfDays, @FormParam("PowerSaved") int PowerSaved) {
		String result = "";
		result = model.insertData(District, ConsumedPower, Month, Year, NoOfHours, NoOfDays, PowerSaved);

		return result;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String update(@FormParam("PlanID") int PlanID, @FormParam("ConsumedPower") int ConsumedPower, @FormParam("NoOfHours") int NoOfHours, 
			 @FormParam("NoOfDays") int NoOfDays, @FormParam("PowerSaved") int PowerSaved) {
		String result = model.updateData(PlanID, ConsumedPower, NoOfHours, NoOfDays, PowerSaved);
		return result;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(@FormParam("PlanID") int PlanID)
	{
		String output = model.deletePlan(PlanID);
		return output;
	}
}
