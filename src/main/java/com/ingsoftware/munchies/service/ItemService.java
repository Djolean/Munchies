package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.controller.response.ItemResponseDTO;

import java.util.List;

public interface ItemService {
        List<ItemResponseDTO> findAll();
        ItemResponseDTO addItemToGroupOrder(String id, ItemRequestDTO request);
}
