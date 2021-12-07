package com.coffee.point.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "coffee_place_review", schema = "public")
public class CoffeePlaceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_place_id")
    private CoffeePlace coffeePlace;

    @Column(name = "review_text")
    private String reviewText;
}