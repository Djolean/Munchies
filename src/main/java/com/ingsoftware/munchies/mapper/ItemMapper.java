package com.ingsoftware.munchies.mapper;

import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.controller.response.ItemResponseDTO;
import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemResponseDTO mapToDTO(final Item item) {

        return ItemResponseDTO.builder()
                .itemId(item.getItemId())
                .groupOrder(item.getGroupOrder())
                .itemName(item.getItemName())
                .employeeName(item.getEmployeeName())
                .createdDate(item.getCreatedDate())
                .lastModifiedDate(item.getLastModifiedDate())
                .price(item.getPrice())
                .build();
    }


    public Item mapToEntity(final ItemRequestDTO request, final GroupOrder groupOrder) {

        return Item.builder().
                itemId(request.getItemId())
                .groupOrder(groupOrder)
                .itemName(request.getItemName())
                .employeeName(request.getEmployeeName())
                .price(request.getPrice())
                .build();
    }
}
