package com.ingsoftware.munchies.mapper;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.DeliveryInfoResponseDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.DeliveryInfo;
import com.ingsoftware.munchies.model.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
public class RestaurantMapper {

    public RestaurantResponseDTO mapToDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .menuUrl(restaurant.getMenuUrl())
                .createdDate(restaurant.getCreatedDate())
                .lastModifiedDate(restaurant.getLastModifiedDate())
                .shortName(generateShortName(restaurant.getRestaurantName()))
                .deliveryInfo(mapDeliveryInfoToDTO(restaurant.getDeliveryInfo()))
                .build();
    }


    public Restaurant mapToEntity(final RestaurantRequestDTO restaurantRequestDTO, String id) {
        Restaurant.RestaurantBuilder builder = Restaurant.builder()
                .restaurantId(id)
                .restaurantName(restaurantRequestDTO.getRestaurantName())
                .address(restaurantRequestDTO.getAddress())
                .shortName(generateShortName(restaurantRequestDTO.getRestaurantName()))
                .phoneNumber(restaurantRequestDTO.getPhoneNumber())
                .menuUrl(restaurantRequestDTO.getMenuUrl())
                .createdDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .deliveryInfo(DeliveryInfo.builder()
                        .deliveryTime(restaurantRequestDTO.getDeliveryTime())
                        .additionalCharges(restaurantRequestDTO.getAdditionalCharges())
                        .createdDate(Instant.now())
                        .lastModifiedDate(Instant.now())
                        .build());

        return builder.build();
    }

    public Restaurant mapToEntity(final RestaurantRequestDTO restaurantRequestDTO) {
        return mapToEntity(restaurantRequestDTO, null);
    }

    private DeliveryInfoResponseDTO mapDeliveryInfoToDTO(DeliveryInfo deliveryInfo) {

        return DeliveryInfoResponseDTO.builder()
                .deliveryId(deliveryInfo.getDeliveryId())
                .deliveryTime(deliveryInfo.getDeliveryTime())
                .additionalCharges(deliveryInfo.getAdditionalCharges())
                .createdDate(deliveryInfo.getCreatedDate())
                .lastModifiedDate(deliveryInfo.getLastModifiedDate())
                .build();
    }

    private String generateShortName(String name) {
        return name.replaceAll(" ", "_");
    }
}
