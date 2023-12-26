package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final RestaurantService restaurantService;

    @GetMapping({"/restaurants"})
    public String getAllRestaurants(Model model) {
        List<RestaurantResponseDTO> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);

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

//    @PostMapping({"/createRestaurant"})
//    public ResponseEntity<RestaurantRequestDTO> createRestaurant(@RequestBody RestaurantRequestDTO restaurantDto, @Valid RestaurantRequestDTO restaurantRequestDTO) {
//
//    }

    @GetMapping({"/editRestaurant/{id}"})
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Restaurant restaurant = restaurantService.findById(id);
        model.addAttribute("restaurant", restaurant);
        return "admin/update-restaurant";
    }

    @PostMapping("/updateRestaurant/{id}")
    public String updateRestaurant(@PathVariable("id") Integer id, @Valid Restaurant restaurant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            restaurant.setRestaurantId(id);
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


    @GetMapping("/login")
    public String showLoginPage() {
        return "admin/admin-login";
    }
}
