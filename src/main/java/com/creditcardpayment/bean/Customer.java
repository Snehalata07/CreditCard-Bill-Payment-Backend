package com.creditcardpayment.bean;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

public class Customer {
	
	private String userId;
	
	@NotNull(message="customer name cannot be null")	
	@NotBlank(message="customer name cannot be blank")
	private String userName;
	
	@NotNull(message="email cannot be null")	
	@NotBlank(message="email cannot be blank")
	@Pattern(regexp="^[A-Za-z0-9]{3,}[@][a-z]{2,}[a-z.]{2,}[a-z]$")
	private String email;
	
	@NotNull(message="number cannot be null")	
	@NotBlank(message="number cannot be blank")
	@Pattern(regexp = "[6-9][0-9]{9}")
	private String contactNo;
	
	@NotNull(message="date of birth cannot be null")	
	@NotBlank(message="date of birth cannot be blank")
	@PastOrPresent(message="Expiry date cannot be in future")
	private  LocalDate dob;
	
	@NotNull(message="address cannot be null")	
	@NotBlank(message="adress cannot be blank")
	private Address address;

	public Customer() {
		/* Default Constructor*/
	}

	/**
	 * @param userId
	 * @param userName
	 * @param email
	 * @param contactNo
	 * @param dob
	 * @param address
	 */
	public Customer(String userId,
			@NotNull(message = "customer name cannot be null") @NotBlank(message = "customer name cannot be blank") String userName,
			@NotNull(message = "email cannot be null") @NotBlank(message = "email cannot be blank") @Pattern(regexp = "^[A-Za-z0-9]{3,}[@][a-z]{2,}[a-z.]{2,}[a-z]$") String email,
			@NotNull(message = "number cannot be null") @NotBlank(message = "number cannot be blank") @Pattern(regexp = "[6-9][0-9]{9}") String contactNo,
			@NotNull(message = "date of birth cannot be null") @NotBlank(message = "date of birth cannot be blank") @PastOrPresent(message = "Expiry date cannot be in future") LocalDate dob,
			@NotNull(message = "address cannot be null") @NotBlank(message = "adress cannot be blank") Address address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	

	@Override
	public String toString() {
		return String.format("userId=%s, userName=%s, email=%s, contactNo=%s, dob=%s, address=%s",
				userId, userName, email, contactNo, dob, address);
	}
	
	
}
