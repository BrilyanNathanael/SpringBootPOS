package com.wideedu.posapi.resource.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {
	@Valid
	
	@NotNull(message = "Item Code is required")
	@NotBlank(message = "Item Code is required")
	private String itemCode;
	
	@NotNull(message = "Price is required")
	@Min(value = 5000, message = "Minimum price is 5000")
	private int price;
	
	@NotNull(message = "Description is required")
	@NotBlank(message = "Description is required")
	private String description;
	
	@NotNull(message = "Type is required")
	@NotBlank(message = "Type is required")
	private String type;
	
	@NotNull(message = "Tax is required")
	@Min(value = -1, message = "Tax value is 0(true) or -1(false)")
	@Max(value = 0, message = "Tax value is 0(true) or -1(false)")
	private int tax;
	
	public String getItemCode() {
		return itemCode;
	}
	public void setitemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
}
