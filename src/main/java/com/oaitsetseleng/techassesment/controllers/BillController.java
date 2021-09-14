package com.oaitsetseleng.techassesment.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.oaitsetseleng.techassesment.models.Bill;
import com.oaitsetseleng.techassesment.models.Person;
import com.oaitsetseleng.techassesment.views.BillView;

@Component
public class BillController {

	@Autowired
	private BillView view; //Using Spring boot built in component to 
	
	//To calculate the bill due and return it
	public Bill calculateBill(Bill bill){
		List<Person> database = generatePersons();//generate a list of person objects
		
		//get the discount for the bill
		double discount = getDiscount(bill, database);
		
		//get the total for the bill
		double initialTotal =  getTotal(bill);
		double total =  initialTotal  - discount; //get total and subtract the discount
		
		//set the total and discount for objeCt Bill to be returned to user
		bill.setDiscount(discount);
		bill.setTotal(total);
		bill.setInitialTotal(initialTotal);
		return bill;
	}
	
	//To generate a list of people  to imitate database storage
	public List<Person> generatePersons(){
		List<Person> list = new ArrayList<Person>();
		
		//Creating 4 persons
		
		Person employee  = new Person(100, "Jack", "Employee", 5);
		Person customer1  = new Person(200, "Tshepo", "Customer", 2);
		Person customer2  = new Person(300, "Angela", "Customer", 1);
		Person affiliate  = new Person(400, "QuickFix Pty Ltd", "Affiliate", 4);
		
		//adding them to a list
		list.add(employee);
		list.add(customer1);
		list.add(customer2);
		list.add(affiliate);
		
		return list;
	}
	
	//Get the amount of discount percentage for a bill
	public double getDiscount(Bill bill, List<Person> list) {
		
		//get total bill for percentage based discounts
		double discountableTotal = getDiscountableTotal(bill); 
		double discount = 0.0;
		//get the total bill
		double total = getTotal(bill);
		
		//get the type of person
		Person person = getPerson(bill.getPersonId(), list);
		//Set to award the largest amount of discount first, in case a person
		//belongs to one or more types
		
		if(person.getType() == "Employee"){
			discount = (discountableTotal * 0.3);
		}
		else if(person.getType() == "Affiliate"){
			discount = (discountableTotal* 0.1);
		}
		else if(person.getType() == "Customer") {
			if(person.getYears() >= 2)
				discount =(discountableTotal * 0.05);
		}
		
		//For every $100 on the bill, there would be a $ 5 discount
		for(int i = 100; i < (int) total; i += 100){
			discount += 5.0; 
		}
		
		
		return discount;
	}
	
	//To get the the total of all products
	public double getTotal(Bill bill){
		double total = 0;
		
		for(int i = 0; i< bill.getItems().length; i++) {
			total += (bill.getItems()[i].getPrice() * (double)bill.getItems()[i].getQuantity());
		}
		
		return total;
	}
	
	//To get the the discountable total of all products
	public double getDiscountableTotal(Bill bill){
		double discountableTotal= 0;
			
		for(int i = 0; i< bill.getItems().length; i++) {
			if(bill.getItems()[i].getType().equals("groceries"))
				continue;
			else
				discountableTotal += (bill.getItems()[i].getPrice() * (double)bill.getItems()[i].getQuantity());
		}
			
		return discountableTotal;
	}
	
	//To get the person if they exist, else returns a new generic person
	public Person getPerson(int id, List<Person> list) {
		
		//Checking for the type of persons
		for(int i = 0; i < list.size(); i++) {
			if(id == list.get(i).getPersonId())
				return list.get(i);
		}
		
		//Assuming they do not exist then a new customer, they will be created
		return new Person(id, "generic", "", 0);
	}
}
