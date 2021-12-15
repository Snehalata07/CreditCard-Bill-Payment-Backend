package com.creditcardpayment.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="statements")
public class StatementEntity {	
	
	@Id
	@Column(name="statement_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long statementId;
	
	@Column(name="due_amount",nullable=false)
	private Double dueAmount;
	
	@Column(name="bill_amount",nullable=false)
	private Double billAmount;
	
	@Column(name="bill_date",nullable=false)
	private LocalDate billDate;
	
	@Column(name="due_date",nullable=false)
	private LocalDate dueDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCardEntity creditCard;
	
	@Column(name="customer_name",nullable=false)
	private String customerName;
	
	
	
	public StatementEntity(Long statementId, Double dueAmount, Double billAmount, LocalDate billDate, LocalDate dueDate,
			CreditCardEntity creditCard) {
		super();
		this.statementId = statementId;
		this.dueAmount = dueAmount;
		this.billAmount = billAmount;
		this.billDate = billDate;
		this.dueDate = dueDate;
		this.creditCard = creditCard;
		this.customerName = customerName;
	}

	//getters and setters

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
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


	public CreditCardEntity getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCardEntity creditCard) {
		this.creditCard = creditCard;
	}

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		
			this.customerName=customerName;
		}

	@Override
	public String toString() {
		return String.format("Statement [statementId=%s, dueAmount=%s, billDate=%s, dueDate=%s,creditCard =%s customer Name=%s]",
				statementId, dueAmount, billDate, dueDate,creditCard);
	}
	
	
}
