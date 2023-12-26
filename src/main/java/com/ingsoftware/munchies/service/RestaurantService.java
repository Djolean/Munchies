package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.Restaurant;

import java.util.*;

public interface RestaurantService {

    List<RestaurantResponseDTO> findAll();

    Restaurant save(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    Restaurant findById(Integer restaurantId);

    void deleteById(Integer restaurantId);
}
