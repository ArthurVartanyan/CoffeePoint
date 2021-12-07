package com.coffee.point.repository;

import com.coffee.point.model.CoffeePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeePlaceRepository extends JpaRepository<CoffeePlace, Long> {

    CoffeePlace findByIdAndDeletedIsFalse(Long id);
}