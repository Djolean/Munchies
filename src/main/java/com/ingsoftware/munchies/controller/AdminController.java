package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.model.entity.Restaurant;
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

    @GetMapping({"/restaurants"})
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());
        return "restaurants";
    }

    @GetMapping({"/createRestaurant"})
    public String showCreateForm(Model model) {
        Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        return "admin/restaurants";
    }
}
