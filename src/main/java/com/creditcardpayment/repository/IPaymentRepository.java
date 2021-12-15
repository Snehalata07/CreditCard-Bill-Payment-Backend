package com.creditcardpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditcardpayment.entity.PaymentEntity;

@Repository
public interface IPaymentRepository extends JpaRepository<PaymentEntity, Long>{
	
	
}