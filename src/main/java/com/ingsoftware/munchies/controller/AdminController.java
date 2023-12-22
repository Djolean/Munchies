package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.model.dto.RestaurantCreationDTO;
import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import com.ingsoftware.munchies.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @GetMapping({"/restaurants"})
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());
        return "admin/restaurants";
    }

    @GetMapping({"/{restaurantId}"})
    public String getRestaurantById(@PathVariable Integer restaurantId, Model model) {
        Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        return "admin/restaurant-details-Admin";
    }

    @GetMapping({"/create"})
    public String showCreateForm(Model model) {
        Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        return "admin/restaurants";
    }

    @PostMapping({"/createRestaurant"})
    public String createRestaurant(@ModelAttribute("restaurant") Restaurant restaurant) {
        restaurantService.save(restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping({"/editRestaurant/{id}"})
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Restaurant restaurant = restaurantService.findById(id);
        model.addAttribute("restaurant", restaurant);
        return "admin/update-restaurant";
    }

    @PostMapping("/updateRestaurant/{id}")
    public String updateRestaurant(@PathVariable("id") Integer id, @Valid Restaurant restaurant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            restaurant.setId(id);
            return "admin/update-restaurant";
        }
        restaurantService.save(restaurant);
        model.addAttribute("restaurants", restaurantService.findAll());
        return "redirect:/restaurants";
    }

    @GetMapping("/deleteRestaurant/{id}")
    public String deleteRestaurant(@PathVariable Integer id) {
    restaurantService.deleteById(id);
        return "redirect:/restaurants";
    }
}
