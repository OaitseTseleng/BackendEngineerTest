package com.oaitsetseleng.techassesment.models;

public class Items {
	private String name;
	private String type;
	private double price;
	private int quantity;
	
	
	
	public Items(String name, String type, double price, int quantity) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int qauntity) {
		this.quantity = qauntity;
	}
	
}
