package com.creditcardpayment.service;

import java.time.LocalDate;
import java.util.List;

import com.creditcardpayment.bean.Statement;
import com.creditcardpayment.exception.CreditCardException;
import com.creditcardpayment.exception.StatementException;

public interface IStatementService {
boolean existsById(Long statementId) throws StatementException;
	
	
	Statement add(Statement statement) throws StatementException;
	Statement save(Statement statement) throws StatementException;
	
	void deleteById(Long statementId) throws StatementException;
	
	Statement findById(Long statementId) throws StatementException;
	
	List<Statement> findAll();
	
	Statement findByBillDate(LocalDate billDate);
	

	Statement getBilledStatement(String cardNumber) throws CreditCardException, StatementException;
	Statement getUnBilledStatement(String cardNumber) throws CreditCardException;
	
	List<Statement> statementHistory(String cardNumber) throws CreditCardException;
}