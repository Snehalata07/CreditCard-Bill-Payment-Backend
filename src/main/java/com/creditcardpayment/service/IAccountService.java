package com.creditcardpayment.service;

import java.util.List;

import java.util.Set;

import com.creditcardpayment.bean.Account;
import com.creditcardpayment.exception.AccountException;
import com.creditcardpayment.exception.CustomerException;

public interface IAccountService {
boolean existsById(String accountNumber) throws AccountException;
	
	Account add(Account account) throws AccountException;
	Account save(Account account) throws AccountException;
	
	void deleteById(String accountNumber) throws AccountException;
	
	void deleteCustomerAccount(String customerId, String accountNumber) throws AccountException, CustomerException;
	
	Account findById(String accountNumber) throws AccountException;
	
	List<Account> findAll();
	
	Account addByCustomer(Account account,String customerId) throws AccountException, CustomerException;
	Set<Account> findAllByCustomerId(String customerId) throws CustomerException;
}
