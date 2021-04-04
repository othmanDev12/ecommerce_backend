package com.ecommerce.app.repository;

import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.paging.CustomerPage;
import com.ecommerce.app.search.CustomerCriteriaSearch;
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
public class CustomerCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public CustomerCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Customer> findAllWithFilters(CustomerPage customerPage , CustomerCriteriaSearch customerCriteriaSearch) {
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);

        Predicate predicate = getPredicate(customerCriteriaSearch , customerRoot);

        criteriaQuery.where(predicate);

        setOrder(customerRoot , criteriaQuery , customerPage);

        TypedQuery<Customer> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(customerPage.getPageNumber() * customerPage.getSizeNumber());
        typedQuery.setMaxResults(customerPage.getSizeNumber());

        Pageable pageable = getPageable(customerPage);

        Long customerCount = getCount(predicate);

        return new PageImpl<>(typedQuery.getResultList() , pageable , customerCount);
    }


    private Predicate getPredicate(CustomerCriteriaSearch customerCriteriaSearch, Root<Customer> customerRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(customerCriteriaSearch.getFirstName())) {
            predicates.add(
                    criteriaBuilder.like(customerRoot.get("firstName") , "%" +
                            customerCriteriaSearch.getFirstName() + "%")
            );
        }
        if(Objects.nonNull(customerCriteriaSearch.getLastName())) {
            predicates.add(
                    criteriaBuilder.like(customerRoot.get("lastName") , "%" +
                            customerCriteriaSearch.getLastName() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(Root<Customer> customerRoot, CriteriaQuery<Customer> criteriaQuery, CustomerPage customerPage) {
        if(customerPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(customerRoot.get(customerPage.getSortedBy())));
        }
        else {
            criteriaQuery.orderBy(criteriaBuilder.desc(customerRoot.get(customerPage.getSortedBy())));
        }
    }


    private Pageable getPageable(CustomerPage customerPage) {
        Sort sort = Sort.by(customerPage.getSortedBy());
        return PageRequest.of(customerPage.getPageNumber() , customerPage.getSizeNumber() , sort);
    }

    private Long getCount(Predicate predicate) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        criteriaQuery.select(criteriaBuilder.count(customerRoot)).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}
