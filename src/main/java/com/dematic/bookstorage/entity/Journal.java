package com.dematic.bookstorage.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Journal extends Book {

	@JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    @Min(value = 0)
    @Max(value = 9)
	private Integer scienceIndex;

	public Integer getScienceIndex() {
		return scienceIndex;
	}

	public void setScienceIndex(Integer scienceIndex) {
		this.scienceIndex = scienceIndex;
	}

	@Override
	public double calculatePrice() {
		return getQuantity() * getPrice() * this.scienceIndex;
	}
}
