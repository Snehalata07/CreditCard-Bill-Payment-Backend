package com.creditcardpayment.exception;

public class PaymentException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public PaymentException(String exp) {
		super(exp);
	}

}
