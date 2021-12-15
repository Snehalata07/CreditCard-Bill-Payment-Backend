package com.creditcardpayment.bean;

public class SignUp {
	private String userId;
	private String key;
	private String createPassword;
	private String confirmPassword;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCreatePassword() {
		return createPassword;
	}
	public void setCreatePassword(String createPassword) {
		this.createPassword = createPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
	public SignUp() {
		
	}
	
	public SignUp(String userId, String key, String createPassword, String confirmPassword) {
		super();
		this.userId = userId;
		this.key = key;
		this.createPassword = createPassword;
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return String.format("SignUp [userId=%s, key=%s, createPassword=%s, confirmPassword=%s]", userId, key,
				createPassword, confirmPassword);
	}
	
	

}
