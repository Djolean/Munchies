package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.repository.GroupOrderRepository;
import com.ingsoftware.munchies.service.GroupOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Group;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupOrderServiceImpl implements GroupOrderService {

    private final GroupOrderRepository groupOrderRepository;
}
