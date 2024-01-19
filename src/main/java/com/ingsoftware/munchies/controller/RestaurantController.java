package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public String getAllRestaurants(
            @RequestParam(defaultValue = "0") String page,
            @RequestParam(defaultValue = "4") String size,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "restaurantName") String sortBy,
            Model model) {

        Page<RestaurantResponseDTO> restaurants = restaurantService.findAll(Integer.parseInt(page), Integer.parseInt(size), sortOrder, sortBy);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("pageSize", size);
        model.addAttribute("pageNum", page);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);


        return "restaurants";
    }


    @GetMapping({"/restaurant/{id}"})
    public String getRestaurantById(@PathVariable String id, Model model) {
        model.addAttribute("restaurant", restaurantService.findById(id));
        return "admin/restaurant-details";
    }


    @PostMapping("/restaurant/save")
    public String saveRestaurant(@Valid @ModelAttribute("request") RestaurantRequestDTO request,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("request", request);
            return "admin/create-restaurant";
        }
        restaurantService.addRestaurant(request);
        return "redirect:/restaurants";
    }

    @PostMapping({"/update-restaurant/{id}"})
    public String updateRestaurant(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("request") RestaurantRequestDTO request,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "admin/update-restaurant";
        }
        request.setRestaurantId(id);
        restaurantService.updateRestaurant(request, id);
        return "redirect:/restaurants";
    }

    @GetMapping("/delete-restaurant/{id}")
    public String deleteRestaurant(@PathVariable("id") String id) {
        restaurantService.delete(id);
        return "redirect:/restaurants";
    }


    //SHOW PAGE METHODS
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("request", new RestaurantRequestDTO());
        return "admin/create-restaurant";
    }

    @GetMapping("/update-restaurant/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        RestaurantResponseDTO response = restaurantService.findById(id);
        model.addAttribute("restaurant", new RestaurantRequestDTO(response.getRestaurantId(),
                response.getRestaurantName(),
                response.getAddress(),
                response.getPhoneNumber(),
                response.getMenuUrl(),
                response.getDeliveryInfo().getDeliveryTime(),
                response.getDeliveryInfo().getAdditionalCharges()));

        return "admin/update-restaurant";
    }
}

