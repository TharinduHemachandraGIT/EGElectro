package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import model.PaymentModel;
import model.BillModel;

@Path("/payment") 
public class PaymentService {
	
	PaymentModel model = new PaymentModel();
	BillModel billmodel = new BillModel();
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String inputUnits(@FormParam("Year") int Year, @FormParam("Month") int Month,@FormParam("PaidDate") int PaidDate,@FormParam("Amount") Float Amount, @FormParam("CustomerID") String CustomerID, @FormParam("BillID") int BillID) {
		String result = "";
		
		result = model.insertData(Year,Month,PaidDate,Amount,CustomerID,BillID);
		
		if(result == "Paid") {
			billmodel.updateData(BillID, "Paid");
		}
		
		Client client1 = Client.create();

		WebResource wr1 = client1.resource("http://localhost:8081/PowerConsumptionManagementService/powerconsumption/consumption/update");
		String input1 ="<bill>"
				+ "<conStatus>"
				+ "Connected"
				+ "</conStatus>"
				+ "<BillID>"
				+ BillID
				+ "</BillID>"
				+ "</bill>"; 
		ClientResponse response1 = wr1.type("application/xml").accept("text/plain").post(ClientResponse.class, input1);
		
		String x1 = response1.getEntity(String.class);

		return result;
	}
}
