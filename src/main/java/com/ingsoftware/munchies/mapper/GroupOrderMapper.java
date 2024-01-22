package com.ingsoftware.munchies.mapper;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GroupOrderMapper {


    public GroupOrderResponseDTO mapToDTO(final GroupOrder groupOrder) {

        return GroupOrderResponseDTO.builder()
                .groupOrderId(groupOrder.getGroupOrderId())
                .creatorName(groupOrder.getCreatorName())
                .groupOrderUrl(groupOrder.getGroupOrderUrl())
                .restaurant(groupOrder.getRestaurant())
                .groupOrderTimeout(groupOrder.getGroupOrderTimeout())
                .createdDate(groupOrder.getCreatedDate())
                .lastModifiedDate(groupOrder.getLastModifiedDate())
                .totalPrice(groupOrder.getTotalPrice())
                .build();
    }

    public GroupOrder mapToEntity(final Restaurant restaurant, final GroupOrderRequestDTO request) {
        return GroupOrder.builder()
                .groupOrderId(request.getGroupOrderId())
                .creatorName(request.getCreatorName())
                .groupOrderTimeout(request.getGroupOrderTimeout())
                .restaurant(restaurant)
                .build();
    }

    public GroupOrder mapToEntityTime(final GroupOrderResponseDTO response) {
      return GroupOrder.builder()
              .groupOrderId(response.getGroupOrderId())
              .creatorName(response.getCreatorName())
              .groupOrderUrl(response.getGroupOrderUrl())
              .restaurant(response.getRestaurant())
              .groupOrderTimeout(response.getGroupOrderTimeout())
              .createdDate(response.getCreatedDate())
              .lastModifiedDate(response.getLastModifiedDate())
              .totalPrice(response.getTotalPrice())
              .build();
    }
}
