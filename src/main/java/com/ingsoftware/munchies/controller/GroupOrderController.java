package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class GroupOrderController {

    private final RestaurantService restaurantService;
    @GetMapping("/create-group-order")
    public String showCreateForm(Model model) {

        model.addAttribute("groupOrder", new GroupOrderRequestDTO());
        model.addAttribute("restaurants", restaurantService.findAll());

        return "create-group-order";
    }
}
