package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.Restaurant;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public interface RestaurantService {

    List<Restaurant> findAll();

    RestaurantResponseDTO findById(String id);

    RestaurantResponseDTO addRestaurant(RestaurantRequestDTO request);

    void delete(String id);
}
