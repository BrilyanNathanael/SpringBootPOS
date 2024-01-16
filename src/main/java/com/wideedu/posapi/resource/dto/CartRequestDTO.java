package com.wideedu.posapi.resource.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CartRequestDTO {
	@Valid
	
	@NotNull(message = "Item Code is required")
	@NotBlank(message = "Item Code is required")
	private String itemCode;
	
	@NotNull(message = "Quantity is required")
	@Min(value = 1, message = "Quantity must be more than 1")
	private int quantity;
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
