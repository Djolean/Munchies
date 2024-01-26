package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.service.ItemService;
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
@RequestMapping("/rest/item")
@RequiredArgsConstructor
@Tag(name = "Item API")
@SecurityRequirement(name = "basicAuth")
public class ItemRestController {

    private final ItemService itemService;
    @Operation(summary = "Add item to group order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added item to group order"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation failed")
    })
    @PostMapping("/save/{groupOrderId}")
    public ResponseEntity<Void> addItemToGroupOrder(@Valid @PathVariable("groupOrderId") String id,
                                                    @RequestBody ItemRequestDTO request,
                                                    BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        itemService.addItemToGroupOrder(id, request);
        return ResponseEntity.ok().build();
    }
}

