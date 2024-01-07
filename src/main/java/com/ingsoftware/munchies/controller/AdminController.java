package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.mapper.RestaurantMapper;
import com.ingsoftware.munchies.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper mapper;

    @GetMapping({"/restaurants"})
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll().stream()
                .map(mapper::mapToDTO)
                .collect(Collectors.toList()));
        return "admin/restaurants";
    }

    @GetMapping("/restaurant-details/{id}")
    public String getRestaurantDetails(@PathVariable("id") String id, Model model) {
        model.addAttribute("restaurantDTO", mapper.mapToDTO(restaurantService.findById(id)));
        return "admin/restaurant-details";
    }


    @GetMapping({"/restaurant/{id}"})
    public String getRestaurantById(@PathVariable String id, Model model) {
        model.addAttribute("restaurant", mapper.mapToDTO(restaurantService.findById(id)));
        return "admin/restaurant-details";
    }


    @PostMapping("/restaurant/save")
    public String saveRestaurant(@ModelAttribute("request") @Valid RestaurantRequestDTO requestDTO,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "admin/create-restaurant";
        }
        restaurantService.save(mapper.mapToEntity(requestDTO));
        return "redirect:/admin/restaurants";
    }

    @GetMapping({"/update-restaurant/{id}"})
    public String updateRestaurant(@PathVariable("id") String id,
                                   @ModelAttribute("request") @Valid RestaurantRequestDTO request,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "admin/update-restaurant";
        }
        restaurantService.save(mapper.mapToEntity(request, id));
        return "redirect:/admin/restaurants";
    }

    @GetMapping("/delete-restaurant/{id}")
    public String deleteRestaurant(@PathVariable("id") String id) {
        restaurantService.delete(id);
        return "redirect:/admin/restaurants";
    }

    //SHOW PAGE METHODS
    @GetMapping("/login")
    public String showLoginPage() {
        return "admin/admin-login";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        RestaurantRequestDTO request = new RestaurantRequestDTO();
        model.addAttribute("request", request);
        return "admin/create-restaurant";
    }

}
