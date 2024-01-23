package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.service.GroupOrderService;
import com.ingsoftware.munchies.service.ItemService;
import com.ingsoftware.munchies.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group-orders")
@RequiredArgsConstructor
public class GroupOrderRestController {

    private final GroupOrderService groupOrderService;

    @PostMapping("/create/{restaurantId}")
    public ResponseEntity<GroupOrderResponseDTO> createGroupOrder(
            @PathVariable("restaurantId") String id,
            @Valid @RequestBody GroupOrderRequestDTO request,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        GroupOrderResponseDTO groupOrder = groupOrderService.addGroupOrder(id, request);
        return ResponseEntity.ok(groupOrder);
    }

    @GetMapping("/{groupOrderId}")
    public ResponseEntity<GroupOrderResponseDTO> groupOrderPage(@PathVariable("groupOrderId") String id) {
        GroupOrderResponseDTO groupOrder = groupOrderService.findGroupOrderById(id);
        return ResponseEntity.ok(groupOrder);
    }

}

