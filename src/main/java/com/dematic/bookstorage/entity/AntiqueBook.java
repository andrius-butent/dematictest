package com.dematic.bookstorage.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class AntiqueBook extends Book {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "year")
	private Integer year;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public double calculatePrice() {

		LocalDate localDate = LocalDate.now();

		return getPrice() * getQuantity() * (localDate.getYear() - getYear()) / 10;
	}
}
