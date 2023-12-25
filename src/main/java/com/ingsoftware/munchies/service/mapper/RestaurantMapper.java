package com.ingsoftware.munchies.service.mapper;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.Restaurant;


public class RestaurantMapper {

    public static RestaurantResponseDTO toResponse(Restaurant restaurant) {

        return RestaurantResponseDTO
                .builder()
                .id(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .additionalCharges(restaurant.getDeliveryInfo().getAdditionalCharges())
                .build();
    }

    public String shortRestaurantName(String name){
        return name.replaceAll(" ", "_");
    }
}
