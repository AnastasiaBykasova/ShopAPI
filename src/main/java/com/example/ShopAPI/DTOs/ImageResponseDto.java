package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for response with image")
public class ImageResponseDto {
    @NotNull
    @Schema(description = "Image ID", example = "123e4567-e89b-12d3-a456-556642440000")
    private UUID imageId;
}
