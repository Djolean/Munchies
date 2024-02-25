package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.exception.Exception;
import com.ingsoftware.munchies.mapper.GroupOrderMapper;
import com.ingsoftware.munchies.mapper.RestaurantMapper;
import com.ingsoftware.munchies.model.DeliveryInfo;
import com.ingsoftware.munchies.model.Restaurant;
import com.ingsoftware.munchies.repository.GroupOrderRepository;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import com.ingsoftware.munchies.service.GroupOrderService;
import com.ingsoftware.munchies.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;
    private final GroupOrderMapper groupOrderMapper;
    private final GroupOrderRepository groupOrderRepository;
    private final GroupOrderService groupOrderService;

    @Override
    public Page<RestaurantResponseDTO> findAll(int pageNum, int size, String sortBy, String sortOrder) {

        Pageable pageable = PageRequest.of(pageNum, size, Sort.Direction.fromString(sortBy), sortOrder);
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);
        List<RestaurantResponseDTO> response = restaurantPage.getContent().stream()
                .map(mapper::mapToDTO)
                .toList();

        return new PageImpl<>(response, pageable, restaurantPage.getTotalElements());
    }

    @Override
    public List<RestaurantResponseDTO> findAllSlack() {
        return restaurantRepository.findAll().stream().map(mapper::mapToDTO).toList();
    }


    @Override
    public RestaurantResponseDTO findById(String id) throws Exception.RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(Exception.RestaurantNotFoundException::new);
        return mapper.mapToDTO(restaurant);
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
        var restaurant = restaurantRepository.findById(restaurantId).orElseThrow(Exception.RestaurantNotFoundException::new);
        var groupOrderList = groupOrderRepository.findGroupOrdersByRestaurant(restaurant);

        for (var groupOrder : groupOrderList) {
            if (groupOrderService.calculateTimeRemaining(groupOrderMapper.mapToDTO(groupOrder)) > 0) {
                throw new Exception.GroupOrderStillActiveException();
            }
        }
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public void updateRestaurant(RestaurantRequestDTO request, String id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(Exception.RestaurantNotFoundException::new);

        mapper.mapToEntityUpdate(request, restaurant);
        restaurantRepository.save(restaurant);
    }


    public String generateShortName(String name) {
        if (name == null) {
            return "";
        }
        return name.replace(" ", "_");
    }
}
