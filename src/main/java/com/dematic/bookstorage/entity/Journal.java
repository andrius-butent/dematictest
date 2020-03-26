package com.dematic.bookstorage.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Journal extends Book {

    @NotNull(message = "Science Index is required")
    @Min(value = 1)
    @Max(value = 10)
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
