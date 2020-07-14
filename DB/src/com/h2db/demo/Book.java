package com.h2db.demo;

public class Book {

	private int id;
	private String description;
	private String isbn;
	private int pages;
	private String title;
	private double unitCost;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String description, String isbn, int pages, String title, double unitCost) {
		super();
		this.id = id;
		this.description = description;
		this.isbn = isbn;
		this.pages = pages;
		this.title = title;
		this.unitCost = unitCost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", description=" + description + ", isbn=" + isbn + ", pages=" + pages + ", title="
				+ title + ", unitCost=" + unitCost + "]";
	}
	
	
}
