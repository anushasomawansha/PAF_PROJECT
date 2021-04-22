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
}
