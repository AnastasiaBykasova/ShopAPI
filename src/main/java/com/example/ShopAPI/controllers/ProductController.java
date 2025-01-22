package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.*;
import com.example.ShopAPI.services.ClientService;
import com.example.ShopAPI.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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

    @PatchMapping("/{id}")
    @Operation(summary = "Reduce product stock", description = "Reduces the available stock of a product by the specified quantity",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Stock reduced successfully",
                            content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input or insufficient stock", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
            })
    public ResponseEntity<ProductResponseDto> reduceProductStock(@PathVariable("id") UUID id,
                                                                 @Valid @RequestBody ProductStockUpdateRequestDto updateRequestDto) {
        ProductResponseDto updatedProduct = productService.reduceProductStock(id, updateRequestDto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ProductResponseDto> getProductById(@RequestParam UUID id) {
        ProductResponseDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(@RequestParam(name = "limit", required = false) Optional<Integer> limit,
                                                                     @RequestParam(name = "offset", required = false) Optional<Integer> offset) {
        List<ProductResponseDto> products = productService.getAllProducts(limit, offset);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
