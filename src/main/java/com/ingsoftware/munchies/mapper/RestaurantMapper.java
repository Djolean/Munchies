package com.ingsoftware.munchies.mapper;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.DeliveryInfoResponseDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.DeliveryInfo;
import com.ingsoftware.munchies.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;


@Component
@RequiredArgsConstructor
public class RestaurantMapper {

    public RestaurantResponseDTO mapToDTO(final Restaurant restaurant) {

        RestaurantResponseDTO response = new RestaurantResponseDTO();

        response.setRestaurantId(restaurant.getRestaurantId());
        response.setRestaurantName(restaurant.getRestaurantName());
        response.setShortName(restaurant.getShortName());
        response.setAddress(restaurant.getAddress());
        response.setPhoneNumber(restaurant.getPhoneNumber());
        response.setMenuUrl(restaurant.getMenuUrl());
        response.setCreatedDate(restaurant.getCreatedDate());
        response.setLastModifiedDate(restaurant.getLastModifiedDate());
        response.setDeliveryInfo(mapDeliveryInfoToDTO(restaurant.getDeliveryInfo()));

        return response;
    }

    public Restaurant mapToEntity(final RestaurantRequestDTO request, final Restaurant restaurant, final DeliveryInfo deliveryInfo) {

        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setShortName(restaurant.getShortName());
        restaurant.setAddress(request.getAddress());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setMenuUrl(request.getMenuUrl());

        deliveryInfo.setDeliveryId(deliveryInfo.getDeliveryId());
        deliveryInfo.setDeliveryTime(request.getDeliveryTime());
        deliveryInfo.setAdditionalCharges(request.getAdditionalCharges());


        restaurant.setDeliveryInfo(mapToDeliveryInfoEntity(request, deliveryInfo));


        return restaurant;
    }

    public Restaurant mapToEntityUpdate(final RestaurantRequestDTO request, final Restaurant restaurant) {

        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setAddress(request.getAddress());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setMenuUrl(request.getMenuUrl());
        restaurant.setLastModifiedDate(Instant.now());

        DeliveryInfo deliveryInfo = restaurant.getDeliveryInfo();
        deliveryInfo.setLastModifiedDate(Instant.now());
        restaurant.setDeliveryInfo(mapToDeliveryInfoEntity(request, deliveryInfo));

        return restaurant;
    }

    private DeliveryInfoResponseDTO mapDeliveryInfoToDTO(final DeliveryInfo deliveryInfo) {

        DeliveryInfoResponseDTO response = new DeliveryInfoResponseDTO();

        response.setDeliveryId(deliveryInfo.getDeliveryId());
        response.setCreatedDate(deliveryInfo.getCreatedDate());
        response.setLastModifiedDate(deliveryInfo.getLastModifiedDate());
        response.setDeliveryTime(deliveryInfo.getDeliveryTime());
        response.setAdditionalCharges(deliveryInfo.getAdditionalCharges());

        return response;
    }

    private DeliveryInfo mapToDeliveryInfoEntity(final RestaurantRequestDTO request, final DeliveryInfo deliveryInfo) {

        deliveryInfo.setDeliveryId(deliveryInfo.getDeliveryId());
        deliveryInfo.setDeliveryTime(request.getDeliveryTime());
        deliveryInfo.setAdditionalCharges(request.getAdditionalCharges());
        deliveryInfo.setCreatedDate(Instant.now());
        deliveryInfo.setLastModifiedDate(Instant.now());
        return deliveryInfo;
    }
}
