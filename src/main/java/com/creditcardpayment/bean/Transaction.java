package com.creditcardpayment.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Transaction {

	private Long transactionId;

	@NotNull(message="CardNumber Cannot to be Null")
	@NotEmpty(message="CardNumber cannot be Empty")
	private String cardNumber;
	
	@NotNull(message="date Cannot to be Null")
	@NotEmpty(message="date cannot be Empty")
	private LocalDate transactionDate;
	
	@NotNull(message="time Cannot to be Null")
	@NotEmpty(message="time cannot be Empty")
	private LocalTime transactionTime;
	
	@NotNull(message="amount Cannot to be Null")
	@NotEmpty(message="amount cannot be Empty")
	private Double amount;

	@NotNull(message="status Cannot to be Null")
	@NotEmpty(message="status cannot be Empty")
	private TransactionStatus status;
	
	@NotNull(message="description Cannot to be Null")
	@NotEmpty(message="description cannot be Empty")
	private String description;
	
	public Transaction() {
		/* Default Constructor*/
	}

	public Transaction(Long transactionId,
			@NotNull(message = "CardNumber Cannot to be Null") @NotEmpty(message = "CardNumber cannot be Empty") String cardNumber,
			@NotNull(message = "amount Cannot to be Null") @NotEmpty(message = "amount cannot be Empty") Double amount,
			@NotNull(message="date Cannot to be Null") @NotEmpty(message="date cannot be Empty") LocalDate transactionDate,
			@NotNull(message="time Cannot to be Null") @NotEmpty(message="time cannot be Empty") LocalTime transactionTime,
			@NotNull(message = "status Cannot to be Null") @NotEmpty(message = "status cannot be Empty") TransactionStatus status,
			@NotNull(message = "description Cannot to be Null") @NotEmpty(message = "description cannot be Empty") String description) {
		super();
		this.transactionId = transactionId;
		this.cardNumber = cardNumber;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.amount = amount;
		this.status = status;
		this.description = description;
	}
	
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	@Override
	public String toString() {
		return String.format(
				"Transaction [transactionId=%s, cardNumber=%s, transactionDate=%s, transactionTime=%s, amount=%s, status=%s, description=%s]",
				transactionId, cardNumber, transactionDate, transactionTime, amount, status, description);
	}
	
	
}
