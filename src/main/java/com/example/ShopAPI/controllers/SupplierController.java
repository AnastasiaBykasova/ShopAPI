package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.*;
import com.example.ShopAPI.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor // for ClientController constructor with 1 argument
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponseDto> createSupplier(@RequestBody SupplierRequestDto supplierRequestDto) {
        SupplierResponseDto createdSupplier = supplierService.createSupplier(supplierRequestDto);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> updateSupplierAddress(@PathVariable UUID id, @RequestBody UpdateAddressDTO updateAddressDTO) {
        SupplierResponseDto updatedSupplier = supplierService.updateSupplierAddress(id, updateAddressDTO);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDto>> getAllSuppliers(@RequestParam(name = "limit", required = false) Optional<Integer> limit,
                                                                 @RequestParam(name = "offset", required = false) Optional<Integer> offset) {
        List<SupplierResponseDto> suppliers = supplierService.getAllSuppliers(limit, offset);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/search")
    public ResponseEntity<SupplierResponseDto> getSupplierById(@RequestParam UUID id) {
        SupplierResponseDto supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

}


