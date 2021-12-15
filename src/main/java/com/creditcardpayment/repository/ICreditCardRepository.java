package com.creditcardpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditcardpayment.entity.CreditCardEntity;

@Repository
public interface ICreditCardRepository extends JpaRepository<CreditCardEntity, String>{

}
