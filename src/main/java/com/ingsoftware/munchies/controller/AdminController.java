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
    private  final RestaurantMapper mapper;

    @GetMapping({"/restaurants"})
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants",  restaurantService.findAll().stream()
                .map(mapper::mapToDTO)
                .collect(Collectors.toList()));
        return "admin/restaurants";
    }

    @GetMapping("/restaurant-details/{id}")
    public String getRestaurantDetails(@PathVariable("id") String id, Model model) {
        model.addAttribute("restaurantDTO",  mapper.mapToDTO(restaurantService.findById(id)));
        return "admin/restaurant-details";
    }


    @GetMapping({"/restaurant/{id}"})
    public String getRestaurantById(@PathVariable String id, Model model) {
        model.addAttribute("restaurant", restaurantService.findById(id));
        return "admin/restaurant-details-admin";
    }


    @PostMapping("/restaurant/save")
    public String saveRestaurant(@ModelAttribute @Valid RestaurantRequestDTO requestDTO,
                                 BindingResult result){
        if(result.hasErrors()){
            return "admin/add-restaurant";
        }
        restaurantService.save(mapper.mapToEntity(requestDTO));
        return "redirect:/admin/restaurants";
    }

    @GetMapping({"/update-restaurant/{id}"})
    public String updateRestaurant(@PathVariable("id") String id,
                                   @ModelAttribute(name = "request") @Valid RestaurantRequestDTO request,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "admin/update-restaurant";
        }
        restaurantService.update(mapper.mapToEntity(request, id));
        return "redirect:/admin/restaurants";
    }

    @GetMapping("/delete-restaurant/{id}")
    public String deleteRestaurant(@PathVariable("id") String id) {
        restaurantService.delete(id);
        return "redirect:/restaurants";
    }

    //SHOW PAGE METHODS
    @GetMapping("/login")
    public String showLoginPage() {
        return "admin/admin-login";
    }

    @GetMapping("/restaurant/add")
    public String restaurant(Model model){
        model.addAttribute("request", RestaurantRequestDTO.builder().build());
        return "admin/add-restaurant";
    }


}
