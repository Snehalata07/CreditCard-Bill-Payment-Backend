package com.creditcardpayment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.creditcardpayment.bean.Transaction;
import com.creditcardpayment.bean.TransactionStatus;
import com.creditcardpayment.entity.CreditCardEntity;
import com.creditcardpayment.entity.CustomerEntity;
import com.creditcardpayment.entity.TransactionEntity;
import com.creditcardpayment.exception.TransactionException;
import com.creditcardpayment.repository.ITransactionRepository;
import com.creditcardpayment.service.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

	@Mock
	private ITransactionRepository transactionRepo;
	
//	@Mock
//	private ICreditCardRepository creditCardRepo;
	
	@InjectMocks
	private TransactionServiceImpl service;
	
//	@InjectMocks
//	private CreditCardServiceImpl creditCard;

	@Test
	@DisplayName("TransactionDetails should retrive")
	void testGetAll() {
		CreditCardEntity creditCard1=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity());
		
		List<TransactionEntity> testData=Arrays.asList(new TransactionEntity[] {
				new TransactionEntity(1L,TransactionStatus.SUCCESSFUL,LocalDate.now(),LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()),6000.0,"buied",creditCard1),
				new TransactionEntity(2L,TransactionStatus.SUCCESSFUL,LocalDate.now(),LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()),3000.0,"sendToFriend",creditCard1)
		});
		
		Mockito.when(transactionRepo.findAll()).thenReturn(testData);
		
		List<Transaction> expected=Arrays.asList(new Transaction[] {
				new Transaction(1L,creditCard1.getCardNumber(),6000.0,LocalDate.now(),LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()), TransactionStatus.SUCCESSFUL,"buied"),
				new Transaction(2L,creditCard1.getCardNumber(),3000.0,LocalDate.now(),LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()),TransactionStatus.SUCCESSFUL,"sendToFriend")
		});
		
		List<Transaction> actual = service.findAll();
		
		assertEquals(expected.get(0).getAmount(),actual.get(0).getAmount());

	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws TransactionException {
		CreditCardEntity creditCard1=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity());
		
		TransactionEntity testdata=new TransactionEntity(1L,TransactionStatus.SUCCESSFUL,LocalDate.now(),LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()),6000.0,"buied",creditCard1);
		
		Transaction expected=new Transaction(1L,creditCard1.getCardNumber(),6000.0,LocalDate.now(),LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()),TransactionStatus.SUCCESSFUL,"buied");
		
		
		Mockito.when(transactionRepo.findById(testdata.getTransactionId())).thenReturn(Optional.of(testdata));
		Mockito.when(transactionRepo.existsById(testdata.getTransactionId())).thenReturn(true);
		
		Transaction actual=service.findById(testdata.getTransactionId());
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected.getAmount(),actual.getAmount());
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws TransactionException {		
		
		Mockito.when(transactionRepo.findById(1L)).thenReturn(Optional.empty());
		Mockito.when(transactionRepo.existsById(1L)).thenReturn(true);
		
		Transaction actual = service.findById(1L);
		assertNull(actual);
	}
	
}