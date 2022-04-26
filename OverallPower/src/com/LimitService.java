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

import model.Limits;

@Path("limitservice")
public class LimitService {
	Limits model = new Limits();
	
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
	public String inputUnits(@FormParam("district") String district, @FormParam("unit_limit") int unit_limit, @FormParam("issued_date") String issued_date) {
		String result = model.insertData(district, unit_limit, issued_date);
		return result;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(@FormParam("limitId") int limitId,@FormParam("district")  String district, @FormParam("unit_limit") int unit_limit, @FormParam("issued_date") String issued_date)
	{
		String output = model.updateLimit(limitId, district, unit_limit, issued_date);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(@FormParam("limitId") int limitId)
	{
		String output = model.deleteLimit(limitId);
	return output;
	}

}
