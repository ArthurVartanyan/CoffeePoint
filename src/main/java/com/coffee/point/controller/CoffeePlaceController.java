package com.coffee.point.controller;

import com.coffee.point.dto.place.CoffeePlaceDTO;
import com.coffee.point.dto.place.CreateCoffeePlaceDTO;
import com.coffee.point.service.CoffeePlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoffeePlaceController {

    private final CoffeePlaceService coffeePlaceService;

    public CoffeePlaceController(CoffeePlaceService coffeePlaceService) {
        this.coffeePlaceService = coffeePlaceService;
    }

    @PostMapping("/api/coffee-places")
    public ResponseEntity<Long> createCoffeePlace(@RequestBody CreateCoffeePlaceDTO createCoffeePlaceDTO) {
        return new ResponseEntity<>(coffeePlaceService.createCoffeePlace(createCoffeePlaceDTO), HttpStatus.CREATED);
    }

    @GetMapping("/api/coffee-places/{id}")
    public ResponseEntity<CoffeePlaceDTO> getCoffeePlace(@PathVariable Long id) {
        return new ResponseEntity<>(coffeePlaceService.getCoffeePlace(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/coffee-places/{id}")
    public ResponseEntity<Void> deleteCoffeePlace(@PathVariable Long id) {
        coffeePlaceService.deleteCoffeePlace(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}