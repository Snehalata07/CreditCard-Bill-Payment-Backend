package com.creditcardpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcardpayment.bean.Payment;
import com.creditcardpayment.bean.Statement;
import com.creditcardpayment.exception.AccountException;
import com.creditcardpayment.exception.CreditCardException;
import com.creditcardpayment.exception.PaymentException;
import com.creditcardpayment.exception.StatementException;
import com.creditcardpayment.service.IPaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentRestController {

	@Autowired
	private IPaymentService paymentService;
	
	@GetMapping("/getAllPayments")
	public ResponseEntity<List<Payment>> findAll() {
		return ResponseEntity.ok(paymentService.findAll());
	}
	
	@GetMapping("/getPayment/{paymentId}")
	public ResponseEntity<Payment> findById(@PathVariable("paymentId") Long paymentId) throws PaymentException{
		return ResponseEntity.ok(paymentService.findById(paymentId));
	}
	
	@PostMapping("/addPayment")
	public ResponseEntity<Payment> add(@RequestBody Payment payment) throws PaymentException {
		payment=paymentService.add(payment);
		return ResponseEntity.ok(payment);
	}
	
	@GetMapping("/pendingBills/{cardNumber}")
	public ResponseEntity<List<Statement>> getPendingStatements(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		return ResponseEntity.ok(paymentService.pendingBills(cardNumber));
	}
	
	@PostMapping("/pendingBills/payUsingUPI/{statementId}")
	public ResponseEntity<Payment> paybill(@RequestBody Payment pay,@PathVariable("statementId") Long statementId) throws PaymentException, CreditCardException, StatementException{
		return ResponseEntity.ok(paymentService.payBill(pay,statementId));
	}
	
	@PostMapping("/pendingBills/payUsingAccount/{statementId}/{accountNumber}")
	public ResponseEntity<Payment> paybillUsingAccount(@RequestBody Payment pay,@PathVariable("statementId") Long statementId,@PathVariable("accountNumber") String accountNumber) throws PaymentException, CreditCardException, StatementException, AccountException{
		return ResponseEntity.ok(paymentService.payBill(pay,statementId,accountNumber));
	}
	
	@GetMapping("/paymentHistory/{cardNumber}")
	public ResponseEntity<List<Payment>> paymentHistory(@PathVariable("cardNumber") String cardNumber) throws CreditCardException{
		return ResponseEntity.ok(paymentService.paymentHistory(cardNumber));
	}
	
}
