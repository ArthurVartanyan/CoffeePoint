package com.coffee.point.service;

import com.coffee.point.dto.place.CoffeePlaceDTO;
import com.coffee.point.dto.place.CreateCoffeePlaceDTO;
import com.coffee.point.mapper.CoffeePlaceMapper;
import com.coffee.point.model.CoffeePlace;
import com.coffee.point.repository.CoffeePlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class CoffeePlaceService {

    private final CoffeePlaceRepository coffeePlaceRepository;

    private final CoffeePlaceMapper coffeePlaceMapper;

    public CoffeePlaceService(CoffeePlaceRepository coffeePlaceRepository, CoffeePlaceMapper coffeePlaceMapper) {
        this.coffeePlaceRepository = coffeePlaceRepository;
        this.coffeePlaceMapper = coffeePlaceMapper;
    }

    /**
     * Create new coffee-place
     *
     * @param createCoffeePlaceDTO - DTO for create
     * @return - new place id
     */
    public Long createCoffeePlace(CreateCoffeePlaceDTO createCoffeePlaceDTO) {
        CoffeePlace coffeePlace = coffeePlaceMapper.map(createCoffeePlaceDTO, CoffeePlace.class);
        coffeePlace.setDeleted(false);
        return coffeePlaceRepository.save(coffeePlace).getId();
    }

    /**
     * Get coffee-place by id
     *
     * @param id - coffee-place id
     * @return - coffee-place DTO
     */
    public CoffeePlaceDTO getCoffeePlace(Long id) {
        CoffeePlace coffeePlace = coffeePlaceRepository.findByIdAndDeletedIsFalse(id);
        return coffeePlaceMapper.map(coffeePlace, CoffeePlaceDTO.class);
    }

    /**
     * Delete coffee-place by id
     */
    public void deleteCoffeePlace(Long id) {
        CoffeePlace coffeePlace = coffeePlaceRepository.findByIdAndDeletedIsFalse(id);
        coffeePlace.setDeleted(true);
        coffeePlaceRepository.save(coffeePlace);
    }
}