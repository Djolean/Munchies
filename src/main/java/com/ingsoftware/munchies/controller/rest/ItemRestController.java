package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/item")
@RequiredArgsConstructor
public class ItemRestController {

    private final ItemService itemService;

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

