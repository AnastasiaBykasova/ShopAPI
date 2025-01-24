package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.*;
import com.example.ShopAPI.services.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/suppliers")
@Tag(name = "Suppliers", description = "API/Endpoints for managing suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping
    @Operation(summary = "Create a new supplier",
            description = "Creates a new supplier with the provided details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Supplier created successfully",
                            content = @Content(schema = @Schema(implementation = SupplierResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
            })
    public ResponseEntity<SupplierResponseDto> createSupplier(@Valid @RequestBody SupplierRequestDto supplierRequestDto) {
        SupplierResponseDto createdSupplier = supplierService.createSupplier(supplierRequestDto);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update supplier's address", description = "Updates supplier's address by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Supplier's address is updated successfully",
                            content = @Content(schema = @Schema(implementation = SupplierResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Supplier not found", content = @Content)
            })
    public ResponseEntity<SupplierResponseDto> updateSupplierAddress(@PathVariable UUID id, @Valid @RequestBody AddressUpdateDTO updateAddressDTO) {
        if(!supplierService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SupplierResponseDto updatedSupplier = supplierService.updateSupplierAddress(id, updateAddressDTO);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a supplier", description = "Deletes a supplier by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Supplier is deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Supplier not found", content = @Content)
            })
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID id) {
        if(!supplierService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "Get all suppliers", description = "Retrieves all suppliers by pages if needed",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Suppliers are retrieved successfully",
                            content = @Content(schema = @Schema(implementation = SupplierResponseDto.class, type = "array")))
            })
    public ResponseEntity<List<SupplierResponseDto>> getAllSuppliers(@RequestParam(name = "limit", required = false) Optional<Integer> limit,
                                                                 @RequestParam(name = "offset", required = false) Optional<Integer> offset) {
        List<SupplierResponseDto> suppliers = supplierService.getAllSuppliers(limit, offset);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/search")
    @Operation(summary = "Get supplier by id", description = "Retrieves the supplier by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Supplier is retrieved successfully",
                            content = @Content(schema = @Schema(implementation = SupplierResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Supplier not found", content = @Content)
            })
    public ResponseEntity<SupplierResponseDto> getSupplierById(@RequestParam UUID id) {
        SupplierResponseDto supplier = supplierService.getSupplierById(id);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(supplier);
    }

}
