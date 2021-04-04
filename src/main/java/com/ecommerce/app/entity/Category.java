package com.ecommerce.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {
	
	private Long categoryId;
	private String categoryName;
	private List<Product> products;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(String categoryName , List<Product> products) {
		super();
		this.categoryName = categoryName;
		this.products = products;
	}

	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_name")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "category" , cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.PERSIST		
			, CascadeType.REFRESH})
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", products=" + products + "]";
	}
	
	
}
