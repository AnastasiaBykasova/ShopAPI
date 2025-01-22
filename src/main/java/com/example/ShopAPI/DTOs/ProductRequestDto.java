package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Schema(description = "DTO for product creation request")
public class ProductRequestDto {
    @NotBlank
    @Schema(description = "Product name", example = "ASUS TUF Gaming F15 FX507ZC4-HN252")
    private String productName;
    @NotBlank
    @Schema(description = "Product category", example = "Электроника")
    private String category;
    @NotBlank
    @Schema(description = "Product price", example = "9999.99")
    private BigDecimal price;
    @NotBlank
    @Schema(description = "Available stock", example = "100")
    private int availableStock;
    @NotBlank
    @Schema(description = "Last update date", example = "2025-01-01")
    private LocalDate lastUpdateDate;
    @NotNull
    @Schema(description = "Supplier ID", example = "28b728be-f657-4180-8534-5eb1060e1d3w")
    private UUID supplierId;
    @Schema(description = "Image ID", example = "28b728be-f657-4180-8534-5eb1060e1d3w", nullable = true)
    private UUID imageId;
}
