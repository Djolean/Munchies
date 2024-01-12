package com.ingsoftware.munchies.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ControllerAdvice {


    @GetMapping("/")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "admin/login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/homePage";
    }
}
