package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;

public interface GroupOrderService {

    GroupOrderResponseDTO addGroupOrder(String id, GroupOrderRequestDTO request);
    GroupOrderResponseDTO findGroupOrderbyId(String id);
}
