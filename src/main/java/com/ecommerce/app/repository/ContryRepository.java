package com.ecommerce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.Contry;

@Repository
public interface ContryRepository extends JpaRepository<Contry, Long> {

}
