package com.oaitsetseleng.techassesment.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lloyd
 *
 *Person object will only be created when it is called,
 *can have multiple objects created at runtime
 */

@Component
@Scope(value="prototype")
public class Person {
	private int personId;
	private String name;
	private String type;
	private int years;

	public Person(int personId, String name, String type, int years) {
		super();
		this.personId = personId;
		this.name = name;
		this.type = type;
		this.years = years;
	}

	public Person(int personId, String name, String type) {
		super();
		this.personId = personId;
		this.name = name;
		this.type = type;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
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
	
	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}
	
}
