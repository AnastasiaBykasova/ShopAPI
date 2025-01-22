package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for adding an image to a product")
public class ImageRequestDto {
    @NotNull
    @Schema(description = "Product ID", example = "123e4567-e89b-12d3-a456-556642440000")
    private UUID productId;
    @NotNull
    @Schema(description = "Image data in byte array", example = "base64encodedimage")
    private byte[] image;
}
