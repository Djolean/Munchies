package com.ingsoftware.munchies.service;

import com.ingsoftware.munchies.model.entity.Admin;

public interface AdminService{

     Admin saveAdmin(Admin admin);
     Admin getLoggedInAdmin();
     Admin updateAdmin(Admin admin);
     Admin getAdminDetails();
     void deleteAdmin();

}
