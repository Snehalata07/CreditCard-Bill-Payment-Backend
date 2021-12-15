package com.creditcardpayment.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.springframework.util.Assert;

import com.creditcardpayment.bean.Address;
import com.creditcardpayment.bean.CardName;
import com.creditcardpayment.bean.CardType;
import com.creditcardpayment.bean.CreditCard;
import com.creditcardpayment.entity.CreditCardEntity;
import com.creditcardpayment.entity.CustomerEntity;
import com.creditcardpayment.exception.CreditCardException;
import com.creditcardpayment.repository.ICreditCardRepository;
import com.creditcardpayment.repository.ICustomerRepository;
import com.creditcardpayment.service.CreditCardServiceImpl;
import com.creditcardpayment.service.CustomerServiceImpl;


@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {

	@Mock
	private ICreditCardRepository creditCardRepo;
	
	@InjectMocks
	private CreditCardServiceImpl service;
	
	@Mock
	private ICustomerRepository customerRepo;
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	@Mock
	private EMParse parser;
	
	
	@Test
	@DisplayName("Card Details should retrive")
	void testGetAll() {
		
		List<CreditCardEntity> testData=Arrays.asList(new CreditCardEntity[] {
				new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity()),
				new CreditCardEntity("2568479632444",CardName.VISA,CardType.GOLD,LocalDate.parse("2023-05-22"),"SBI",528,10000.0,10000.0,new CustomerEntity())
		});
		Mockito.when(creditCardRepo.findAll()).thenReturn(testData);
		List<CreditCard> expected=Arrays.asList(new CreditCard[] {
				new CreditCard("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity().getUserId()),
				new CreditCard("2568479632444",CardName.VISA,CardType.GOLD,LocalDate.parse("2023-05-22"),"SBI",528,10000.0,10000.0,new CustomerEntity().getUserId())
		});
		Mockito.when(parser.parse(testData.get(0))).thenReturn(expected.get(0));
		Mockito.when(parser.parse(testData.get(1))).thenReturn(expected.get(1));
		List<CreditCard> actual = service.findAll();
		assertEquals(expected.size(), 2);
		assertEquals(actual.size(), 2);
		assertEquals(expected.get(0).getBankName(),actual.get(0).getBankName());

	}
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws CreditCardException {
		CreditCardEntity testdata=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity());
		
		CreditCard expected=new CreditCard("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity().getUserId());
		
		Mockito.when(creditCardRepo.findById(testdata.getCardNumber())).thenReturn(Optional.of(testdata));
		Mockito.when(creditCardRepo.existsById(testdata.getCardNumber())).thenReturn(true);
		Mockito.when(parser.parse(testdata)).thenReturn(expected);
		
	
		CreditCard actual=service.findById(testdata.getCardNumber());
		assertEquals(expected.getBankName(),actual.getBankName());
		assertEquals(expected.getCardNumber(),actual.getCardNumber());

	}
	
		
	@Test
	@DisplayName("CreditCardDetails add")
	void testAdd() throws CreditCardException {
		CustomerEntity customer=new CustomerEntity("U101","10-10A","nellore","andhra",LocalDate.now(),new Address());
		CreditCard expected=new CreditCard("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,customer.getUserId());
		CreditCardEntity creditCard1=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,customer);
		Mockito.when(creditCardRepo.save(Mockito.any())).thenReturn(creditCard1);
		Mockito.when(parser.parse((CreditCard) Mockito.any())).thenReturn(creditCard1);
		Mockito.when(parser.parse((CreditCardEntity) Mockito.any())).thenReturn(expected);
		CreditCard actual = service.add(expected);
		assertEquals(expected,actual);
	}
		
	@Test
	@DisplayName("CreditCardDetails should delete")
	void testDelete() throws CreditCardException {
		CustomerEntity customer=new CustomerEntity("U101","10-10A","nellore","andhra",LocalDate.now(),new Address());
		CreditCard expected=new CreditCard("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,customer.getUserId());
		Mockito.when(creditCardRepo.existsById(expected.getCardNumber())).thenReturn(true);
		service.deleteById(expected.getCardNumber());
		Mockito.when(creditCardRepo.existsById(expected.getCardNumber())).thenReturn(false);
		boolean test=service.getCreditCardRepo().existsById(expected.getCardNumber());
		assertFalse(test);

	}

	
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws CreditCardException {		
		
		Mockito.when(creditCardRepo.findById("425631257892")).thenReturn(Optional.empty());
		Mockito.when(creditCardRepo.existsById("425631257892")).thenReturn(true);
		
		CreditCard actual = service.findById("425631257892");
		
		
		
		assertNull(actual);
	
	}	
}
	
