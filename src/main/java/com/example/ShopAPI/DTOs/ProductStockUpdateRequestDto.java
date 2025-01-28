package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for updating product stock quantity")
public class ProductStockUpdateRequestDto {
    @NotNull
    @Min(value = 1, message = "The quantity to reduce must be at least 1")
    @Schema(description = "Quantity to reduce from available stock", example = "100")
    private int toReduce;

}
