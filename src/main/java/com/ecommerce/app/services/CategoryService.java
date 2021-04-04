package com.ecommerce.app.services;

import com.ecommerce.app.entity.Category;
import com.ecommerce.app.paging.CategoryPage;
import com.ecommerce.app.search.CategoryCriteriaSearch;

import org.springframework.data.domain.Page;

import java.util.List;


public interface CategoryService {
	
	public Page<Category> findAll(CategoryPage categoryPage , CategoryCriteriaSearch categoryCriteriaSearch);

	public List<Category> listAll();
	
	public Category findById(Long categoryId);
	
	public Category update(Category category , Long categoryId);
	
	public Category create(Category category);
	
	public void delete(Long categoryId);
	
}
