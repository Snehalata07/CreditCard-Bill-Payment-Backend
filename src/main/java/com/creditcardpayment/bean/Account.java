package com.creditcardpayment.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Account {
	
	@NotNull(message="Account name cannot be null")	
	@NotBlank(message="Account name cannot be blank")
	private String accountNumber;
	
	@NotNull(message="Account name cannot be null")	
	@NotBlank(message="Account name cannot be blank")
	private String accountName;
	
	@NotNull(message="balance cannot be null")	
	@NotBlank(message="balance cannot be blank")
	private Double accountBalance;
	
	@NotNull(message="account type cannot be null")	
	@NotBlank(message="account type cannot be blank")
	
	private AccountType accountType;
	
	
	public Account() {
		/* Default Constructor */
	}
	
	public Account(String accountNumber, String accountName, Double accountBalance, AccountType accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	
	@Override
	public String toString() {
		return String.format("Account [accountNumber=%s, accountName=%s, accountBalance=%s, accountType=%s]",
				accountNumber, accountName, accountBalance, accountType);
	}

	
}
