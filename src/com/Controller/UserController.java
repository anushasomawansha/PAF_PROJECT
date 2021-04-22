package com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.Model.Users;



public class UserController {

	//Database Connection
		private Connection connect() 
		{ 
			Connection con = null; 
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
	 
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafprojtest", "root", "root"); 
			} 
			catch (Exception e) 
			{e.printStackTrace();} 
			return con; 
		} 

	//Insert User
		public String userRegister(Users user) 
		{ 
			String output = ""; 
		
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for inserting."; } 
	 
				// create a prepared statement
				String query = " insert into pafprojtest.user (`userid`,`fname`,`lname`,`pnumber`,`address`,`username`,`password`,`type`)"
						+ " values (?,?,?,?,?,?,?,?)"; 
	 
				PreparedStatement preparedStmt = con.prepareStatement(query); 

				// binding values
				
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, user.getFname()); 
				preparedStmt.setString(3, user.getLname()); 
				preparedStmt.setString(4, user.getPnumber());
				preparedStmt.setString(5, user.getAddress());
				preparedStmt.setString(6, user.getFname()+"@GB.lk");
				preparedStmt.setString(7, user.getPassword());
				preparedStmt.setString(8, user.getType());
				// execute the statement
				preparedStmt.execute(); 
				 con.close(); 
				 
				 output = "Inserted successfully"; 
			} 
			catch (Exception e) 
			{ 
				 output = "Error while inserting the item."; 
				 System.err.println(e.getMessage()); 
			} 
				 
			return output; 
		}

	//Update User
		public String updateUser(String userid, String fname, String lname, String pnumber, String address,String password,String type)
		{ 
			 String output = ""; 
			 try
			 { 
				 	Connection con = connect(); 
			 
				 	if (con == null) 
				 	{return "Error while connecting to the database for updating."; } 
			 
				 	// create a prepared statement
				 	String query = "UPDATE user SET fname=?,lname=?,pnumber=?,address=?,username=?,password=?,type=? WHERE userid =?"; 
			 
				 	PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
				 	// binding values
				 	preparedStmt.setString(1, fname); 
				 	preparedStmt.setString(2, lname); 
				 	preparedStmt.setString(3, pnumber); 
				 	preparedStmt.setString(4, address); 
				 	preparedStmt.setString(5, fname+"@GB.lk"); 
				 	preparedStmt.setString(6, password); 
				 	preparedStmt.setString(7, type); 
				 	preparedStmt.setInt(8, Integer.parseInt(userid)); 
				
				 	// execute the statement
				 	preparedStmt.execute(); 
				 	con.close(); 
				 	output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
				 	output = "Error while updating the item."; 
				 	System.err.println(e.getMessage()); 
			 } 
			 
			 return output; 
			 
		}
}
