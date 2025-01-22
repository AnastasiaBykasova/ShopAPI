package com.example.ShopAPI.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateAddressDTO {
    @NotNull
    private UUID addressId;
}
