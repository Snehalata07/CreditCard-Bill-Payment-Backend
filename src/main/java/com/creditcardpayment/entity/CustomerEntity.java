package com.creditcardpayment.entity;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.creditcardpayment.bean.Address;


@Entity
@Table(name="customers")
public class CustomerEntity {
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name",nullable=false)
	private String userName;
	
	@Column(name="email",nullable=false)
	private String email;
	
	@Column(name="contact_number",nullable=false)
	private String contactNo;
	
	@Column(name="date_of_birth",nullable=false)
	private  LocalDate dob;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="user")
	private UserEntity user;
		
	@Embedded
	private Address address;
	

	@OneToMany(mappedBy="customer")
	private Set<CreditCardEntity> creditCard;

	
	@ManyToMany(fetch=FetchType.LAZY,cascade= CascadeType.ALL)
	@JoinTable(name="customer_account",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="account_number"))
	private Set<AccountEntity> accounts;
	
	
	public CustomerEntity()
	{
		
	}
	

	
	



	public CustomerEntity(String string, String string2, String string3, String string4, LocalDate parse,
			Address address1) {
		super();
		this.userId=string;
		this.userName=string2;
		this.email=string3;
		this.contactNo=string4;
		this.dob=parse;
		this.address=address1;
		
	}







	public Set<AccountEntity> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	public Set<CreditCardEntity> getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(Set<CreditCardEntity> creditCard) {
		this.creditCard = creditCard;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return userName;
	}
	public void setName(String userName) {
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
		return String.format("userId=%s, userName=%s, email=%s, contactNo=%s, dob=%s, address=%s", userId, userName,
				email, contactNo, dob, address);
	}

	
}