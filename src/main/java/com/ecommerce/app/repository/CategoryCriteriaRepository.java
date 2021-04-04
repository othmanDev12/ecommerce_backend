package com.ecommerce.app.repository;

import com.ecommerce.app.entity.Category;
import com.ecommerce.app.paging.CategoryPage;
import com.ecommerce.app.search.CategoryCriteriaSearch;
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
public class CategoryCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public CategoryCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Category> findAllWithPagingAndFiltering(CategoryPage categoryPage , CategoryCriteriaSearch categoryCriteriaSearch) {
        // create query with criteriaBuilder object
        CriteriaQuery<Category> categoryCriteriaQuery = criteriaBuilder.createQuery(Category.class);
        // create a root for finding the model that you want;
        Root<Category> root = categoryCriteriaQuery.from(Category.class);
        // searching with categoryName
        Predicate predicate = getPredicate( root , categoryCriteriaSearch);
        categoryCriteriaQuery.where(predicate);
        setOrder(categoryPage , root , categoryCriteriaQuery);
        TypedQuery<Category> categoryTypedQuery = entityManager.createQuery(categoryCriteriaQuery);
        categoryTypedQuery.setFirstResult(categoryPage.getPageNumber() * categoryPage.getPageSize());
        categoryTypedQuery.setMaxResults(categoryPage.getPageSize());

        // for define the request page
        Pageable pageable = getPageble(categoryPage);

        Long countCategory = getCount(predicate);
        return new PageImpl<>(categoryTypedQuery.getResultList() , pageable , countCategory);
    }



    private Predicate getPredicate(Root<Category> categoryRoot, CategoryCriteriaSearch categoryCriteriaSearch) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(categoryCriteriaSearch.getCategoryName())) {
           predicates.add(
                   criteriaBuilder.like(categoryRoot.get("categoryName") , "%" +
                           categoryCriteriaSearch.getCategoryName() + "%")
           );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(CategoryPage categoryPage, Root<Category> root, CriteriaQuery<Category> categoryCriteriaQuery) {
        if(categoryPage.getSortDirection().equals(Sort.Direction.ASC)) {
            categoryCriteriaQuery.orderBy(criteriaBuilder.asc(root.get(categoryPage.getSortedBy())));
        }
        else {
            categoryCriteriaQuery.orderBy(criteriaBuilder.desc(root.get(categoryPage.getSortedBy())));
        }
    }

    private Pageable getPageble(CategoryPage categoryPage) {
        Sort sort = Sort.by(categoryPage.getSortDirection() , categoryPage.getSortedBy());
        return PageRequest.of(categoryPage.getPageNumber() , categoryPage.getPageSize() ,
                 sort);
    }

    private Long getCount(Predicate predicate) {
        CriteriaQuery<Long> categoryCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Category> countRoot = categoryCriteriaQuery.from(Category.class);
        categoryCriteriaQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(categoryCriteriaQuery).getSingleResult();
    }

}
