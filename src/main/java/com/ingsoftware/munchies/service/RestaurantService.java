package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.Restaurant;
import org.springframework.data.domain.Page;

import java.util.*;

public interface RestaurantService {

    Page<RestaurantResponseDTO> findAll(int page, int size, String sortBy, String sortOrder);

    RestaurantResponseDTO findById(String id);

    Restaurant findByIdEntity(String id);

    RestaurantResponseDTO addRestaurant(RestaurantRequestDTO request);

    void delete(String id);

    void updateRestaurant(RestaurantRequestDTO request, String id);

    List<RestaurantResponseDTO> sortRestaurantByNameDesc();

    String generateShortName(String name);
}


