package com.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Controller.UserAdminController;



@Path("/UserManagement") 
public class UserServices {
	
	//create object from UserAdminController class
	UserAdminController userAdmin = new UserAdminController();
	
	@GET
	@Path("/GetUsers") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUser() 
	{ 
		return userAdmin.readUsers(); 
	} 
}
