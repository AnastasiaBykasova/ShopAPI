package com.example.ShopAPI.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SupplierDto {
    @NotNull
    private UUID id;
    @NotBlank
    private String supplierName;
    @NotNull
    private UUID addressId;
    @NotBlank
    private String phoneNumber;
}
