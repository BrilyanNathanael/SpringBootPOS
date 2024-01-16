package com.wideedu.posapi.domain;

import java.io.Serializable;
import java.util.List;

import org.hibernate.query.sqm.CastType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "item")
public class Product implements Serializable{
	@Id
	@Column(name = "item_code", length = 15)
	private String itemCode;
	private int price;
	private String description;
	private String type;
	
	@Column(name = "taxable")
	private int tax;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SaleItem> saleItem;
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public List<SaleItem> getSaleItem() {
		return saleItem;
	}
	public void setSaleItem(List<SaleItem> saleItem) {
		this.saleItem = saleItem;
	}
}

