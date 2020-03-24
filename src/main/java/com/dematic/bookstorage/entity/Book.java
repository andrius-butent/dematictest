package com.dematic.bookstorage.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty(message = "Name is required")
	@Column(name = "name")
	private String name;

	@NotEmpty(message = "Author is required")
	@Column(name = "author")
	private String author;

	@NotEmpty(message = "Barcode is required")
	@Column(name = "barcode")
	private String barcode;

	@Min(value = 0, message = "Quantity must be greater than or equal to zero")
	@NotNull(message = "Quantity is required")
	@Column(name = "quantity")
	private Integer quantity;

	@Min(value = 0, message = "Price must be greater than or equal to zero")
	@NotNull(message = "Price is required")
	@Column(name = "price")
	private Double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
