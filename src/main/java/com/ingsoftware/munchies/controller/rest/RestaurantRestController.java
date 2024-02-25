package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
@Tag(name = "Restaurant API")
@SecurityRequirement(name = "basicAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantRestController {

    private final RestaurantService restaurantService;


    @Operation(summary = "Get all restaurants with pagination and sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched all restaurant data"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation failed")
    })
    @GetMapping
    public ResponseEntity<Page<RestaurantResponseDTO>> getAllRestaurants(
            @RequestParam(defaultValue = "1") String pageNum,
            @RequestParam(defaultValue = "5") String pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "restaurantName") String sortBy) {

        Page<RestaurantResponseDTO> restaurants = restaurantService.findAll(Integer.parseInt(pageNum), Integer.parseInt(pageSize), sortOrder, sortBy);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/allRestaurants")
    public ResponseEntity<List<RestaurantResponseDTO>> getAllRestaurants() {
        List<RestaurantResponseDTO> restaurants = restaurantService.findAllSlack();
        return ResponseEntity.ok().body(restaurants);
    }

    @Operation(summary = "Get restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched restaurant data"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable String id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }

    @Operation(summary = "Create new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added new restaurant"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation failed")
    })
    @PostMapping("/add")
    public ResponseEntity<Object> saveRestaurant(@Valid @RequestBody RestaurantRequestDTO request,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        restaurantService.addRestaurant(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update an existing restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated restaurant"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation failed")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurant(@PathVariable("id") String id,
                                                 @Valid @RequestBody RestaurantRequestDTO request,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        restaurantService.updateRestaurant(request, id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted restaurant"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("id") String id) {
        restaurantService.delete(id);
        return ResponseEntity.ok().build();
    }
}