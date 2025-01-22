package com.example.ShopAPI.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for full address transporting")
public class AddressDto {
    @NotNull
    @Schema(description = "Address id", example = "123e4567-e89b-12d3-a456-556642440000")
    private UUID id;
    @NotBlank
    @Schema(description = "Country in the address", example = "Russia")
    private String country;
    @NotBlank
    @Schema(description = "City in the address", example = "Moscow")
    private String city;
    @NotBlank
    @Schema(description = "Street in the address", example = "Lenina Street")
    private String street;
}
