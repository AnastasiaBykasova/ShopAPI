package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for image creation request")
public class ImageRequestDto {
    @NotNull
    @Schema(description = "Product id", example = "123e4567-e89b-12d3-a456-556642440000")
    private UUID productId;
    @NotNull
    @Schema(description = "Image data in byte array", example = "base64encodedimage")
    private byte[] image;
}
