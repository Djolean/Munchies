package com.ingsoftware.munchies.controller;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.service.GroupOrderService;
import com.ingsoftware.munchies.service.ItemService;
import com.ingsoftware.munchies.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class GroupOrderController {

    private final RestaurantService restaurantService;
    private final GroupOrderService groupOrderService;
    private final ItemService itemService;

    @GetMapping("/create-group-order/{restaurantId}")
    public String showCreateForm(@PathVariable("restaurantId") String id, GroupOrderRequestDTO request, Model model) {

        model.addAttribute("groupOrder", request);
        model.addAttribute("restaurants", restaurantService.findById(id));

        return "create-group-order";
    }

    @PostMapping("/restaurant/{restaurantId}/group-order/create")
    public String createGroupOrder(@PathVariable("restaurantId") String id,
                                   @Valid @ModelAttribute("groupOrder") GroupOrderRequestDTO request,
                                   BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("groupOrder", request);
            return "create-group-order";
        }

        var groupOrder = groupOrderService.addGroupOrder(id, request);
        model.addAttribute("groupOrder", groupOrder);
        redirectAttributes.addAttribute("groupOrderId", groupOrder.getGroupOrderId());

        return "redirect:/restaurants/group-order/{groupOrderId}";
    }

    @GetMapping("/restaurants/group-order/{groupOrderId}")
    public String groupOrderPage(@PathVariable("groupOrderId") String id, ItemRequestDTO request, Model model) {

        var groupOrder = groupOrderService.findGroupOrderById(id);

        model.addAttribute("groupOrder", groupOrderService.findGroupOrderById(id));
        model.addAttribute("item", request);
        model.addAttribute("items", itemService.findAllByGroupOrder(id));
        model.addAttribute("timeout", groupOrderService.calculateTimeRemaining(groupOrder));

        return "group-order";
    }

    @GetMapping("/reload/{groupOrderId}")
    public String reloadItems(@PathVariable("groupOrderId") String id, Model model) {

        var groupOrder = groupOrderService.findGroupOrderById(id);
        int timeRemaining = groupOrderService.calculateTimeRemaining(groupOrder);
        model.addAttribute("groupOrder", groupOrderService.findGroupOrderById(id));
        model.addAttribute("items", itemService.findAllByGroupOrder(id));
        model.addAttribute("timeout", timeRemaining);
        model.addAttribute("formatted", groupOrderService.formatTime(timeRemaining));
        return "group-order :: reloadTable";
    }
}
