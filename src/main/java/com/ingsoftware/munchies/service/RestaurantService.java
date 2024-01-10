package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.Restaurant;

import java.util.*;

public interface RestaurantService {

    List<RestaurantResponseDTO> findAll();

    RestaurantResponseDTO findById(String id);

    RestaurantResponseDTO addRestaurant(RestaurantRequestDTO request);

    void delete(String id);

    void updateRestaurant(RestaurantRequestDTO request, String id);

    List<RestaurantResponseDTO> sortRestaurantByNameAsc();
    List<RestaurantResponseDTO> sortRestaurantByNameDesc();
    List<RestaurantResponseDTO> sortRestaurantByCreatedDateAsc();
    List<RestaurantResponseDTO> sortRestaurantByCreatedDateDesc();
}
