package com.creditcardpayment.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcardpayment.bean.Account;
import com.creditcardpayment.bean.Payment;
import com.creditcardpayment.bean.Statement;
import com.creditcardpayment.entity.AccountEntity;
import com.creditcardpayment.entity.CreditCardEntity;
import com.creditcardpayment.entity.StatementEntity;
import com.creditcardpayment.exception.AccountException;
import com.creditcardpayment.exception.CreditCardException;
import com.creditcardpayment.exception.PaymentException;
import com.creditcardpayment.exception.StatementException;
import com.creditcardpayment.repository.IAccountRepository;
import com.creditcardpayment.repository.ICreditCardRepository;
import com.creditcardpayment.repository.IPaymentRepository;
import com.creditcardpayment.repository.IStatementRepository;


@Service
public class PaymentServiceImpl implements IPaymentService {
	
	@Autowired
	private IPaymentRepository paymentRepo;
	
	@Autowired
	private ICreditCardRepository creditCardRepo;
	
	@Autowired
	private IAccountRepository accountRepo;
	
	@Autowired
	private IStatementRepository statementRepo;

	@Autowired
	private IPaymentService paymentService;
	
	@Autowired
	private EMParse parser;
	
	public PaymentServiceImpl() {
		
	}

	public PaymentServiceImpl(IPaymentRepository paymentRepo) {
		super();
		this.paymentRepo = paymentRepo;
		this.parser = new EMParse();
	}


	@Override
	public Payment add(Payment payment) throws PaymentException {
		if(payment !=null) {
			if(paymentRepo.existsById(payment.getPaymentId())) {
				throw new PaymentException("Payment "+payment.getPaymentId()+" is already Exists");
			}else {
				payment=parser.parse(paymentRepo.save(parser.parse(payment)));
			}
		}
		return payment;
	}

	@Override
	public Payment save(Payment payment) throws PaymentException {
		if(payment==null) {
			throw new PaymentException("Payment can not be Null");
		}
		return parser.parse(paymentRepo.save(parser.parse(payment)));
	}

	@Override
	public void deleteById(Long paymentId) throws PaymentException {
		if(paymentId==null) {
			throw new PaymentException("payment Id can not be null");
		}else if(!paymentRepo.existsById(paymentId)) {
			throw new PaymentException("Payment Id "+paymentId+" Does not Exist");
		}
		paymentRepo.deleteById(paymentId);
	}

	@Override
	public Payment findById(Long paymentId) throws PaymentException {
		if(paymentId==null) {
			throw new PaymentException("payment Id can not be null");
		}else if(!paymentRepo.existsById(paymentId)) {
			throw new PaymentException("Payment Id "+paymentId+" Does not Exist");
		}
		return parser.parse(paymentRepo.findById(paymentId).orElse(null));
	}

	@Override
	public List<Payment> findAll() {
		return paymentRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}


	@Override
	public boolean existsById(Long paymentId) {
		return paymentRepo.existsById(paymentId);
	}


	@Override
	public List<Statement> pendingBills(String cardNumber) throws CreditCardException {
		CreditCardEntity card=creditCardRepo.findById(cardNumber).orElse(null);
		if(cardNumber==null) {
			throw new CreditCardException("Card Number can not be null");
		}else if(card==null) {
			throw new CreditCardException("Card does not Exists");
		}		
		Set<StatementEntity> statements =card.getStatement();			
	
		List<Statement> pendingStatements=statements.stream().filter(state->state.getDueAmount()>=1.0).distinct().map(parser::parse).collect(Collectors.toList());
		
		//pendingStatements.sort((st1,st2)->st1.getStatementId().compareTo(st2.getStatementId()));
		
		return pendingStatements;
	}


	@Override
	public Payment payBill(Payment pay, Long statementId,String accountNumber) throws PaymentException, CreditCardException, StatementException, AccountException {
		
		CreditCardEntity card=creditCardRepo.findById(pay.getCardNumber()).orElse(null);
		
		if(card==null) {
			throw new CreditCardException("Credit card does not Exists");
		}
		
		Statement statement=card.getStatement().stream().filter(state->state.getStatementId().equals(statementId)).map(parser::parse).findFirst().orElse(null);
		if(statementId==null) {
			throw new StatementException("StatementId can not be Null");
		}else if(statement==null) {
			throw new StatementException("Statement Does not ");
		}
		Payment payment=new Payment();
		payment.setCardNumber(pay.getCardNumber());
		payment.setMethod(pay.getMethod());
		
		if(accountNumber==null) {
			throw new AccountException("account number can not be null");
		}
		AccountEntity acc=accountRepo.findById(accountNumber).orElse(null);
		if(acc==null) {
			throw new AccountException("Account Does not Exists");
		}
		Double accountBalance=acc.getAccountBalance();
		
		Double amount=pay.getAmount();
		Account account=parser.parse(accountRepo.findById(accountNumber).orElse(null));
		account.setAccountBalance(accountBalance-amount);
		payment.setAmount(amount);
		card.setUsedLimit(card.getUsedLimit()-amount);
		statement.setDueAmount(statement.getDueAmount()-amount);
		accountRepo.save(parser.parse(account));
		payment.setPaidDate(LocalDate.now());
		payment.setPaidTime(LocalTime.now());
		payment.setPaymentId(pay.getPaymentId());
		statementRepo.save(parser.parse(statement));
		paymentService.add(payment);
		return payment;
	}


	@Override
	public Payment payBill(Payment pay, Long statementId) throws PaymentException, CreditCardException, StatementException{
		CreditCardEntity card=creditCardRepo.findById(pay.getCardNumber()).orElse(null);
		if(card==null) {
			throw new CreditCardException("Card does not exists");
		}
		Statement statement=card.getStatement().stream().filter(state->state.getStatementId().equals(statementId)).map(parser::parse).findFirst().orElse(null);
		if(statement==null) {
			throw new StatementException("Statement Does not exists");
		}
		Payment payment=new Payment();
		payment.setPaymentId(pay.getPaymentId());
		payment.setCardNumber(pay.getCardNumber());
		payment.setMethod(pay.getMethod());
		Double amount=pay.getAmount();
		Double dueAmount=statement.getDueAmount();
		payment.setAmount(amount);
		card.setUsedLimit(card.getUsedLimit()-amount);
		payment.setPaidDate(LocalDate.now());
		payment.setPaidTime(LocalTime.now());
		statement.setDueAmount(dueAmount-amount);
		statementRepo.save(parser.parse(statement));
		paymentService.add(payment);
		return payment;
	}


	@Override
	public List<Payment> paymentHistory(String cardNumber) throws CreditCardException {
		CreditCardEntity card=creditCardRepo.findById(cardNumber).orElse(null);
		if(cardNumber==null) {
			throw new CreditCardException("Card number can not be null");
		}else if(card==null) {
			throw new CreditCardException("Credit Card does not exists");
		}
		return card.getPayments().stream().map(parser::parse).collect(Collectors.toList());
	}
	
	

}