package com.ecommerce.app.repository;

import com.ecommerce.app.entity.Product;
import com.ecommerce.app.paging.ProductPaging;
import com.ecommerce.app.search.ProductCriteriaSearch;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ProductCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Product> findAllWithFilters(ProductPaging productPaging , ProductCriteriaSearch productCriteriaSearch) {
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Predicate predicate = getPredicate(productRoot , productCriteriaSearch);
        criteriaQuery.where(predicate);
        setOrder(productPaging , productRoot , criteriaQuery);

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(productPaging.getPageNumber() * productPaging.getPageSize());
        typedQuery.setMaxResults(productPaging.getPageSize());

        Pageable pageable = getPageable(productPaging);

        Long countProduct = getCountProducts(predicate);
       return new PageImpl<>(typedQuery.getResultList() , pageable , countProduct);
    }



    private Predicate getPredicate(Root<Product> productRoot ,  ProductCriteriaSearch productCriteriaSearch) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(productCriteriaSearch.getProductName())) {
            predicates.add(
                    criteriaBuilder.like(productRoot.get("productName") , "%" +
                            productCriteriaSearch.getProductName() + "%")
            );
        }
        if(Objects.nonNull(productCriteriaSearch.getBrand())) {
            predicates.add(
                    criteriaBuilder.like(productRoot.get("brand") ,  "%"
                            + productCriteriaSearch.getBrand() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ProductPaging productPaging, Root<Product> productRoot, CriteriaQuery<Product> criteriaQuery) {
        if(productPaging.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(productPaging.getSortedBy())));
        }
        else {
            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(productPaging.getSortedBy())));
        }
    }

    private Pageable getPageable(ProductPaging productPaging) {
        Sort sort = Sort.by(productPaging.getSortDirection(), productPaging.getSortedBy());
        return PageRequest.of(productPaging.getPageNumber() , productPaging.getPageSize() , sort);
    }


    private Long getCountProducts(Predicate predicate) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        criteriaQuery.select(criteriaBuilder.count(productRoot)).where(predicate);
        return  entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}
