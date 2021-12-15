package com.creditcardpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditcardpayment.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, String>{
	
}
