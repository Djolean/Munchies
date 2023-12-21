package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final RestaurantService restaurantService;

    @GetMapping({"/allRestaurants"})
        public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());
        return "admin/allRestaurants";
    }
}
