package com.oaitsetseleng.techassesment.models;

import org.springframework.stereotype.Component;

/**
 * @author lloyd
 *
 *Bill model will only have one object created at runtime, 
 *whether or not the object is used or not
 */
@Component
public class Bill {
	
	private int personId;
	private double discount;
	private Items[] items;
	private double initialTotal;
	private double total;

	public Bill(int personId, Items [] list) {
		super();
		this.personId = personId;
		this.items = list;
		this.discount = 0;
		this.total = 0;
		this.initialTotal = 0;
	}

	public Bill() {
		
	}
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public Items[] getItems() {
		return items;
	}

	public void setItems(Items[] items) {
		this.items = items;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getInitialTotal() {
		return initialTotal;
	}

	public void setInitialTotal(double initialTotal) {
		this.initialTotal = initialTotal;
	}
	
	
}
