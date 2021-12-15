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

import com.creditcardpayment.bean.Address;
import com.creditcardpayment.bean.Customer;
import com.creditcardpayment.entity.CustomerEntity;
import com.creditcardpayment.exception.CustomerException;
import com.creditcardpayment.repository.ICustomerRepository;
import com.creditcardpayment.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	private ICustomerRepository customerRepo;
	
	@InjectMocks
	private CustomerServiceImpl service;

	@Test
	@DisplayName("CustomerDetails should retrive")
	void testGetAll() {
		Address address1=new Address("10-10A","rkl","sector-5","Rourkela","Odisha",769002);
		Address address2=new Address("10-10B","rkl","sector-5","Rourkela","Odisha",769002);
		List<CustomerEntity> testData=Arrays.asList(new CustomerEntity[] {
				new CustomerEntity("U107","Sneha","snehadash@gmail.com","9178143290",LocalDate.parse("1999-10-18"),address1),
				new CustomerEntity("U106","Dash","dashsneha@gmail.com","8327745672",LocalDate.parse("1999-10-18"),address2)
		});
		
		Mockito.when(customerRepo.findAll()).thenReturn(testData);
		
		List<Customer> expected=Arrays.asList(new Customer[] {
				new Customer("U107","Sneha","snehadash@gmail.com","9178143290",LocalDate.parse("1999-10-18"),address1),
				new Customer("U106","Dash","dashsneha@gmail.com","8327745672",LocalDate.parse("1999-10-18"),address2)
		});
		
		List<Customer> actual = service.findAll();
		
		assertEquals(expected.get(0).getUserName(),actual.get(0).getUserName());

	}
	@Test
	@DisplayName("CustomerDetails add")
	void testAdd() throws CustomerException {
		Address address1=new Address("10-10A","rkl","sector-5","Rourkela","Odhisa",769002);
		Customer expected=new Customer("U107","Manoj","manojkumaraich@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		CustomerEntity customer1=new CustomerEntity("U107","Manoj","manojkumaraich@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		Mockito.when(customerRepo.save(Mockito.any())).thenReturn(customer1);
		Customer actual = service.addCustomer(service.getParser().parse(customer1),customer1.getUserId());
		assertEquals(expected.getUserId(),actual.getUserId());
		assertEquals(expected.getUserName(),actual.getUserName());

	}
	
	
	@Test
	@DisplayName("CustomerDetails should delete")
	void testDelete() throws CustomerException {
		Address address1=new Address("10-10A","rkl","sector-5","Rourkela","Odhisa",769002);
		CustomerEntity customer1=new CustomerEntity("U107","Manoj","manojkumaraich@gmail.com","7207388240",LocalDate.parse("1999-10-18"), address1);
		Customer expected=new Customer("U107","Manoj","manojkumaraich@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		Mockito.doNothing().when(customerRepo).deleteById(expected.getUserId());
		Mockito.when(customerRepo.existsById(expected.getUserId())).thenReturn(true);
		service.deleteById(expected.getUserId());
		Mockito.when(customerRepo.existsById(expected.getUserId())).thenReturn(false);
		boolean test=service.existsById(expected.getUserId());
		assertFalse(test);
		
	}
	
	@Test
	@DisplayName("get by Id ")
	void testGetById () throws CustomerException {
		Address address1=new Address("10-10A","rkl","sector-5","Rourkela","Odisha",769002);
		CustomerEntity testdata=new CustomerEntity("U107","Sneha","snehadash@gmail.com","9178143290",LocalDate.parse("1999-10-18"),address1);
		
		Customer expected=new Customer("U107","Sneha","snehadash@gmail.com","9178143290",LocalDate.parse("1999-10-18"),address1);
		
		
		Mockito.when(customerRepo.findById(testdata.getUserId())).thenReturn(Optional.of(testdata));
		Mockito.when(customerRepo.existsById(testdata.getUserId())).thenReturn(true);
		
		Customer actual=service.findById(testdata.getUserId());
		
		assertEquals(expected.getUserName(),actual.getUserName());
	}
	
	@Test
	@DisplayName("get by Id not exists")
	void testGetByIdNotExists () throws CustomerException {
		Address address1=new Address("10-10A","rkl","sector-5","Rourkela","Odisha",769002);
		CustomerEntity testdata=new CustomerEntity("U107","Sneha","snehadash@gmail.com","9178143290",LocalDate.parse("1999-10-18"),address1);
		
		Mockito.when(customerRepo.findById(testdata.getUserId())).thenReturn(Optional.empty());
		Mockito.when(customerRepo.existsById(testdata.getUserId())).thenReturn(true);
		
		Customer actual = service.findById(testdata.getUserId());
		assertNull(actual);
	}
	
	@Test
	@DisplayName("exists by number ")
	void testExistsBynumber () throws CustomerException {
		Address address1=new Address("10-10A","rkl","sector-5","Rourkela","Odhisa",769002);
		CustomerEntity testdata=new CustomerEntity("U107","Manoj","manojkumaraich@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		
		boolean expected=true;
		
		Mockito.when(customerRepo.existsByContactNo(testdata.getContactNo())).thenReturn(true);
	
		boolean actual=service.existsByContactNo(testdata.getContactNo());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("exists by Email ")
	void testExistsByEmail() throws CustomerException {
		Address address1=new Address("10-10A","rkl","sector-5","Rourkela","Odhisa",769002);
		CustomerEntity testdata=new CustomerEntity("U107","Manoj","manojkumaraich@gmail.com","7207388240",LocalDate.parse("1999-10-18"),address1);
		
		boolean expected=true;
		
		Mockito.when(customerRepo.existsByEmail(testdata.getEmail())).thenReturn(true);
	
		boolean actual=service.existsByEmail(testdata.getEmail());
		
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("get by id return null")
	void testGetByIdNull() throws CustomerException {		
		
		Mockito.when(customerRepo.findById("U101")).thenReturn(Optional.empty());
		Mockito.when(customerRepo.existsById("U101")).thenReturn(true);
		
		Customer actual = service.findById("U101");
		assertNull(actual);
	}
	
}
