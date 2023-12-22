package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.repository.ItemRepository;
import com.ingsoftware.munchies.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
}
