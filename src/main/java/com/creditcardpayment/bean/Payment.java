package com.creditcardpayment.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Payment {
	
	private Long paymentId;
	
	@NotNull(message="payment method cannot be null")	
	@NotBlank(message="payment method cannot be blank")
	private PaymentMethod method;
	
	@NotNull(message="amount cannot be null")	
	@NotBlank(message="amount cannot be blank")
	private Double amount;
	
	@NotNull(message="credit card cannot be null")	
	@NotBlank(message="credit card cannot be blank")
	private String cardNumber;
	
	@NotNull(message="paid date cannot be null")	
	@NotBlank(message="paid date cannot be blank")
	private LocalDate paidDate;
	
	@NotNull(message="paid time cannot be null")	
	@NotBlank(message="paid time cannot be blank")
	private LocalTime paidTime;
	
	
	

	public Payment() {
		/*Default Constructor*/
	}

	

	/**
	 * @param paymentId
	 * @param method
	 * @param amount
	 * @param cardNumber
	 */
	public Payment(Long paymentId,
			@NotNull(message = "payment method cannot be null") @NotBlank(message = "payment method cannot be blank") PaymentMethod method,
			@NotNull(message = "amount cannot be null") @NotBlank(message = "amount cannot be blank") Double amount,
			@NotNull(message="paid date cannot be null") @NotBlank(message="paid date cannot be blank") LocalDate paidDate,
			@NotNull(message="paid time cannot be null") @NotBlank(message="paid time cannot be blank") LocalTime paidTime,
			@NotNull(message = "credit card cannot be null") @NotBlank(message = "credit card cannot be blank") String cardNumber) {
		super();
		this.paymentId = paymentId;
		this.method = method;
		this.paidDate=paidDate;
		this.paidTime=paidTime;
		this.amount = amount;
		this.cardNumber = cardNumber;
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

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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
	

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	@Override
	public String toString() {
		return String.format("PaymentModel [paymentId=%s, method=%s, amount=%s, cardNumber=%s]", paymentId, method,
				amount, cardNumber);
	}
		
}
