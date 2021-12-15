package com.creditcardpayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.creditcardpayment.bean.CardName;
import com.creditcardpayment.bean.CardType;
import com.creditcardpayment.bean.Statement;
import com.creditcardpayment.entity.CreditCardEntity;
import com.creditcardpayment.entity.CustomerEntity;
import com.creditcardpayment.entity.StatementEntity;
import com.creditcardpayment.exception.StatementException;
import com.creditcardpayment.repository.IStatementRepository;
import com.creditcardpayment.service.StatementServiceImpl;

@ExtendWith(MockitoExtension.class)
class StatementServiceTest {

	@Mock
	private IStatementRepository statementRepo;
	
	@InjectMocks
	private StatementServiceImpl service;

	@Test
	@DisplayName("StatementDetails should retrive")
	void testGetAll() {
		
		CreditCardEntity creditCard1=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity());
		
		List<StatementEntity> testData=Arrays.asList(new StatementEntity[] {
				new StatementEntity(1L,25000.0,25000.0,LocalDate.of(2021, 03, 18),LocalDate.of(2021, 04, 8),creditCard1),
				new StatementEntity(2L,25000.0,25000.0,LocalDate.of(2021, 03, 18),LocalDate.of(2021, 04, 8),creditCard1)
		});
		
		Mockito.when(statementRepo.findAll()).thenReturn(testData);
		
		List<Statement> expected=Arrays.asList(new Statement[] {
				new Statement(1L,25000.0,25000.0,LocalDate.of(2021, 03, 18),LocalDate.of(2021, 04, 8),creditCard1.getCardNumber(),creditCard1.getCustomer().getName()),
				new Statement(2L,25000.0,25000.0,LocalDate.of(2021, 03, 18),LocalDate.of(2021, 04, 8),creditCard1.getCardNumber(),creditCard1.getCustomer().getName())
		});
		
		List<Statement> actual = service.findAll();
		
		assertEquals(expected.get(0).getBillAmount(),actual.get(0).getBillAmount());

	}
	
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws StatementException {
		CreditCardEntity creditCard1=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity());
		
		StatementEntity testdata=new StatementEntity(1L,25000.0,25000.0,LocalDate.of(2021, 03, 18),LocalDate.of(2021, 04, 8),creditCard1);
		
		Statement expected=new Statement(1L,25000.0,25000.0,LocalDate.of(2021, 03, 18),LocalDate.of(2021, 04, 8),creditCard1.getCardNumber(),creditCard1.getCustomer().getName());
				
		
		Mockito.when(statementRepo.findById(testdata.getStatementId())).thenReturn(Optional.of(testdata));
		Mockito.when(statementRepo.existsById(testdata.getStatementId())).thenReturn(true);
		
		Statement actual=service.findById(testdata.getStatementId());
		
		assertEquals(expected.getBillAmount(),actual.getBillAmount());
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws StatementException {		
		
		Mockito.when(statementRepo.findById(1L)).thenReturn(Optional.empty());
		Mockito.when(statementRepo.existsById(1L)).thenReturn(true);
		
		Statement actual = service.findById(1L);
		assertNull(actual);
	}
	
}
