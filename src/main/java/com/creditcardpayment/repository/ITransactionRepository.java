package com.creditcardpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditcardpayment.entity.TransactionEntity;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long>{
	

}
