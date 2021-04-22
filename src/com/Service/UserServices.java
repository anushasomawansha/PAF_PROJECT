package com.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Controller.UserAdminController;
import com.Controller.UserController;
import com.Model.Users;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;







@Path("/UserManagement") 
public class UserServices {
	
	//create object from UserAdminController class
	UserAdminController userAdmin = new UserAdminController();
	
	//create object from UserController class
	UserController userController = new UserController();
	
	
	//read users
	@GET
	@Path("/GetUsers") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUser() 
	{ 
		return userAdmin.readUsers(); 
	} 
	
	//register users
	@POST
	@Path("/RegisterUser") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String userRegister(String userData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String fname = userObject.get("fname").getAsString(); 
		 String lname = userObject.get("lname").getAsString(); 
		 String pnumber = userObject.get("pnumber").getAsString();
		 String address = userObject.get("address").getAsString();
		 String type = userObject.get("type").getAsString();
		 String password = userObject.get("password").getAsString(); 
		 
		 Users user= new Users(fname,lname,pnumber,address,password,type);
		 
		 String output = userController.userRegister(user);
	return output; 
	}
}
