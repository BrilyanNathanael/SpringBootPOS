package com.wideedu.posapi.resource.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentDTO {
	@NotNull(message = "Payment is required")
	@NotBlank(message = "Payment is required")
	private String payment;
	
	@NotNull(message = "Amount is required")
	@Min(value = 1, message = "Amount must be more than 1")
	private int amount;
	private int cash_in_hand;
	
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCash_in_hand() {
		return cash_in_hand;
	}
	public void setCash_in_hand(int cash_in_hand) {
		this.cash_in_hand = cash_in_hand;
	}
	
	
}
