package com.creditcardpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditcardpayment.entity.CustomerEntity;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, String>{
	boolean existsByContactNo(String contactNo);
	boolean existsByEmail(String email);
	
}

