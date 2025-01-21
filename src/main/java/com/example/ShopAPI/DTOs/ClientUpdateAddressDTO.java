package com.example.ShopAPI.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ClientUpdateAddressDTO {
    @NotNull
    private UUID addressId;
}
