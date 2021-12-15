package com.creditcardpayment.entity;


import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.creditcardpayment.bean.TransactionStatus;

@Entity
@Table(name="transactions")
public class TransactionEntity {

	@Id
	@Column(name="trans_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transactionId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",nullable=false)
	private TransactionStatus status;
	
	@Column(name="trans_date",nullable=false)
	private LocalDate transactionDate;
	
	@Column(name="trans_time",nullable=false)
	private LocalTime transactionTime;
	
	@Column(name="amount",nullable=false)
	private Double amount;
	
	@Column(name="description",nullable=false)
	private String description;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCardEntity creditCard;
	
	

	public TransactionEntity(Long transactionId, TransactionStatus status, LocalDate transactionDate,
			LocalTime transactionTime, Double amount, String description, CreditCardEntity creditCard) {
		super();
		this.transactionId = transactionId;
		this.status = status;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.amount = amount;
		this.description = description;
		this.creditCard = creditCard;
	}


	public TransactionEntity(Long transactionId2, TransactionStatus status2, CreditCardEntity orElse, Double amount2,
			String description2) {
		 //TODO Auto-generated constructor stub
	}


	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public TransactionStatus getStatus() {
		return status;
	}


	public void setStatus(TransactionStatus status) {
		this.status = status;
	}


	public LocalDate getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}


	public LocalTime getTransactionTime() {
		return transactionTime;
	}


	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}



	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public CreditCardEntity getCreditCard() {
		return creditCard;
	}


	public void setCreditCard(CreditCardEntity creditCard) {
		this.creditCard = creditCard;
	}
	
	public String getCardNumber() {
		return creditCard.getCardNumber();
	}


	
	@Override
	public String toString() {
		return String.format(
				"TransactionEntity [transactionId=%s, status=%s, transactionDate=%s, transactionTime=%s, amount=%s, description=%s, creditCard=%s]",
				transactionId, status, transactionDate, transactionTime, amount, description, creditCard);
	}


		
	
}