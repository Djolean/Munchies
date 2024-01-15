package com.ingsoftware.munchies.mapper;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupOrderMapper {
    private final RestaurantRepository restaurantRepository;

    public GroupOrderResponseDTO mapToDTO(final GroupOrder groupOrder) {


        return GroupOrderResponseDTO.builder()
                .groupOrderId(groupOrder.getGroupOrderId())
                .creatorName(groupOrder.getCreatorName())
                .groupOrderUrl(groupOrder.getGroupOrderUrl())
                .restaurant(groupOrder.getRestaurant())
                .timeout(groupOrder.getGroupOrderTimeout())
                .dateCreated(groupOrder.getCreatedDate())
                .lastTimeModified(groupOrder.getLastModifiedDate())
                .totalPrice(groupOrder.getTotalPrice())
                .build();
    }

    public GroupOrder mapToEntity(final Restaurant restaurant,final GroupOrderRequestDTO request) {
        return GroupOrder.builder()
                .groupOrderId(request.getRestaurantId())
                .creatorName(request.getCreatorName())
                .groupOrderTimeout(request.getTimeout())
                .restaurant(restaurantRepository.getReferenceById(restaurant.getRestaurantId()))
                .build();
    }
}
