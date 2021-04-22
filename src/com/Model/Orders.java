package com.Model;

public class Orders {
	
	private int id;
	private String Prname;
	private float price;
	private int quantity;
	private String email;
	private String paymentM;
	
	public Orders(int id, String prname, float price, int quantity, String email, String paymentM) {
		super();
		this.id = id;
		Prname = prname;
		this.price = price;
		this.quantity = quantity;
		this.email = email;
		this.paymentM = paymentM;
	}

	public Orders(String prname, float price, int quantity, String email, String paymentM) {
		super();
		Prname = prname;
		this.price = price;
		this.quantity = quantity;
		this.email = email;
		this.paymentM = paymentM;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrname() {
		return Prname;
	}

	public void setPrname(String prname) {
		Prname = prname;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaymentM() {
		return paymentM;
	}

	public void setPaymentM(String paymentM) {
		this.paymentM = paymentM;
	}
	
	

}
