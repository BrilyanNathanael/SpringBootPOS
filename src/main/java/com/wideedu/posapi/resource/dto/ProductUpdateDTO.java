package com.wideedu.posapi.resource.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class ProductUpdateDTO {
	@Valid
	
	@Min(value = 5000, message = "Minimum price is 5000")
	private int price;
	
	@Size(min = 1, message = "Minimum description is 1")
	private String description;
	
	@Size(min = 1, message = "Minimum type is 1")
	private String type;
	
	@Min(value = -1, message = "Tax value is 0(true) or -1(false)")
	@Max(value = 0, message = "Tax value is 0(true) or -1(false)")
	private int tax;
	
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
