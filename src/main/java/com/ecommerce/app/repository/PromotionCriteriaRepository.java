package com.ecommerce.app.repository;

import com.ecommerce.app.entity.Promotion;
import com.ecommerce.app.paging.PromotionPaging;
import com.ecommerce.app.search.PromotionCriteriaSearch;
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
public class PromotionCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public PromotionCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Promotion> findAllWithFilters(PromotionPaging promotionPaging , PromotionCriteriaSearch promotionCriteriaSearch) {
        CriteriaQuery<Promotion> criteriaQuery = criteriaBuilder.createQuery(Promotion.class);
        Root<Promotion> promotionRoot = criteriaQuery.from(Promotion.class);
        Predicate predicate = getPredicate(promotionRoot , promotionCriteriaSearch);
        criteriaQuery.where(predicate);
        setOrder(promotionRoot , criteriaQuery , promotionPaging);

        TypedQuery<Promotion> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(promotionPaging.getPageNumber() * promotionPaging.getPageSize());
        typedQuery.setMaxResults(promotionPaging.getPageSize());

        Pageable pageable = getPageable(promotionPaging);

        Long countPromotions = getCountPromotion(predicate);
        return new PageImpl<>(typedQuery.getResultList() , pageable , countPromotions);
    }



    private Predicate getPredicate(Root<Promotion> promotionRoot, PromotionCriteriaSearch promotionCriteriaSearch) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(promotionCriteriaSearch.getPromotionName())) {
            predicates.add(
                    criteriaBuilder.like(promotionRoot.get("promotionName") , "%" +
                            promotionCriteriaSearch.getPromotionName())
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(Root<Promotion> promotionRoot, CriteriaQuery<Promotion> criteriaQuery, PromotionPaging promotionPaging) {
        if(promotionPaging.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(promotionRoot.get(promotionPaging.getSortedBy())));
        }

        else {
            criteriaQuery.orderBy(criteriaBuilder.desc(promotionRoot.get(promotionPaging.getSortedBy())));
        }
    }

    private Pageable getPageable(PromotionPaging promotionPaging) {
        Sort sort = Sort.by(promotionPaging.getSortedBy());
        return PageRequest.of(promotionPaging.getPageNumber() , promotionPaging.getPageSize() , sort);
    }

    private Long getCountPromotion(Predicate predicate) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Promotion> countRoot = criteriaQuery.from(Promotion.class);
        criteriaQuery.select(criteriaBuilder.count(countRoot)).where(predicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
