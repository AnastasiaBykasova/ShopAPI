package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.ClientDto;
import com.example.ShopAPI.DTOs.ClientRequestDto;
import com.example.ShopAPI.DTOs.ClientResponseDto;
import com.example.ShopAPI.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
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
}







//@RestController
//@RequestMapping("/api/v1/clients")
//public class ClientController {
//    private final ClientService clientService;
//    public ClientController(ClientService clientService) {
//        this.clientService = clientService;
//    }
//    @Operation(summary = "Создание нового клиента")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Клиент успешно создан",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ClientDto.class))}),
//            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content)
//    })
//    @PostMapping
//    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
//        ClientDto createdClient = clientService.createClient(clientDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
//    }
//    @Operation(summary = "Получение клиента по ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Клиент найден",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ClientDto.class))}),
//            @ApiResponse(responseCode = "404", description = "Клиент не найден", content = @Content)
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<ClientDto> getClientById(@PathVariable UUID id) {
//        ClientDto clientDto = clientService.getClientById(id);
//        return ResponseEntity.ok(clientDto);
//    }
//    @Operation(summary = "Обновление данных клиента")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Клиент успешно обновлен",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ClientDto.class))}),
//            @ApiResponse(responseCode = "404", description = "Клиент не найден", content = @Content),
//            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content)
//    })
//    @PutMapping("/{id}")
//    public ResponseEntity<ClientDto> updateClient(@PathVariable UUID id, @Valid @RequestBody ClientDto clientDto) {
//        ClientDto updatedClient = clientService.updateClient(id, clientDto);
//        return ResponseEntity.ok(updatedClient);
//    }
//}