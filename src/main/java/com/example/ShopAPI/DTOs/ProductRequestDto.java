package com.example.ShopAPI.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductRequestDto {
    @NotBlank
    private String productName;
    @NotBlank
    private String category;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private int availableStock;
    @NotBlank
    private LocalDate lastUpdateDate;
    @NotNull
    private UUID supplierId;
    @NotNull
    private UUID imageId;
}
