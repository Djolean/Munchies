package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.controller.response.ItemResponseDTO;
import com.ingsoftware.munchies.mapper.ItemMapper;
import com.ingsoftware.munchies.repository.GroupOrderRepository;
import com.ingsoftware.munchies.repository.ItemRepository;
import com.ingsoftware.munchies.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final GroupOrderRepository groupOrderRepository;
    private final ItemMapper mapper;

    @Override
    public ItemResponseDTO addItemToGroupOrder(String id, ItemRequestDTO request) {

        var groupOrder = groupOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Group order doesn't exist!"));
        return mapper.mapToDTO(itemRepository.save(mapper.mapToEntity(request, groupOrder)));
    }
}