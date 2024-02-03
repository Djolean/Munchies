package com.ingsoftware.munchies.controller.web;

import com.ingsoftware.munchies.controller.request.AdminRequestDTO;
import com.ingsoftware.munchies.controller.response.AdminResponseDTO;
import com.ingsoftware.munchies.service.AdminService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admins")
    public String getAllAdmins(Model model) {
        model.addAttribute("admins", adminService.findAll());

        return "admin/admins";
    }

    @PostMapping("/admin/save")
    public String saveAdmin(@Valid @ModelAttribute("request") AdminRequestDTO request,
                            BindingResult result, Model model) throws MessagingException {
        if (result.hasErrors()) {
            model.addAttribute("admin", request);
            return "admin/create-admin";
        }
        adminService.addAdmin(request);
        return "redirect:/admins";
    }

    @PutMapping({"/update-admin/{id}"})
    public String updateAdmin(@PathVariable("id") String id,
                              @Valid @ModelAttribute("request") AdminRequestDTO request,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("admin", request);
            return "admin/update-admin";
        }

        adminService.updateAdmin(id, request);
        return "redirect:/admins";
    }

    @DeleteMapping("/delete-admin/{id}")
    public String deleteAdmin(@PathVariable("id") String id) {
        adminService.deleteAdmin(id);
        return "redirect:/admins";
    }

    @GetMapping("/create-admin")
    public String showCreateForm(Model model) {
        model.addAttribute("request", new AdminRequestDTO());
        return "admin/create-admin";
    }

    @GetMapping("/update-admin/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        AdminResponseDTO response = adminService.findById(id);
        model.addAttribute("admin", new AdminRequestDTO(
                response.getAdminId(),
                response.getAdminName(),
                response.getAdminEmail(),
                response.getPassword()));
        return "admin/update-admin";
    }

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token") String token, Model model) {


        adminService.verifyAccount(token);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmationPage() {

        return "emails/confirmation";
    }
}
