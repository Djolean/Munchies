package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;


public interface RestaurantService{

    Page<RestaurantResponseDTO> findAll(int page, int size, String sortBy, String sortOrder);

    List<RestaurantResponseDTO> findAllSlack();

    RestaurantResponseDTO findById(String id);

    RestaurantResponseDTO addRestaurant(RestaurantRequestDTO request);

    void delete(String id);

    void updateRestaurant(RestaurantRequestDTO request, String id);

    String generateShortName(String name);
}


