package com.ingsoftware.munchies.controller.web;

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


@Controller
@RequiredArgsConstructor
@RequestMapping
public class RestaurantController {

    private final RestaurantService restaurantService;


    @GetMapping("/restaurants")
    public String getAllRestaurants(
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "restaurantName") String sortBy,
            Model model) {

        Page<RestaurantResponseDTO> restaurants = restaurantService.findAll(pageNum, pageSize, sortOrder, sortBy);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);

        return "restaurants";
    }


    @GetMapping({"/restaurant/{id}"})
    public String getRestaurantById(@PathVariable String id, Model model) {
        model.addAttribute("restaurant", restaurantService.findById(id));
        model.addAttribute("deliveryInfo", restaurantService.findById(id).getDeliveryInfo());
        return "restaurant-details";
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

    @PutMapping({"/update-restaurant/{id}"})
    public String updateRestaurant(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("request") RestaurantRequestDTO request, Model model,
                                   BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("restaurant", request);
            return "admin/update-restaurant";
        }
        restaurantService.updateRestaurant(request, id);
        return "redirect:/restaurants";
    }

    @DeleteMapping("/delete-restaurant/{id}")
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
        model.addAttribute("restaurant", new RestaurantRequestDTO(
                response.getRestaurantId(),
                response.getRestaurantName(),
                response.getAddress(),
                response.getPhoneNumber(),
                response.getMenuUrl(),
                response.getDeliveryInfo().getDeliveryTime(),
                response.getDeliveryInfo().getAdditionalCharges()));

        return "admin/update-restaurant";
    }

}

