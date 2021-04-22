package com.Controller;
import java.sql.*;

public class FundController {

	
	
	//A common method to connect to the DB
			private Connection connect()
			 {
			 Connection con = null;
			 try
			 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "1234");
			 }
			 catch (Exception e)
			 {e.printStackTrace();}
			 return con;
			 }
			public String insertFund(String code, String name, String price, String desc)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 // create a prepared statement
			 String query = " insert into payment.funds(`fundID`,`fundCode`,`fundName`,`fundPrice`,`fundDecs`)"
			 + " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, code);
			 preparedStmt.setString(3, name);
			 preparedStmt.setDouble(4, Double.parseDouble(price));
			 preparedStmt.setString(5, desc);
			// execute the statement
			
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while inserting the fund.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String readFunds()
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Fund ID</th><th>Fund Code</th><th>Fund Name</th>" +
			 "<th>Fund Price</th>" +
			 "<th>Fund Description</th>" +
			 "<th>Update</th><th>Remove</th></tr>";

			 String query = "select * from funds";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String fundID = Integer.toString(rs.getInt("fundID"));
			 String fundCode = rs.getString("fundCode");
			 String fundName = rs.getString("fundName");
			 String fundPrice = Double.toString(rs.getDouble("fundPrice"));
			 String fundDecs = rs.getString("fundDecs");
			 // Add into the html table
			 output += "<tr><td>" + fundID + "</td>";
			 output += "<td>" + fundCode + "</td>";
			 output += "<td>" + fundName + "</td>";
			 output += "<td>" + fundPrice + "</td>";
			 output += "<td>" + fundDecs + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
			      
			 + "<td><form method='post' action='items.jsp'>"
			 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
			
			 + "<input name='fundID' type='hidden' value='" + fundID
			 + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			 }
			 catch (Exception e)
			 {
			 output = "Error while reading the funds.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String updateFund(String ID, String code, String name, String price, String decs)
			
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 // create a prepared statement
			 String query = "UPDATE funds SET fundCode=?,fundName=?,fundPrice=?,fundDecs=? WHERE fundID=?";
			
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, code);
			 preparedStmt.setString(2, name);
			 preparedStmt.setDouble(3, Double.parseDouble(price));
			 preparedStmt.setString(4, decs);
			 preparedStmt.setInt(5, Integer.parseInt(ID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the fund.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String deleteFund(String fundID)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from funds where fundID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(fundID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while deleting the fund.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
		
		

	
	
	
	
	
	
	
	
	

}
