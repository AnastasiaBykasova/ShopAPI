package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.AddressDto;
import com.example.ShopAPI.DTOs.ClientRequestDto;
import com.example.ShopAPI.DTOs.ClientResponseDto;
import com.example.ShopAPI.DTOs.ClientUpdateAddressDTO;
import com.example.ShopAPI.models.Address;
import com.example.ShopAPI.models.Client;
import com.example.ShopAPI.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
//@RequiredArgsConstructor // for ClientController constructor with 1 argument
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody ClientRequestDto clientRequestDto) {
        ClientResponseDto createdClient = clientService.createClient(clientRequestDto);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientResponseDto>> getClientsByNameAndSurname(@RequestParam String clientName, @RequestParam String clientSurname) {
        List<ClientResponseDto> clients = clientService.getClientsByNameAndSurname(clientName, clientSurname);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAllClients(@RequestParam(name = "limit", required = false) Optional<Integer> limit,
                                                                 @RequestParam(name = "offset", required = false) Optional<Integer> offset) {
        List<ClientResponseDto> clients = clientService.getAllClients(limit, offset);
        return ResponseEntity.ok(clients);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClientAddress(@PathVariable UUID id, @RequestBody ClientUpdateAddressDTO updateAddressDTO) {
        ClientResponseDto updatedClient = clientService.updateClientAddress(id, updateAddressDTO);
        return ResponseEntity.ok(updatedClient);
    }
}


