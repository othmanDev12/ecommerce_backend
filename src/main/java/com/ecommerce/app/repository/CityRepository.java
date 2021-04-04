package com.ecommerce.app.repository;

import com.ecommerce.app.entity.Contry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.app.entity.City;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
    List<City> findAllByContry(Contry contry);
    
    Optional<City> findByCityIdAndContryContryId(Long cityId , Long countryId);
}
