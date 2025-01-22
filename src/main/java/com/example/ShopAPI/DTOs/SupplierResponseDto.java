package com.example.ShopAPI.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SupplierResponseDto {
    @NotNull
    private UUID id;
    @NotBlank
    private String supplierName;
    @NotNull
    private UUID addressId;
    @NotBlank
    private String phoneNumber;
}