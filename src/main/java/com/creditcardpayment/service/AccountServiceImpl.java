package com.creditcardpayment.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcardpayment.bean.Account;
import com.creditcardpayment.entity.AccountEntity;
import com.creditcardpayment.entity.CustomerEntity;
import com.creditcardpayment.exception.AccountException;
import com.creditcardpayment.exception.CustomerException;
import com.creditcardpayment.repository.IAccountRepository;
import com.creditcardpayment.repository.ICustomerRepository;


@Service
public class AccountServiceImpl implements IAccountService {
	
	String constant="Account ";
	
	@Autowired
	private IAccountRepository accountRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	@Autowired
	private EMParse parser;
	
	public AccountServiceImpl() {
		
	}
	
	public AccountServiceImpl(IAccountRepository accountRepo) {
		super();
		this.accountRepo = accountRepo;
		this.parser=new EMParse();
	}

	public IAccountRepository getAccountRepo() {
		return accountRepo;
	}

	public void setAccountRepo(IAccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}

	public EMParse getParser() {
		return parser;
	}

	public void setParser(EMParse parser) {
		this.parser = parser;
	}

	@Override
	public Account add(Account account) throws AccountException {
		if(account !=null) {
			if(accountRepo.existsById(account.getAccountNumber())) {
				throw new AccountException(constant+account.getAccountNumber()+" is already Exists");
			}else {
				account=parser.parse(accountRepo.save(parser.parse(account)));
			}
		}
		return account;
	}

	@Override
	public Account save(Account account) throws AccountException {
		if(account==null) {
			throw new AccountException("Account should not be null");
		}
		return parser.parse(accountRepo.save(parser.parse(account)));
	}

	@Override
	public void deleteById(String accountNumber) throws AccountException {
		if(accountNumber==null) {
			throw new AccountException("Account Number should not be null");
		}else if(!accountRepo.existsById(accountNumber)) {
			throw new AccountException(constant+accountNumber+" Does not Exists");
		}
		accountRepo.deleteById(accountNumber);
	}

	@Override
	public Account findById(String accountNumber) throws AccountException {
		if(accountNumber==null) {
			throw new AccountException("Account Number should not be null");
		}
		return parser.parse(accountRepo.findById(accountNumber).orElse(null));
	}

	@Override
	public List<Account> findAll() {
		return accountRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public boolean existsById(String accountNumber) throws AccountException {
		if(accountNumber==null) {
			throw new AccountException("Account Number can not be Null");
		}
		return accountRepo.existsById(accountNumber);
	}

	@Override
	public Account addByCustomer(Account account, String customerId) throws AccountException, CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id can not be null");
		}else if(customer==null) {
			throw new CustomerException("Customer does not exists");
		}
		Set<Account> accounts=customer.getAccounts().stream().map(parser::parse).collect(Collectors.toSet());
		if(accounts.contains(account)) {
			throw new AccountException(constant+account.getAccountNumber()+" is already Exists");
		}else {
			account=parser.parse(accountRepo.save(parser.parse(account)));
			customer.getAccounts().add(parser.parse(account));
			customer.setAccounts(customer.getAccounts());
			customerRepo.save(customer);
			
		}
		return account;
	}

	@Override
	public Set<Account> findAllByCustomerId(String customerId) throws CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id can not be null");
		}else if(customer==null) {
			throw new CustomerException("No Customer Exists");
		}else if(customer.getAccounts().isEmpty()) {
			throw new CustomerException("No Accounts Exists");
		}
		return customer.getAccounts().stream().map(parser::parse).collect(Collectors.toSet());
	}

	@Override
	public void deleteCustomerAccount(String customerId, String accountNumber) throws AccountException, CustomerException {
		CustomerEntity customer=customerRepo.findById(customerId).orElse(null);
		if(customerId==null) {
			throw new CustomerException("Customer Id can not be null");
		}else if(customer==null) {
			throw new CustomerException("No Customer Exists");
		}else if(customer.getAccounts().isEmpty()) {
			throw new CustomerException("No Accounts Exists");
		}
		AccountEntity account = accountRepo.findById(accountNumber).orElse(null);
		if(account==null) {
			throw new AccountException("Account doesnot exist to delete");
		}
		customer.getAccounts().remove(account);
	}
}
