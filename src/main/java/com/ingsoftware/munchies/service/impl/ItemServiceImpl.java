package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.controller.response.ItemResponseDTO;
import com.ingsoftware.munchies.exception.Exception;
import com.ingsoftware.munchies.mapper.GroupOrderMapper;
import com.ingsoftware.munchies.mapper.ItemMapper;
import com.ingsoftware.munchies.repository.GroupOrderRepository;
import com.ingsoftware.munchies.repository.ItemRepository;
import com.ingsoftware.munchies.service.GroupOrderService;
import com.ingsoftware.munchies.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final GroupOrderRepository groupOrderRepository;
    private final GroupOrderService groupOrderService;
    private final ItemMapper mapper;
    private final GroupOrderMapper groupOrderMapper;

    @Override
    public List<ItemResponseDTO> findAllByGroupOrder(String id) {
        var groupOrder = groupOrderRepository.findById(id).orElseThrow(Exception.GroupOrderNotFoundException::new);

        return itemRepository.findAllByGroupOrder(groupOrder).stream()
                .map(mapper::mapToDTO)
                .toList();
    }

    @Override
    public ItemResponseDTO addItemToGroupOrder(String id, ItemRequestDTO request) {

        var groupOrder = groupOrderRepository.findById(id).orElseThrow(Exception.GroupOrderNotFoundException::new);

        var item = mapper.mapToEntity(request, groupOrder);

        if (groupOrderService.calculateTimeRemaining(groupOrderMapper.mapToDTO(groupOrder)) < 0) {
            throw new Exception.GroupOrderNotActiveException();
        } else {
            item.setCreatedDate(Instant.now());
            item.setLastModifiedDate(Instant.now());
            item.setGroupOrder(groupOrder);

            groupOrder.setTotalPrice(groupOrder.getTotalPrice() + item.getPrice());
            groupOrderRepository.save(groupOrder);
            return mapper.mapToDTO(itemRepository.save(item));
        }
    }


}