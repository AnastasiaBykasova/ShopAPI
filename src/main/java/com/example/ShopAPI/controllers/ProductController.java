package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.*;
import com.example.ShopAPI.services.ClientService;
import com.example.ShopAPI.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor // for ClientController constructor with 1 argument
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product",
            description = "Creates a new product with the provided details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created successfully",
                            content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
            })
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto createdProduct = productService.createProduct(productRequestDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


//    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
//        ProductResponseDto createdProduct = productService.createProduct(productRequestDto);
//        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//    }
}


