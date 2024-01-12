package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;

public interface GroupOrderService {

    GroupOrderResponseDTO createGroupOrder(GroupOrderRequestDTO request);
}
