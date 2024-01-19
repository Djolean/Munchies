package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.mapper.GroupOrderMapper;
import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Item;
import com.ingsoftware.munchies.repository.GroupOrderRepository;
import com.ingsoftware.munchies.repository.ItemRepository;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import com.ingsoftware.munchies.service.GroupOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GroupOrderServiceImpl implements GroupOrderService {
    private final GroupOrderRepository groupOrderRepository;
    private final GroupOrderMapper mapper;
    private final RestaurantRepository restaurantRepository;
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public GroupOrderResponseDTO addGroupOrder(String id, GroupOrderRequestDTO request) {

        var restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        var groupOrder = mapper.mapToEntity(restaurant, request);

        groupOrder.setCreatedDate(Instant.now());
        groupOrder.setLastModifiedDate(Instant.now());
        groupOrder.setRestaurant(restaurant);
        groupOrder.setGroupOrderUrl("localhost:8080/restaurants/group-order/");
        groupOrderRepository.save(groupOrder);
        groupOrder.setTotalPrice(calculateTotalPrice(groupOrder));

        return mapper.mapToDTO(groupOrderRepository.save(groupOrder));
    }

    @Override
    public GroupOrderResponseDTO findGroupOrderById(String id) {
        return mapper.mapToDTO(groupOrderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("GroupOrder not existing")));
    }

    private Double calculateTotalPrice(GroupOrder groupOrder) {
        double price = itemRepository.findAllByGroupOrder(groupOrder).stream()
                .mapToDouble(Item::getPrice)
                .sum();

        double additionalCharges = groupOrder.getRestaurant().getDeliveryInfo().getAdditionalCharges().doubleValue();

        return price + additionalCharges;
    }

    public Integer calculateTimeRemaining(GroupOrderResponseDTO response) {

        int timeoutMinutes = response.getGroupOrderTimeout();
        int timeoutSeconds = timeoutMinutes * 60;
        int remainingTimeSeconds = timeoutSeconds - (int) Duration.between(response.getCreatedDate(), Instant.now()).getSeconds();


        response.setValid(true);
        if (remainingTimeSeconds <= 0) {
            response.setValid(false);
        }
        return remainingTimeSeconds;
    }

    public String formatTime(int remainingTimeSeconds) {
        int minutes = remainingTimeSeconds / 60;
        int seconds = remainingTimeSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
