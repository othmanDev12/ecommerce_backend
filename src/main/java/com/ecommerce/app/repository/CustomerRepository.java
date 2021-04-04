package com.ecommerce.app.repository;

import com.ecommerce.app.entity.City;
import com.ecommerce.app.entity.Contry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findAllByCity(City city);
    
    Optional<Customer> findByCustomerIdAndCityCityId(Long customerId , Long cityId);
    
}
