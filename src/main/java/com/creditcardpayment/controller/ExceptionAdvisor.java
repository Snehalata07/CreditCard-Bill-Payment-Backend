package com.creditcardpayment.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.creditcardpayment.exception.AccountException;
import com.creditcardpayment.exception.CustomerException;
import com.creditcardpayment.exception.PaymentException;
import com.creditcardpayment.exception.StatementException;
import com.creditcardpayment.exception.TransactionException;
import com.creditcardpayment.exception.UserException;

@RestControllerAdvice
public class ExceptionAdvisor {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(UserException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(CustomerException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(PaymentException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StatementException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(StatementException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(TransactionException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<String> handleCreditCardPaymentExceptionAction(AccountException excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptionAction(Exception excep) {
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	static String messageFrom(BindingResult result) {		
		return result.getAllErrors().stream()
				.map(err -> err.getObjectName() + "-"+err.getDefaultMessage())
				.collect(Collectors.toList()).toString();
	}
}
