package com.creditcardpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcardpayment.bean.Account;
import com.creditcardpayment.bean.Customer;
import com.creditcardpayment.exception.AccountException;
import com.creditcardpayment.exception.CustomerException;
import com.creditcardpayment.service.ICustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok(customerService.findAll());
	}
	
	@GetMapping("/getCustomer/{userId}")
	public ResponseEntity<Customer> findById(@PathVariable("userId") String userId) throws CustomerException{
		ResponseEntity<Customer> response=null;
		if(!customerService.existsById(userId)){
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			response=new ResponseEntity<>(customerService.findById(userId),HttpStatus.FOUND);
		}
		return response;
	}
	
	@PostMapping("/addCustomer/{userId}")
	public ResponseEntity<String> add(@RequestBody Customer customer,@PathVariable("userId") String userId) throws CustomerException {
		ResponseEntity<String> response=null;
		if(customer==null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			customerService.addCustomer(customer,userId);
			response= new ResponseEntity<>("Customer is Added",HttpStatus.CREATED);
		}
		return response;
	}
	
	@DeleteMapping("/deleteCustomer/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) throws CustomerException {
		ResponseEntity<String> response=null;
		Customer customer=customerService.findById(userId);
		if(customer==null) {
			response = new ResponseEntity<>("Customer is not Exists",HttpStatus.NOT_FOUND);
		}else {
			customerService.deleteById(customer.getUserId());
			customerService.deleteById(userId);
			response=new ResponseEntity<>("Customer is Deleted",HttpStatus.OK);
		}
		return response;
	}
	
	@PutMapping("/updateCustomer/{userId}")
	public ResponseEntity<Customer> updateUser(@RequestBody Customer user,@PathVariable("userId") String userId) throws CustomerException{
		
		ResponseEntity<Customer> response=null;
		if(user==null) {
			response=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			user =customerService.updateCustomer(user);
			response =new ResponseEntity<>(user,HttpStatus.OK);
		}
		
		return response;
	}
	
	@PostMapping("/addAccount/{customerId}")
	public ResponseEntity<String> addAccount(@RequestBody Account account,@PathVariable("customerId") String customerId) throws AccountException, CustomerException{
		ResponseEntity<String> response=null;
		if(customerService.addAccount(account, customerId)) {
			response = new ResponseEntity<>("Account is Added",HttpStatus.CREATED);
		}else {
			response= new ResponseEntity<>("Account is not Added",HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
	@GetMapping("/getAccounts/{customerId}")
	public ResponseEntity<List<Account>> getAccounts(@PathVariable("customerId") String customerId) throws AccountException{
		ResponseEntity<List<Account>> response=null;
		List<Account> accounts=customerService.getAccounts(customerId);
		if(accounts==null) {
			response=new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			response=new ResponseEntity<>(customerService.getAccounts(customerId),HttpStatus.FOUND);
		}
		return response;
	}
}
