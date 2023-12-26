package com.ingsoftware.munchies.mapper;

import com.ingsoftware.munchies.controller.response.DeliveryInfoResponseDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.DeliveryInfo;
import com.ingsoftware.munchies.model.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class RestaurantMapper {

    public RestaurantResponseDTO mapRestaurantToDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .menuUrl(restaurant.getMenuUrl())
                .createdDate(restaurant.getCreatedDate())
                .lastModifiedDate(restaurant.getLastModifiedDate())
                .uuid(restaurant.getUuid())
                .shortName(generateShortName(restaurant.getRestaurantName()))
                .deliveryInfo(mapDeliveryInfoToDTO(restaurant.getDeliveryInfo()))
                .build();
    }

    public DeliveryInfoResponseDTO mapDeliveryInfoToDTO(DeliveryInfo deliveryInfo) {
        return DeliveryInfoResponseDTO.builder()
                .deliveryId(deliveryInfo.getDeliveryId())
                .deliveryTime(deliveryInfo.getDeliveryTime())
                .additionalCharges(deliveryInfo.getAdditionalCharges())
                .createdDate(deliveryInfo.getCreatedDate())
                .lastModifiedDate(deliveryInfo.getLastModifiedDate())
                .uuid(deliveryInfo.getUuid())
                .build();
    }

    public List<RestaurantResponseDTO> mapRestaurantsToDTO(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::mapRestaurantToDTO)
                .collect(Collectors.toList());
    }

    public String generateShortName(String name) {
        return name.replaceAll(" ", "_");
    }
}
