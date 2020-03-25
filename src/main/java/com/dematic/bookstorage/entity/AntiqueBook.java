package com.dematic.bookstorage.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AntiqueBook extends Book {

	@JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 1900)
    @Max(value = 2020)
    @NotNull
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
