package com.dematic.bookstorage.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Book {

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Author is required")
	private String author;

	@NotEmpty(message = "Barcode is required")
	private String barcode;

	@Min(value = 0, message = "Quantity must be greater than or equal to zero")
	@NotNull(message = "Quantity is required")
	private Integer quantity;

	@Min(value = 0, message = "Price must be greater than or equal to zero")
	@NotNull(message = "Price is required")
	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public double calculatePrice() {
		return this.price * this.quantity;
	}
}
