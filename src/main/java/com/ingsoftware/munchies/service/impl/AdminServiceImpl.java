package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.repository.AdminRepository;
import com.ingsoftware.munchies.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
}
