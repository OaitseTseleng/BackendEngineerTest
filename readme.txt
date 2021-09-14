Written by: Oaitse Lloyd Tseleng 

Requirements:
	1. Java JDK
	2. Spring Boot with web services
	3. Preferably Postman or any relevant API testing platform (web browser can work but not recommended)
	
Running Code:
	1. clone the code into any directory
	2. head to directory and open the folder in your IDE
	3. Right click on folder, or src/main/java/com.oaitsetseleng/techassesment/TechnicalAssesmentApplication.java
	   and select run spring boot app.
	4. Open your relevant API testing platform and enter the following
			- url : http://localhost:8080/api/v1/getBill
			- json data format:
				{
					"personId": any 3 digit integer ranging from 100 - 900
					"Items": [
						{
							"name": "The name of the good",
            				"type": "The type, electronics/groceries/Tools..etc (Please be aware only items written exactly as "groceries" will not be discounted)",
            				"price": price of good as a decimal (double),
            				"quantity": the number that is being bought as a whole number (Integer)
						}
						{
							"name": "The name of the good",
            				"type": "The type, electronics/groceries/Tools..etc (Please be aware only items written exactly as "groceries" will not be discounted)",
            				"price": price of good as a decimal (double),
            				"quantity": the number that is being bought as a whole number (Integer)
						}
					
						NB.. This is a list, therefore you can chain one or more in JSON format				
					] 
				}
	5. The response will be in JSON format and will have the following:
			{
			    "personId": The ID provided in the POST,
			    "discount": Calculated discount,
			    "items": [ 
			        {
			            "name": "pears",
			            "type": "The type that was entered",
			            "price": price of good that was entered,
			            "quantity": number of goods that was entered
			        },
			        {
			            "name": "name that was entered",
			            "type": "The type that was entered",
			            "price": price of good that was entered,
			            "quantity": number of goods that was entered
			         }
			   		NB: will return list of all the goods that were entered
			    ],
			    "initialTotal": the total of goods before discount subtracted
			    "total": the total price after discount was subtracted
			}		
			
Assumptions and Improvisations

	1. The database of existing customers, employees and ffiliates is hard coded and exist as craeated objects
		when program is run:
			Person employee  = new Person(100, "Jack", "Employee", 5);
			Person customer1  = new Person(200, "Tshepo", "Customer", 2);
			Person customer2  = new Person(300, "Angela", "Customer", 1);
			Person affiliate  = new Person(400, "QuickFix Pty Ltd", "Affiliate", 4);
	2. That if the customer does not exist, they will be created with the current ID they have:
			return new Person(id, "generic", "", 0);
			
	3. That information about a good/item will be provided from API (conventionally information 
		about a product is stored in database)
	
	4. All Customers, Employee and Affiliates, are of type Person, or object person.
		They are differentiated by their type attribute
		public class Person {
			private int personId;
			private String name;
			private String type;
			private int years;
			
			...
		}
	
Architecture: Model-View-Controller was used:
	Model contained data Objects that could store information:
		- Person: An entity that is either a Employee, Customer or Affiliate
		- Bill: the bill that needs to be paid
		- Items: An item/good/product that is bought
	
	Views contained the API endpoint itself, as we only needed one:
		- BillView: contains one API url endpoint that accepts a POST request, and returns JSON data 
	
	Controller: Contains the brains or the methods used to calulate the total and discount.
		- BillController: Accepts data from url and stores it before working on it and returning response
		
Test Data

Test: 1.1
Procedure: Check whether it can calculate the discount for an existing employee
Test Data: 
	Stored in Object:
	Person employee  = new Person(id = 100, name = "Jack", type ="Employee", years = 5);
	
	JSON data sent:
	{
	    "personId": 100,
	    "items" : [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40,
	            "quantity": 1
	        }
	    ]
	}

Expected Result: 
	{
	    "personId": 100,
	    "discount": 12.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 60.94
	}
Achieved Result:
	{
	    "personId": 100,
	    "discount": 12.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 60.94
	}

Test: 1.2
Procedure: Check whether it can calculate the discount for an existing Customer greater than 2 years
Test Data:
	Stored in Object:
	Person employee  = new Person(id = 200, name = "Tshepo", type ="Customer", years = 2);
	
	JSON data sent:
	{
	    "personId": 200,
	    "items" : [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40,
	            "quantity": 1
	        }
	    ]
	}
	
Expected Result:
	{
	    "personId": 200,
	    "discount": 2.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 70.94
	}
Achieved Result:
	{
	    "personId": 200,
	    "discount": 2.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 70.94
	}

Test: 1.3
Procedure: Check whether it can calculate the discount for an existing Customer greater than 2 years
Test Data:
	Stored in Object:
	Person employee  = new Person(id = 300, name = "Angela", type ="Customer", years = 1);
	
	JSON data sent:
	{
	    "personId": 300,
	    "items" : [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40,
	            "quantity": 1
	        }
	    ]
	}
Expected Result:
	{
	    "personId": 300,
	    "discount": 0.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 72.94
	}
Achieved Result:
	{
	    "personId": 300,
	    "discount": 0.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 72.94
	}

Test: 1.4
Procedure: Check whether it can calculate the percentage for an existing Customer greater than 2 years
Test Data:
	Stored in Object:
	Person employee  = new Person(id = 400, name = "QuickFix Pty Ltd", type ="Affiliate", years = 4);
	
	JSON data sent:
	{
	    "personId": 400,
	    "items" : [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40,
	            "quantity": 1
	        }
	    ]
	}
Expected Result:
	{
	    "personId": 400,
	    "discount": 4.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 68.94
	}
Achieved Result:
	{
	    "personId": 400,
	    "discount": 4.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "Electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 68.94
	}
	
Test: 2.1
Procedure: Check whether it can calculate the percentage for non existing Customers
Test Data:	
	JSON data sent:
	{
	    "personId": 500,
	    "items" : [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "electronics",
	            "price": 40,
	            "quantity": 1
	        }
	    ]
	}
Expected Result:
	{
	    "personId": 500,
	    "discount": 0.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 72.94
	}
Achieved Result:
	{
	    "personId": 500,
	    "discount": 0.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "electronics",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 72.94
	}
	
Test: 3.1
Procedure: Check whether it does not calculate DISCOUNT FOR groceries 
Test Data:
	Stored in Object:
	Person employee  = new Person(id = 400, name = "QuickFix Pty Ltd", type ="Affiliate", years = 4);
	
	JSON data sent:
	{
	    "personId": 400,
	    "items" : [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "groceries",
	            "price": 40,
	            "quantity": 1
	        }
	    ]
	}
Expected Result:
	{
	    "personId": 400,
	    "discount": 0.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "groceries",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 72.94
	}
Achieved Result:
	{
	    "personId": 400,
	    "discount": 0.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "groceries",
	            "price": 40.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 72.94,
	    "total": 72.94
	}
	
Test: 3.2
Procedure: Check whether it adds $5 per $100 total charge 
Test Data:
	Stored in Object:
	Person employee  = new Person(id = 400, name = "QuickFix Pty Ltd", type ="Affiliate", years = 4);
	
	JSON data sent:
	{
	    "personId": 400,
	    "items" : [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "groceries",
	            "price": 100,
	            "quantity": 1
	        }
	    ]
	}
Expected Result:
	{
	    "personId": 400,
	    "discount": 5.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "groceries",
	            "price": 100.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 132.94,
	    "total": 127.94
	}
Achieved Result:
	{
	    "personId": 400,
	    "discount": 5.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "groceries",
	            "price": 100.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 132.94,
	    "total": 127.94
	}

Test: 3.3
Procedure: Check whether it adds $5 per $100 total charge 
Test Data:
	Stored in Object:
	Person employee  = new Person(id = 400, name = "QuickFix Pty Ltd", type ="Affiliate", years = 4);
	
	JSON data sent:
	{
	    "personId": 400,
	    "items" : [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "groceries",
	            "price": 200,
	            "quantity": 1
	        }
	    ]
	}
Expected Result:
	{
	    "personId": 400,
	    "discount": 10.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "groceries",
	            "price": 200.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 232.94,
	    "total": 222.94
	}
Achieved Result:
	{
	    "personId": 400,
	    "discount": 10.0,
	    "items": [
	        {
	            "name": "apples",
	            "type": "groceries",
	            "price": 2.99,
	            "quantity": 1
	        },
	        {
	            "name": "pears",
	            "type": "groceries",
	            "price": 5.99,
	            "quantity": 5
	        },
	        {
	            "name": "Laptop",
	            "type": "groceries",
	            "price": 200.0,
	            "quantity": 1
	        }
	    ],
	    "initialTotal": 232.94,
	    "total": 222.94
	}