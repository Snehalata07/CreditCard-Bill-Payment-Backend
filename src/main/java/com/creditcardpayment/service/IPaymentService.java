package com.creditcardpayment.service;

import java.util.List;

import com.creditcardpayment.bean.Payment;
import com.creditcardpayment.bean.Statement;
import com.creditcardpayment.exception.AccountException;
import com.creditcardpayment.exception.CreditCardException;
import com.creditcardpayment.exception.PaymentException;
import com.creditcardpayment.exception.StatementException;

public interface IPaymentService {
	boolean existsById(Long paymentId);

	Payment add(Payment payment) throws PaymentException;
	Payment save(Payment payment) throws PaymentException;

	void deleteById(Long paymentId) throws PaymentException;
	
	Payment findById(Long paymentId) throws PaymentException;
	List<Payment> findAll();
	
	Payment payBill(Payment payment,Long statementId,String accountNumber)throws PaymentException, CreditCardException, StatementException, AccountException;
	Payment payBill(Payment payment,Long statementId) throws PaymentException, CreditCardException, StatementException;
	List<Statement> pendingBills(String cardNumber) throws CreditCardException;
	List<Payment> paymentHistory (String cardNumber) throws CreditCardException;
}
