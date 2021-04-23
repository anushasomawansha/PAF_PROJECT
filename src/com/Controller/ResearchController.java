package com.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.Model.Research;



public class ResearchController {
	
	private Connection connect() {
		
		 Connection con = null;
		 try{
			 	
			 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbresearchdb", "root", ""); 
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			 }
		 
		 return con; 
		 }  
		 
	
		//CREAING A METHOD TO INSERT RESEARCH TABLE DATA
	
	public String insertResearch(Research research) {
		
		String output = "";
		
		try {
			Connection con = connect();
			if(con == null) {
				return "error while connecting to the researcher database when inserting data";	
			}
			
			//CREATE A PREPARED STATEMENT
			String query =" insert into gbresearchdb.research (`rid`,`userid`,`name`,`description`,`status`,`budget`)"
					+ " values (?,?,?,?,?,?)"; 
			 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			//BINDING VALUES
			
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, research.getUserid());
			preparedStmt.setString(3, research.getName());
			preparedStmt.setString(4, research.getDescription());
			preparedStmt.setString(5, research.getStatus());
			preparedStmt.setDouble(6, research.getBudget());
			
			//EXECUTE THE STATEMENTS
			
			preparedStmt.execute();
			con.close();
			output = "inserted successfully";
			
		}
		catch(Exception e) {
			output = "error while connecting to the researcher database when inserting data";
			System.err.println(e.getMessage());
		}
		return output;	
	}
	
	//CREATING A METHOD TO UPDATE RESEARCHER TABLE DATA
	
	public String updateResearch(String rid,String userid, String name, String description, String status, String budget) {
		String output = "";
		try {
			Connection con = connect();
			
			if(con == null) {
				return "error while connecting to the database when updating research information";
			}
			
			//CREATE A PREPARED STATEMENT
			String query = "UPDATE research SET userid=?,name=?,description=?,status=?,budget=? WHERE rid=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			//BINDING VALUES
			
			preparedStmt.setInt(1,Integer.parseInt(userid));
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, status);
			preparedStmt.setDouble(5,Double.parseDouble(budget));
			preparedStmt.setInt(6, Integer.parseInt(rid));
			
			//EXECUTE THE STATEMENT
			
			preparedStmt.execute();
			con.close();
			output = "updated data successfully";
			
		}
		catch(Exception e) {
			output = "error while connecting to the database when updating research information";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//CREATING A METHOD TO DELETE RESEARCHER TABLE DATA
	
	public String deleteResearch(String rid) {
		
		String output="";
		
		try {
			Connection con = connect();
			if(con == null) {
				return "error while deleting research data";
			}
			
			//create a prepared statement
			String query = "DELETE FROM research WHERE rid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			//BINDING VALUES
			preparedStmt.setInt(1, Integer.parseInt(rid));
			
			//EXECUTE THE STATEMENT
			preparedStmt.execute();
			con.close();
			
			output = "deleted research data successfully";	
		}
		catch(Exception e) {
			output = "error while deleting research data";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//CREATING A CONNECTION FOR READING DATA FROM RESEARCHER TABLE
	
	public String readResearch() {
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null) {
				return "error while connecting to the database for reading data";
			}
			
			//HTML TABLE TO DISPLAY
			output = "<table border='1'><tr><th>Researcher ID</th><th>User ID</th><th>Researcher Name</th><th>Description</th><th>Status ID</th><th>Proposed Budget</th></tr>";
			
			String query = "SELECT * FROM research";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		//ITERATE THROUGH ROWS IN THE RESULT SET
			while(rs.next()) {
				
				int rid = rs.getInt("rid");
				int userid =rs.getInt("userid");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String status = rs.getString("status");
				Double budget= rs.getDouble("budget");
				
				//ADD INTO HTML TABLE
				
				output += "<tr><td>" + rid +"</td>";
				output += "<td>" + userid +"</td>";
				output += "<td>" + name +"</td>";
				output += "<td>" + description +"</td>";
				output += "<td>" + status +"</td>";
				output += "<td>" + budget +"</td>";
				
				
				//HTML FOR BUTTONS
				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><input name='btnDelete' type='submit' value='Delete' class='btn btn-danger'></td>"
						+ "<input name='rid' type='hidden' value='" + rid 
						+ "'>" + "</form></td></tr>"; 	
			}
			con.close();
		
			output +="</table>";
		
		}
		catch(Exception e) {
			output = "error while connecting to the database for reading data";
			System.err.print(e.getMessage());
		}
		return output;
	}

}
