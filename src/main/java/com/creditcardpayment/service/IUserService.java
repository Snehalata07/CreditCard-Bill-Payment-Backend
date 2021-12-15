package com.creditcardpayment.service;

import com.creditcardpayment.bean.ChangePassword;
import com.creditcardpayment.bean.SignUp;
import com.creditcardpayment.bean.User;
import com.creditcardpayment.exception.UserException;

import java.util.List;

public interface IUserService {
	boolean existsById(String userId);

	User add(User user) throws UserException;

	User save(User user) throws UserException;

	boolean signIn(User user) throws UserException;

	boolean signOut(User user);

	void deleteById(String userId) throws UserException;

	User findById(String userId) throws UserException;

	List<User> findAll();

	boolean changePassword(ChangePassword changePassword) throws UserException;

	User signUp(SignUp signUp) throws UserException;
}
