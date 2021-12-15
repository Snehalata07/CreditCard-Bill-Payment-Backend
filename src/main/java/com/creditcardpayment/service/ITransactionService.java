package com.creditcardpayment.service;

import java.util.List;

import com.creditcardpayment.bean.Transaction;
import com.creditcardpayment.exception.CreditCardException;
import com.creditcardpayment.exception.TransactionException;

public interface ITransactionService {
	boolean existsById(Long transactionId) throws TransactionException;

	Transaction add(Transaction transaction) throws TransactionException;
	Transaction save(Transaction transaction) throws TransactionException;
	
	void deleteById(Long transactionId) throws TransactionException;
	
	Transaction findById(Long transactionId) throws TransactionException;
	
	List<Transaction> findAll();
	
	Transaction transaction(String cardNumber,Double amount,String discription) throws CreditCardException;
}
