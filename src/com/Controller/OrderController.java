package com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.Model.Orders;



public class OrderController {
	
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafp", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 
	
	
	public String getOrder() 
	{ 
		 String output = ""; 
		 
		 try
		 { 
			 Connection con = connect(); 
		 
			 if (con == null) 
			 {return "Error while connecting to the database for reading."; } 
		 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Produt ID</th><th>Product Name</th><th>Price</th><th>Quantity</th><th>Email</th><th>Payment</th></tr>"; 
		 
			 String query = "select * from order_db"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
		 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 int id = rs.getInt("id");
				 String Prname = rs.getString("Prname");
				 float price = rs.getFloat("price"); 
				 int quantity = rs.getInt("quantity");
				 String email = rs.getString("email");
				 String paymentM = rs.getString("paymentM");
				 // Add into the html table
				 output += "<tr><td>" + id + "</td>"; 
				 output += "<td>" + Prname + "</td>"; 
				 output += "<td>" + price + "</td>"; 
				 output += "<td>" + quantity + "</td>"; 
				 output += "<td>" + email + "</td>"; 
				 output += "<td>" + paymentM + "</td>"; 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'></td>"
						+ "<input name='userid' type='hidden' value='" + id 
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
	
	
	public String insertOrder(Orders order) 
	{ 
		String output = ""; 
	
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
 
			// create a prepared statement
			String query = " insert into pafp.order_db (`id`,`Prname`,`price`,`quantity`,`email`,`paymentM`)"
					+ " values (?,?,?,?,?,?)"; 
 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2,order.getPrname()); 
			preparedStmt.setFloat(3, order.getPrice());
			preparedStmt.setInt(4, order.getQuantity());
			preparedStmt.setString(5,order.getEmail());
			preparedStmt.setString(6, order.getPaymentM());
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
	
	public String updateOrders(int id, String Prname, float price, int quantity, String email, String paymentM)
	{ 
		 String output = ""; 
		 try
		 { 
			 	Connection con = connect(); 
		 
			 	if (con == null) 
			 	{return "Error while connecting to the database for updating."; } 
		 
			 	// create a prepared statement
			 	String query = "UPDATE order_db SET  Prname=?,price=?,quantity=?,email=?,paymentM=? WHERE id =?"; 
		 
			 	PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 	// binding values
			 	preparedStmt.setString(1, Prname); 
			 	preparedStmt.setFloat(2, price); 
			 	preparedStmt.setInt(3,quantity); 
			 	preparedStmt.setString(4, email); 
			 	preparedStmt.setString(5, paymentM); 
			 	preparedStmt.setInt(6,id); 
			
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
	
	public String deleteOrder(String id) 
	 { 
		String output = ""; 
	 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement
			String query = "delete from order_db where id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			
	 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id)); 
	 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
	 
			output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
	 } 
		
	 return output;
	 
	 } 

}
