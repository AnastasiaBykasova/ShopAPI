package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.*;
import com.example.ShopAPI.services.ClientService;
import com.example.ShopAPI.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
//        clientService.deleteClient(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<ClientResponseDto>> getClientsByNameAndSurname(@RequestParam String clientName, @RequestParam String clientSurname) {
//        List<ClientResponseDto> clients = clientService.getClientsByNameAndSurname(clientName, clientSurname);
//        return new ResponseEntity<>(clients, HttpStatus.OK);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ClientResponseDto>> getAllClients(@RequestParam(name = "limit", required = false) Optional<Integer> limit,
//                                                                 @RequestParam(name = "offset", required = false) Optional<Integer> offset) {
//        List<ClientResponseDto> clients = clientService.getAllClients(limit, offset);
//        return ResponseEntity.ok(clients);
//    }
//
}


