package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.service.GroupOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/group-order")
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

    @GetMapping("/{id}")
    public ResponseEntity<GroupOrderResponseDTO> groupOrderPage(@PathVariable("id") String id) {
        return ResponseEntity.ok(groupOrderService.findGroupOrderById(id));
    }

}

