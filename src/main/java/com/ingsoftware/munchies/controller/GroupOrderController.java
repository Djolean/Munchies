package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.service.GroupOrderService;
import com.ingsoftware.munchies.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Controller
@RequiredArgsConstructor
public class GroupOrderController {

    private final RestaurantService restaurantService;
    private final GroupOrderService groupOrderService;

    @GetMapping("/create-group-order/{restaurantId}")
    public String showCreateForm(@PathVariable("restaurantId") String id, Model model) {

        model.addAttribute("groupOrder", new GroupOrderRequestDTO());
        model.addAttribute("restaurants", restaurantService.findById(id));

        return "create-group-order";
    }

    @PostMapping("/restaurant/{restaurantId}/group-order/create")
    public String createGroupOrder(@PathVariable("restaurantId") String id,
                                   @Valid @ModelAttribute("groupOrder") GroupOrderRequestDTO request,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("groupOrder", request);
            return "create-group-order";
        }

        model.addAttribute("groupOrder", groupOrderService.addGroupOrder(id, request));
        return "redirect:/admin/restaurants";
    }


}
