package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.mapper.RestaurantMapper;
import com.ingsoftware.munchies.model.entity.DeliveryInfo;
import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import com.ingsoftware.munchies.service.RestaurantService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();

    }

    @Override
    public RestaurantResponseDTO findById(String id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        return mapper.mapToDTO(restaurant);
    }

    @Transactional
    @Override
    public RestaurantResponseDTO addRestaurant(RestaurantRequestDTO request) {

        Restaurant restaurant = new Restaurant();
        DeliveryInfo deliveryInfo = new DeliveryInfo();

        deliveryInfo.setCreatedDate(Instant.now());
        deliveryInfo.setLastModifiedDate(Instant.now());

        restaurant.setCreatedDate(Instant.now());
        restaurant.setLastModifiedDate(Instant.now());
        restaurant.setDeliveryInfo(deliveryInfo);

        mapper.mapToEntity(request, restaurant, deliveryInfo);
        restaurantRepository.save(restaurant);
        return mapper.mapToDTO(restaurant);
    }


    @Override
    public void delete(String restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }


}
