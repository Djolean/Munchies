package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.model.entity.Restaurant;

import java.util.*;

public interface RestaurantService {

    List<Restaurant> findAll();

    Restaurant save(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    Restaurant findById(Integer restaurantId);

    void deleteById(Integer restaurantId);
}
