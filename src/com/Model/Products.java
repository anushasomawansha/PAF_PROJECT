package com.Model;

public class Products {
	private int productid;
	private String productname;
	private String productcategory;
	private String description;
	private int price;
	public Products(int productid, String productname, String productcategory, String description, int price) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.productcategory = productcategory;
		this.description = description;
		this.price = price;
	}
	public Products(String productname, String productcategory, String description, int price) {
		super();
		this.productname = productname;
		this.productcategory = productcategory;
		this.description = description;
		this.price = price;
	}
	public Products() {
		super();
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductcategory() {
		return productcategory;
	}
	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	

}
