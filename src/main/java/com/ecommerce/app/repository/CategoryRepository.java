package com.ecommerce.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByCategoryName(String categoryName);
	Optional<Category> findByCategoryId(Long categoryId);

}
