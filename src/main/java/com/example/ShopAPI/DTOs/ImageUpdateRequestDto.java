package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for updating image data")
public class ImageUpdateRequestDto {
    @NotBlank
    @Schema(description = "New Base64 encoded image string", example = "newbase64encodedimage")
    private String image;
}
