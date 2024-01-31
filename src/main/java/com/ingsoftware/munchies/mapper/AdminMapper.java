package com.ingsoftware.munchies.mapper;

import com.ingsoftware.munchies.controller.request.AdminRequestDTO;
import com.ingsoftware.munchies.controller.response.AdminResponseDTO;
import com.ingsoftware.munchies.model.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AdminMapper {


    public AdminResponseDTO mapToDTO(final Admin admin) {

        return AdminResponseDTO.builder()
                .adminId(admin.getAdminId())
                .adminName(admin.getAdminName())
                .adminEmail(admin.getAdminEmail())
                .password(admin.getPassword())
                .createdDate(admin.getCreatedDate())
                .lastModifiedDate(admin.getLastModifiedDate())
                .build();
    }


    public Admin mapToEntity(final AdminRequestDTO request) {

        return Admin.builder()
                .adminId(request.getAdminId())
                .adminName(request.getAdminName())
                .adminEmail(request.getAdminEmail())
                .password(request.getPassword())
                .build();
    }

    public Admin mapToEntityUpdate(final Admin admin, final AdminRequestDTO request) {

            admin.setAdminName(request.getAdminName());
            admin.setAdminEmail(admin.getAdminEmail());
            admin.setLastModifiedDate(Instant.now());

        return admin;
    }
}
