package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.controller.response.ItemResponseDTO;
import com.ingsoftware.munchies.model.entity.GroupOrder;

import java.util.List;

public interface ItemService {
        List<ItemResponseDTO> findAllByGroupOrder(String id);
        ItemResponseDTO addItemToGroupOrder(String id, ItemRequestDTO request);
}
