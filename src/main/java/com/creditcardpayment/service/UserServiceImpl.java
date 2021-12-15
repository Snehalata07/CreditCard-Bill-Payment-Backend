package com.creditcardpayment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcardpayment.bean.ChangePassword;
import com.creditcardpayment.bean.SignUp;
import com.creditcardpayment.bean.User;
import com.creditcardpayment.entity.UserEntity;
import com.creditcardpayment.exception.UserException;
import com.creditcardpayment.repository.IUserRepository;


@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private EMParse parser;
	
	public UserServiceImpl() {
		
	}
	
	public UserServiceImpl(IUserRepository userRepo) {
		super();
		this.userRepo = userRepo;
		this.parser = new EMParse();
	}

	
	public IUserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(IUserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public EMParse getParser() {
		return parser;
	}

	public void setParser(EMParse parser) {
		this.parser = parser;
	}

	@Override
	public User add(User user) throws UserException {
		if(user !=null) {
			if(userRepo.existsById(user.getUserId())) {
				throw new UserException("User "+user.getUserId()+" is already Exists");
			}
			if(!user.getUserId().matches("^[A-Za-z][A-Za-z0-9]{3,20}$")) {
				throw new UserException("UserId should be non empty and minimum of length 4");
			}
			if(!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+$).{8,}$")) {
				throw new UserException("Password should contain upper case, Lower case, Special charecter, numbers and length minimum 8");
			}
			else {
				user=parser.parse(userRepo.save(parser.parse(user)));
			}
		}
		return user;
	}

	@Override
	public User save(User user) throws UserException {
		User old=parser.parse(userRepo.findById(user.getUserId()).orElse(null));
		if(old == null) {
			throw new UserException("No user with Id "+user.getUserId()+" is present");
		}else if(!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+$).{8,}$")) {
			throw new UserException("Password should contain upper case, Lower case, Special charecter, numbers and length minimum 8");
		}else {
			user=parser.parse(userRepo.save(parser.parse(user)));
		}
		return user;
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());
	}

	@Override
	public void deleteById(String userId) throws UserException {
		if(userId==null) {
			throw new UserException("User Id Cannot be Null");
		}else if(!userRepo.existsById(userId)) {
			throw new UserException("User with user Id "+userId+" Does not exists");
		}
		userRepo.deleteById(userId);
	}

	@Override
	public User findById(String userId) throws UserException {
		if(userId==null) {
			throw new UserException("User Id Cannot be Null");
		}else if(!userRepo.existsById(userId)) {
			throw new UserException("User with user Id "+userId+" Does not exists");
		}
		return parser.parse(userRepo.findById(userId).orElse(null));
	}

	@Override
	public boolean existsById(String userId) {
		return userRepo.existsById(userId);
	}

	@Override
	public boolean signIn(User user) throws UserException {
		if(user==null) {
			throw new UserException("SignIn details Cannot be Null");
		}
		UserEntity userDetails=userRepo.findById(user.getUserId()).orElse(null);
		if(userDetails==null) {
			throw new UserException("User Details doesnot Exists");
		}
		return userDetails.getPassword().equals(user.getPassword());
	}

	@Override
	public boolean signOut(User user) {
		return true;
	}

	@Override
	public boolean changePassword(ChangePassword changePassword) throws UserException {
		if(changePassword==null) {
			throw new UserException("Change details should not be null");
		}
		User user=parser.parse(userRepo.findById(changePassword.getUserId()).orElse(null));
		if(user==null) {
			throw new UserException("User details Does not exists");
		}
		boolean isChanged=false;
		if(user.getPassword().equals(changePassword.getCurrentPassword()) && changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
			user.setPassword(changePassword.getConfirmPassword());
			userRepo.save(parser.parse(user));
			isChanged= true;
		}
		return isChanged;
	}

	@Override
	public User signUp(SignUp signUp) throws UserException {
		if(signUp==null) {
			throw new UserException("SignUp details cannot be Null");
		}
		User user=parser.parse(userRepo.findById(signUp.getUserId()).orElse(null));
		if(user==null) {
			throw new UserException("SignUp details Does not Exists");
		}
		if(user.getPassword().equals(signUp.getKey()) && signUp.getCreatePassword().equals(signUp.getConfirmPassword())) {
			user.setPassword(signUp.getConfirmPassword());
			user=parser.parse(userRepo.save(parser.parse(user)));
		}
		return user;
	}

}
