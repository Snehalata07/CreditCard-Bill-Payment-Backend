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
import com.creditcardpayment.bean.Payment;
import com.creditcardpayment.bean.PaymentMethod;
import com.creditcardpayment.entity.CreditCardEntity;
import com.creditcardpayment.entity.CustomerEntity;
import com.creditcardpayment.entity.PaymentEntity;
import com.creditcardpayment.exception.PaymentException;
import com.creditcardpayment.repository.IPaymentRepository;
import com.creditcardpayment.service.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

	@Mock
	private IPaymentRepository paymentRepo;
	
	@InjectMocks
	private PaymentServiceImpl service;

	@Test
	@DisplayName("PaymentDetails should retrive")
	void testGetAll() {
		CreditCardEntity creditCard1=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity());
		
		List<PaymentEntity> testData=Arrays.asList(new PaymentEntity[] {
				new PaymentEntity(1L,PaymentMethod.UPI,LocalDate.now(),LocalTime.now(),6000.0,creditCard1),
				new PaymentEntity(2L,PaymentMethod.UPI,LocalDate.now(),LocalTime.now(),7000.0,creditCard1)
		});
		
		Mockito.when(paymentRepo.findAll()).thenReturn(testData);
		
		List<Payment> expected=Arrays.asList(new Payment[] {
				new Payment(1L,PaymentMethod.UPI,6000.0,LocalDate.now(),LocalTime.now(),creditCard1.getCardNumber()),
				new Payment(2L,PaymentMethod.UPI,7000.0,LocalDate.now(),LocalTime.now(),creditCard1.getCardNumber())
		});
		
		List<Payment> actual = service.findAll();
		
		assertEquals(expected.get(0).getAmount(),actual.get(0).getAmount());

	}
	
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws PaymentException {
		CreditCardEntity creditCard1=new CreditCardEntity("2568479632140",CardName.VISA,CardType.GOLD,LocalDate.parse("2022-10-18"),"SBI",623,10000.0,10000.0,new CustomerEntity());
		
		PaymentEntity testdata=new PaymentEntity(1L,PaymentMethod.UPI,LocalDate.now(),LocalTime.now(),6000.0,creditCard1);
		
		Payment expected=new Payment(1L,PaymentMethod.UPI,6000.0,LocalDate.now(),LocalTime.now(),creditCard1.getCardNumber());
		
		
		Mockito.when(paymentRepo.findById(testdata.getPaymentId())).thenReturn(Optional.of(testdata));
		Mockito.when(paymentRepo.existsById(testdata.getPaymentId())).thenReturn(true);
	
		Payment actual=service.findById(testdata.getPaymentId());
		
		assertEquals(expected.getAmount(),actual.getAmount());
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws PaymentException {		
		
		Mockito.when(paymentRepo.findById(1L)).thenReturn(Optional.empty());
		Mockito.when(paymentRepo.existsById(1L)).thenReturn(true);
		Payment actual = service.findById(1L);
		assertNull(actual);
	}
}
	
		

