package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.model.entity.GroupOrder;

import java.sql.Time;
import java.time.Instant;

public interface GroupOrderService {

    GroupOrderResponseDTO addGroupOrder(String id, GroupOrderRequestDTO request);
    GroupOrderResponseDTO findGroupOrderById(String id);
    Integer calculateTimeRemaining(GroupOrderResponseDTO response);
    String formatTime(int remainingTimeSeconds);
}
