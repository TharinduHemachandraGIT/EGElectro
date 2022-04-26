package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.OverallConsumptionModel;

@Path("OverallConsumption")
public class OverallConsumption {
	OverallConsumptionModel model = new OverallConsumptionModel();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUnits() {
		String result = model.retrieveData();
		return result;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String inputUnits(@FormParam("District") String District, @FormParam("OverallUnits") int OverallUnits, @FormParam("ConsumptionStatus") String ConsumptionStatus) {
		String result = model.insertData(District, OverallUnits, ConsumptionStatus);
		return result;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUnits(@FormParam("RecID") int RecID,@FormParam("District")  String District, @FormParam("OverallUnits") int OverallUnits, @FormParam("ConsumptionStatus") String ConsumptionStatus)
	{
		String output = model.updateUnits(RecID, District, OverallUnits, ConsumptionStatus);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUnits(@FormParam("RecID") int RecID)
	{
		String output = model.deleteUnits(RecID);
	return output;
	}

}
