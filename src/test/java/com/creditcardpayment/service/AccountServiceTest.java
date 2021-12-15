package com.creditcardpayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.creditcardpayment.bean.Account;
import com.creditcardpayment.bean.AccountType;
import com.creditcardpayment.entity.AccountEntity;
import com.creditcardpayment.exception.AccountException;
import com.creditcardpayment.repository.IAccountRepository;
import com.creditcardpayment.service.AccountServiceImpl;
import com.creditcardpayment.service.EMParse;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	
	
	 @Mock
	 private IAccountRepository accountRepo;
	  
	  @Mock
	  private EMParse parser;
	 
	
	@InjectMocks
	private AccountServiceImpl service;

	@Test
	@DisplayName("AccountDetails should retrive")
	void testGetAll() {
		List<AccountEntity> testData=Arrays.asList(new AccountEntity[] {
				new AccountEntity("42356879562","Sneha Dash",50000.0,AccountType.SAVINGS),
				new AccountEntity("42356879563","Tripti",40000.0,AccountType.SAVINGS),
				new AccountEntity("42356879564","Smita",90000.0,AccountType.CURRENT)
		});
		
		Mockito.when(accountRepo.findAll()).thenReturn(testData);
		
		List<Account> expected=Arrays.asList(new Account[] {
				new Account("42356879562","Sneha Dash",50000.0,AccountType.SAVINGS),
				new Account("42356879563","Tripti",40000.0,AccountType.SAVINGS),
				new Account("42356879564","Smita",90000.0,AccountType.CURRENT)
		});
		
		List<Account> actual = service.findAll();
		
		assertEquals(expected.get(0).getAccountName(),actual.get(0).getAccountName());

	}
	
	
	@Test
	@DisplayName("AccountDetails add")
	void testAdd() throws AccountException {
		AccountEntity account1=new AccountEntity("42356879562","Manoj Aich",50000.0,AccountType.SAVINGS);
		Account expected=new Account("42356879562","Manoj Aich",50000.0,AccountType.SAVINGS);
		when(accountRepo.existsById(account1.getAccountNumber())).thenReturn(false);
	    when(accountRepo.save(Mockito.any())).thenReturn(account1);
		Account actual = service.add(expected);
		assertEquals(expected.getAccountName(),actual.getAccountName());
	}
	
	@Test
	@DisplayName("AccountDetails should delete")
	void testDelete() throws AccountException {
		AccountEntity account1=new AccountEntity("42356879562","Manoj Aich",50000.0,AccountType.SAVINGS);
		Mockito.when(accountRepo.existsById(account1.getAccountNumber())).thenReturn(true);
		Mockito.doNothing().when(accountRepo).deleteById(account1.getAccountNumber());
		service.deleteById(account1.getAccountNumber());
	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws AccountException {
		AccountEntity testdata=new AccountEntity("42356879564","Smita",90000.0,AccountType.SAVINGS);
		
		Account expected=new Account("42356879564","Smita",90000.0,AccountType.SAVINGS);
		
		
		Mockito.when(accountRepo.findById(testdata.getAccountNumber())).thenReturn(Optional.of(testdata));
	
		Account actual=service.findById(testdata.getAccountNumber());
		
		assertEquals(expected.getAccountName(),actual.getAccountName());
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws AccountException {		
		
		Mockito.when(accountRepo.findById("425631257892")).thenReturn(Optional.empty());
		
		Account actual = service.findById("425631257892");
		assertNull(actual);
	}
	
}
