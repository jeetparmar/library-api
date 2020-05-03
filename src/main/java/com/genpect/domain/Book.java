package com.genpect.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	private String title;
	
	@NotNull
	private String author;
	
	@NotNull
	private Integer isbnNumber;
	
	@NotNull
	private Double price;
	
	@NotNull
	private String language;
	
	public Book() {
		
	}
	
	public Book(Integer id, @NotNull String title, @NotNull String author, @NotNull Integer isbnNumber, @NotNull Double price,
			@NotNull String language) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbnNumber = isbnNumber;
		this.price = price;
		this.language = language;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(Integer isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
