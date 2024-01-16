package com.wideedu.posapi.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Payment {
	private String payment;
	private int cash_in_hand;
	private int amount;
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public int getCash_in_hand() {
		return cash_in_hand;
	}
	public void setCash_in_hand(int cash_in_hand) {
		this.cash_in_hand = cash_in_hand;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
