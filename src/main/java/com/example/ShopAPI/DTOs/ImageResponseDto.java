package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for response with image")
public class ImageResponseDto {
    @NotNull
    @Schema(description = "Image id", example = "123e4567-e89b-12d3-a456-556642440000")
    private UUID imageId;
}
