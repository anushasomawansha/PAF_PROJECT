package com.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Controller.OrderController;
import com.Model.Orders;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



@Path("/OrderManagement") 
public class OrderServices {
	
	
	OrderController orderController = new OrderController();


	@GET
	@Path("/getOrders") 
	@Produces(MediaType.TEXT_HTML) 
	public String getOrder() 
	{ 
		return orderController.getOrder(); 
	} 
	
	
	@POST
	@Path("/insertOrder") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertOrder(String orderData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String Prname = orderObject.get("Prname").getAsString(); 
		 float price = orderObject.get("price").getAsFloat(); 
		 int quantity = orderObject.get("quantity").getAsInt(); 
		 String email = orderObject.get("email").getAsString();
		 String paymentM = orderObject.get("paymentM").getAsString();
		 
		 Orders order = new Orders(Prname, price, quantity, email, paymentM);
		 
		//String output = us.insertItem(fname,lname,age,pnumber,address,password);
		 String output = orderController.insertOrder(order);
	return output; 
	}
	
	@PUT
	@Path("/UpdateOrder") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateOrder(String orderData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject(); 
	//Read the values from the JSON object
	 int id = orderObject.get("id").getAsInt(); 
	 String Prname = orderObject.get("Prname").getAsString(); 
	 Float price = orderObject.get("price").getAsFloat(); 
	 int quantity = orderObject.get("quantity").getAsInt(); 
	 String email = orderObject.get("email").getAsString();
	 String paymentM = orderObject.get("paymentM").getAsString(); 


	 String output = orderController.updateOrders(id, Prname, price, quantity, email, paymentM);
	return output; 
	}

	
	@DELETE
	@Path("/DeleteOrder") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteOrder(String orderData) 
	{ 
	//Convert the input string to a JSON object 
	JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject(); 
	 
	//Read the value from the element <itemID>
	 String ID = orderObject.get("id").getAsString();
	
	 String output = orderController.deleteOrder(ID);
	return output; 
	}

}