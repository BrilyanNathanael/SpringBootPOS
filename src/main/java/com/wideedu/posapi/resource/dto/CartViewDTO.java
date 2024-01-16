package com.wideedu.posapi.resource.dto;

import java.util.List;

import com.wideedu.posapi.domain.Cart;
import com.wideedu.posapi.domain.Tax;


public class CartViewDTO {
	private List<Cart> listCarts;
	private int grandPrice;
	private int tax;
	
	public List<Cart> getListCarts() {
		return listCarts;
	}
	public void setListCarts(List<Cart> listCarts) {
		this.listCarts = listCarts;
	}
	public int getGrandPrice() {
		return grandPrice;
	}
	public void setTotalGrandPrice() {
		int totalPrice = 0;
		for(Cart c:this.listCarts) {
			totalPrice = totalPrice + (c.getQuantity() * c.getPrice());
		}
		
		this.grandPrice = totalPrice;
	}
	public int getTax() {
		return tax;
	}
	public void setCalculateTax() {
		int itemTax = 0;
		for(Cart c:this.listCarts) {
			if(c.getProduct().getTax() == 0) {
				itemTax = itemTax + ((int) (c.getQuantity() * c.getPrice() * Tax.tax));
			}
		}
		
		this.tax = itemTax;
	}
	
	
}
