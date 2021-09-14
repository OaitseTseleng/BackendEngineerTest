package com.oaitsetseleng.techassesment.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oaitsetseleng.techassesment.controllers.BillController;
import com.oaitsetseleng.techassesment.models.Bill;


/**
 * @author lloyd
 *
 *To get API request from browser,
 *only deals with fetching the request and returning result
 */
@RestController
@RequestMapping("/api/v1")
public class BillView {
	//Built in component to point to BillController
	@Autowired
	private BillController billController;
		
	//Get the request in a Bill format, and pass it to Controller
	@PostMapping("/getBill")
	public Bill createBill(@RequestBody Bill bill) {
		return billController.calculateBill(bill);
	}
}
