package com.ecommerce.app.repository;



import com.ecommerce.app.entity.Category;
import com.ecommerce.app.entity.Promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByProductName(String productName);
	List<Product> findAllByCategoryAndPromotion(Category category , Promotion promotion);
	Optional<Product> findByProductIdAndCategoryCategoryIdAndPromotionPromotionId(Long id , Long categoryId , Long promotionId);

}
