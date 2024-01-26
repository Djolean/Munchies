package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.service.GroupOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/group-order")
@RequiredArgsConstructor
@Tag(name = "Group Order API")
@SecurityRequirement(name = "basicAuth")
public class GroupOrderRestController {

    private final GroupOrderService groupOrderService;

    @Operation(summary = "Create group order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created group order"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation failed")
    })
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

    @Operation(summary = "Get group order details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched group order data"),
            @ApiResponse(responseCode = "404", description = "Group order not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GroupOrderResponseDTO> groupOrderPage(@PathVariable("id") String id) {
        return ResponseEntity.ok(groupOrderService.findGroupOrderById(id));
    }

}

