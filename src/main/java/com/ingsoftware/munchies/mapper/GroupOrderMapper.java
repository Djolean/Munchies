package com.ingsoftware.munchies.mapper;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class GroupOrderMapper {

    public GroupOrderResponseDTO mapToDTO(final GroupOrder groupOrder) {


        return GroupOrderResponseDTO.builder()
                .groupOrderId(groupOrder.getGroupOrderId())
                .creatorName(groupOrder.getCreatorName())
                .groupOrderUrl(groupOrder.getGroupOrderUrl())
                .restaurant(groupOrder.getRestaurant())
                .timeout(groupOrder.getTimeout())
                .dateCreated(groupOrder.getDateCreated())
                .lastTimeModified(groupOrder.getLastModifiedDate())
                .totalPrice(groupOrder.getTotalPrice())
                .build();
    }

    public GroupOrder mapToEntity(GroupOrderRequestDTO request, Restaurant restaurant) {


        return GroupOrder.builder()
                .creatorName(request.getCreatorName())
                .restaurant(request.getRestaurant())
                .timeout(request.getTimeout())
                .restaurant(restaurant)
                .build();
    }
}
