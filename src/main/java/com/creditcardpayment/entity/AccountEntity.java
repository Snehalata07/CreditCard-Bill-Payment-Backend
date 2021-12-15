package com.creditcardpayment.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.creditcardpayment.bean.AccountType;

@Entity
@Table(name="accounts")
public class AccountEntity {
	@Id
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="account_Name", nullable=false)
	private String accountName;
	
	@Column(name="balance", nullable=false)
	private Double accountBalance;
	
	@Column(name="account_Type", nullable=false)
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
	@JoinTable(name="customer_account",
	joinColumns=@JoinColumn(name="account_number"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<CustomerEntity> customers;
	
	public AccountEntity() {
		/* Default Constructor */
	}
	
	public AccountEntity(String accountNumber, String accountName, Double accountBalance, AccountType accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
		
	}
	
	public Set<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<CustomerEntity> customers) {
		this.customers = customers;
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
		return String.format(
				"AccountEntity [accountNumber=%s, accountName=%s, accountBalance=%s, accountType=%s, customers=%s]",
				accountNumber, accountName, accountBalance, accountType, customers);
	}
	
}
