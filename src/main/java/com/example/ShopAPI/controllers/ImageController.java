package com.example.ShopAPI.controllers;

import com.example.ShopAPI.DTOs.ImageRequestDto;
import com.example.ShopAPI.DTOs.ImageResponseDto;
import com.example.ShopAPI.DTOs.ImageUpdateRequestDto;
import com.example.ShopAPI.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@Tag(name = "Images", description = "Endpoints for managing product images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    @Operation(summary = "Add an image to a product",
            description = "Adds an image to a product by product ID",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Image added successfully",
                            content = @Content(schema = @Schema(implementation = ImageResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
            })
    public ResponseEntity<ImageResponseDto> addImageToProduct(@Valid @RequestBody ImageRequestDto imageDto) {
        ImageResponseDto responseDto = imageService.addImageToProduct(imageDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an image", description = "Updates an image by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Image is updated successfully",
                            content = @Content(schema = @Schema(implementation = ImageResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
            })
    public ResponseEntity<ImageResponseDto> updateImage(@PathVariable("id") UUID id, @Valid @RequestBody ImageUpdateRequestDto updateRequestDto) {
        ImageResponseDto responseDto = imageService.updateImage(id, updateRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an image", description = "Deletes an image by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Image is deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
            })
    public ResponseEntity<Void> deleteImage(@PathVariable UUID id) {
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get product image", description = "Retrieves the image of a product by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Image retrieved successfully",
                            content = @Content(mediaType = "application/octet-stream", schema = @Schema(type = "string", format = "binary"))),
                    @ApiResponse(responseCode = "404", description = "Product or Image not found", content = @Content)
            })
    public ResponseEntity<byte[]> getProductImage(@PathVariable UUID productId) {
        byte[] image = imageService.getProductImage(productId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(image);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product image", description = "Retrieves the image by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Image retrieved successfully",
                            content = @Content(mediaType = "application/octet-stream", schema = @Schema(type = "string", format = "binary"))),
                    @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
            })
    public ResponseEntity<byte[]> getImageById(@PathVariable UUID id) {
        try {
            byte[] image = imageService.getImageById(id);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentDispositionFormData("attachment", "image.octet-stream");
            return new ResponseEntity<>(image, httpHeaders, HttpStatus.OK);
        }
        catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage().getBytes(), HttpStatus.NOT_FOUND);
        }
    }

}
