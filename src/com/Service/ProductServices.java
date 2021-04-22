package com.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.Controller.ProductAdminController;
import com.Model.Products;


@Path("/ProductManagement") 
public class ProductServices {
	
	ProductAdminController productAdmin= new ProductAdminController();
	
	@GET
	@Path("/getProducts") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProducts() 
	{ 
		return productAdmin.readProducts(); 
	} 

	@POST
	@Path("/AddProducts") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String addProducts(String productsData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject productObject = new JsonParser().parse(productsData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String productname = productObject.get("productname").getAsString(); 
		 String productcategory = productObject.get("productcategory").getAsString(); 
		 String description = productObject.get("description").getAsString();
		 int price = productObject.get("price").getAsInt(); 
	
		 Products products= new Products(productname,productcategory,description,price);
		 

		//String output = us.insertItem(fname,lname,age,pnumber,address,password);
		 String output = productAdmin.addProducts(products);
	return output; 
	}
	
	@PUT
	@Path("/UpdateProducts") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProducts(String productsData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject productObject = new JsonParser().parse(productsData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String productid = productObject.get("productid").getAsString(); 
	 String productname = productObject.get("productname").getAsString(); 
	 String productcategory = productObject.get("productcategory").getAsString(); 
	 String description = productObject.get("description").getAsString(); 
	 String price = productObject.get("price").getAsString();
	

	 String output = productAdmin.updateProducts(productid, productname, productcategory, description, price);
	return output; 
	}
	
	@DELETE
	@Path("/DeleteProducts") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProducts(String productsData) 
	{ 
	//Convert the input string to a JSON object 
	JsonObject productObject = new JsonParser().parse(productsData).getAsJsonObject(); 
	 
	//Read the value from the element <itemID>
	 String productid = productObject.get("productid").getAsString();
	
	 String output = productAdmin.deleteProducts(productid);
	return output; 
	}
}
