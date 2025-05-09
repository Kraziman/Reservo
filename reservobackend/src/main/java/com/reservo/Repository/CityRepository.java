package com.reservo.Repository;

import com.reservo.Models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCountryId(Integer countryId);
    Optional<City> findByName(String name);
}
