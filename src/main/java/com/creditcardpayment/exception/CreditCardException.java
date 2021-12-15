package com.creditcardpayment.exception;

public class CreditCardException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public CreditCardException(String exp) {
		super(exp);
	}

}