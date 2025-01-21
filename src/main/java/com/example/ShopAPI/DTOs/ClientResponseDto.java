package com.example.ShopAPI.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ClientResponseDto {
    @NotNull
    private UUID id;
    @NotBlank
    private String clientName;
    @NotBlank
    private String clientSurname;
    @NotNull
    private LocalDate birthday;
    @NotBlank
    private String gender;
    @NotNull
    private LocalDate registrationDate;
    @NotNull
    private UUID addressId;
}
