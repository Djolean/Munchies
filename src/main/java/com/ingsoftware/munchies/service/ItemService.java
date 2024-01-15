package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.controller.response.ItemResponseDTO;

public interface ItemService {
        ItemResponseDTO addItemToGroupOrder(String id, ItemRequestDTO request);
}
