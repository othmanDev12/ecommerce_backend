package com.ecommerce.app.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ecommerce.app.entity.Category;
import com.ecommerce.app.paging.CategoryPage;
import com.ecommerce.app.repository.CategoryCriteriaRepository;
import com.ecommerce.app.repository.CategoryRepository;
import com.ecommerce.app.search.CategoryCriteriaSearch;


@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryCriteriaRepository categoryCriteriaRepository;
	
	public CategoryServiceImp() {
		
	}

	@Override
	public Page<Category> findAll(CategoryPage categoryPage , CategoryCriteriaSearch categoryCriteriaSearch) {
		return categoryCriteriaRepository.findAllWithPagingAndFiltering(categoryPage , categoryCriteriaSearch);
	}

	@Override
	public List<Category> listAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Long categoryId) {
		Category maybeCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> {
					throw new RuntimeException("this user is not found");
				});
		
		return maybeCategory;
	}

	
	@Override
	public  Category update(Category category , Long categoryId) {
	  Category maybeCategory = categoryRepository.findById(categoryId)
			  .orElseThrow(() -> {
				  throw new RuntimeException("the category is not found");
			  });
	  Optional<Category> checkCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
	  Optional<Category> checkCategoryId = categoryRepository.findById(categoryId);
	  if(checkCategoryName.isPresent() && 
	     checkCategoryName.get().getCategoryId() != checkCategoryId.get().getCategoryId()) {
		  throw new RuntimeException("the category is already exsist");
	  }
	  maybeCategory.setCategoryName(category.getCategoryName());
	  return categoryRepository.save(maybeCategory);
	 
	}

	@Override
	public Category create(Category category) {
		categoryRepository.findByCategoryName(category.getCategoryName()).ifPresent(
				(c) -> {
					throw new RuntimeException("this category with this category name: "
							+ c.getCategoryName() + "is already exsist");
				}
		);
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Long categoryId) {
		categoryRepository.findById(categoryId)
		.orElseThrow(()-> {
			throw new RuntimeException("this category is not found");
		});
		
		categoryRepository.deleteById(categoryId);
		
	}
	
	

}
