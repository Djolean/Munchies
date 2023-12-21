package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.repository.DeliveryInfoRepository;
import com.ingsoftware.munchies.service.DeliveryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryInfoServiceImpl implements DeliveryInfoService {

    private final DeliveryInfoRepository deliveryInfoRepository;
}
