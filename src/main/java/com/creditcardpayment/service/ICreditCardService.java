package com.creditcardpayment.service;

import java.util.List;
import java.util.Set;

import com.creditcardpayment.bean.CreditCard;
import com.creditcardpayment.exception.CreditCardException;
import com.creditcardpayment.exception.CustomerException;

public interface ICreditCardService {
	boolean existsById(String cardNumber) throws CreditCardException;

	CreditCard add(CreditCard creditCard) throws CreditCardException;

	CreditCard save(CreditCard creditCard) throws CreditCardException;

	void deleteById(String cardNumber) throws CreditCardException;

	CreditCard findById(String cardNumber) throws CreditCardException;

	List<CreditCard> findAll();

	Set<CreditCard> findByCustomerId(String customerId) throws CreditCardException, CustomerException;

	CreditCard addToCustomer(CreditCard creditCard, String customerId) throws CreditCardException, CustomerException;

	void deleteCreditCardOfCustomer(String customerId, String cardNumber) throws CreditCardException, CustomerException;
}

