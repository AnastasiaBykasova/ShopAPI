package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for address updating")
public class AddressUpdateDTO {
    @NotNull
    @Schema(description = "Address id", example = "123e4567-e89b-12d3-a456-556642440000")
    private UUID addressId;
}
