package com.creditcardpayment.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class User {
	
	@NotNull(message="user id cannot be null")	
	@NotBlank(message="user id cannot be blank")
	@Pattern(regexp="^[A-Za-z][A-Za-z0-9]{3,20}$")
	private String userId;
	
	@NotNull(message="password cannot be null")	
	@NotBlank(message="password cannot be blank")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\\\S+$).{8,30}$")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String role;
	
	public User() {
		/* Default Constructor*/
	}

	public User(
			@NotNull(message = "user id cannot be null") @NotBlank(message = "user id cannot be blank") @Pattern(regexp="^[A-Za-z][A-Za-z0-9]{3,20}$") String userId,
			@NotNull(message = "password cannot be null") @NotBlank(message = "password cannot be blank") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\\\S+$).{8,30}$") String password,
			String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	@Override
	public String toString() {
		return String.format("User [userId=%s, password=%s, role=%s]", userId, password, role);
	}
	
	
}
