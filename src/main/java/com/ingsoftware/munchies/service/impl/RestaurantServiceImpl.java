package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.mapper.RestaurantMapper;
import com.ingsoftware.munchies.model.entity.DeliveryInfo;
import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import com.ingsoftware.munchies.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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

    public Page<RestaurantResponseDTO> findAll(int page, int size, String sortBy, String sortOrder) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortBy), sortOrder);
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);
        List<RestaurantResponseDTO> response = restaurantPage.getContent().stream()
                .map(mapper::mapToDTO)
                .toList();

        return new PageImpl<>(response, pageable, restaurantPage.getTotalElements());
    }


    @Override
    public RestaurantResponseDTO findById(String id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Restaurant doesn't exist"));
        return mapper.mapToDTO(restaurant);
    }

    @Override
    public Restaurant findByIdEntity(String id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NoSuchElementException("empty"));
    }

    @Transactional
    @Override
    public RestaurantResponseDTO addRestaurant(RestaurantRequestDTO request) {

        Restaurant restaurant = new Restaurant();
        DeliveryInfo deliveryInfo = new DeliveryInfo();

        mapper.mapToEntity(request, restaurant, deliveryInfo);

        deliveryInfo.setCreatedDate(Instant.now());
        deliveryInfo.setLastModifiedDate(Instant.now());

        restaurant.setCreatedDate(Instant.now());
        restaurant.setLastModifiedDate(Instant.now());
        restaurant.setDeliveryInfo(deliveryInfo);
        restaurant.setShortName(generateShortName(request.getRestaurantName()));

        restaurantRepository.save(restaurant);

        return mapper.mapToDTO(restaurant);
    }


    @Override
    public void delete(String restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public void updateRestaurant(RestaurantRequestDTO request, String id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Restaurant doesn't exist"));

        mapper.mapToEntityUpdate(request, restaurant);
        restaurantRepository.save(restaurant);
    }


    @Override
    public List<RestaurantResponseDTO> sortRestaurantByNameDesc() {

        return restaurantRepository.findAll(PageRequest.of(4, 1000, Sort.Direction.ASC, "restaurantName"))
                .getContent().stream().map(mapper::mapToDTO).toList();

    }

    public String generateShortName(String name) {
        if (name == null) {
            return "";
        }
        return name.replaceAll(" ", "_");
    }
}
