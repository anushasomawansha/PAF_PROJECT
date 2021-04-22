package com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserAdminController {
	
	//Database Connection
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbuserdb", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 

	//Admin read users
	public String readUsers() 
	{ 
		 String output = "";
		 try
		 { 
			 Connection con = connect(); 
		 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
		 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>User ID</th><th>Name</th><th>Phone Number</th><th>Address</th><th>User Name</th><th>Password</th><th>Type</th></tr>"; 
		 
			 String query = "select * from user"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
		 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 int userid = rs.getInt("userid");
				 String fname = rs.getString("fname");
				 String lname = rs.getString("lname");
				 String pnumber = rs.getString("pnumber");
				 String address = rs.getString("address");
				 String username = rs.getString("username");
				 String password = rs.getString("password");
				 String type = rs.getString("type");
				 // Add into the html table
				 output += "<tr><td>" + userid + "</td>"; 
				 output += "<td>" + fname+" "+lname + "</td>";
				 output += "<td>" + pnumber + "</td>"; 
				 output += "<td>" + address + "</td>"; 
				 output += "<td>" + username + "</td>"; 
				 output += "<td>" + password + "</td>"; 
				 output += "<td>" + type + "</td>"; 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'></td>"
						+ "<input name='userid' type='hidden' value='" + userid 
						+ "'>" + "</form></td></tr>"; 
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

}
