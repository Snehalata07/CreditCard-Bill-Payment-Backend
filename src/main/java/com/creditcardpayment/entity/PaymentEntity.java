package com.creditcardpayment.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.creditcardpayment.bean.PaymentMethod;

@Entity
@Table(name="payments")
public class PaymentEntity {
	
	@Id
	@Column(name="payment_id")
	private Long paymentId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_method",nullable=false)
	private PaymentMethod method;

	@Column(name="paid_date")
	private LocalDate paidDate;

	@Column(name="paid_time")
	private LocalTime paidTime;
	
	@Column(name="amount",nullable=false)
	private Double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="card_number")
	private CreditCardEntity card;

	
	

	public PaymentEntity(Long paymentId, PaymentMethod method, LocalDate paidDate, LocalTime paidTime, Double amount,
			CreditCardEntity card) {
		super();
		this.paymentId = paymentId;
		this.method = method;
		this.paidDate = paidDate;
		this.paidTime = paidTime;
		this.amount = amount;
		this.card = card;
	}

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}

	public LocalTime getPaidTime() {
		return paidTime;
	}

	public void setPaidTime(LocalTime paidTime) {
		this.paidTime = paidTime;
	}

	public Long getPaymentId() {
		return paymentId;
	}


	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public CreditCardEntity getCard() {
		return card;
	}

	public void setCard(CreditCardEntity card) {
		this.card = card;
	}


	
	@Override
	public String toString() {
		return String.format("PaymentEntity [paymentId=%s, method=%s, amount=%s, card=%s]", paymentId, method, amount);
			
	}
	
	
	
	
}