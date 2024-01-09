package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
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
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper mapper;

    @GetMapping({"/restaurants"})
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());
        return "admin/restaurants";
    }


    @GetMapping({"/restaurant/{id}"})
    public String getRestaurantById(@PathVariable String id, Model model) {
        model.addAttribute("restaurant", restaurantService.findById(id));
        return "admin/restaurant-details";
    }


    @PostMapping("/restaurant/save")
    public String saveRestaurant(@Valid @ModelAttribute("restaurant") RestaurantRequestDTO request,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("restaurant", request);
            return "admin/create-restaurant";
        }
        restaurantService.addRestaurant(request);
        return "redirect:/admin/restaurants";
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
        return "redirect:/admin/restaurants";
    }

    @GetMapping("/delete-restaurant/{id}")
    public String deleteRestaurant(@PathVariable("id") String id) {
        restaurantService.delete(id);
        return "redirect:/admin/restaurants";
    }

    //SHOW PAGE METHODS
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        RestaurantRequestDTO request = new RestaurantRequestDTO();
        model.addAttribute("request", request);
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

