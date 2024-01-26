package com.ingsoftware.munchies.controller.web;

import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item/save/{groupOrderId}")
    public String addItemToGroupOrder(@Valid @PathVariable("groupOrderId") String id, ItemRequestDTO request,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("item", request);
            return "create-group-order";
        }

        itemService.addItemToGroupOrder(id, request);
        return "redirect:/restaurants/group-order/" + id;
    }


}
