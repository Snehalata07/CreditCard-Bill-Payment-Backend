package com.creditcardpayment.exception;

public class TransactionException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public TransactionException(String exp) {
		super(exp);
	}

}
