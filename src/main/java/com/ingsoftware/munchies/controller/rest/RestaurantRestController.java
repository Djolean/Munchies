package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<Page<RestaurantResponseDTO>> getAllRestaurants(
            @RequestParam(defaultValue = "1") String pageNum,
            @RequestParam(defaultValue = "5") String pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "restaurantName") String sortBy) {

        Page<RestaurantResponseDTO> restaurants = restaurantService.findAll(Integer.parseInt(pageNum), Integer.parseInt(pageSize), sortOrder, sortBy);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable String id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> saveRestaurant(@Valid @RequestBody RestaurantRequestDTO request,
                                               BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        restaurantService.addRestaurant(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable("id") String id,
                                                 @Valid @RequestBody RestaurantRequestDTO request,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        request.setRestaurantId(id);
        restaurantService.updateRestaurant(request, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") String id) {
        restaurantService.delete(id);
        return ResponseEntity.ok().build();
    }
}