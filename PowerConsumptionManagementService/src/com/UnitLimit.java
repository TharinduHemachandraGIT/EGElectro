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

import model.UnitLimitModel;

@Path("limits")
public class UnitLimit {
	UnitLimitModel model = new UnitLimitModel();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readLimits() {
		String result = model.retrieveData();
		return result;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String inputUnits(@FormParam("connectionType") String connectionType, @FormParam("unitLimit") int unitLimit, @FormParam("IssuedDate") String IssuedDate) {
		String result = model.insertData(connectionType, unitLimit, IssuedDate);
		return result;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(@FormParam("limitID") int limitID,@FormParam("connectionType")  String connectionType, @FormParam("unitLimit") int unitLimit)
	{
		String output = model.updateItem(limitID, connectionType, unitLimit);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(@FormParam("limitID") int limitID)
	{
		String output = model.deleteItem(limitID);
		return output;
	}

}
