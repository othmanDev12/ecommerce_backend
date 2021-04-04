package com.ecommerce.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.entity.Category;
import com.ecommerce.app.paging.CategoryPage;
import com.ecommerce.app.search.CategoryCriteriaSearch;
import com.ecommerce.app.services.CategoryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories/api")
public class CategoryController  {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/save")
	public ResponseEntity<Category> createCategory(@ModelAttribute Category category) {
	   Category createCategory = categoryService.create(category);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(createCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Category>> listCategories() {
		return new ResponseEntity<>(categoryService.listAll() , HttpStatus.OK);
	}
	
	@GetMapping("/listCategories")
	public ResponseEntity<Page<Category>> findAllCategories(CategoryPage categoryPage , CategoryCriteriaSearch
			categoryCriteriaSearch) {
		
		return new ResponseEntity<>(categoryService.findAll(categoryPage, categoryCriteriaSearch) , HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		Category category = categoryService.findById(id);
		return ResponseEntity.ok(category);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id , @ModelAttribute Category category) {
		Category updateCategory = categoryService.update(category, id);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(updateCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

}
