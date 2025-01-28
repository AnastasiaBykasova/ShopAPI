package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.*;
import com.example.ShopAPI.services.ProductService;
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
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "API/Endpoints for managing products")
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
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Insufficient stock", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
            })
    public ResponseEntity<ProductResponseDto> reduceProductStock(@PathVariable("id") UUID id,
                                                                 @Valid @RequestBody ProductStockUpdateRequestDto updateRequestDto) {
        if(!productService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try{
            ProductResponseDto updatedProduct = productService.reduceProductStock(id, updateRequestDto);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/search")
    @Operation(summary = "Get product by ID", description = "Retrieves the product by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product is retrieved successfully",
                            content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
            })
    public ResponseEntity<ProductResponseDto> getProductById(@RequestParam UUID id) {
        ProductResponseDto product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieves all products by pages if needed",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Products are retrieved successfully",
                            content = @Content(schema = @Schema(implementation = ProductResponseDto.class, type = "array"))),
            })
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(@RequestParam(name = "limit", required = false) Optional<Integer> limit,
                                                                     @RequestParam(name = "offset", required = false) Optional<Integer> offset) {
        List<ProductResponseDto> products = productService.getAllProducts(limit, offset);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Product is deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
            })
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
