package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.controller.request.AdminRequestDTO;
import com.ingsoftware.munchies.controller.response.AdminResponseDTO;
import java.util.List;

public interface AdminService{

     AdminResponseDTO addAdmin(AdminRequestDTO request);

     AdminResponseDTO getLoggedInAdmin();

     List<AdminResponseDTO> findAll();

     void updateAdmin(String id, AdminRequestDTO request);

     AdminResponseDTO getAdminDetails();

     void deleteAdmin(String id);

     AdminResponseDTO findById(String id);

}
