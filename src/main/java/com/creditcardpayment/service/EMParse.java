package com.creditcardpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcardpayment.bean.Account;
import com.creditcardpayment.bean.CreditCard;
import com.creditcardpayment.bean.Customer;
import com.creditcardpayment.bean.Payment;
import com.creditcardpayment.bean.Statement;
import com.creditcardpayment.bean.Transaction;
import com.creditcardpayment.bean.User;
import com.creditcardpayment.entity.AccountEntity;
import com.creditcardpayment.entity.CreditCardEntity;
import com.creditcardpayment.entity.CustomerEntity;
import com.creditcardpayment.entity.PaymentEntity;
import com.creditcardpayment.entity.StatementEntity;
import com.creditcardpayment.entity.TransactionEntity;
import com.creditcardpayment.entity.UserEntity;
import com.creditcardpayment.repository.ICreditCardRepository;
import com.creditcardpayment.repository.ICustomerRepository;

@Service
public class EMParse {
	@Autowired
	private ICreditCardRepository creditCardRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	public Account parse(AccountEntity source) {
		return source==null?null:
			new Account(source.getAccountNumber(),
						source.getAccountName(),
						source.getAccountBalance(),
						source.getAccountType());
	}
	
	public AccountEntity parse(Account source) {
		return source==null?null:
			new AccountEntity(source.getAccountNumber(),
							source.getAccountName(),
							source.getAccountBalance(),
							source.getAccountType());
	}

	public CreditCard parse(CreditCardEntity source) {
		return source==null?null:
			new CreditCard(source.getCardNumber(),
						source.getCardName(),
						source.getCardType(),
						source.getExpiryDate(),
						source.getBankName(),
						source.getCvv(),
						source.getCreditLimit(),
						source.getUsedLimit(),
						source.getCustomer().getUserId());
	}
	
	public CreditCardEntity parse(CreditCard source) {
		return source==null?null:
				new CreditCardEntity(source.getCardNumber(),
						source.getCardName(),
						source.getCardType(),
						source.getExpiryDate(),
						source.getBankName(),
						source.getCvv(),
						source.getCardLimit(),
						source.getUsedLimit(),
						customerRepo.findById(source.getCustomerId()).orElse(null));
	}
	
	public Customer parse(CustomerEntity source) {
		return source==null?null:
			new Customer(source.getUserId(),
						source.getName(),
						source.getEmail(),
						source.getContactNo(),
						source.getDob(),
						source.getAddress());
	}
	
	public CustomerEntity parse(Customer source) {
		return source==null?null:
				new CustomerEntity(source.getUserId(),
						source.getUserName(),
						source.getEmail(),
						source.getContactNo(),
						source.getDob(),
						source.getAddress());
	}
	
	public Payment parse(PaymentEntity source) {
		return source==null?null:
			new Payment(source.getPaymentId(),
						source.getMethod(),
						source.getAmount(),
						source.getPaidDate(),
						source.getPaidTime(),
						source.getCard().getCardNumber());
	}
	
	public PaymentEntity parse(Payment source) {
		return source==null?null:
				new PaymentEntity(source.getPaymentId(),
								source.getMethod(),
								source.getPaidDate(),
								source.getPaidTime(),
								source.getAmount(),
								creditCardRepo.findById(source.getCardNumber()).orElse(null));
	}
	
	public Statement parse(StatementEntity source) {
		return source==null?null:
			new Statement(source.getStatementId(),
						source.getBillAmount(),
						source.getDueAmount(),
						source.getBillDate(),
						source.getDueDate(),
						source.getCreditCard().getCardNumber(),
						source.getCreditCard().getCustomer().getName());
	}
	
	public StatementEntity parse(Statement source) {
		return source==null?null:
				new StatementEntity(source.getStatementId(),
							source.getBillAmount(),
							source.getDueAmount(),
							source.getBillDate(),
							source.getDueDate(),
							creditCardRepo.findById(source.getCardNumber()).orElse(null));
	}
	
	public Transaction parse(TransactionEntity source) {
		return source==null?null:
			new Transaction(source.getTransactionId(),
						source.getCreditCard().getCardNumber(),
						source.getAmount(),
						source.getTransactionDate(),
						source.getTransactionTime(),
						source.getStatus(),
						source.getDescription());
	}
	
	public TransactionEntity parse(Transaction source) {
		return source==null?null:
				new TransactionEntity(source.getTransactionId(), 
						source.getStatus(),
						creditCardRepo.findById(source.getCardNumber()).orElse(null),
						source.getAmount(),
						source.getDescription());
	}
	
	public User parse(UserEntity source) {
		return source==null?null:
			new User(source.getUserId(),
						source.getPassword(),
						source.getRole());
	}
	
	public UserEntity parse(User source) {
		return source==null?null:
				new UserEntity(source.getUserId(),
							source.getPassword(),
							source.getRole());
	}
}
