package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.*;
import com.example.ShopAPI.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Clients", description = "API/Endpoints for managing clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(summary = "Create a new client",
            description = "Creates a new client with the provided details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Client is created successfully",
                            content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
            })
    public ResponseEntity<ClientResponseDto> createClient(@Valid @RequestBody ClientRequestDto clientRequestDto) {
        ClientResponseDto createdClient = clientService.createClient(clientRequestDto);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client", description = "Deletes a client by its id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client is created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Client is not found", content = @Content)
            })
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        if (!clientService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    @Operation(summary = "Gets some clients", description = "Gets clients by theirs name and surname",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client is found successfully",
                            content = @Content(schema = @Schema(implementation = ImageResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Client is not found", content = @Content)
            })
    public ResponseEntity<List<ClientResponseDto>> getClientsByNameAndSurname(@RequestParam String clientName, @RequestParam String clientSurname) {
        List<ClientResponseDto> clients = clientService.getClientsByNameAndSurname(clientName, clientSurname);
        if(clients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Gets all clients", description = "Gets all clients by pages if needed",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client is found successfully",
                            content = @Content(schema = @Schema(implementation = ImageResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Client is not found", content = @Content)
            })
    public ResponseEntity<List<ClientResponseDto>> getAllClients(@RequestParam(name = "limit", required = false) Optional<Integer> limit,
                                                                 @RequestParam(name = "offset", required = false) Optional<Integer> offset) {
        List<ClientResponseDto> clients = clientService.getAllClients(limit, offset);
        return ResponseEntity.ok(clients);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Updates client's address", description = "Updates client's address by its id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client is updated successfully",
                            content = @Content(schema = @Schema(implementation = ImageResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Client or address is not found", content = @Content)
            })
    public ResponseEntity<ClientResponseDto> updateClientAddress(@PathVariable UUID id, @Valid @RequestBody AddressUpdateDTO updateAddressDTO) {
        if(!clientService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ClientResponseDto updatedClient = clientService.updateClientAddress(id, updateAddressDTO);
        return ResponseEntity.ok(updatedClient);
    }
}
