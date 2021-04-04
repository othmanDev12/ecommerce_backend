package com.ecommerce.app.repository;



import com.ecommerce.app.entity.Users;
import com.ecommerce.app.paging.UsersPage;
import com.ecommerce.app.search.UsersCriteriaSearch;
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
public class UserCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public UserCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Users> findAllWithFilters(UsersPage usersPage , UsersCriteriaSearch usersCriteriaSearch) {
        CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
        // the place when we must retrieve the instance.
        Root<Users> root = criteriaQuery.from(Users.class);
        Predicate predicate = getPredicate(usersCriteriaSearch , root);
        criteriaQuery.where(predicate);
        SetOrder(usersPage , criteriaQuery , root);
        // predicate limited
        TypedQuery<Users> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(usersPage.getPageNumber() * usersPage.getPageSize());
        typedQuery.setMaxResults(usersPage.getPageSize());

        Pageable pageable = getPageable(usersPage);

        Long countUsers = getCountUsers(predicate);
        return new PageImpl<>(typedQuery.getResultList() , pageable , countUsers);
    }

    


    public Predicate getPredicate(UsersCriteriaSearch usersCriteriaSearch , Root<Users> root) {
        // list predicate for searching
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(usersCriteriaSearch.getFullname())) {
           predicates.add(
                   criteriaBuilder.like(root.get("fullname") , "%" +
                           usersCriteriaSearch.getFullname() + "%")
           );
        }
        if (Objects.nonNull(usersCriteriaSearch.getEmail())) {
            predicates.add(
                    criteriaBuilder.like(root.get("email") , "%" +
                            usersCriteriaSearch.getEmail() + "%")
            );
        }
        // merge the predicates in one collection
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    public void SetOrder(UsersPage usersPage , CriteriaQuery<Users> criteriaQuery ,
                         Root<Users> usersRoot) {
        if(usersPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(usersRoot.get(usersPage.getSortedBy())));
        }
        else {
            criteriaQuery.orderBy(criteriaBuilder.desc(usersRoot.get(usersPage.getSortedBy())));
        }

    }

    private Pageable getPageable(UsersPage usersPage) {
        Sort sort = Sort.by(usersPage.getSortDirection() , usersPage.getSortedBy());
        return PageRequest.of(usersPage.getPageNumber() , usersPage.getPageSize() , sort);
    }

    private Long getCountUsers(Predicate predicate) {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Users> countRoot = criteriaQuery.from(Users.class);
        criteriaQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return  entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
