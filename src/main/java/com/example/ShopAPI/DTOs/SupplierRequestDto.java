package com.example.ShopAPI.DTOs;

import com.example.ShopAPI.models.Address;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SupplierRequestDto {
    @NotBlank
    private String supplierName;
    @NotNull
    private UUID addressId;
    @NotBlank
    private String phoneNumber;
}