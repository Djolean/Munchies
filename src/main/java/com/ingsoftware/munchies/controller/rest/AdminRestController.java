package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.AdminRequestDTO;
import com.ingsoftware.munchies.controller.response.AdminResponseDTO;
import com.ingsoftware.munchies.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/admin")
@RequiredArgsConstructor
@Tag(name = "Admin API")
@SecurityRequirement(name = "basicAuth")
public class AdminRestController {

    private final AdminService adminService;

    @Operation(summary = "Get all admins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched all admin data")
    })
    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> getAllAdmins() {
        List<AdminResponseDTO> admins = adminService.findAll();
        return ResponseEntity.ok(admins);
    }

    @Operation(summary = "Get admin by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched admin data"),
            @ApiResponse(responseCode = "404", description = "Admin not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> getAdminById(@PathVariable String id) {
        return ResponseEntity.ok(adminService.findById(id));
    }

    @Operation(summary = "Create new admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added new admin"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation failed")
    })
    @PostMapping
    public ResponseEntity<Void> saveAdmin(@Valid @RequestBody AdminRequestDTO request,
                                          BindingResult result) throws MessagingException {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        adminService.addAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an existing admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated admin"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation failed"),
            @ApiResponse(responseCode = "404", description = "Admin not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable("id") String id,
                                            @Valid @RequestBody AdminRequestDTO request,
                                            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        adminService.updateAdmin(id, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete admin by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted admin"),
            @ApiResponse(responseCode = "404", description = "Admin not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") String id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }
}

