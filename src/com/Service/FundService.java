package com.Service;



//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.Controller.FundController;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Funds")


public class FundService {
	FundController fundObj = new FundController();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	 {
	 return fundObj.readFunds();
	 } 
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("fundCode") String fundCode,
	 @FormParam("fundName") String fundName,
	 @FormParam("fundPrice") String fundPrice,
	 @FormParam("fundDesc") String fundDesc)
	{
	 String output = fundObj.insertFund(fundCode, fundName, fundPrice, fundDesc);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String fundData)
	{
	//Convert the input string to a JSON object
	 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
	//Read the values from the JSON object
	 String fundID = fundObject.get("fundID").getAsString();
	 String fundCode = fundObject.get("fundCode").getAsString();
	 String fundName = fundObject.get("fundName").getAsString();
	 String fundPrice =fundObject.get("fundPrice").getAsString();
	 String fundDecs = fundObject.get("fundDecs").getAsString();
	 String output = fundObj.updateFund(fundID, fundCode, fundName, fundPrice, fundDecs);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

	//Read the value from the element <fundID>
	 String fundID = doc.select("fundID").text();
	 String output = fundObj.deleteFund(fundID);
	return output;
	}

}
