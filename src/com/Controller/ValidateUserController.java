package com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ValidateUserController {
	
	//Database Connection
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbuserdb", "root", "root"); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 
	
	//Validate User when login
	public String validateUser(String username,String password) {
		
		 String output = ""; 
		 try
		 { 
			 	Connection con = connect(); 
			 	 
			 	
			 	
			 	// create a prepared statement
			 	String query = "SELECT * FROM USER WHERE username='"+username+"'AND password ='"+password+"'"; 
			 	
		 
			 	 Statement stmt = con.createStatement();
			 	 
				 ResultSet rs = stmt.executeQuery(query); 
					 
				 // iterate through the rows in the result set
				 if (rs.next()==true) 
				 { 
					 int userid = rs.getInt("userid");
					 String fname = rs.getString("fname");
					 String lname = rs.getString("lname"); 
					 String pnumber = rs.getString("pnumber");
					 String address = rs.getString("address");
					 String username1 = rs.getString("username");
					 String password1 = rs.getString("password");
					 String type = rs.getString("type");
					 output = "<table border='1'><tr><th>User ID</th><th>Name</th><th>Phone Number</th><th>Address</th><th>User Name</th><th>Password</th><th>Type</th></tr>";
					 
					 output+="Welcome "+fname+" "+lname+"!";
					 
					 output += "<tr><td>" + userid + "</td>"; 
					 output += "<td>" + fname+" "+lname + "</td>"; 
					 output += "<td>" + pnumber + "</td>"; 
					 output += "<td>" + address + "</td>"; 
					 output += "<td>" + username1 + "</td>"; 
					 output += "<td>" + type + "</td>"; 
					 output += "<td>" + password1 + "</td>"; 
			 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							+ "<td><input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'></td>"
							+ "<input name='userid' type='hidden' value='" + userid 
							+ "'>" + "</form></td></tr>"; 
			 } else {
				 output+="No user";
			 }
			 
	
			 
			con.close(); 
			 
			// Complete the html table
			 output += "</table>"; 
				 }
		catch (Exception e) 
		{ 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
		} 
		 
		 
			 return output; 
	} 
	
	//Validate User By Type
	public String getUserByType(String username,String password) {
		
		 String output = ""; 
		 try
		 { 
			 	Connection con = connect(); 
			 	 
			 	
			 	
			 	// create a prepared statement
			 	String query = "SELECT * FROM USER WHERE username='"+username+"'AND password ='"+password+"'"; 
			 	
		 
			 	 Statement stmt = con.createStatement();
			 	 
				 ResultSet rs = stmt.executeQuery(query); 
					 
				 if (rs.next()==true)  
				 {
					 String type = rs.getString("type");
					 System.out.println(type);
					 output+=""+type;
				 }else {
					 output+="No user matching type";
				 }
			con.close(); 
	
				 }
		catch (Exception e) 
		{ 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
		} 
		 
		 
			 return output; 
	}
}
