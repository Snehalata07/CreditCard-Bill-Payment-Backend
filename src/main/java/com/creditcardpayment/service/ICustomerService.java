package com.creditcardpayment.service;

import java.util.List;

import com.creditcardpayment.bean.Account;
import com.creditcardpayment.bean.Customer;
import com.creditcardpayment.exception.AccountException;
import com.creditcardpayment.exception.CustomerException;

public interface ICustomerService {
	boolean existsByContactNo(String contactNo) throws CustomerException;
	boolean existsByEmail(String email) throws CustomerException;
	boolean existsById(String userId) throws CustomerException;

	Customer addCustomer(Customer customer,String userId) throws CustomerException;
	Customer updateCustomer(Customer customer) throws CustomerException;
	void deleteById(String customerId) throws CustomerException;
	
	Customer findById(String customerId) throws CustomerException;
	
	List<Customer> findAll();


	boolean addAccount(Account account,String customerId) throws AccountException, CustomerException;
	List<Account> getAccounts(String customerId) throws AccountException;
}
