package com.creditcardpayment.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcardpayment.bean.CreditCard;
import com.creditcardpayment.entity.CreditCardEntity;
import com.creditcardpayment.entity.CustomerEntity;
import com.creditcardpayment.exception.CreditCardException;
import com.creditcardpayment.exception.CustomerException;
import com.creditcardpayment.repository.ICreditCardRepository;
import com.creditcardpayment.repository.ICustomerRepository;


@Service
public class CreditCardServiceImpl implements ICreditCardService {
	
	@Autowired
	private ICreditCardRepository creditCardRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;

	
	private EMParse parser;
	
public CreditCardServiceImpl() {
		
	}

	public CreditCardServiceImpl(ICreditCardRepository creditCardRepo,EMParse parser) {
		super();
		this.creditCardRepo = creditCardRepo;
		this.parser = parser;
	}
	public ICreditCardRepository getCreditCardRepo() {
		return creditCardRepo;
	}

	public void setCreditCardRepo(ICreditCardRepository creditCardRepo) {
		this.creditCardRepo = creditCardRepo;
	}

	public EMParse getParser() {
		return parser;
	}

	public void setParser(EMParse parser) {
		this.parser = parser;
	}

	@Override
	public CreditCard add(CreditCard creditCard) throws CreditCardException {
		if(creditCard !=null) {
			if(creditCardRepo.existsById(creditCard.getCardNumber())) {
				throw new CreditCardException("CreditCard "+creditCard.getCardNumber()+" is already Exists");
			}else {
				creditCard=parser.parse(creditCardRepo.save(parser.parse(creditCard)));
			}
		}
		return creditCard;
	}

	@Override
	public CreditCard save(CreditCard creditCard) throws CreditCardException {
		if(creditCard==null) {
			throw new CreditCardException("Credit Card  should not be null");
		}else {
			return parser.parse(creditCardRepo.save(parser.parse(creditCard)));
		}
	}

	@Override
	public void deleteById(String creditCardId) throws CreditCardException {
		if(creditCardId==null) {
			throw new CreditCardException("Card number should not be null");
		}else if (!creditCardRepo.existsById(creditCardId)) {
			throw new CreditCardException("Card Number"+creditCardId+" does not exists");
		}else {
			creditCardRepo.deleteById(creditCardId);
		}
	}

	@Override
	public CreditCard findById(String creditCardId) throws CreditCardException {
		if(creditCardId==null) {
			throw new CreditCardException("Card number should not be null");
		}else if (!creditCardRepo.existsById(creditCardId)) {
			throw new CreditCardException("Card Number"+creditCardId+" does not exists");
		}else {
			return parser.parse(creditCardRepo.findById(creditCardId).orElse(null));
		}
	}

	@Override
	public List<CreditCard> findAll() {
		return creditCardRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public boolean existsById(String cardNumber) throws CreditCardException {
		if(cardNumber==null) {
			throw new CreditCardException("Card Number should not be null");
		}
		return creditCardRepo.existsById(cardNumber);
	}

	@Override
	public CreditCard addToCustomer(CreditCard creditCard, String customerId) throws CreditCardException, CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		creditCard.setCustomerId(customerId);
		if(customerId==null) {
			throw new CustomerException("Customer Id should not null");
		}else if (customer==null) {
			throw new CustomerException("Customer does not exist");
		}
		Set<CreditCard> creditCards=customer.getCreditCard().stream().map(parser::parse).collect(Collectors.toSet());
		if(creditCards.contains(creditCard)) {
			throw new CreditCardException("CreditCard "+creditCard.getCardNumber()+" is already Exists");
		}else {
			creditCard=parser.parse(creditCardRepo.save(parser.parse(creditCard)));
			customer.getCreditCard().add(parser.parse(creditCard));
			customer.setCreditCard(customer.getCreditCard());
			customerRepo.save(customer);	
		}
		return creditCard;
	}

	@Override
	public Set<CreditCard> findByCustomerId(String customerId) throws CreditCardException, CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id can not be null");
		}else if(customer==null) {
			throw new CustomerException("Customer Does not Exists");
		}else if(customer.getCreditCard().isEmpty()) {
			throw new CreditCardException("No Credit Cards Exists");
		}else {
			return customer.getCreditCard().stream().map(parser::parse).collect(Collectors.toSet());
		}
	}

	@Override
	public void deleteCreditCardOfCustomer(String customerId, String cardNumber) throws CreditCardException, CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id can not be null");
		}else if(customer==null) {
			throw new CustomerException("No Customer Exists");
		}else if(customer.getCreditCard().isEmpty()) {
			throw new CustomerException("No Credit Card Exists");
		}
		CreditCardEntity card =creditCardRepo.findById(cardNumber).orElse(null);
		if(card==null) {
			throw new CreditCardException("CreditCard doesnot exist to delete");
		}
		if(!customer.getCreditCard().contains(card)) {
			throw new CustomerException("Credit Card Not Exists");
		}
		customer.getCreditCard().remove(card);
		creditCardRepo.deleteById(cardNumber);
	}

}

