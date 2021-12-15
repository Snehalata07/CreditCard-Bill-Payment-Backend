package com.creditcardpayment.bean;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Statement {	
	
	private Long statementId;
	
	@NotNull(message="due amount cannot be null")	
	@NotBlank(message="due amount cannot be blank")
	private Double dueAmount;
	
	@NotNull(message="bill amount cannot be null")	
	@NotBlank(message="bill amount cannot be blank")
	private Double billAmount;
	
	@NotNull(message="bill date cannot be null")	
	@NotBlank(message="bill date cannot be blank")
	private LocalDate billDate;
	
	@NotNull(message="due date cannot be null")	
	@NotBlank(message="due date cannot be blank")
	private LocalDate dueDate;

	@NotNull(message="credit card cannot be null")	
	@NotBlank(message="credit card cannot be blank")
	private String cardNumber;

	@NotNull(message="customer name cannot be null")	
	@NotBlank(message="customer name cannot be blank")
	private String customerName;
	
	public Statement() {
		/*Default Constructor*/
	}

	

	/**
	 * @param statementId
	 * @param dueAmount
	 * @param billDate
	 * @param dueDate
	 * @param cardNumber
	 */
	public Statement(Long statementId,
			@NotNull(message = "bill amount cannot be null") @NotBlank(message = "bill amount cannot be blank") Double billAmount,
			Double dueAmount,
			@NotNull(message = "bill date cannot be null") @NotBlank(message = "bill date cannot be blank") LocalDate billDate,
			@NotNull(message = "due date cannot be null") @NotBlank(message = "due date cannot be blank") LocalDate dueDate,
			@NotNull(message = "credit card cannot be null") @NotBlank(message = "credit card cannot be blank") String cardNumber,
			@NotNull(message="customer name cannot be null") @NotBlank(message="customer name cannot be blank") String customerName) {
		super();
		this.statementId=statementId;
		this.dueAmount = dueAmount;
		this.billDate = billDate;
		this.billAmount=billAmount;
		this.dueDate = dueDate;
		this.cardNumber = cardNumber;
		this.customerName=customerName;
	}



	public Double getBillAmount() {
		return billAmount;
	}



	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	public Long getStatementId() {
		return statementId;
	}

	public void setStatementId(Long statementId) {
		this.statementId = statementId;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	

	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	@Override
	public String toString() {
		return String.format(
				"Statement [statementId=%s, dueAmount=%s, billAmount=%s, billDate=%s, dueDate=%s, cardNumber=%s, customerName=%s]",
				statementId, dueAmount, billAmount, billDate, dueDate, cardNumber, customerName);
	}	
	
	
}