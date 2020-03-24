package com.dematic.bookstorage.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Journal extends Book {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "science_index")
	private Integer scienceIndex;

	public Integer getScienceIndex() {
		return scienceIndex;
	}

	public void setScienceIndex(Integer scienceIndex) {
		this.scienceIndex = scienceIndex;
	}

	@Override
	public double calculatePrice() {
		return getQuantity() * getPrice() * getScienceIndex();
	}
}
