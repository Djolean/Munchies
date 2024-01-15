package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.mapper.GroupOrderMapper;
import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.repository.GroupOrderRepository;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import com.ingsoftware.munchies.service.GroupOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GroupOrderServiceImpl implements GroupOrderService {
    private final GroupOrderRepository groupOrderRepository;
    private final GroupOrderMapper mapper;
    private final RestaurantRepository restaurantRepository;

    @Override
    public GroupOrderResponseDTO addGroupOrder(String id, GroupOrderRequestDTO request) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        GroupOrder groupOrder = mapper.mapToEntity(restaurant, request);
        groupOrder.setCreatedDate(Instant.now());
        groupOrder.setLastModifiedDate(Instant.now());
        groupOrder.setGroupOrderUrl("MakeAnOrder");
        groupOrder.setRestaurant(restaurant);
        groupOrder.setTotalPrice(200.3);

        return mapper.mapToDTO(groupOrderRepository.save(mapper.mapToEntity(restaurant, request)));
    }

    @Override
    public GroupOrderResponseDTO findGroupOrderbyId(String id) {
        return mapper.mapToDTO(groupOrderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("GroupOrder not existing")));
    }
}
